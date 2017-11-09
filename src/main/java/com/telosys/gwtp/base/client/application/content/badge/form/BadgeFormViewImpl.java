package com.telosys.gwtp.base.client.application.content.badge.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter.BadgeFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.BadgeDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class BadgeFormViewImpl extends AbstractFormView<BadgeFormPresenter, BadgeDto> implements BadgeFormView {
	interface Binder extends UiBinder<Widget, BadgeFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<BadgeDto, BadgeFormViewImpl> {
	}

	@UiField
	protected IntegerBox badgeNumber;
	@UiField
	protected IntegerBox authorizationLevel;
	@UiField
	protected DatePicker endOfValidity;

	@Inject
	BadgeFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		initDatePicker(endOfValidity);
		this.driver.initialize(this);
	}

	@Override
	public String getBadgeNumberValue() {
		return badgeNumber.getText();
	}

}