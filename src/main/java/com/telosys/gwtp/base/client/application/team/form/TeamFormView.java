package com.telosys.gwtp.base.client.application.team.form;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamFormView extends ViewImpl implements TeamFormPresenter.MyView {
	interface Binder extends UiBinder<Widget, TeamFormView> {
	}

	@Inject
	TeamFormView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void display(TeamDto team) {
	}

}
