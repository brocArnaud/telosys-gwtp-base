package com.telosys.gwtp.base.client.util;

import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;

public abstract class BasePresenter<V extends View, P extends Proxy<?>> extends Presenter<V, P> {

	protected static final Logger logger = Logger.getLogger(BasePresenter.class.getSimpleName());

	protected RestDispatch dispatcher;

	protected PlaceManager placeManager;

	public BasePresenter(EventBus eventBus, V view, P proxy, RevealType revealType, PlaceManager placeManager, RestDispatch dispatcher) {
		this(eventBus, view, proxy, revealType, null, placeManager, dispatcher);
	}

	public BasePresenter(EventBus eventBus, V view, P proxy, GwtEvent.Type<RevealContentHandler<?>> slot, PlaceManager placeManager, RestDispatch dispatcher) {
		this(eventBus, view, proxy, null, slot, placeManager, dispatcher);
	}

	public BasePresenter(EventBus eventBus, V view, P proxy, RevealType revealType, GwtEvent.Type<RevealContentHandler<?>> slot, PlaceManager placeManager,
			RestDispatch dispatcher) {
		super(eventBus, view, proxy, revealType, slot);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
	}

	protected void manageError(Throwable caught) {
		LoadingEvent.fire(this, false);
		logger.severe("An error occured : " + caught.getMessage());
		revealPlace(NameTokens.ERROR);
	}

	protected void revealPlace(String token) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(token).build());
	}

	protected void revealPlace(String token, String param, String value) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(token).with(param, value).build());
	}

	protected abstract class CallBack<T> implements AsyncCallback<T> {
		@Override
		public void onFailure(Throwable caught) {
			manageError(caught);
		}
	}

}
