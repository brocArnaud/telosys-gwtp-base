package com.telosys.gwtp.base.client.application.content.employee.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
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
import com.telosys.gwtp.base.shared.api.resources.EmployeeResource;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;

public class EmployeeFormPresenter extends AbstractFormPresenter<EmployeeFormProxy, EmployeeFormView, EmployeeDto, EmployeeResource> {

	public interface EmployeeFormView extends FormView<EmployeeFormPresenter, EmployeeDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.EMPLOYEE_FORM)
	public interface EmployeeFormProxy extends ProxyPlace<EmployeeFormPresenter> {
	}

	@Inject
	EmployeeFormPresenter(EventBus eventBus, EmployeeFormView view, EmployeeFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public EmployeeDto newInstance() {
		return new EmployeeDto();
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
			getView().load(newInstance());
		}
	}
}