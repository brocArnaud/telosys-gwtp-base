package com.telosys.gwtp.base.client.application.layout.header;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.BasePresenter;

public class HeaderPresenter extends BasePresenter<HeaderPresenter.MyView, HeaderPresenter.MyProxy> implements HeaderUiHandlers, LoadingEvent.LoadingHandler {
	interface MyView extends View, HasUiHandlers<HeaderUiHandlers> {
		void showSpinner(boolean visible);
	}

	@ProxyStandard
	interface MyProxy extends Proxy<HeaderPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	HeaderPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, SLOT_CONTENT, placeManager, dispatcher);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(LoadingEvent.TYPE, this);
	}

	@Override
	public void onTeamClick() {
		revealPlace(NameTokens.TEAM_LIST);
	}

	@Override
	public void onPlayerClick() {
		revealPlace(NameTokens.PLAYER_LIST);
	}

	@Override
	public void onHomeClick() {
		revealPlace(NameTokens.HOME);
	}

	@Override
	public void onLoadingEvent(LoadingEvent event) {
		getView().showSpinner(event.isShow());
	}
}