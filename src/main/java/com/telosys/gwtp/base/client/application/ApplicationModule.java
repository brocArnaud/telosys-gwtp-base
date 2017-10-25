package com.telosys.gwtp.base.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.telosys.gwtp.base.client.application.content.error.ErrorModule;
import com.telosys.gwtp.base.client.application.content.home.HomeModule;
import com.telosys.gwtp.base.client.application.content.player.form.PlayerFormModule;
import com.telosys.gwtp.base.client.application.content.player.list.PlayerListModule;
import com.telosys.gwtp.base.client.application.content.team.form.TeamFormModule;
import com.telosys.gwtp.base.client.application.content.team.list.TeamListModule;
import com.telosys.gwtp.base.client.application.layout.footer.FooterModule;
import com.telosys.gwtp.base.client.application.layout.header.HeaderModule;

public class ApplicationModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new HomeModule());

		install(new HeaderModule());
		install(new FooterModule());

		install(new PlayerListModule());
		install(new PlayerFormModule());
		install(new TeamListModule());
		install(new TeamFormModule());

		install(new ErrorModule());

		bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
	}
}
