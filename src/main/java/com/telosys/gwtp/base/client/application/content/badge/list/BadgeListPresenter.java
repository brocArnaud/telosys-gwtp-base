package com.telosys.gwtp.base.client.application.content.badge.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter.BadgeListProxy;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter.BadgeListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.BadgeResource;
import com.telosys.gwtp.base.shared.dto.BadgeDto;

public class BadgeListPresenter extends AbstractListPresenter<BadgeListProxy, BadgeListView, BadgeDto, BadgeResource> {

	public interface BadgeListView extends ListView<BadgeListPresenter, BadgeDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.BADGE_LIST)
	public interface BadgeListProxy extends ProxyPlace<BadgeListPresenter> {
	}

	@Inject
	BadgeListPresenter(EventBus eventBus, BadgeListView view, BadgeListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.BADGE_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.BADGE_NUMBER, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<BadgeDto>>() {
			@Override
			public void onSuccess(List<BadgeDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(BadgeListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(BadgeDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getBadgeNumber());
	}

	@Override
	public void onUpdateClick(BadgeDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.BADGE_NUMBER, data.getBadgeNumber());
	}

}