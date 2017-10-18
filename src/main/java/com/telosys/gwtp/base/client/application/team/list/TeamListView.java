package com.telosys.gwtp.base.client.application.team.list;

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
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamListView extends ViewWithUiHandlers<TeamListUiHandlers> implements TeamListPresenter.MyView {
	interface Binder extends UiBinder<Widget, TeamListView> {
	}

	@UiField
	CellTable<TeamDto> teamList;

	private ListDataProvider<TeamDto> cellTableProvider = new ListDataProvider<TeamDto>();

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
		final TextColumn<TeamDto> col1 = new TextColumn<TeamDto>() {

			@Override
			public String getValue(final TeamDto object) {
				return String.valueOf(object.getId());
			}
		};
		teamList.addColumn(col1, "Id");
		final TextColumn<TeamDto> col2 = new TextColumn<TeamDto>() {

			@Override
			public String getValue(final TeamDto object) {
				return String.valueOf(object.getName());
			}
		};
		teamList.addColumn(col2, "Name");

		cellTableProvider.addDataDisplay(teamList);
	}
	
	@UiHandler(value = "create")
	public void onCreateClick(ClickEvent event) {
		getUiHandlers().onCreateClick();
	}
}
