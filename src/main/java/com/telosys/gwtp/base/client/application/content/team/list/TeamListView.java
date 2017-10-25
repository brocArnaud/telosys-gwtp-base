package com.telosys.gwtp.base.client.application.content.team.list;

import java.util.List;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamListView extends ViewWithUiHandlers<TeamListUiHandlers> implements TeamListPresenter.MyView {
	interface Binder extends UiBinder<Widget, TeamListView> {
	}

	@UiField
	CellTable<TeamDto> teamList;

	private ListDataProvider<TeamDto> cellTableProvider = new ListDataProvider<>();

	@Inject
	TeamListView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void display(List<TeamDto> teams) {
		cellTableProvider.getList().clear();
		cellTableProvider.getList().addAll(teams);
		cellTableProvider.flush();
	}

	private void initCellTable() {
		final TextColumn<TeamDto> id = new TextColumn<TeamDto>() {

			@Override
			public String getValue(final TeamDto team) {
				return String.valueOf(team.getId());
			}
		};
		teamList.addColumn(id, "Id");
		final TextColumn<TeamDto> name = new TextColumn<TeamDto>() {

			@Override
			public String getValue(final TeamDto team) {
				return String.valueOf(team.getName());
			}
		};
		teamList.addColumn(name, "Name");

		final Column<TeamDto, String> deletion = new Column<TeamDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(TeamDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, team, value) -> getUiHandlers().onDeleteClick(team));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		teamList.addColumn(deletion, "Delete");
		final Column<TeamDto, String> update = new Column<TeamDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(TeamDto object) {
				return "";
			}
		};
		update.setFieldUpdater((index, team, value) -> getUiHandlers().onUpdateClick(team));
		update.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		teamList.addColumn(update, "Update");
		teamList.setColumnWidth(deletion, 30, Unit.PX);
		teamList.setColumnWidth(update, 30, Unit.PX);
		cellTableProvider.addDataDisplay(teamList);
	}

	@UiHandler(value = "create")
	public void onCreateClick(ClickEvent event) {
		getUiHandlers().onCreateClick();
	}
}
