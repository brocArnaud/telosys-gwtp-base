package com.telosys.gwtp.base.client.application.content.team.form;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TeamFormModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(TeamFormPresenter.class, TeamFormPresenter.MyView.class, TeamFormView.class, TeamFormPresenter.MyProxy.class);
	}
}
