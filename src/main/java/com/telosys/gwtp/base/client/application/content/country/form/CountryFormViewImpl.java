package com.telosys.gwtp.base.client.application.content.country.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter.CountryFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.CountryDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class CountryFormViewImpl extends AbstractFormView<CountryFormPresenter, CountryDto> implements CountryFormView {
	interface Binder extends UiBinder<Widget, CountryFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<CountryDto, CountryFormViewImpl> {
	}

	@UiField
	protected TextBox code;
	@UiField
	protected TextBox name;

	@Inject
	CountryFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}