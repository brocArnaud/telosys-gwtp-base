package com.telosys.gwtp.base.client.util.validation;

import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.telosys.gwtp.base.shared.dto.AuthorDto;
import com.telosys.gwtp.base.shared.dto.BadgeDto;
import com.telosys.gwtp.base.shared.dto.BookDto;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;
import com.telosys.gwtp.base.shared.dto.CountryDto;
import com.telosys.gwtp.base.shared.dto.CustomerDto;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;
import com.telosys.gwtp.base.shared.dto.PublisherDto;
import com.telosys.gwtp.base.shared.dto.ReviewDto;
import com.telosys.gwtp.base.shared.dto.ShopDto;
import com.telosys.gwtp.base.shared.dto.SynopsisDto;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;
import com.telosys.gwtp.base.shared.dto.team.TeamDto;

import de.knightsoftnet.validators.client.AbstractGwtValidatorFactory;
import de.knightsoftnet.validators.client.GwtValidation;
import de.knightsoftnet.validators.client.impl.AbstractGwtValidator;

public class FormValidationFactory extends AbstractGwtValidatorFactory {

	/**
	 * The Interface GwtValidator.
	 */
	@GwtValidation(value = { TeamDto.class, PlayerDto.class, AuthorDto.class, BadgeDto.class, //
			BookDto.class, BookOrderDto.class, BookOrderItemDto.class, CountryDto.class, CustomerDto.class, //
			EmployeeDto.class, EmployeeGroupDto.class, PublisherDto.class, ReviewDto.class, //
			ShopDto.class, SynopsisDto.class, WorkgroupDto.class })
	public interface GwtValidator extends Validator {
	}

	/** {@inheritDoc} */
	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}

}