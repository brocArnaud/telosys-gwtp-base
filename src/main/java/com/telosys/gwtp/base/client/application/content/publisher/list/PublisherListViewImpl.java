package com.telosys.gwtp.base.client.application.content.publisher.list;

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
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter.PublisherListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.PublisherDto;

public class PublisherListViewImpl extends AbstractListView<PublisherListPresenter, PublisherDto> implements PublisherListView {
	interface Binder extends UiBinder<Widget, PublisherListViewImpl> {
	}

	@Inject
	PublisherListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<PublisherDto> col1 = new TextColumn<PublisherDto>() {

			@Override
			public String getValue(final PublisherDto object) {
				return String.valueOf(object.getCode());
			}
		};
		table.addColumn(col1, "Code");
		final TextColumn<PublisherDto> col2 = new TextColumn<PublisherDto>() {

			@Override
			public String getValue(final PublisherDto object) {
				return String.valueOf(object.getName());
			}
		};
		table.addColumn(col2, "Name");
		final TextColumn<PublisherDto> col3 = new TextColumn<PublisherDto>() {

			@Override
			public String getValue(final PublisherDto object) {
				return String.valueOf(object.getEmail());
			}
		};
		table.addColumn(col3, "Email");
		final TextColumn<PublisherDto> col4 = new TextColumn<PublisherDto>() {

			@Override
			public String getValue(final PublisherDto object) {
				return String.valueOf(object.getPhone());
			}
		};
		table.addColumn(col4, "Phone");
		final TextColumn<PublisherDto> col5 = new TextColumn<PublisherDto>() {

			@Override
			public String getValue(final PublisherDto object) {
				return String.valueOf(object.getContact());
			}
		};
		table.addColumn(col5, "Contact");
		final Column<PublisherDto, String> deletion = new Column<PublisherDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(PublisherDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<PublisherDto, String> update = new Column<PublisherDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(PublisherDto object) {
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