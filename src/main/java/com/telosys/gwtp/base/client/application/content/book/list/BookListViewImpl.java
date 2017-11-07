package com.telosys.gwtp.base.client.application.content.book.list;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter.BookListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.BookDto;

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
		final Column<BookDto, String> deletion = new Column<BookDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(BookDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<BookDto, String> update = new Column<BookDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(BookDto object) {
				return "";
			}
		};
		update.setFieldUpdater((index, author, value) -> presenter.onUpdateClick(author));
		update.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(update, "Update");
		table.setColumnWidth(deletion, 30, Unit.PX);
		table.setColumnWidth(update, 30, Unit.PX);
		provider.addDataDisplay(table);
	}
}