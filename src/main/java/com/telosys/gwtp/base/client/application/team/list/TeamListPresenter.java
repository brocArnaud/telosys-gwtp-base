package com.telosys.gwtp.base.client.application.team.list;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamListPresenter extends Presenter<TeamListPresenter.MyView, TeamListPresenter.MyProxy> implements TeamListUiHandlers {

	interface MyView extends View, HasUiHandlers<TeamListUiHandlers> {
		void display(List<TeamDto> team);
	}

	@ProxyStandard
	@NameToken(NameTokens.TEAM_LIST)
	interface MyProxy extends ProxyPlace<TeamListPresenter> {
	}

	@Inject
	RestDispatch dispatcher;

	@Inject
	TeamResource teamResource;

	@Inject
	private PlaceManager placeManager;

	@Inject
	TeamListPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		getView().setUiHandlers(this);
	}

	@Override
	public boolean useManualReveal() {
		return true;
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		load();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		load();
	}

	@Override
	public void onCreateClick() {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.TEAM_FORM).with(TokenParameters.ID, TokenParameters.DEFAULT_ID).build());
	}

	private void load() {
		dispatcher.execute(teamResource.getTeam(), ManualRevealCallback.create(this, new AsyncCallback<List<TeamDto>>() {

			@Override
			public void onSuccess(List<TeamDto> teams) {
				getView().display(teams);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("onFailure : " + caught);
			}
		}));
	}

	@Override
	public void onDeleteClick(TeamDto team) {
		dispatcher.execute(teamResource.delete(team.getId()), ManualRevealCallback.create(this, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void nothing) {
				load();
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("onFailure : " + caught);
			}
		}));
	}

	@Override
	public void onUpdateClick(TeamDto team) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.TEAM_FORM).with(TokenParameters.ID, String.valueOf(team.getId())).build());
	}
}
