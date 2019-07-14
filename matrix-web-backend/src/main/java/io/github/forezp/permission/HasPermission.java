package io.github.forezp.permission;

import java.lang.annotation.*;

/**
 * Created by forezp on 2018/8/5.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HasPermission {

    String value() default "";

    String hasRole() default "";

    String hasPermission() default "";

}
