package com.telosys.gwtp.base.client.application.content.employee.form;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter.EmployeeFormProxy;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter.EmployeeFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.BadgeResource;
import com.telosys.gwtp.base.shared.api.resources.EmployeeResource;
import com.telosys.gwtp.base.shared.api.resources.ShopResource;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

public class EmployeeFormPresenter extends AbstractFormPresenter<EmployeeFormProxy, EmployeeFormView, EmployeeDto, EmployeeResource> {

	public interface EmployeeFormView extends FormView<EmployeeFormPresenter, EmployeeDto> {

		void loadShop(List<ListItemDto> items);

		void loadBadge(List<ListItemDto> items);
	}

	@ProxyStandard
	@NameToken(NameTokens.EMPLOYEE_FORM)
	public interface EmployeeFormProxy extends ProxyPlace<EmployeeFormPresenter> {
	}

	@Inject
	ResourceDelegate<ShopResource> shopService;
	@Inject
	ResourceDelegate<BadgeResource> badgeService;

	@Inject
	EmployeeFormPresenter(EventBus eventBus, EmployeeFormView view, EmployeeFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		LoadingEvent.fire(this, true);
		shopService.withCallback(new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> items) {
				getView().loadShop(items);
				LoadingEvent.fire(EmployeeFormPresenter.this, false);
			}
		}).listItems();
		badgeService.withCallback(new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> items) {
				getView().loadBadge(items);
				LoadingEvent.fire(EmployeeFormPresenter.this, false);
			}
		}).listItems();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.EMPLOYEE_LIST;
	}

	@Override
	public void saveAction(EmployeeDto data) {
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
			service.withCallback(new CallBack<EmployeeDto>() {
				@Override
				public void onSuccess(EmployeeDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(EmployeeFormPresenter.this, false);
				}
			}).get(code);
		} else {
			getView().load(new EmployeeDto());
		}
	}
}