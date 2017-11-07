package com.telosys.gwtp.base.client.application.content.employee.group.list;

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
		final Column<EmployeeGroupDto, String> deletion = new Column<EmployeeGroupDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(EmployeeGroupDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, employeeGroup, value) -> presenter.onDeleteClick(employeeGroup));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<EmployeeGroupDto, String> update = new Column<EmployeeGroupDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(EmployeeGroupDto object) {
				return "";
			}
		};
		update.setFieldUpdater((index, employeeGroup, value) -> presenter.onUpdateClick(employeeGroup));
		update.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(update, "Update");
		table.setColumnWidth(deletion, 30, Unit.PX);
		table.setColumnWidth(update, 30, Unit.PX);
		provider.addDataDisplay(table);
	}
}