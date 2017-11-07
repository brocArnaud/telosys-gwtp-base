package com.telosys.gwtp.base.client.application.content.book.order.item.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.DoubleBox;
import org.gwtbootstrap3.client.ui.IntegerBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter.BookOrderItemFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class BookOrderItemFormViewImpl extends AbstractFormView<BookOrderItemFormPresenter, BookOrderItemDto> implements BookOrderItemFormView {
	interface Binder extends UiBinder<Widget, BookOrderItemFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<BookOrderItemDto, BookOrderItemFormViewImpl> {
	}

	@UiField
	protected IntegerBox bookOrderId;
	@UiField
	protected IntegerBox bookId;
	@UiField
	protected IntegerBox quantity;
	@UiField
	protected DoubleBox price;

	@Inject
	BookOrderItemFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}