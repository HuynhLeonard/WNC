package com.wnc.hw3.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpecialFeatureValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSpecialFeatures {
    String message() default "FILM_RATING_INVALID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
