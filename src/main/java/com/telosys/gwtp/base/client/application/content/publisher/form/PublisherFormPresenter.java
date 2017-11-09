package com.telosys.gwtp.base.client.application.content.publisher.form;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter.PublisherFormProxy;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter.PublisherFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.api.resources.PublisherResource;
import com.telosys.gwtp.base.shared.dto.PublisherDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

public class PublisherFormPresenter extends AbstractFormPresenter<PublisherFormProxy, PublisherFormView, PublisherDto, PublisherResource> {

	public interface PublisherFormView extends FormView<PublisherFormPresenter, PublisherDto> {

		void loadCountry(List<ListItemDto> items);
	}

	@ProxyStandard
	@NameToken(NameTokens.PUBLISHER_FORM)
	public interface PublisherFormProxy extends ProxyPlace<PublisherFormPresenter> {
	}

	@Inject
	ResourceDelegate<CountryResource> countryService;

	@Inject
	PublisherFormPresenter(EventBus eventBus, PublisherFormView view, PublisherFormProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager);
		getView().setPresenter(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		countryService.withCallback(new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> items) {
				getView().loadCountry(items);
			}
		}).listItems();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.PUBLISHER_LIST;
	}

	@Override
	public void saveAction(PublisherDto data) {
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
			service.withCallback(new CallBack<PublisherDto>() {
				@Override
				public void onSuccess(PublisherDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(PublisherFormPresenter.this, false);
				}
			}).get(Integer.valueOf(code));
		} else {
			getView().load(new PublisherDto());
		}
	}
}