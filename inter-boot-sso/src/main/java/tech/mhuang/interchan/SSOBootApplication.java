package tech.mhuang.interchan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.mhuang.ext.interchan.core.application.BaseApplication;

/**
 * Demo启动
 *
 * @author mhuang
 * @since 1.0.0
 */
@SpringBootApplication
public class SSOBootApplication extends BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOBootApplication.class, args);
    }
}