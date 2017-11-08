package com.telosys.gwtp.base.client.application.content.country.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
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
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}