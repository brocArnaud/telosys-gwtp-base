package com.telosys.gwtp.base.client.application.content.book.order.list;

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
				return String.valueOf(object.getDate());
			}
		};
		table.addColumn(col3, "Date");
		final Column<BookOrderDto, String> deletion = new Column<BookOrderDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(BookOrderDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<BookOrderDto, String> update = new Column<BookOrderDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(BookOrderDto object) {
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