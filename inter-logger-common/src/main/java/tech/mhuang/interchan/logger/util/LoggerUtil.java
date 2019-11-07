package tech.mhuang.interchan.logger.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import tech.mhuang.core.date.DateTimeUtil;
import tech.mhuang.core.util.StringUtil;
import tech.mhuang.ext.interchan.core.constans.Global;
import tech.mhuang.ext.spring.util.IpUtil;
import tech.mhuang.interchan.logger.annoation.ESLogger;
import tech.mhuang.interchan.logger.consts.LoggerConsts;
import tech.mhuang.interchan.logger.entity.ESOperatorLogger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * 日志工具类
 *
 * @author mhuang
 * @since 1.0.0
 */
@Slf4j
public class LoggerUtil {

    /**
     * 组装EsLogger数据
     * @param request request
     * @return EsOperatorLogger EsOperatorLogger
     */
    public static ESOperatorLogger getEsLogger(HttpServletRequest request, JoinPoint jPoint) {
        ESOperatorLogger esLogger = new ESOperatorLogger();

        LocalDateTime now = LocalDateTime.now();
        //基础配置
        try {
            esLogger.setIp(IpUtil.getIp(request));
        } catch (UnknownHostException e) {
            log.error("日志:{}获取不到IP地址异常", request.getMethod(), e);
        }
        esLogger.setType(request.getMethod());
        esLogger.setUrl(request.getRequestURL().toString());
        esLogger.setStartDate(now.toString() + "+08:00");
        esLogger.setStartDateUnit(DateTimeUtil.localDateTimeToDate(now));
        //auth
        packAuthData(request, esLogger);

        Object[] args = jPoint.getArgs();
        //组装传输对象数组
        if (args != null && args.length > 0) {
            String data = JSON.toJSONString(args);
            data = data.replaceAll("\\\\", "");
            esLogger.setSendData(data);
        }
        esLogger.setQueryData(request.getQueryString());
        packMethod(jPoint, esLogger);
        return esLogger;
    }

    public static ESOperatorLogger getEsLogger(JoinPoint jPoint) {
        ESOperatorLogger esLogger = new ESOperatorLogger();
        LocalDateTime now = LocalDateTime.now();
        //基础配置
        esLogger.setStartDate(now.toString() + "+08:00");
        esLogger.setStartDateUnit(DateTimeUtil.localDateTimeToDate(now));
        //auth
        Object[] args = jPoint.getArgs();
        //组装传输对象数组
        if (args != null && args.length > 0) {
            String data;
            try {
                data = JSON.toJSONString(args);
                data = data.replaceAll("\\\\", "");
                esLogger.setSendData(data);
            } catch (Exception e) {
            }
        }
        packMethod(jPoint, esLogger);
        return esLogger;
    }

    private static void packMethod(JoinPoint joinPoint, ESOperatorLogger esOperatorLogger) {
        String clazz = joinPoint.getTarget().getClass().getName();
        esOperatorLogger.setClazz(clazz);

        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        esOperatorLogger.setMethod(method.getName());

        //注解
        try {
            String remark = getMethodByRemark(method);
            esOperatorLogger.setRemark(remark);
        } catch (Exception e) {
        }
    }

    public static void packAuthData(HttpServletRequest request, ESOperatorLogger esLogger) {
        if (LoggerConsts.getJwtHeader()) {
            esLogger.setHeaderData(
                    request.getHeader(LoggerConsts.getJwtHeaderName()).replaceAll("\\\\", "")
            );
            if (StringUtil.isNotBlank(esLogger.getHeaderData())) {
                JSONObject obj = JSON.parseObject(esLogger.getHeaderData());
                esLogger.setUserId(obj.getString(Global.USER_ID));
            }
        }
    }

    public static String getMethodByRemark(MethodSignature methodSignature) throws Exception {
        Method method0 = methodSignature.getMethod();
        ESLogger esLogger = method0.getAnnotation(ESLogger.class);
        if (esLogger != null) {
            return esLogger.remark();
        }
        return null;
    }
}