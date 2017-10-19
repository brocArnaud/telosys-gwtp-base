package com.telosys.gwtp.base.client.application.player.list;

import com.gwtplatform.mvp.client.UiHandlers;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

interface PlayerListUiHandlers extends UiHandlers {

	void onCreateClick();

	void onDeleteClick(PlayerDto player);

	void onUpdateClick(PlayerDto player);

}
