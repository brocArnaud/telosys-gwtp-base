package com.telosys.gwtp.base.client.application.content.book.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.DoubleBox;
import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter.BookFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.BookDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class BookFormViewImpl extends AbstractFormView<BookFormPresenter, BookDto> implements BookFormView {
	interface Binder extends UiBinder<Widget, BookFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<BookDto, BookFormViewImpl> {
	}

	@UiField
	protected IntegerBox id;

	@UiField
	protected IntegerBox publisherId;

	@UiField
	protected IntegerBox authorId;

	@UiField
	protected TextBox isbn;

	@UiField
	protected TextBox title;

	@UiField
	protected DoubleBox price;

	@UiField
	protected IntegerBox quantity;

	@UiField
	protected IntegerBox discount;

	@UiField
	protected IntegerBox availability;

	@UiField
	protected IntegerBox bestSeller;

	@Inject
	BookFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}