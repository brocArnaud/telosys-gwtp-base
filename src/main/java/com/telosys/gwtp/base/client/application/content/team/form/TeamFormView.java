package com.telosys.gwtp.base.client.application.content.team.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.team.TeamDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class TeamFormView extends AbstractFormView<TeamFormPresenter, TeamDto, Long> implements TeamFormPresenter.MyView {
	interface Binder extends UiBinder<Widget, TeamFormView> {
	}

	interface TeamDriver extends BeanValidationEditorDriver<TeamDto, TeamFormView> {
	}

	@UiField
	protected TextBox name;

	@Inject
	TeamFormView(Binder uiBinder, final TeamDriver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}