package com.telosys.gwtp.base.client.application.content.team.list;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TeamListModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(TeamListPresenter.class, TeamListPresenter.MyView.class, TeamListView.class, TeamListPresenter.MyProxy.class);
	}
}
