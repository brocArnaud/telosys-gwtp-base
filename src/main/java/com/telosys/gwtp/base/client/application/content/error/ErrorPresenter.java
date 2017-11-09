package com.telosys.gwtp.base.client.application.content.error;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.error.ErrorPresenter.ErrorProxy;
import com.telosys.gwtp.base.client.application.content.error.ErrorPresenter.ErrorView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.common.BasePresenter;

public class ErrorPresenter extends BasePresenter<ErrorView, ErrorProxy> {
	public interface ErrorView extends View {
	}

	@ProxyStandard
	@NameToken(NameTokens.ERROR)
	public interface ErrorProxy extends ProxyPlace<ErrorPresenter> {
	}

	@Inject
	ErrorPresenter(EventBus eventBus, ErrorView view, ErrorProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager);
	}
}