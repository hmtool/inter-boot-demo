package tech.mhuang.interchan.logger;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tech.mhuang.core.date.DateTimeUtil;
import tech.mhuang.core.id.BaseIdeable;
import tech.mhuang.core.util.ObjectUtil;
import tech.mhuang.core.util.StringUtil;
import tech.mhuang.ext.kafka.admin.KafkaFramework;
import tech.mhuang.interchan.logger.entity.ESAsyncLogger;
import tech.mhuang.interchan.logger.entity.ESAsyncLoggerOpType;
import tech.mhuang.interchan.logger.entity.ESOperatorLogger;
import tech.mhuang.interchan.logger.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 日志拦截
 *
 * @author mhuang
 * @since 1.0.0
 */
@Component
@Aspect
@Order(0)
@Slf4j
public class WebLoggerAspect {

    @Autowired
    private KafkaFramework kafkaFramework;

    /**
     * 可自行调整结构
     */
    @Pointcut("execution(public * tech.mhuang..*Controller.*(..))||@annotation(tech.mhuang.interchan.logger.annoation.ESLogger)")
    private void webLog() {
    }

    private ThreadLocal<ESOperatorLogger> tLocal = ThreadLocal.withInitial(() -> null);

    @Value("${spring.application.name}")
    private String application;

    @Autowired
    private LoggerProperties properties;
    @Autowired
    private BaseIdeable ideable;

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ESOperatorLogger eslogger = null;
        //接收到请求，记录请求内容,先判断是不是web的spring应用，若是其他请自行修改
        if (ObjectUtil.isNotEmpty(Class.forName("org.springframework.web.context.request.RequestContextHolder"))) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (ObjectUtil.isNotEmpty(attributes)) {
                HttpServletRequest request = attributes.getRequest();
                eslogger = LoggerUtil.getEsLogger(request, joinPoint);
            }
        }

        if (ObjectUtil.isEmpty(eslogger)) {
            eslogger = LoggerUtil.getEsLogger(joinPoint);
        }
        tLocal.set(eslogger);
    }

    /**
     * 设置异常数据
     *
     * @param esLogger 异常的原值
     * @param ex       异常值
     * @return void
     */
    private void setExceptionData(ESOperatorLogger esLogger, Throwable ex) {
        if (esLogger == null) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        esLogger.setEndDate(now.toString() + "+08:00");
        esLogger.setEndDateUnit(DateTimeUtil.localDateTimeToDate(now));
        esLogger.setStatus(2);

        esLogger.setEMsg(ex.toString());
        StackTraceElement[] stes = ex.getStackTrace();
        StringBuilder errDetailMsg = new StringBuilder();
        if (stes != null && stes.length > 0) {
            errDetailMsg.append(esLogger.getEMsg());
            for (StackTraceElement ste : stes) {
                errDetailMsg.append(ste.toString());
                errDetailMsg.append(System.getProperty("line.separator"));
            }
        }
        esLogger.setEDetailMsg(errDetailMsg.toString());

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        ESOperatorLogger esLogger = tLocal.get();
        LocalDateTime now = LocalDateTime.now();
        esLogger.setEndDate(now.toString() + "+08:00");
        esLogger.setEndDateUnit(DateTimeUtil.localDateTimeToDate(now));
        esLogger.setStatus(1);
        esLogger.setRestData(JSON.toJSONString(ret));
        aspectNotify(esLogger);
        tLocal.remove();
    }

    @AfterThrowing(value = "webLog()", throwing = "ex")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        ESOperatorLogger esLogger = tLocal.get();
        this.setExceptionData(esLogger, ex);
        aspectNotify(esLogger);
        tLocal.remove();
    }

    /**
     * aop通知异步执行
     *
     * @param esOperatorLogger 执行的对象
     */
    private void aspectNotify(ESOperatorLogger esOperatorLogger) {
        ESAsyncLogger esAsyncLogger = new ESAsyncLogger();
        esAsyncLogger.setEsOperatorLogger(esOperatorLogger);
        esAsyncLogger.setName(properties.getEsKey());
        esAsyncLogger.setIndex(application);
        if (StringUtil.isEmpty(esOperatorLogger.getId())) {
            esAsyncLogger.setOpType(ESAsyncLoggerOpType.INSERT);
        } else {
            esAsyncLogger.setOpType(ESAsyncLoggerOpType.MOD);
        }
        //kafka方式
        kafkaFramework.getSuccessProducerMap().get(properties.getKafkaKey()).send(properties.getKafkaTopic(), ideable.generateId(), JSON.toJSONString(esAsyncLogger));
    }
}
