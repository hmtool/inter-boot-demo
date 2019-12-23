package tech.mhuang.interchan.logger;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import tech.mhuang.core.check.CheckAssert;

/**
 * 日志
 *
 * @author mhuang
 * @since 1.0.0
 */
@Configuration
@ConditionalOnProperty(prefix = "mhuang.interchan.logger", name = "enable", havingValue = "true",matchIfMissing = true)
@EnableConfigurationProperties(value = {LoggerProperties.class})
public class LoggerAutoConfiguration {

    private LoggerProperties properties;

    public LoggerAutoConfiguration(LoggerProperties properties) {
        CheckAssert.check(properties, "not found auth properties");
        this.properties = properties;
    }
}
