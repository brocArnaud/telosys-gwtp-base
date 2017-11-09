package com.telosys.gwtp.base.client.application.content.book.order.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.ValueListBox;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter.BookOrderFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class BookOrderFormViewImpl extends AbstractFormView<BookOrderFormPresenter, BookOrderDto> implements BookOrderFormView {
	interface Binder extends UiBinder<Widget, BookOrderFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<BookOrderDto, BookOrderFormViewImpl> {
	}

	@UiField
	protected IntegerBox id;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> shopCode;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> customerCode;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> employeeCode;
	@UiField
	protected DatePicker date;
	@UiField
	protected IntegerBox state;

	@Inject
	BookOrderFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		shopCode = initListItemBox();
		customerCode = initListItemBox();
		employeeCode = initListItemBox();
		initWidget(uiBinder.createAndBindUi(this));
		initDatePicker(date);
		this.driver.initialize(this);
	}

	@Override
	protected BookOrderDto beforeValidation(BookOrderDto data) {
		data.setShopCode(shopCode.getValue().getValue());
		data.setCustomerCode(customerCode.getValue().getValue());
		data.setEmployeeCode(employeeCode.getValue().getValue());
		return data;
	}

	@Override
	public void loadShop(List<ListItemDto> items) {
		shopCode.setValue(items.get(0));
		shopCode.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}

	@Override
	public void loadCustomer(List<ListItemDto> items) {
		customerCode.setValue(items.get(0));
		customerCode.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}

	@Override
	public void loadEmployee(List<ListItemDto> items) {
		employeeCode.setValue(items.get(0));
		employeeCode.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}
}