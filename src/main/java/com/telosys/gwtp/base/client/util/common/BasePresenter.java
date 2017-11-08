package com.telosys.gwtp.base.client.util.common;

import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestCallback;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;

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

	protected void manageError(Throwable caught, int statutCode) {
		LoadingEvent.fire(this, false);
		logger.severe("An error occured : " + caught.getMessage() + " | Response status code :" + statutCode);
		revealPlace(NameTokens.ERROR);
	}

	protected void revealPlace(String token) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(token).build());
	}

	protected void revealPlace(String token, String param, String value) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(token).with(param, value).build());
	}

	protected void revealPlace(String token, String param, Integer value) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(token).with(param, String.valueOf(value)).build());
	}

	protected void revealPlace(String token, String param1, String value1, String param2, String value2) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(token).with(param1, value1).with(param2, value2).build());
	}

	protected void revealPlace(String token, String param1, Integer value1, String param2, Integer value2) {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(token).with(param1, String.valueOf(value1)).with(param2, String.valueOf(value2)).build());
	}

	protected abstract class CallBack<T> implements RestCallback<T> {

		private int statusCode;

		@Override
		public void onFailure(Throwable caught) {
			manageError(caught, statusCode);
		}

		@Override
		public void setResponse(Response response) {
			statusCode = response.getStatusCode();
		}
	}

	public String getCurrentPlaceRequestId() {
		return getCurrentPlaceRequestParam(TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	public String getCurrentPlaceRequestId(String param) {
		return getCurrentPlaceRequestParam(param, TokenParameters.DEFAULT_ID);
	}

	public String getCurrentPlaceRequestParam(String parameter, String defaultValue) {
		return placeManager.getCurrentPlaceRequest().getParameter(parameter, defaultValue);
	}

}
