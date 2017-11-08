package com.telosys.gwtp.base.shared.dto.book.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.telosys.gwtp.base.shared.dto.book.BookDto;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;
import com.telosys.gwtp.base.shared.dto.player.validation.ValidPlayer;

public class BookValidator implements ConstraintValidator<ValidBook, BookDto> {

	@Override
	public void initialize(ValidBook constraintAnnotation) {
		// nothing to do on this init
	}

	@Override
	public boolean isValid(BookDto value, ConstraintValidatorContext context) {
		boolean state = true;
		if (value.getAuthorId() == null) {
			context.buildConstraintViolationWithTemplate("Author is required").addPropertyNode("authorId").addConstraintViolation();
			state = false;
		}
		if (value.getPublisherId() == null) {
			context.buildConstraintViolationWithTemplate("Publisher is required").addPropertyNode("publisherId").addConstraintViolation();
			state = false;
		}
		return state;
	}

}
