package com.telosys.gwtp.base.client.application.team.form;

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
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamFormPresenter extends Presenter<TeamFormPresenter.MyView, TeamFormPresenter.MyProxy> implements TeamFormUiHandlers {

	interface MyView extends View, HasUiHandlers<TeamFormUiHandlers> {
		void showNotification(boolean visible);

		void load(TeamDto team);

		void setUpdateMode(boolean updateMode);
	}

	@ProxyStandard
	@NameToken(NameTokens.TEAM_FORM)
	interface MyProxy extends ProxyPlace<TeamFormPresenter> {
	}

	@Inject
	RestDispatch dispatcher;

	@Inject
	TeamResource teamResource;

	@Inject
	private PlaceManager placeManager;

	private boolean updateMode = false;

	@Inject
	TeamFormPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
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

	private void load() {
		final String id = placeManager.getCurrentPlaceRequest().getParameter(TokenParameters.ID, TokenParameters.DEFAULT_ID);
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			dispatcher.execute(teamResource.get(Long.valueOf(id)), ManualRevealCallback.create(this, new AsyncCallback<TeamDto>() {

				@Override
				public void onSuccess(TeamDto team) {
					getView().load(team);
					getView().setUpdateMode(updateMode);
				}

				@Override
				public void onFailure(Throwable caught) {
					GWT.log("onFailure : " + caught);
				}
			}));
		} else {
			getView().load(new TeamDto());
		}

	}

	@Override
	public void save(TeamDto team) {
		if (updateMode) {
			dispatcher.execute(teamResource.update(team, team.getId()), ManualRevealCallback.create(this, new AsyncCallback<Void>() {

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
			dispatcher.execute(teamResource.create(team), ManualRevealCallback.create(this, new AsyncCallback<Void>() {

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
