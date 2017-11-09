package com.telosys.gwtp.base.client.application.content.country.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter.CountryFormProxy;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter.CountryFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.dto.CountryDto;

public class CountryFormPresenter extends AbstractFormPresenter<CountryFormProxy, CountryFormView, CountryDto, CountryResource> {

	public interface CountryFormView extends FormView<CountryFormPresenter, CountryDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.COUNTRY_FORM)
	public interface CountryFormProxy extends ProxyPlace<CountryFormPresenter> {
	}

	@Inject
	CountryFormPresenter(EventBus eventBus, CountryFormView view, CountryFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.COUNTRY_LIST;
	}

	@Override
	public void saveAction(CountryDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getCode());
		} else {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).create(data);
		}
	}

	@Override
	public void loadAction() {
		final String code = getCurrentPlaceRequestId(TokenParameters.CODE);
		updateMode = !code.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<CountryDto>() {
				@Override
				public void onSuccess(CountryDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(CountryFormPresenter.this, false);
				}
			}).get(code);
		} else {
			getView().load(new CountryDto());
		}
	}
}