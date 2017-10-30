package com.telosys.gwtp.base.client.application.content.team.form;

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
import com.telosys.gwtp.base.client.application.content.player.list.PlayerListPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.presenter.BasePresenter;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamFormPresenter extends BasePresenter<TeamFormPresenter.MyView, TeamFormPresenter.MyProxy> {

	public interface MyView extends View {
		void setPresenter(TeamFormPresenter presenter);

		void showNotification(boolean visible);

		void load(TeamDto team);

		void setUpdateMode(boolean updateMode);
	}

	@ProxyStandard
	@NameToken(NameTokens.TEAM_FORM)
	public interface MyProxy extends ProxyPlace<TeamFormPresenter> {
	}

	@Inject
	ResourceDelegate<TeamResource> teamService;

	private boolean updateMode = false;

	@Inject
	TeamFormPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		// ensure notification panel not visible
		getView().showNotification(false);
		load();
	}

	private void load() {
		final String id = getCurrentPlaceRequestId();
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			teamService.withCallback(new CallBack<TeamDto>() {
				@Override
				public void onSuccess(TeamDto team) {
					getView().load(team);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(TeamFormPresenter.this, false);
				}
			}).get(Long.valueOf(id));
		} else {
			getView().load(new TeamDto());
		}

	}

	public void save(TeamDto team) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			teamService.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(team, team.getId());
		} else {
			teamService.withCallback(new CallBack<Void>() {

				@Override
				public void onSuccess(Void nothing) {
					success();
				}

			}).create(team);
		}
	}

	private void success() {
		getView().showNotification(true);
		LoadingEvent.fire(TeamFormPresenter.this, false);
		revealPlace(NameTokens.TEAM_LIST);
	}

	public void reset() {
		load();
	}
}