package com.telosys.gwtp.base.client.application.content.synopsis.list;

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
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter.SynopsisListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.SynopsisDto;

public class SynopsisListViewImpl extends AbstractListView<SynopsisListPresenter, SynopsisDto> implements SynopsisListView {
	interface Binder extends UiBinder<Widget, SynopsisListViewImpl> {
	}

	@Inject
	SynopsisListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<SynopsisDto> col1 = new TextColumn<SynopsisDto>() {

			@Override
			public String getValue(final SynopsisDto object) {
				return String.valueOf(object.getBookId());
			}
		};
		table.addColumn(col1, "Book Id");
		final TextColumn<SynopsisDto> col2 = new TextColumn<SynopsisDto>() {

			@Override
			public String getValue(final SynopsisDto object) {
				return String.valueOf(object.getSynopsis());
			}
		};
		table.addColumn(col2, "Synopsis");
		final Column<SynopsisDto, String> deletion = new Column<SynopsisDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(SynopsisDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<SynopsisDto, String> update = new Column<SynopsisDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(SynopsisDto object) {
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