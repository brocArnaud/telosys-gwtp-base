package com.telosys.gwtp.base.client.util.common.list.presenter;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.BasePresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.util.GenericResource;

public abstract class AbstractListPresenter<P extends Proxy<?>, V extends ListView<? extends AbstractListPresenter<P, V, F, I, R>, F>, F, I, R extends GenericResource<F, I>>
		extends BasePresenter<V, P> implements ListPresenter<I> {

	@Inject
	ResourceDelegate<R> service;

	public AbstractListPresenter(final EventBus eventBus, final V view, final P proxy, final GwtEvent.Type<RevealContentHandler<?>> pslot, PlaceManager placeManager,
			RestDispatch dispatcher) {
		super(eventBus, view, proxy, pslot, placeManager, dispatcher);
	}

	public AbstractListPresenter(EventBus eventBus, V view, P proxy, RevealType revealType, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, revealType, placeManager, dispatcher);
	}

	/**
	 * Return the root tokens for the page after successfully creation/update.
	 */
	public abstract String getFormRouteToken();

	@Override
	public void onReveal() {
		super.onReveal();
		load();
	}

	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	private void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<F>>() {
			@Override
			public void onSuccess(List<F> datas) {
				getView().display(datas);
				LoadingEvent.fire(AbstractListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(I dataId) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(dataId);
	}

	@Override
	public void onUpdateClick(I dataId) {
		revealPlace(getFormRouteToken(), TokenParameters.ID, String.valueOf(dataId));
	}
}