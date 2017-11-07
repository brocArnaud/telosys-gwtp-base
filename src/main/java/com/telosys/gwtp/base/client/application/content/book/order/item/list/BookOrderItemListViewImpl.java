package com.telosys.gwtp.base.client.application.content.book.order.item.list;

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
		final Column<BookOrderItemDto, String> deletion = new Column<BookOrderItemDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(BookOrderItemDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<BookOrderItemDto, String> update = new Column<BookOrderItemDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(BookOrderItemDto object) {
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