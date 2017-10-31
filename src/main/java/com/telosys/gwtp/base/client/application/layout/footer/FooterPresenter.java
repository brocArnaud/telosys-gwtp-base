package com.telosys.gwtp.base.client.application.layout.footer;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.common.BasePresenter;
import com.telosys.gwtp.base.client.util.common.SimpleView;

public class FooterPresenter extends BasePresenter<FooterPresenter.MyView, FooterPresenter.MyProxy> {
	public interface MyView extends SimpleView<FooterPresenter> {
	}

	@ProxyStandard
	public interface MyProxy extends Proxy<FooterPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	FooterPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, SLOT_CONTENT, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	public void onTeamClick() {
		revealPlace(NameTokens.TEAM_LIST);
	}

	public void onPlayerClick() {
		revealPlace(NameTokens.PLAYER_LIST);
	}
}
