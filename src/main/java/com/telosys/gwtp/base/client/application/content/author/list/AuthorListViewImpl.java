package com.telosys.gwtp.base.client.application.content.author.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter.AuthorListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.AuthorDto;

public class AuthorListViewImpl extends AbstractListView<AuthorListPresenter, AuthorDto> implements AuthorListView {

	interface Binder extends UiBinder<Widget, AuthorListViewImpl> {
	}

	@Inject
	AuthorListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<AuthorDto> col1 = new TextColumn<AuthorDto>() {

			@Override
			public String getValue(final AuthorDto object) {
				return String.valueOf(object.getId());
			}
		};
		table.addColumn(col1, "Id");
		final TextColumn<AuthorDto> col2 = new TextColumn<AuthorDto>() {

			@Override
			public String getValue(final AuthorDto object) {
				return String.valueOf(object.getFirstName());
			}
		};
		table.addColumn(col2, "Firstname");
		final TextColumn<AuthorDto> col3 = new TextColumn<AuthorDto>() {

			@Override
			public String getValue(final AuthorDto object) {
				return String.valueOf(object.getLastName());
			}
		};
		table.addColumn(col3, "Lastname");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}