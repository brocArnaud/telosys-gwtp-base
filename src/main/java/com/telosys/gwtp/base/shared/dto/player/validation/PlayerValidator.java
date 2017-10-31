package com.telosys.gwtp.base.shared.dto.player.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.telosys.gwtp.base.shared.dto.player.PlayerDto;

public class PlayerValidator implements ConstraintValidator<ValidPlayer, PlayerDto> {

	@Override
	public void initialize(ValidPlayer constraintAnnotation) {
	}

	@Override
	public boolean isValid(PlayerDto value, ConstraintValidatorContext context) {
		if (value.getName() != null && value.getName().equals("AAA")) {
			context.buildConstraintViolationWithTemplate("The value can't be \"AAA\"").addPropertyNode("name").addConstraintViolation();
			return false;
		}
		return true;
	}

}
