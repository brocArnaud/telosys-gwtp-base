package com.telosys.gwtp.base.client.application.content.employee.group.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter.EmployeeGroupFormProxy;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter.EmployeeGroupFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.EmployeeGroupResource;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;

public class EmployeeGroupFormPresenter extends AbstractFormPresenter<EmployeeGroupFormProxy, EmployeeGroupFormView, EmployeeGroupDto, EmployeeGroupResource> {

	public interface EmployeeGroupFormView extends FormView<EmployeeGroupFormPresenter, EmployeeGroupDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.EMPLOYEE_GROUP_FORM)
	public interface EmployeeGroupFormProxy extends ProxyPlace<EmployeeGroupFormPresenter> {
	}

	@Inject
	EmployeeGroupFormPresenter(EventBus eventBus, EmployeeGroupFormView view, EmployeeGroupFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public EmployeeGroupDto newInstance() {
		return new EmployeeGroupDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.EMPLOYEE_GROUP_LIST;
	}

	@Override
	public void saveAction(EmployeeGroupDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getEmployeeCode(), data.getGroupId());
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
		final String employeeCode = getCurrentPlaceRequestId(TokenParameters.EMPLOYEE_CODE);
		final String groupId = getCurrentPlaceRequestId(TokenParameters.GROUP_ID);
		updateMode = !employeeCode.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<EmployeeGroupDto>() {
				@Override
				public void onSuccess(EmployeeGroupDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(EmployeeGroupFormPresenter.this, false);
				}
			}).get(employeeCode, Integer.valueOf(groupId));
		} else {
			getView().load(newInstance());
		}
	}
}