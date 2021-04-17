package pl.mg.javatst.cert.ocp.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(BusinessPolicies.class)
public @interface BusinessPolicy {
    String name() default "default policy";

    String[] countries();

    String value();
}
