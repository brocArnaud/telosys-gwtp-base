package com.telosys.gwtp.base.client.application.content.customer.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter.CustomerListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.CustomerDto;

public class CustomerListViewImpl extends AbstractListView<CustomerListPresenter, CustomerDto> implements CustomerListView {
	interface Binder extends UiBinder<Widget, CustomerListViewImpl> {
	}

	@Inject
	CustomerListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<CustomerDto> col1 = new TextColumn<CustomerDto>() {

			@Override
			public String getValue(final CustomerDto object) {
				return String.valueOf(object.getCode());
			}
		};
		table.addColumn(col1, "Code");
		final TextColumn<CustomerDto> col2 = new TextColumn<CustomerDto>() {

			@Override
			public String getValue(final CustomerDto object) {
				return String.valueOf(object.getFirstName());
			}
		};
		table.addColumn(col2, "Firstname");
		final TextColumn<CustomerDto> col3 = new TextColumn<CustomerDto>() {

			@Override
			public String getValue(final CustomerDto object) {
				return String.valueOf(object.getLastName());
			}
		};
		table.addColumn(col3, "Lastname");

		final TextColumn<CustomerDto> col4 = new TextColumn<CustomerDto>() {

			@Override
			public String getValue(final CustomerDto object) {
				return String.valueOf(object.getLogin());
			}
		};
		table.addColumn(col4, "Login");
		final TextColumn<CustomerDto> col5 = new TextColumn<CustomerDto>() {

			@Override
			public String getValue(final CustomerDto object) {
				return String.valueOf(object.getPhone());
			}
		};
		table.addColumn(col5, "Phone");
		final TextColumn<CustomerDto> col6 = new TextColumn<CustomerDto>() {

			@Override
			public String getValue(final CustomerDto object) {
				return String.valueOf(object.getCity());
			}
		};
		table.addColumn(col6, "City");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}