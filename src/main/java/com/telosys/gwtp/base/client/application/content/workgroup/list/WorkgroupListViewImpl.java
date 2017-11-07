package com.telosys.gwtp.base.client.application.content.workgroup.list;

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
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter.WorkgroupListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;

public class WorkgroupListViewImpl extends AbstractListView<WorkgroupListPresenter, WorkgroupDto> implements WorkgroupListView {
	interface Binder extends UiBinder<Widget, WorkgroupListViewImpl> {
	}

	@Inject
	WorkgroupListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<WorkgroupDto> col1 = new TextColumn<WorkgroupDto>() {

			@Override
			public String getValue(final WorkgroupDto object) {
				return String.valueOf(object.getId());
			}
		};
		table.addColumn(col1, "Id");
		final TextColumn<WorkgroupDto> col2 = new TextColumn<WorkgroupDto>() {

			@Override
			public String getValue(final WorkgroupDto object) {
				return String.valueOf(object.getName());
			}
		};
		table.addColumn(col2, "Name");
		final TextColumn<WorkgroupDto> col3 = new TextColumn<WorkgroupDto>() {

			@Override
			public String getValue(final WorkgroupDto object) {
				return String.valueOf(object.getCreationDate());
			}
		};
		table.addColumn(col3, "Creation Date");
		final TextColumn<WorkgroupDto> col4 = new TextColumn<WorkgroupDto>() {

			@Override
			public String getValue(final WorkgroupDto object) {
				return String.valueOf(object.getDescription());
			}
		};
		table.addColumn(col4, "Description");
		final Column<WorkgroupDto, String> deletion = new Column<WorkgroupDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(WorkgroupDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<WorkgroupDto, String> update = new Column<WorkgroupDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(WorkgroupDto object) {
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