package com.telosys.gwtp.base.client.application.team.form;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamFormPresenter extends Presenter<TeamFormPresenter.MyView, TeamFormPresenter.MyProxy> {

	interface MyView extends View {
		void display(TeamDto team);
	}

	@ProxyStandard
	@NameToken(NameTokens.NEW_TEAM)
	interface MyProxy extends ProxyPlace<TeamFormPresenter> {
	}

	@Inject
	RestDispatch dispatcher;

	@Inject
	TeamResource teamResource;

	@Inject
	TeamFormPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
//		dispatcher.execute(teamResource.getTeam(), ManualRevealCallback.create(this, new AsyncCallback<List<TeamDto>>() {
//
//			@Override
//			public void onSuccess(List<TeamDto> teams) {
//				getView().display(teams);
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				GWT.log("onFailure : " + caught);
//			}
//		}));
	}
}
