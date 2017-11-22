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

/**
 * Presenter associated with error view.
 * 
 * @see ErrorViewImpl
 * @author Arnaud Brochain (arnaud.brochain@sogeti.com)
 *
 */
public class ErrorPresenter extends BasePresenter<ErrorView, ErrorProxy> {
	public interface ErrorView extends View {
	}

	// Proxy declaration
	// @see pro
	/**
	 * Proxy declaration, define an accessible place of the application.
	 * @see NameToken for more information about place definition
	 * @see ProxyPlace for more information about place definition
	 */
	@ProxyStandard
	@NameToken(NameTokens.ERROR)
	public interface ErrorProxy extends ProxyPlace<ErrorPresenter> {
	}

	/**
	 * Standard constructor.
	 * @param eventBus the shared event bus.
	 * @param view the view contract
	 * @param proxy the presenter proxy
	 * @param placeManager the place manager
	 */
	@Inject
	ErrorPresenter(EventBus eventBus, ErrorView view, ErrorProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager);
	}
}