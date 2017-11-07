package com.telosys.gwtp.base.client.application.content.shop.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter.ShopFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.ShopDto;

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
	@UiField
	protected TextBox countryCode;
	@UiField
	protected TextBox phone;
	@UiField
	protected TextBox email;
	@UiField
	protected TextBox executive;

	@Inject
	ShopFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}