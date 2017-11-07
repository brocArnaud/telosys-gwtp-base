package com.telosys.gwtp.base.client.application.content.player.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;

public class PlayerListPresenter extends AbstractListPresenter<PlayerListPresenter.MyProxy, PlayerListPresenter.MyView, PlayerDto, PlayerResource> {

	public interface MyView extends ListView<PlayerListPresenter, PlayerDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.PLAYER_LIST)
	public interface MyProxy extends ProxyPlace<PlayerListPresenter> {
	}

	@Inject
	PlayerListPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.PLAYER_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<PlayerDto>>() {
			@Override
			public void onSuccess(List<PlayerDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(PlayerListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(PlayerDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getId());
	}

	@Override
	public void onUpdateClick(PlayerDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.ID, String.valueOf(data.getId()));
	}

}