package com.telosys.gwtp.base.client.application.player.form;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.ListItemDto;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerFormPresenter extends Presenter<PlayerFormPresenter.MyView, PlayerFormPresenter.MyProxy> implements PlayerFormUiHandlers {

	interface MyView extends View, HasUiHandlers<PlayerFormUiHandlers> {

		void showNotification(boolean visible);

		void load(PlayerDto team);

		void setUpdateMode(boolean updateMode);

		void loadTeams(List<ListItemDto> teams);
	}

	@ProxyStandard
	@NameToken(NameTokens.PLAYER_FORM)
	interface MyProxy extends ProxyPlace<PlayerFormPresenter> {
	}

	@Inject
	RestDispatch dispatcher;

	@Inject
	PlayerResource playerResource;

	@Inject
	TeamResource teamResource;

	@Inject
	private PlaceManager placeManager;

	private boolean updateMode = false;

	@Inject
	PlayerFormPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		// ensure notification panel not visible
		getView().showNotification(false);
		load();
	}

	@Override
	protected void onBind() {
		super.onBind();
		dispatcher.execute(teamResource.getTeamList(), ManualRevealCallback.create(this, new AsyncCallback<List<ListItemDto>>() {

			@Override
			public void onSuccess(List<ListItemDto> teams) {
				getView().loadTeams(teams);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("onFailure : " + caught);
			}
		}));
	}

	private void load() {
		final String id = placeManager.getCurrentPlaceRequest().getParameter(TokenParameters.ID, TokenParameters.DEFAULT_ID);
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			dispatcher.execute(playerResource.get(Long.valueOf(id)), ManualRevealCallback.create(this, new AsyncCallback<PlayerDto>() {

				@Override
				public void onSuccess(PlayerDto team) {
					getView().load(team);
					getView().setUpdateMode(updateMode);
				}

				@Override
				public void onFailure(Throwable caught) {
					GWT.log("onFailure : " + caught);
				}
			}));
		} else {
			getView().load(new PlayerDto());
		}

	}

	@Override
	public void save(PlayerDto player) {
		// FIXME cause to the existing behaviour we can have ListItemDto on
		// object player for represent team, we have to do this tricks before
		// send information to the api
		String[] teamValue = player.getTeam().split("\\|");
		player.setTeam(teamValue[0].trim());

		if (updateMode) {
			dispatcher.execute(playerResource.update(player, player.getId()), ManualRevealCallback.create(this, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void nothing) {
					getView().showNotification(true);
				}

				@Override
				public void onFailure(Throwable caught) {
					GWT.log("onFailure : " + caught);
				}
			}));
		} else {
			dispatcher.execute(playerResource.create(player), ManualRevealCallback.create(this, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void nothing) {
					getView().showNotification(true);
				}

				@Override
				public void onFailure(Throwable caught) {
					GWT.log("onFailure : " + caught);
				}

			}));
		}
	}

	@Override
	public void reset() {
		load();
	}
}
