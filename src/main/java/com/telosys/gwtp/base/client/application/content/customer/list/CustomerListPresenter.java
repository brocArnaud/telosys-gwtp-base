package com.telosys.gwtp.base.client.application.content.customer.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter.CustomerListProxy;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter.CustomerListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.CustomerResource;
import com.telosys.gwtp.base.shared.dto.CustomerDto;

public class CustomerListPresenter extends AbstractListPresenter<CustomerListProxy, CustomerListView, CustomerDto, CustomerResource> {

	public interface CustomerListView extends ListView<CustomerListPresenter, CustomerDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.CUSTOMER_LIST)
	public interface CustomerListProxy extends ProxyPlace<CustomerListPresenter> {
	}

	@Inject
	CustomerListPresenter(EventBus eventBus, CustomerListView view, CustomerListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.CUSTOMER_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<CustomerDto>>() {
			@Override
			public void onSuccess(List<CustomerDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(CustomerListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(CustomerDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getCode());
	}

	@Override
	public void onUpdateClick(CustomerDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, data.getCode());
	}

}