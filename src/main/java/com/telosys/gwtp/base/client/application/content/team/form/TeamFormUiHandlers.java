package com.telosys.gwtp.base.client.application.content.team.form;

import com.gwtplatform.mvp.client.UiHandlers;
import com.telosys.gwtp.base.shared.dto.TeamDto;

interface TeamFormUiHandlers extends UiHandlers {

	void save(TeamDto team);
	
	void reset();

}
