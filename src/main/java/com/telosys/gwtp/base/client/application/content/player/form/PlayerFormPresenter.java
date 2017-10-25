package com.telosys.gwtp.base.client.application.content.player.form;

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
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.ListItemDto;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerFormPresenter extends BasePresenter<PlayerFormPresenter.MyView, PlayerFormPresenter.MyProxy> implements PlayerFormUiHandlers {

	interface MyView extends View, HasUiHandlers<PlayerFormUiHandlers> {

		void showNotification(boolean visible);

		void load(PlayerDto team);

		void setUpdateMode(boolean updateMode);

		void loadTeams(List<ListItemDto> teams);
	}

	@ProxyStandard
	@NameToken(NameTokens.PLAYER_FORM)
	interface MyProxy extends ProxyPlace<PlayerFormPresenter> {
	}

	@Inject
	ResourceDelegate<PlayerResource> playerService;
	@Inject
	ResourceDelegate<TeamResource> teamService;

	@Inject
	TeamResource teamResource;

	private boolean updateMode = false;

	@Inject
	PlayerFormPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		// ensure notification panel not visible
		getView().showNotification(false);
		load();
	}

	@Override
	protected void onBind() {
		super.onBind();
		LoadingEvent.fire(this, true);
		teamService.withCallback(new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> teams) {
				getView().loadTeams(teams);
				LoadingEvent.fire(PlayerFormPresenter.this, false);
			}
		}).getTeamList();
	}

	private void load() {
		final String id = getCurrentPlaceRequestId();
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			playerService.withCallback(new CallBack<PlayerDto>() {
				@Override
				public void onSuccess(PlayerDto player) {
					getView().load(player);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(PlayerFormPresenter.this, false);
				}
			}).get(Long.valueOf(id));
		} else {
			getView().load(new PlayerDto());
		}
	}

	@Override
	public void save(PlayerDto player) {
		final String[] teamValue = player.getTeam().split("\\|");
		player.setTeam(teamValue[0].trim());
		LoadingEvent.fire(this, true);
		if (updateMode) {
			playerService.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(player, player.getId());
		} else {
			playerService.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).create(player);
		}
	}

	private void success() {
		getView().showNotification(true);
		LoadingEvent.fire(PlayerFormPresenter.this, false);
		revealPlace(NameTokens.PLAYER_LIST);
	}

	@Override
	public void reset() {
		load();
	}
}
