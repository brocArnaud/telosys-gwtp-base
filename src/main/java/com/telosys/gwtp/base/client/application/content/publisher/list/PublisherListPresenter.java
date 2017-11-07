package com.telosys.gwtp.base.client.application.content.publisher.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter.PublisherListProxy;
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter.PublisherListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.PublisherResource;
import com.telosys.gwtp.base.shared.dto.PublisherDto;

public class PublisherListPresenter extends AbstractListPresenter<PublisherListProxy, PublisherListView, PublisherDto, PublisherResource> {

	public interface PublisherListView extends ListView<PublisherListPresenter, PublisherDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.PUBLISHER_LIST)
	public interface PublisherListProxy extends ProxyPlace<PublisherListPresenter> {
	}

	@Inject
	PublisherListPresenter(EventBus eventBus, PublisherListView view, PublisherListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.PUBLISHER_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<PublisherDto>>() {
			@Override
			public void onSuccess(List<PublisherDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(PublisherListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(PublisherDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getCode());
	}

	@Override
	public void onUpdateClick(PublisherDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.CODE, data.getCode());
	}

}