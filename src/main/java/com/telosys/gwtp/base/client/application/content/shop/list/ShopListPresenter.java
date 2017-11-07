package com.telosys.gwtp.base.client.application.content.shop.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter.ShopListProxy;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter.ShopListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.ShopResource;
import com.telosys.gwtp.base.shared.dto.ShopDto;

public class ShopListPresenter extends AbstractListPresenter<ShopListProxy, ShopListView, ShopDto, ShopResource> {

	public interface ShopListView extends ListView<ShopListPresenter, ShopDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.SHOP_LIST)
	public interface ShopListProxy extends ProxyPlace<ShopListPresenter> {
	}

	@Inject
	ShopListPresenter(EventBus eventBus, ShopListView view, ShopListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.SHOP_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<ShopDto>>() {
			@Override
			public void onSuccess(List<ShopDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(ShopListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(ShopDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getCode());
	}

	@Override
	public void onUpdateClick(ShopDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, data.getCode());
	}

}