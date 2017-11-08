package com.telosys.gwtp.base.client.application.content.home;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.home.HomePresenter.HomeProxy;
import com.telosys.gwtp.base.client.application.content.home.HomePresenter.HomeView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.common.BasePresenter;

public class HomePresenter extends BasePresenter<HomeView, HomeProxy> {
	public interface HomeView extends View {
	}

	@ProxyStandard
	@NameToken(NameTokens.HOME)
	public interface HomeProxy extends ProxyPlace<HomePresenter> {
	}

	@Inject
	HomePresenter(EventBus eventBus, HomeView view, HomeProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
	}
}