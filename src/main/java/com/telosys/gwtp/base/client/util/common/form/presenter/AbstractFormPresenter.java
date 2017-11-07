package com.telosys.gwtp.base.client.util.common.form.presenter;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.util.common.BasePresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;

public abstract class AbstractFormPresenter<P extends Proxy<?>, V extends FormView<? extends AbstractFormPresenter<P, V, F, R>, F>, F, R> extends BasePresenter<V, P>
		implements FormPresenter<F> {

	@Inject
	protected ResourceDelegate<R> service;
	protected boolean updateMode = false;

	public AbstractFormPresenter(final EventBus eventBus, final V view, final P proxy, final GwtEvent.Type<RevealContentHandler<?>> pslot, PlaceManager placeManager,
			RestDispatch dispatcher) {
		super(eventBus, view, proxy, pslot, placeManager, dispatcher);
	}

	public AbstractFormPresenter(EventBus eventBus, V view, P proxy, RevealType revealType, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, revealType, placeManager, dispatcher);
	}

	/**
	 * Return the root tokens for the page after successfully creation/update.
	 */
	public abstract String getListRouteToken();

	/** Give a new empty F object. */
	public abstract F newInstance();

	/**
	 * Override this method if special operation need to be done on data before
	 * call server.
	 */
	public F beforeSave(F data) {
		return data;
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getView().showNotification(false);
		load();
	}
	

	public abstract void loadAction();

	@SuppressWarnings("unchecked")
	@Override
	public void load() {
		loadAction();
//		final String id = getCurrentPlaceRequestId();
//		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
//		if (updateMode) {
//			LoadingEvent.fire(this, true);
//			service.withCallback(new CallBack<F>() {
//				@Override
//				public void onSuccess(F data) {
//					getView().load(data);
//					getView().setUpdateMode(updateMode);
//					LoadingEvent.fire(AbstractFormPresenter.this, false);
//				}
//			}).get((I) Long.valueOf(id));
//		} else {
//			getView().load(newInstance());
//		}
	}

	public abstract void saveAction(F data);

	@Override
	public void save(F data) {
		saveAction(data);
		// beforeSave(data);
		// LoadingEvent.fire(this, true);
		// if (updateMode) {
		// service.withCallback(new CallBack<Void>() {
		// @Override
		// public void onSuccess(Void nothing) {
		// success();
		// }
		// }).update(data, data.getId());
		// } else {
		// service.withCallback(new CallBack<Void>() {
		// @Override
		// public void onSuccess(Void nothing) {
		// success();
		// }
		// }).create(data);
		// }
	}

	protected void success() {
		getView().showNotification(true);
		LoadingEvent.fire(AbstractFormPresenter.this, false);
		revealPlace(getListRouteToken());
	}

	@Override
	public void reset() {
		load();
	}
}