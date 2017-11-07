package com.telosys.gwtp.base.client.application.content.publisher.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter.PublisherFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.PublisherDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class PublisherFormViewImpl extends AbstractFormView<PublisherFormPresenter, PublisherDto> implements PublisherFormView {
	interface Binder extends UiBinder<Widget, PublisherFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<PublisherDto, PublisherFormViewImpl> {
	}

	@UiField
	protected IntegerBox code;
	@UiField
	protected TextBox countryCode;
	@UiField
	protected TextBox name;
	@UiField
	protected TextBox email;
	@UiField
	protected TextBox contact;
	@UiField
	protected TextBox city;
	@UiField
	protected IntegerBox zipCode;
	@UiField
	protected TextBox phone;

	@Inject
	PublisherFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}