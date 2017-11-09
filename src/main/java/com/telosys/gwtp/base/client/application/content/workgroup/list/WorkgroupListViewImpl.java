package com.telosys.gwtp.base.client.application.content.workgroup.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
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
				return object != null ? formatDate(object.getCreationDate()) : "";
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
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}