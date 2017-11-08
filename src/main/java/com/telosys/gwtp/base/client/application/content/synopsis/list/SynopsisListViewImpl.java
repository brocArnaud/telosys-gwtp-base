package com.telosys.gwtp.base.client.application.content.synopsis.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
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
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}