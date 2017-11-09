package com.telosys.gwtp.base.client.application.content.shop.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter.ShopFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.ShopDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class ShopFormViewImpl extends AbstractFormView<ShopFormPresenter, ShopDto> implements ShopFormView {
	interface Binder extends UiBinder<Widget, ShopFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<ShopDto, ShopFormViewImpl> {
	}

	@UiField
	protected TextBox code;
	@UiField
	protected TextBox name;
	@UiField
	protected TextBox address1;
	@UiField
	protected TextBox address2;
	@UiField
	protected IntegerBox zipCode;
	@UiField
	protected TextBox city;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> countryCode;
	@UiField
	protected TextBox phone;
	@UiField
	protected TextBox email;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> executive;

	@Inject
	ShopFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		countryCode = initListItemBox();
		executive = initListItemBox();
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
	
	@Override
	protected ShopDto beforeValidation(ShopDto data) {
		data.setCountryCode(countryCode.getValue().getValue());
		return data;
	}

	@Override
	public void loadCountry(List<ListItemDto> items) {
		countryCode.setValue(items.get(0));
		countryCode.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}
	
	@Override
	public void loadEmployee(List<ListItemDto> items) {
		executive.setValue(items.get(0));
		executive.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}
}