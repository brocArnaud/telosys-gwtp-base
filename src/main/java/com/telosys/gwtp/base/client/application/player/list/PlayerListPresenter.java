package com.telosys.gwtp.base.client.application.player.list;

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
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerListPresenter extends Presenter<PlayerListPresenter.MyView, PlayerListPresenter.MyProxy> implements PlayerListUiHandlers {

	interface MyView extends View, HasUiHandlers<PlayerListUiHandlers> {

		void display(List<PlayerDto> players);
	}

	@ProxyStandard
	@NameToken(NameTokens.PLAYER)
	interface MyProxy extends ProxyPlace<PlayerListPresenter> {
	}

	@Inject
	RestDispatch dispatcher;

	@Inject
	PlayerResource playerResource;

	@Inject
	private PlaceManager placeManager;

	@Inject
	PlayerListPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		getView().setUiHandlers(this);
	}

	@Override
	public boolean useManualReveal() {
		return true;
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {

		dispatcher.execute(playerResource.getPlayers(), ManualRevealCallback.create(this, new AsyncCallback<List<PlayerDto>>() {

			@Override
			public void onSuccess(List<PlayerDto> players) {
				getView().display(players);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("onFailure : " + caught);
			}
		}));

	}

	@Override
	public void onCreateClick() {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.NEW_PLAYER).build());
	}
}
