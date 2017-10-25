package com.telosys.gwtp.base.client.application.content.player.list;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PlayerListModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(PlayerListPresenter.class, PlayerListPresenter.MyView.class, PlayerListView.class, PlayerListPresenter.MyProxy.class);
	}
}
