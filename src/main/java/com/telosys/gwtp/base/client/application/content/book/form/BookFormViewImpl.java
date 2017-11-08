package com.telosys.gwtp.base.client.application.content.book.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.DoubleBox;
import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter.BookFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.book.BookDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class BookFormViewImpl extends AbstractFormView<BookFormPresenter, BookDto> implements BookFormView {
	interface Binder extends UiBinder<Widget, BookFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<BookDto, BookFormViewImpl> {
	}

	@UiField
	protected IntegerBox id;

	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> authorId;

	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> publisherId;

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
		authorId =  initListItemBox();
		publisherId = initListItemBox();
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}

	@Override
	protected BookDto beforeValidation(BookDto data) {
		final String authorValue = authorId.getValue().getValue();
		final String publisherValue = publisherId.getValue().getValue();
		data.setAuthorId(Integer.valueOf(authorValue));
		data.setPublisherId(Integer.valueOf(publisherValue));
		return data;
	}

	@Override
	public void loadPublisher(List<ListItemDto> items) {
		publisherId.setValue(items.get(0));
		publisherId.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}

	@Override
	public void loadAuthor(List<ListItemDto> items) {
		authorId.setValue(items.get(0));
		authorId.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}

}