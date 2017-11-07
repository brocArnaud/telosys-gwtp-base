package com.telosys.gwtp.base.client.application.content.workgroup.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter.WorkgroupListProxy;
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter.WorkgroupListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.AuthorResource;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;

public class WorkgroupListPresenter extends AbstractListPresenter<WorkgroupListProxy, WorkgroupListView, WorkgroupDto, AuthorResource> {

	public interface WorkgroupListView extends ListView<WorkgroupListPresenter, WorkgroupDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.WORKGROUP_LIST)
	public interface WorkgroupListProxy extends ProxyPlace<WorkgroupListPresenter> {
	}

	@Inject
	WorkgroupListPresenter(EventBus eventBus, WorkgroupListView view, WorkgroupListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.WORKGROUP_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<WorkgroupDto>>() {
			@Override
			public void onSuccess(List<WorkgroupDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(WorkgroupListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(WorkgroupDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(Integer.valueOf(data.getId()));
	}

	@Override
	public void onUpdateClick(WorkgroupDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.ID, String.valueOf(data.getId()));
	}

}