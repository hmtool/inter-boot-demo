package tech.mhuang.interchan.logger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * 日志需要的属性
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "mhuang.interchan.logger")
public class LoggerProperties {

    /**
     * 默认开启配置
     */
    private boolean enable = true;

    /**
     * 配置日志的es
     */
    private String esKey;

    /**
     * 配置kafka生产的key
     */
    private String kafkaKey;

    /**
     * 配置kafka生产日志的主题
     */
    private String kafkaTopic;
}
