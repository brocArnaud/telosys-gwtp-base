package com.telosys.gwtp.base.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.telosys.gwtp.base.client.application.home.HomeModule;
import com.telosys.gwtp.base.client.application.layout.footer.FooterModule;
import com.telosys.gwtp.base.client.application.layout.header.HeaderModule;
import com.telosys.gwtp.base.client.application.player.form.PlayerFormModule;
import com.telosys.gwtp.base.client.application.player.list.PlayerListModule;
import com.telosys.gwtp.base.client.application.team.form.TeamFormModule;
import com.telosys.gwtp.base.client.application.team.list.TeamListModule;

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

		bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
	}
}
