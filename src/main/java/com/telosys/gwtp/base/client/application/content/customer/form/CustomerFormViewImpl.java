package com.telosys.gwtp.base.client.application.content.customer.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter.CustomerFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.CustomerDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class CustomerFormViewImpl extends AbstractFormView<CustomerFormPresenter, CustomerDto> implements CustomerFormView {
	interface Binder extends UiBinder<Widget, CustomerFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<CustomerDto, CustomerFormViewImpl> {
	}

	@UiField
	protected TextBox code;
	@UiField
	protected TextBox countryCode;
	@UiField
	protected TextBox firstName;
	@UiField
	protected TextBox lastName;
	@UiField
	protected TextBox login;
	@UiField
	protected TextBox password;
	@UiField
	protected IntegerBox age;
	@UiField
	protected TextBox city;
	@UiField
	protected IntegerBox zipCode;
	@UiField
	protected TextBox phone;
	@UiField
	protected IntegerBox reviewer;

	@Inject
	CustomerFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}