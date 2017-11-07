package com.telosys.gwtp.base.client.application.content.shop.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter.ShopFormProxy;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter.ShopFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.ShopResource;
import com.telosys.gwtp.base.shared.dto.ShopDto;

public class ShopFormPresenter extends AbstractFormPresenter<ShopFormProxy, ShopFormView, ShopDto, ShopResource> {

	public interface ShopFormView extends FormView<ShopFormPresenter, ShopDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.SHOP_FORM)
	public interface ShopFormProxy extends ProxyPlace<ShopFormPresenter> {
	}

	@Inject
	ShopFormPresenter(EventBus eventBus, ShopFormView view, ShopFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public ShopDto newInstance() {
		return new ShopDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.SHOP_LIST;
	}

	@Override
	public void saveAction(ShopDto data) {
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
		final String id = getCurrentPlaceRequestId(TokenParameters.CODE);
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<ShopDto>() {
				@Override
				public void onSuccess(ShopDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(ShopFormPresenter.this, false);
				}
			}).get(id);
		} else {
			getView().load(newInstance());
		}
	}
}