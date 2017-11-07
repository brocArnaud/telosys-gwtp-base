package com.telosys.gwtp.base.client.application.content.team.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.team.TeamDto;

public class TeamListPresenter extends AbstractListPresenter<TeamListPresenter.MyProxy, TeamListPresenter.MyView, TeamDto, TeamResource> {

	public interface MyView extends ListView<TeamListPresenter, TeamDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.TEAM_LIST)
	public interface MyProxy extends ProxyPlace<TeamListPresenter> {
	}

	@Inject
	TeamListPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.TEAM_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<TeamDto>>() {
			@Override
			public void onSuccess(List<TeamDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(TeamListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(TeamDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getId());
	}

	@Override
	public void onUpdateClick(TeamDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.ID, String.valueOf(data.getId()));
	}

}