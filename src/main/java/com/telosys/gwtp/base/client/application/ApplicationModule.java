package com.telosys.gwtp.base.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.telosys.gwtp.base.client.application.content.error.ErrorPresenter;
import com.telosys.gwtp.base.client.application.content.error.ErrorView;
import com.telosys.gwtp.base.client.application.content.home.HomePresenter;
import com.telosys.gwtp.base.client.application.content.home.HomeView;
import com.telosys.gwtp.base.client.application.content.player.form.PlayerFormPresenter;
import com.telosys.gwtp.base.client.application.content.player.form.PlayerFormView;
import com.telosys.gwtp.base.client.application.content.player.list.PlayerListPresenter;
import com.telosys.gwtp.base.client.application.content.player.list.PlayerListView;
import com.telosys.gwtp.base.client.application.content.team.form.TeamFormPresenter;
import com.telosys.gwtp.base.client.application.content.team.form.TeamFormView;
import com.telosys.gwtp.base.client.application.content.team.list.TeamListPresenter;
import com.telosys.gwtp.base.client.application.content.team.list.TeamListView;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter;
import com.telosys.gwtp.base.client.application.layout.footer.FooterView;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter;
import com.telosys.gwtp.base.client.application.layout.header.HeaderView;

public class ApplicationModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		/** Header part */
		bindPresenter(HeaderPresenter.class, HeaderPresenter.MyView.class, HeaderView.class, HeaderPresenter.MyProxy.class);
		/** Footer part */
		bindPresenter(FooterPresenter.class, FooterPresenter.MyView.class, FooterView.class, FooterPresenter.MyProxy.class);
		/** Home Page */
		bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
		/** Error page */
		bindPresenter(ErrorPresenter.class, ErrorPresenter.MyView.class, ErrorView.class, ErrorPresenter.MyProxy.class);

		/** Player list page */
		bindPresenter(PlayerListPresenter.class, PlayerListPresenter.MyView.class, PlayerListView.class, PlayerListPresenter.MyProxy.class);
		/** Player form page */
		bindPresenter(PlayerFormPresenter.class, PlayerFormPresenter.MyView.class, PlayerFormView.class, PlayerFormPresenter.MyProxy.class);
		/** Team list page */
		bindPresenter(TeamListPresenter.class, TeamListPresenter.MyView.class, TeamListView.class, TeamListPresenter.MyProxy.class);
		/** Team form page */
		bindPresenter(TeamFormPresenter.class, TeamFormPresenter.MyView.class, TeamFormView.class, TeamFormPresenter.MyProxy.class);

		bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
	}
}
