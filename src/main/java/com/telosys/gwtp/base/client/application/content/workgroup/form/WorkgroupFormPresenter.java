package com.telosys.gwtp.base.client.application.content.workgroup.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter.WorkgroupFormProxy;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter.WorkgroupFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.WorkgroupResource;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;

public class WorkgroupFormPresenter extends AbstractFormPresenter<WorkgroupFormProxy, WorkgroupFormView, WorkgroupDto, WorkgroupResource> {

	public interface WorkgroupFormView extends FormView<WorkgroupFormPresenter, WorkgroupDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.WORKGROUP_FORM)
	public interface WorkgroupFormProxy extends ProxyPlace<WorkgroupFormPresenter> {
	}

	@Inject
	WorkgroupFormPresenter(EventBus eventBus, WorkgroupFormView view, WorkgroupFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.WORKGROUP_LIST;
	}

	@Override
	public void saveAction(WorkgroupDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getId());
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
			service.withCallback(new CallBack<WorkgroupDto>() {
				@Override
				public void onSuccess(WorkgroupDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(WorkgroupFormPresenter.this, false);
				}
			}).get(Integer.valueOf(id));
		} else {
			getView().load(new WorkgroupDto());
		}
	}
}