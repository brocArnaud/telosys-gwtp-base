package com.telosys.gwtp.base.client.application.layout.header;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.place.NameTokens;

public class HeaderPresenter extends Presenter<HeaderPresenter.MyView, HeaderPresenter.MyProxy> implements HeaderUiHandlers {
	interface MyView extends View, HasUiHandlers<HeaderUiHandlers> {
	}

	@ProxyStandard
	interface MyProxy extends Proxy<HeaderPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	private PlaceManager placeManager;

	@Inject
	HeaderPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, SLOT_CONTENT);
		getView().setUiHandlers(this);
	}

	@Override
	public void onTeamClick() {
		GWT.log("Presenter on team click");
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.TEAM).build());
	}

	@Override
	public void onPlayerClick() {
		GWT.log("Presenter on player click");
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.PLAYER).build());
	}

	@Override
	public void onHomeClick() {
		GWT.log("Presenter on home click");
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.HOME).build());
	}
}
