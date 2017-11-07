package com.telosys.gwtp.base.client.application.content.country.list;

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
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter.CountryListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.CountryDto;

public class CountryListViewImpl extends AbstractListView<CountryListPresenter, CountryDto> implements CountryListView {
	interface Binder extends UiBinder<Widget, CountryListViewImpl> {
	}

	@Inject
	CountryListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<CountryDto> col1 = new TextColumn<CountryDto>() {

			@Override
			public String getValue(final CountryDto object) {
				return String.valueOf(object.getCode());
			}
		};
		table.addColumn(col1, "Id");
		final TextColumn<CountryDto> col2 = new TextColumn<CountryDto>() {

			@Override
			public String getValue(final CountryDto object) {
				return String.valueOf(object.getName());
			}
		};
		table.addColumn(col2, "Name");
		final Column<CountryDto, String> deletion = new Column<CountryDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(CountryDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<CountryDto, String> update = new Column<CountryDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(CountryDto object) {
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