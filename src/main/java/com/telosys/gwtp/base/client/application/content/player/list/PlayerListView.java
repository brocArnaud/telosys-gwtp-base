package com.telosys.gwtp.base.client.application.content.player.list;

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
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;

public class PlayerListView extends AbstractListView<PlayerListPresenter, PlayerDto, Long> implements PlayerListPresenter.MyView {
	interface Binder extends UiBinder<Widget, PlayerListView> {
	}

	@Inject
	PlayerListView(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<PlayerDto> col1 = new TextColumn<PlayerDto>() {

			@Override
			public String getValue(final PlayerDto object) {
				return String.valueOf(object.getId());
			}
		};
		table.addColumn(col1, "Id");
		final TextColumn<PlayerDto> col2 = new TextColumn<PlayerDto>() {

			@Override
			public String getValue(final PlayerDto object) {
				return String.valueOf(object.getName());
			}
		};
		table.addColumn(col2, "Name");
		final TextColumn<PlayerDto> col3 = new TextColumn<PlayerDto>() {

			@Override
			public String getValue(final PlayerDto object) {
				return String.valueOf(object.getTeam());
			}
		};
		table.addColumn(col3, "Team Id");
		final Column<PlayerDto, String> deletion = new Column<PlayerDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(PlayerDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player.getId()));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<PlayerDto, String> update = new Column<PlayerDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(PlayerDto object) {
				return "";
			}
		};
		update.setFieldUpdater((index, player, value) -> presenter.onUpdateClick(player.getId()));
		update.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(update, "Update");
		table.setColumnWidth(deletion, 30, Unit.PX);
		table.setColumnWidth(update, 30, Unit.PX);
		provider.addDataDisplay(table);
	}
}