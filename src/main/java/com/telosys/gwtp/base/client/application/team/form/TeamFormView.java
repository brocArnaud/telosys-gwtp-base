package com.telosys.gwtp.base.client.application.team.form;

import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.PanelBody;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.telosys.gwtp.base.client.application.team.form.editor.TeamEditor;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamFormView extends ViewWithUiHandlers<TeamFormUiHandlers> implements TeamFormPresenter.MyView {
	interface Binder extends UiBinder<Widget, TeamFormView> {
	}

	interface TeamDriver extends SimpleBeanEditorDriver<TeamDto, TeamEditor> {
	}

	private static final TeamDriver DRIVER = GWT.create(TeamDriver.class);

	@Ignore
	@UiField
	protected PanelBody body;

	@Ignore
	@UiField
	Column notification;

	@Ignore
	@UiField
	Button create;

	@Ignore
	@UiField
	Label labelNotification;

	@Inject
	TeamFormView(Binder uiBinder, TeamEditor editor) {
		initWidget(uiBinder.createAndBindUi(this));
		body.add(editor);
		DRIVER.initialize(editor);
	}

	@UiHandler("create")
	public void onLoginClick(ClickEvent event) {
		TeamDto team = DRIVER.flush();

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<TeamDto>> violations = validator.validate(team, Default.class);
		if (!violations.isEmpty()) {
			DRIVER.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(violations));
		} else {
			getUiHandlers().save(team);
		}
	}

	@UiHandler("reset")
	public void onResetClick(ClickEvent event) {
		getUiHandlers().reset();
	}

	@Override
	public void showNotification(boolean visible) {
		notification.setVisible(visible);
	}

	@Override
	public void load(TeamDto team) {
		DRIVER.edit(team);
	}

	@Override
	public void setUpdateMode(boolean updateMode) {
		create.setText(updateMode ? "Save" : "Create");
		labelNotification.setText(updateMode ? "Team saved succesfully" : "Team created succesfully");
	}

}