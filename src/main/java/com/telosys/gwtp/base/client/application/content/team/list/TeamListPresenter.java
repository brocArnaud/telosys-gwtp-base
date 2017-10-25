package com.telosys.gwtp.base.client.application.content.team.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.presenter.BasePresenter;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamListPresenter extends BasePresenter<TeamListPresenter.MyView, TeamListPresenter.MyProxy> implements TeamListUiHandlers {

	public interface MyView extends View, HasUiHandlers<TeamListUiHandlers> {
		void display(List<TeamDto> team);
	}

	@ProxyStandard
	@NameToken(NameTokens.TEAM_LIST)
	interface MyProxy extends ProxyPlace<TeamListPresenter> {
	}

	@Inject
	ResourceDelegate<TeamResource> teamService;

	@Inject
	TeamListPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		load();
	}

	@Override
	public void onCreateClick() {
		revealPlace(NameTokens.TEAM_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	private void load() {
		LoadingEvent.fire(this, true);
		teamService.withCallback(new CallBack<List<TeamDto>>() {
			@Override
			public void onSuccess(List<TeamDto> teams) {
				getView().display(teams);
				LoadingEvent.fire(TeamListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(TeamDto team) {
		LoadingEvent.fire(this, true);
		teamService.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(team.getId());
	}

	@Override
	public void onUpdateClick(TeamDto team) {
		revealPlace(NameTokens.TEAM_FORM, TokenParameters.ID, String.valueOf(team.getId()));
	}
}