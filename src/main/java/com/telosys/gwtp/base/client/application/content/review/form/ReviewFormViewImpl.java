package com.telosys.gwtp.base.client.application.content.review.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter.ReviewFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.ReviewDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class ReviewFormViewImpl extends AbstractFormView<ReviewFormPresenter, ReviewDto> implements ReviewFormView {
	interface Binder extends UiBinder<Widget, ReviewFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<ReviewDto, ReviewFormViewImpl> {
	}

	@UiField
	protected TextBox customerCode;
	@UiField
	protected IntegerBox bookId;
	@UiField
	protected TextBox reviewText;
	@UiField
	protected IntegerBox reviewNote;
	@UiField
	protected DatePicker creation;
	@UiField
	protected DatePicker lastUpdate;

	@Inject
	ReviewFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		initDatePicker(creation);
		initDatePicker(lastUpdate);
		this.driver.initialize(this);
	}
}