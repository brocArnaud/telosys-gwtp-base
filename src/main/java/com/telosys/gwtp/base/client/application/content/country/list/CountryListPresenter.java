package com.telosys.gwtp.base.client.application.content.country.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter.CountryListProxy;
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter.CountryListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.dto.CountryDto;

public class CountryListPresenter extends AbstractListPresenter<CountryListProxy, CountryListView, CountryDto, CountryResource> {

	public interface CountryListView extends ListView<CountryListPresenter, CountryDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.COUNTRY_LIST)
	public interface CountryListProxy extends ProxyPlace<CountryListPresenter> {
	}

	@Inject
	CountryListPresenter(EventBus eventBus, CountryListView view, CountryListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.COUNTRY_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<CountryDto>>() {
			@Override
			public void onSuccess(List<CountryDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(CountryListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(CountryDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getCode());
	}

	@Override
	public void onUpdateClick(CountryDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, data.getCode());
	}

}