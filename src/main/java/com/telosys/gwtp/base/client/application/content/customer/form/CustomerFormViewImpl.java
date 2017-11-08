package com.telosys.gwtp.base.client.application.content.customer.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter.CustomerFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;
import com.telosys.gwtp.base.shared.dto.CustomerDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class CustomerFormViewImpl extends AbstractFormView<CustomerFormPresenter, CustomerDto> implements CustomerFormView {
	interface Binder extends UiBinder<Widget, CustomerFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<CustomerDto, CustomerFormViewImpl> {
	}

	@UiField
	protected TextBox code;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> countryCode;
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
		countryCode = initListItemBox();
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}

	@Override
	protected CustomerDto beforeValidation(CustomerDto data) {
		data.setCountryCode(countryCode.getValue().getValue());
		return data;
	}

	@Override
	public void loadCountry(List<ListItemDto> items) {
		countryCode.setValue(items.get(0));
		countryCode.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}
}