package com.telosys.gwtp.base.client.application.content.team.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.team.TeamDto;

public class TeamFormPresenter extends AbstractFormPresenter<TeamFormPresenter.MyProxy, TeamFormPresenter.MyView, TeamDto, Long, TeamResource> {

	public interface MyView extends FormView<TeamFormPresenter, TeamDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.TEAM_FORM)
	public interface MyProxy extends ProxyPlace<TeamFormPresenter> {
	}

	@Inject
	TeamFormPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public TeamDto newInstance() {
		return new TeamDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.TEAM_LIST;
	}
}