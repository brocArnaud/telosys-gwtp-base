package com.telosys.gwtp.base.client.application.player.form;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.BasePresenter;
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
	PlayerResource playerResource;

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
		dispatcher.execute(teamResource.getTeamList(), ManualRevealCallback.create(this, new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> teams) {
				getView().loadTeams(teams);
				LoadingEvent.fire(PlayerFormPresenter.this, false);
			}
		}));
	}

	private void load() {
		final String id = placeManager.getCurrentPlaceRequest().getParameter(TokenParameters.ID, TokenParameters.DEFAULT_ID);
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			dispatcher.execute(playerResource.get(Long.valueOf(id)), ManualRevealCallback.create(this, new CallBack<PlayerDto>() {
				@Override
				public void onSuccess(PlayerDto team) {
					getView().load(team);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(PlayerFormPresenter.this, false);
				}
			}));
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
			dispatcher.execute(playerResource.update(player, player.getId()), ManualRevealCallback.create(this, new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					getView().showNotification(true);
					LoadingEvent.fire(PlayerFormPresenter.this, false);
				}
			}));
		} else {
			dispatcher.execute(playerResource.create(player), ManualRevealCallback.create(this, new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					getView().showNotification(true);
					LoadingEvent.fire(PlayerFormPresenter.this, false);
				}
			}));
		}
	}

	@Override
	public void reset() {
		load();
	}
}
