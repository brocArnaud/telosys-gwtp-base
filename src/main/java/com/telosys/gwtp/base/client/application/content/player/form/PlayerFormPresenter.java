package com.telosys.gwtp.base.client.application.content.player.form;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;

public class PlayerFormPresenter extends AbstractFormPresenter<PlayerFormPresenter.MyProxy, PlayerFormPresenter.MyView, PlayerDto, PlayerResource> {

	public interface MyView extends FormView<PlayerFormPresenter, PlayerDto> {
		void loadTeams(List<ListItemDto> teams);
	}

	@ProxyStandard
	@NameToken(NameTokens.PLAYER_FORM)
	public interface MyProxy extends ProxyPlace<PlayerFormPresenter> {
	}

	@Inject
	ResourceDelegate<TeamResource> teamService;

	@Inject
	PlayerFormPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		LoadingEvent.fire(this, true);
		teamService.withCallback(new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> teams) {
				getView().loadTeams(teams);
				LoadingEvent.fire(PlayerFormPresenter.this, false);
			}
		}).getTeamList();
	}

	@Override
	public PlayerDto beforeSave(PlayerDto player) {
		final String[] teamValue = player.getTeam().split("\\|");
		player.setTeam(teamValue[0].trim());
		return player;
	}

	@Override
	public PlayerDto newInstance() {
		return new PlayerDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.PLAYER_LIST;
	}

	@Override
	public void saveAction(PlayerDto data) {
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
			service.withCallback(new CallBack<PlayerDto>() {
				@Override
				public void onSuccess(PlayerDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(PlayerFormPresenter.this, false);
				}
			}).get(Long.valueOf(id));
		} else {
			getView().load(newInstance());
		}
	}
}