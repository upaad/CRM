package com.hjf.annotation;

import com.hjf.annotation.support.DataVal;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataVal.class)
public @interface DataValidator {
    String message() default "数据格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
