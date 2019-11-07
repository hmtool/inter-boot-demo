package tech.mhuang.interchan.logger.consumer;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.mhuang.core.util.ObjectUtil;
import tech.mhuang.ext.elasticsearch.admin.ESFramework;
import tech.mhuang.ext.elasticsearch.admin.factory.IESFactory;
import tech.mhuang.ext.interchan.rediskafkamiddle.annaotion.RedisKafka;
import tech.mhuang.ext.kafka.producer.bean.KafkaMsg;
import tech.mhuang.interchan.logger.entity.ESAsyncLogger;
import tech.mhuang.interchan.logger.entity.ESAsyncLoggerOpType;

import java.io.IOException;

/**
 *
 * 日志消费
 *
 * @author mhuang
 * @since 1.0.0
 */
@Component("loggerConsumer")
public class LoggerConsumer {

    @Autowired
    private ESFramework esFramework;

    /**
     * redis注解是不允许重复执行、此处是aop进行拦截
     * @param kafkaMsg 接收的消息
     * @throws IOException
     */
    @RedisKafka
    public void invoke(KafkaMsg kafkaMsg) throws IOException {
        ESAsyncLogger logger = JSON.parseObject(kafkaMsg.getMsg().toString(),ESAsyncLogger.class);
        if (ObjectUtil.isNotEmpty(logger)) {
            IESFactory esFactory = esFramework.getFactory(logger.getName());
            if (logger.getOpType().equals(ESAsyncLoggerOpType.INSERT)) {
                esFactory.insert(logger.getEsOperatorLogger(), logger.getIndex(), logger.getType());
            } else {
                esFactory.update(logger.getEsOperatorLogger(), logger.getIndex(), logger.getType(),
                        logger.getEsOperatorLogger().getId());
            }
        }
    }
}
