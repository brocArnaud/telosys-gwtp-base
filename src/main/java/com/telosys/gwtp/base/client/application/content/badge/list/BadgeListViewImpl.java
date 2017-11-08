package com.telosys.gwtp.base.client.application.content.badge.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter.BadgeListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.BadgeDto;

public class BadgeListViewImpl extends AbstractListView<BadgeListPresenter, BadgeDto> implements BadgeListView {
	interface Binder extends UiBinder<Widget, BadgeListViewImpl> {
	}

	@Inject
	BadgeListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<BadgeDto> col1 = new TextColumn<BadgeDto>() {

			@Override
			public String getValue(final BadgeDto object) {
				return String.valueOf(object.getBadgeNumber());
			}
		};
		table.addColumn(col1, "Number");
		final TextColumn<BadgeDto> col2 = new TextColumn<BadgeDto>() {

			@Override
			public String getValue(final BadgeDto object) {
				return String.valueOf(object.getAuthorizationLevel());
			}
		};
		table.addColumn(col2, "Authorization Level");
		final TextColumn<BadgeDto> col3 = new TextColumn<BadgeDto>() {

			@Override
			public String getValue(final BadgeDto object) {
				return String.valueOf(object.getEndOfValidity());
			}
		};
		table.addColumn(col3, "End of validity");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}