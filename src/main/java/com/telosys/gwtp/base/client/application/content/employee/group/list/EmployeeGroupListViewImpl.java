package com.telosys.gwtp.base.client.application.content.employee.group.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter.EmployeeGroupListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;

public class EmployeeGroupListViewImpl extends AbstractListView<EmployeeGroupListPresenter, EmployeeGroupDto> implements EmployeeGroupListView {
	interface Binder extends UiBinder<Widget, EmployeeGroupListViewImpl> {
	}

	@Inject
	EmployeeGroupListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<EmployeeGroupDto> col1 = new TextColumn<EmployeeGroupDto>() {

			@Override
			public String getValue(final EmployeeGroupDto object) {
				return String.valueOf(object.getEmployeeCode());
			}
		};
		table.addColumn(col1, "Employee code");
		final TextColumn<EmployeeGroupDto> col2 = new TextColumn<EmployeeGroupDto>() {

			@Override
			public String getValue(final EmployeeGroupDto object) {
				return String.valueOf(object.getGroupId());
			}
		};
		table.addColumn(col2, "Group id");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}