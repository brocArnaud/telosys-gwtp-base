package com.telosys.gwtp.base.client.application.layout.footer;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.BasePresenter;

public class FooterPresenter extends BasePresenter<FooterPresenter.MyView, FooterPresenter.MyProxy> implements FooterUiHandlers {
	interface MyView extends View, HasUiHandlers<FooterUiHandlers> {
	}

	@ProxyStandard
	interface MyProxy extends Proxy<FooterPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	FooterPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, SLOT_CONTENT, placeManager, dispatcher);
		getView().setUiHandlers(this);
	}

	@Override
	public void onTeamClick() {
		revealPlace(NameTokens.TEAM_LIST);
	}

	@Override
	public void onPlayerClick() {
		revealPlace(NameTokens.PLAYER_LIST);
	}
}
