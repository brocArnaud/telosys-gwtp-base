package com.telosys.gwtp.base.client.application.player.form;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PlayerFormModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(PlayerFormPresenter.class, PlayerFormPresenter.MyView.class, PlayerFormView.class, PlayerFormPresenter.MyProxy.class);
	}
}
