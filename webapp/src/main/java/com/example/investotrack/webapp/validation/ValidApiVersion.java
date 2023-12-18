package com.example.investotrack.webapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ApiVersionValidator.class)
public @interface ValidApiVersion {
    String message() default "{com.example.investotrack.webapp.validation.ValidApiVersion.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
