package com.telosys.gwtp.base.client.application.content.synopsis.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter.SynopsisFormProxy;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter.SynopsisFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.SynopsisResource;
import com.telosys.gwtp.base.shared.dto.SynopsisDto;

public class SynopsisFormPresenter extends AbstractFormPresenter<SynopsisFormProxy, SynopsisFormView, SynopsisDto, SynopsisResource> {

	public interface SynopsisFormView extends FormView<SynopsisFormPresenter, SynopsisDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.SYNOPSIS_FORM)
	public interface SynopsisFormProxy extends ProxyPlace<SynopsisFormPresenter> {
	}

	@Inject
	SynopsisFormPresenter(EventBus eventBus, SynopsisFormView view, SynopsisFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public SynopsisDto newInstance() {
		return new SynopsisDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.SYNOPSIS_LIST;
	}

	@Override
	public void saveAction(SynopsisDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getBookId());
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
		final String id = getCurrentPlaceRequestId(TokenParameters.BOOK_ID);
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<SynopsisDto>() {
				@Override
				public void onSuccess(SynopsisDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(SynopsisFormPresenter.this, false);
				}
			}).get(Integer.valueOf(id));
		} else {
			getView().load(newInstance());
		}
	}
}