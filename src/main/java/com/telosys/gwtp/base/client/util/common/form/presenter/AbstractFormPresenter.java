package com.telosys.gwtp.base.client.util.common.form.presenter;

import org.apache.http.HttpStatus;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
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

	@Override
	public void load() {
		loadAction();
	}

	public abstract void saveAction(F data);

	@Override
	public void save(F data) {
		saveAction(data);
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

	/**
	 * Override this method in form presenter if you want specific message.
	 * Occurs when rest-api return a CONFLICT(409) httpStatus code.
	 */
	public void manageConflictError() {
		logger.severe("A conflict error occured");
		getView().showNotificationError(true, "The entity already exist !");
	}

	/**
	 * Override this method in form presenter if you want specific message.
	 * Occurs when rest-api return a NOT_FOUND(404) httpStatus code.
	 */
	public void manageNotFoundError() {
		logger.severe("A not found error occured");
		getView().showNotificationError(true, "The entity don't exist !");
	}

	@Override
	protected void manageError(Throwable caught, int statutCode) {
		LoadingEvent.fire(this, false);
		if (statutCode == HttpStatus.SC_CONFLICT) {
			manageConflictError();
		} else if (statutCode == HttpStatus.SC_NOT_FOUND) {
			manageNotFoundError();
		} else {
			logger.severe("An error occured dans form presenter : " + caught.getMessage());
			revealPlace(NameTokens.ERROR);
		}
	}
}