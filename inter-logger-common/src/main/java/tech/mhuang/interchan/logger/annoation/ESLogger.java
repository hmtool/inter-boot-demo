package tech.mhuang.interchan.logger.annoation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 注解记录日志
 *
 * @author mhuang
 * @since 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface ESLogger {

    /**
     * 备注
     *
     * @return 默认空
     */
    String remark() default "";
}