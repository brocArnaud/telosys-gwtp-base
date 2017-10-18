package com.telosys.gwtp.base.client.application.player.list;

import java.util.List;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerListView extends ViewWithUiHandlers<PlayerListUiHandlers> implements PlayerListPresenter.MyView {
	interface Binder extends UiBinder<Widget, PlayerListView> {
	}

	@UiField
	CellTable<PlayerDto> playerList;

	private ListDataProvider<PlayerDto> cellTableProvider = new ListDataProvider<PlayerDto>();

	@Inject
	PlayerListView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void display(List<PlayerDto> players) {
		cellTableProvider.getList().clear();
		cellTableProvider.getList().addAll(players);
		cellTableProvider.flush();
	}

	private void initCellTable() {
		final TextColumn<PlayerDto> col1 = new TextColumn<PlayerDto>() {

			@Override
			public String getValue(final PlayerDto object) {
				return String.valueOf(object.getId());
			}
		};
		playerList.addColumn(col1, "Id");
		final TextColumn<PlayerDto> col2 = new TextColumn<PlayerDto>() {

			@Override
			public String getValue(final PlayerDto object) {
				return String.valueOf(object.getName());
			}
		};
		playerList.addColumn(col2, "Name");
		final TextColumn<PlayerDto> col3 = new TextColumn<PlayerDto>() {

			@Override
			public String getValue(final PlayerDto object) {
				return String.valueOf(object.getTeam());
			}
		};
		playerList.addColumn(col3, "Team Id");

		cellTableProvider.addDataDisplay(playerList);
	}

	@UiHandler(value = "create")
	public void onCreateClick(ClickEvent event) {
		getUiHandlers().onCreateClick();
	}
}
