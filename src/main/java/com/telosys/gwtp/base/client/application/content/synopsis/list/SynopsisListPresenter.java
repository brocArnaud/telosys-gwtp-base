package com.telosys.gwtp.base.client.application.content.synopsis.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter.SynopsisListProxy;
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter.SynopsisListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.SynopsisResource;
import com.telosys.gwtp.base.shared.dto.SynopsisDto;

public class SynopsisListPresenter extends AbstractListPresenter<SynopsisListProxy, SynopsisListView, SynopsisDto, SynopsisResource> {

	public interface SynopsisListView extends ListView<SynopsisListPresenter, SynopsisDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.SYNOPSIS_LIST)
	public interface SynopsisListProxy extends ProxyPlace<SynopsisListPresenter> {
	}

	@Inject
	SynopsisListPresenter(EventBus eventBus, SynopsisListView view, SynopsisListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.SYNOPSIS_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.BOOK_ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<SynopsisDto>>() {
			@Override
			public void onSuccess(List<SynopsisDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(SynopsisListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(SynopsisDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getBookId());
	}

	@Override
	public void onUpdateClick(SynopsisDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.BOOK_ID, data.getBookId());
	}

}