package com.telosys.gwtp.base.shared.dto.book.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = BookValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBook {
	String message() default "Invalid book (due to edge case)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
