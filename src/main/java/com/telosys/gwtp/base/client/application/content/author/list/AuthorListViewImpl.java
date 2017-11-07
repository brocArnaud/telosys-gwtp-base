package com.telosys.gwtp.base.client.application.content.author.list;

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
		final Column<AuthorDto, String> deletion = new Column<AuthorDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(AuthorDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<AuthorDto, String> update = new Column<AuthorDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(AuthorDto object) {
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