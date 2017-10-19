package com.telosys.gwtp.base.client.application.player.form;

import com.gwtplatform.mvp.client.UiHandlers;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

interface PlayerFormUiHandlers extends UiHandlers {

	void save(PlayerDto player);
	
	void reset();

}
