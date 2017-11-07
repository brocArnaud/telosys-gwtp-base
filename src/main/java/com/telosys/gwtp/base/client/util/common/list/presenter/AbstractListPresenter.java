package com.telosys.gwtp.base.client.util.common.list.presenter;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.telosys.gwtp.base.client.util.common.BasePresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;

public abstract class AbstractListPresenter<P extends Proxy<?>, V extends ListView<? extends AbstractListPresenter<P, V, F, R>, F>, F, R> extends BasePresenter<V, P>
		implements ListPresenter<F> {

	@Inject
	protected ResourceDelegate<R> service;

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

	
	public abstract void onDeleteClick(F data);
	
	public abstract void load();
	
	public abstract void onCreateClick();

	public abstract void onUpdateClick(F data);
//	public void onCreateClick() {
//		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
//	}

//	public void load() {
//		LoadingEvent.fire(this, true);
//		service.withCallback(new CallBack<List<F>>() {
//			@Override
//			public void onSuccess(List<F> datas) {
//				getView().display(datas);
//				LoadingEvent.fire(AbstractListPresenter.this, false);
//			}
//		}).getAll();
//	}
//
//	@Override
//	public void onDeleteClick(F data) {
//		LoadingEvent.fire(this, true);
//		service.withCallback(new CallBack<Void>() {
//			@Override
//			public void onSuccess(Void nothing) {
//				load();
//			}
//		}).delete(data.getId());
//	}
//
//	@Override
//	public void onUpdateClick(F data) {
//		revealPlace(getFormRouteToken(), TokenParameters.ID, String.valueOf(data.getId()));
//	}
}