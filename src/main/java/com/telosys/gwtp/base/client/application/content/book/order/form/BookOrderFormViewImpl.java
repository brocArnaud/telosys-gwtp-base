package com.telosys.gwtp.base.client.application.content.book.order.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter.BookOrderFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class BookOrderFormViewImpl extends AbstractFormView<BookOrderFormPresenter, BookOrderDto> implements BookOrderFormView {
	interface Binder extends UiBinder<Widget, BookOrderFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<BookOrderDto, BookOrderFormViewImpl> {
	}

	@UiField
	protected IntegerBox id;
	@UiField
	protected TextBox shopCode;
	@UiField
	protected TextBox customerCode;
	@UiField
	protected TextBox employeeCode;
	@UiField
	protected DatePicker date;
	@UiField
	protected IntegerBox state;

	@Inject
	BookOrderFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}