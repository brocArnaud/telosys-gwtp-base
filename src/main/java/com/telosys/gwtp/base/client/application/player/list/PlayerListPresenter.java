package com.telosys.gwtp.base.client.application.player.list;

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
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.BasePresenter;
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerListPresenter extends BasePresenter<PlayerListPresenter.MyView, PlayerListPresenter.MyProxy> implements PlayerListUiHandlers {

	interface MyView extends View, HasUiHandlers<PlayerListUiHandlers> {

		void display(List<PlayerDto> players);
	}

	@ProxyStandard
	@NameToken(NameTokens.PLAYER_LIST)
	interface MyProxy extends ProxyPlace<PlayerListPresenter> {
	}

	@Inject
	PlayerResource playerResource;

	@Inject
	PlayerListPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
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
		revealPlace(NameTokens.PLAYER_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	private void load() {
		LoadingEvent.fire(this, true);
		dispatcher.execute(playerResource.getPlayers(), ManualRevealCallback.create(this, new CallBack<List<PlayerDto>>() {
			@Override
			public void onSuccess(List<PlayerDto> players) {
				getView().display(players);
				LoadingEvent.fire(PlayerListPresenter.this, false);
			}
		}));
	}

	@Override
	public void onDeleteClick(PlayerDto team) {
		LoadingEvent.fire(this, true);
		dispatcher.execute(playerResource.delete(team.getId()), ManualRevealCallback.create(this, new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}));
	}

	@Override
	public void onUpdateClick(PlayerDto team) {
		revealPlace(NameTokens.PLAYER_FORM, TokenParameters.ID, String.valueOf(team.getId()));
	}
}
