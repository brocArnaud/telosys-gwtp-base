package com.telosys.gwtp.base.client.util;

import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import com.telosys.gwtp.base.shared.dto.PlayerDto;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class FormValidationFactory extends AbstractGwtValidatorFactory {

	/**
	 * The Interface GwtValidator.
	 */
	@GwtValidation(value = { TeamDto.class, PlayerDto.class })
	public interface GwtValidator extends Validator {
	}

	/** {@inheritDoc} */
	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}

}
