package com.softarex.QuestionsPortal.validation.annotation;

import com.softarex.QuestionsPortal.validation.ExistEmailValidator;
import com.softarex.QuestionsPortal.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ExistEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistAndActive {
    String message() default "User with such Email does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}