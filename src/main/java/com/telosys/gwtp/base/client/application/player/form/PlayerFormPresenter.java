package com.telosys.gwtp.base.client.application.player.form;

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
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerFormPresenter extends Presenter<PlayerFormPresenter.MyView, PlayerFormPresenter.MyProxy> {

	interface MyView extends View {

		void display(PlayerDto player);
	}

	@ProxyStandard
	@NameToken(NameTokens.NEW_PLAYER)
	interface MyProxy extends ProxyPlace<PlayerFormPresenter> {
	}

	@Inject
	RestDispatch dispatcher;

	@Inject
	PlayerResource playerResource;

	@Inject
	PlayerFormPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {

//		dispatcher.execute(playerResource.getPlayers(), ManualRevealCallback.create(this, new AsyncCallback<List<PlayerDto>>() {
//
//			@Override
//			public void onSuccess(List<PlayerDto> players) {
//				getView().display(players);
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				GWT.log("onFailure : " + caught);
//			}
//		}));

	}
}
