package com.telosys.gwtp.base.client.application.content.author.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter.AuthorListProxy;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter.AuthorListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.AuthorResource;
import com.telosys.gwtp.base.shared.dto.AuthorDto;

public class AuthorListPresenter extends AbstractListPresenter<AuthorListProxy, AuthorListView, AuthorDto, AuthorResource> {

	public interface AuthorListView extends ListView<AuthorListPresenter, AuthorDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.AUTHOR_LIST)
	public interface AuthorListProxy extends ProxyPlace<AuthorListPresenter> {
	}

	@Inject
	AuthorListPresenter(EventBus eventBus, AuthorListView view, AuthorListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.AUTHOR_FORM;
	}
	
	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<AuthorDto>>() {
			@Override
			public void onSuccess(List<AuthorDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(AuthorListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(AuthorDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getId());
	}

	@Override
	public void onUpdateClick(AuthorDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.ID, String.valueOf(data.getId()));
	}

}