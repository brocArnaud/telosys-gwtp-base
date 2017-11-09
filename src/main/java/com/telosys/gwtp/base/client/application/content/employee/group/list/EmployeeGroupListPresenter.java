package com.telosys.gwtp.base.client.application.content.employee.group.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter.EmployeeGroupListProxy;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter.EmployeeGroupListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.EmployeeGroupResource;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;

public class EmployeeGroupListPresenter extends AbstractListPresenter<EmployeeGroupListProxy, EmployeeGroupListView, EmployeeGroupDto, EmployeeGroupResource> {

	public interface EmployeeGroupListView extends ListView<EmployeeGroupListPresenter, EmployeeGroupDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.EMPLOYEE_GROUP_LIST)
	public interface EmployeeGroupListProxy extends ProxyPlace<EmployeeGroupListPresenter> {
	}

	@Inject
	EmployeeGroupListPresenter(EventBus eventBus, EmployeeGroupListView view, EmployeeGroupListProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.EMPLOYEE_GROUP_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.EMPLOYEE_CODE, TokenParameters.DEFAULT_ID, TokenParameters.GROUP_ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<EmployeeGroupDto>>() {
			@Override
			public void onSuccess(List<EmployeeGroupDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(EmployeeGroupListPresenter.this, false);
			}
		}).findAll();
	}

	@Override
	public void onDeleteClick(EmployeeGroupDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getEmployeeCode(), data.getGroupId());
	}

	@Override
	public void onUpdateClick(EmployeeGroupDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.EMPLOYEE_CODE, data.getEmployeeCode(), TokenParameters.GROUP_ID, String.valueOf(data.getGroupId()));
	}

}