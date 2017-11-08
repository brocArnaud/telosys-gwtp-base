package com.telosys.gwtp.base.client.application.content.publisher.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
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
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}