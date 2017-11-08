package com.telosys.gwtp.base.client.application.content.book.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter.BookListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.book.BookDto;

public class BookListViewImpl extends AbstractListView<BookListPresenter, BookDto> implements BookListView {
	interface Binder extends UiBinder<Widget, BookListViewImpl> {
	}

	@Inject
	BookListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<BookDto> col1 = new TextColumn<BookDto>() {

			@Override
			public String getValue(final BookDto object) {
				return String.valueOf(object.getId());
			}
		};
		table.addColumn(col1, "Id");
		final TextColumn<BookDto> col2 = new TextColumn<BookDto>() {

			@Override
			public String getValue(final BookDto object) {
				return String.valueOf(object.getPrice());
			}
		};
		table.addColumn(col2, "Price");
		final TextColumn<BookDto> col3 = new TextColumn<BookDto>() {

			@Override
			public String getValue(final BookDto object) {
				return String.valueOf(object.getIsbn());
			}
		};
		table.addColumn(col3, "Isbn");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}