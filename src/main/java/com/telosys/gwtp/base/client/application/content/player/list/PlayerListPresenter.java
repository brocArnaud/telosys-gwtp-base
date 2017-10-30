package com.telosys.gwtp.base.client.application.content.player.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.presenter.BasePresenter;
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerListPresenter extends BasePresenter<PlayerListPresenter.MyView, PlayerListPresenter.MyProxy> {

	public interface MyView extends View {
		void setPresenter(PlayerListPresenter presenter);

		void display(List<PlayerDto> players);
	}

	@ProxyStandard
	@NameToken(NameTokens.PLAYER_LIST)
	public interface MyProxy extends ProxyPlace<PlayerListPresenter> {
	}

	@Inject
	ResourceDelegate<PlayerResource> playerService;

	@Inject
	PlayerListPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		load();
	}

	public void onCreateClick() {
		revealPlace(NameTokens.PLAYER_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	private void load() {
		LoadingEvent.fire(this, true);
		playerService.withCallback(new CallBack<List<PlayerDto>>() {
			@Override
			public void onSuccess(List<PlayerDto> players) {
				getView().display(players);
				LoadingEvent.fire(PlayerListPresenter.this, false);
			}
		}).getAll();
	}

	public void onDeleteClick(PlayerDto player) {
		LoadingEvent.fire(this, true);
		playerService.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(player.getId());
	}

	public void onUpdateClick(PlayerDto player) {
		revealPlace(NameTokens.PLAYER_FORM, TokenParameters.ID, String.valueOf(player.getId()));
	}
}