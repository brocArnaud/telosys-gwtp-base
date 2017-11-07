package com.telosys.gwtp.base.client.application.content.employee.list;

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
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter.EmployeeListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;

public class EmployeeListViewImpl extends AbstractListView<EmployeeListPresenter, EmployeeDto> implements EmployeeListView {
	interface Binder extends UiBinder<Widget, EmployeeListViewImpl> {
	}

	@Inject
	EmployeeListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<EmployeeDto> col1 = new TextColumn<EmployeeDto>() {

			@Override
			public String getValue(final EmployeeDto object) {
				return String.valueOf(object.getCode());
			}
		};
		table.addColumn(col1, "Code");
		final TextColumn<EmployeeDto> col2 = new TextColumn<EmployeeDto>() {

			@Override
			public String getValue(final EmployeeDto object) {
				return String.valueOf(object.getBadgeNumber());
			}
		};
		table.addColumn(col2, "Badge Number");
		final TextColumn<EmployeeDto> col3 = new TextColumn<EmployeeDto>() {

			@Override
			public String getValue(final EmployeeDto object) {
				return String.valueOf(object.getFirstName());
			}
		};
		table.addColumn(col3, "Firstname");
		final TextColumn<EmployeeDto> col4 = new TextColumn<EmployeeDto>() {

			@Override
			public String getValue(final EmployeeDto object) {
				return String.valueOf(object.getLastName());
			}
		};
		table.addColumn(col4, "Lastname");
		final TextColumn<EmployeeDto> col5= new TextColumn<EmployeeDto>() {

			@Override
			public String getValue(final EmployeeDto object) {
				return String.valueOf(object.getManager());
			}
		};
		table.addColumn(col5, "Manager");
		final TextColumn<EmployeeDto> col6 = new TextColumn<EmployeeDto>() {

			@Override
			public String getValue(final EmployeeDto object) {
				return String.valueOf(object.getEmail());
			}
		};
		table.addColumn(col6, "Email");
		final TextColumn<EmployeeDto> col7 = new TextColumn<EmployeeDto>() {

			@Override
			public String getValue(final EmployeeDto object) {
				return String.valueOf(object.getShopCode());
			}
		};
		table.addColumn(col7, "Shop code");
		final Column<EmployeeDto, String> deletion = new Column<EmployeeDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(EmployeeDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<EmployeeDto, String> update = new Column<EmployeeDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(EmployeeDto object) {
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