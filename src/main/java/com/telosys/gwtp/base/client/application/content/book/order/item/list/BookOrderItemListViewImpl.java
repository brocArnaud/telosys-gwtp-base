package com.telosys.gwtp.base.client.application.content.book.order.item.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter.BookOrderItemListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;

public class BookOrderItemListViewImpl extends AbstractListView<BookOrderItemListPresenter, BookOrderItemDto> implements BookOrderItemListView {
	interface Binder extends UiBinder<Widget, BookOrderItemListViewImpl> {
	}

	@Inject
	BookOrderItemListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<BookOrderItemDto> col1 = new TextColumn<BookOrderItemDto>() {

			@Override
			public String getValue(final BookOrderItemDto object) {
				return String.valueOf(object.getBookOrderId());
			}
		};
		table.addColumn(col1, "Book Order Id");
		final TextColumn<BookOrderItemDto> col2 = new TextColumn<BookOrderItemDto>() {

			@Override
			public String getValue(final BookOrderItemDto object) {
				return String.valueOf(object.getBookId());
			}
		};
		table.addColumn(col2, "Book Id");
		final TextColumn<BookOrderItemDto> col3 = new TextColumn<BookOrderItemDto>() {

			@Override
			public String getValue(final BookOrderItemDto object) {
				return String.valueOf(object.getPrice());
			}
		};
		table.addColumn(col3, "Price");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}