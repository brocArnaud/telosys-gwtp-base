package com.telosys.gwtp.base.client.application.content.team.list;

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
import com.telosys.gwtp.base.shared.dto.team.TeamDto;

public class TeamListView extends AbstractListView<TeamListPresenter, TeamDto, Long> implements TeamListPresenter.MyView {
	interface Binder extends UiBinder<Widget, TeamListView> {
	}

	@Inject
	TeamListView(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<TeamDto> id = new TextColumn<TeamDto>() {

			@Override
			public String getValue(final TeamDto team) {
				return String.valueOf(team.getId());
			}
		};
		table.addColumn(id, "Id");
		final TextColumn<TeamDto> name = new TextColumn<TeamDto>() {

			@Override
			public String getValue(final TeamDto team) {
				return String.valueOf(team.getName());
			}
		};
		table.addColumn(name, "Name");

		final Column<TeamDto, String> deletion = new Column<TeamDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(TeamDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, team, value) -> presenter.onDeleteClick(team.getId()));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");
		final Column<TeamDto, String> update = new Column<TeamDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(TeamDto object) {
				return "";
			}
		};
		update.setFieldUpdater((index, team, value) -> presenter.onUpdateClick(team.getId()));
		update.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(update, "Update");
		table.setColumnWidth(deletion, 30, Unit.PX);
		table.setColumnWidth(update, 30, Unit.PX);
		provider.addDataDisplay(table);
	}
}