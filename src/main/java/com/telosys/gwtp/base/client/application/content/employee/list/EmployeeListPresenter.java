package com.telosys.gwtp.base.client.application.content.employee.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter.EmployeeListProxy;
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter.EmployeeListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.EmployeeResource;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;

public class EmployeeListPresenter extends AbstractListPresenter<EmployeeListProxy, EmployeeListView, EmployeeDto, EmployeeResource> {

	public interface EmployeeListView extends ListView<EmployeeListPresenter, EmployeeDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.EMPLOYEE_LIST)
	public interface EmployeeListProxy extends ProxyPlace<EmployeeListPresenter> {
	}

	@Inject
	EmployeeListPresenter(EventBus eventBus, EmployeeListView view, EmployeeListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.EMPLOYEE_FORM;
	}
	
	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<EmployeeDto>>() {
			@Override
			public void onSuccess(List<EmployeeDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(EmployeeListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(EmployeeDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getCode());
	}

	@Override
	public void onUpdateClick(EmployeeDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, data.getCode());
	}

}