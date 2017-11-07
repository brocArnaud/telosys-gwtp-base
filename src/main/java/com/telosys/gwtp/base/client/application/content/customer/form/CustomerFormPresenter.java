package com.telosys.gwtp.base.client.application.content.customer.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter.CustomerFormProxy;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter.CustomerFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.CustomerResource;
import com.telosys.gwtp.base.shared.dto.CustomerDto;

public class CustomerFormPresenter extends AbstractFormPresenter<CustomerFormProxy, CustomerFormView, CustomerDto, CustomerResource> {

	public interface CustomerFormView extends FormView<CustomerFormPresenter, CustomerDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.CUSTOMER_FORM)
	public interface CustomerFormProxy extends ProxyPlace<CustomerFormPresenter> {
	}

	@Inject
	CustomerFormPresenter(EventBus eventBus, CustomerFormView view, CustomerFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public CustomerDto newInstance() {
		return new CustomerDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.CUSTOMER_LIST;
	}

	@Override
	public void saveAction(CustomerDto data) {
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
		final String id = getCurrentPlaceRequestId();
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<CustomerDto>() {
				@Override
				public void onSuccess(CustomerDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(CustomerFormPresenter.this, false);
				}
			}).get(id);
		} else {
			getView().load(newInstance());
		}
	}
}