package com.telosys.gwtp.base.shared.dto.player.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = PlayerValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPlayer {
	String message() default "Invalid player (due to edge case)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
