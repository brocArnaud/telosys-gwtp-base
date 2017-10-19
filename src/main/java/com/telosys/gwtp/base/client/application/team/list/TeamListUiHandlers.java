package com.telosys.gwtp.base.client.application.team.list;

import com.gwtplatform.mvp.client.UiHandlers;
import com.telosys.gwtp.base.shared.dto.TeamDto;

interface TeamListUiHandlers extends UiHandlers {

	void onCreateClick();

	void onDeleteClick(TeamDto team);

	void onUpdateClick(TeamDto team);

}
