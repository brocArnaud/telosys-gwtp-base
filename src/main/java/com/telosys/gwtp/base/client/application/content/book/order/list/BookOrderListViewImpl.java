package com.telosys.gwtp.base.client.application.content.book.order.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter.BookOrderListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;

public class BookOrderListViewImpl extends AbstractListView<BookOrderListPresenter, BookOrderDto> implements BookOrderListView {
	interface Binder extends UiBinder<Widget, BookOrderListViewImpl> {
	}

	@Inject
	BookOrderListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<BookOrderDto> col1 = new TextColumn<BookOrderDto>() {

			@Override
			public String getValue(final BookOrderDto object) {
				return String.valueOf(object.getId());
			}
		};
		table.addColumn(col1, "Id");
		final TextColumn<BookOrderDto> col2 = new TextColumn<BookOrderDto>() {

			@Override
			public String getValue(final BookOrderDto object) {
				return String.valueOf(object.getCustomerCode());
			}
		};
		table.addColumn(col2, "Customer Code");
		final TextColumn<BookOrderDto> col3 = new TextColumn<BookOrderDto>() {

			@Override
			public String getValue(final BookOrderDto object) {
				return object != null ? formatDate(object.getDate()) : "";
			}
		};
		table.addColumn(col3, "Date");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}