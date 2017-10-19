package com.telosys.gwtp.base.client.application.player.form;

import java.util.ArrayList;
import java.util.List;
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
import com.telosys.gwtp.base.client.application.player.form.editor.PlayerEditor;
import com.telosys.gwtp.base.shared.dto.ListItemDto;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerFormView extends ViewWithUiHandlers<PlayerFormUiHandlers> implements PlayerFormPresenter.MyView {
	interface Binder extends UiBinder<Widget, PlayerFormView> {
	}

	interface PlayerDriver extends SimpleBeanEditorDriver<PlayerDto, PlayerEditor> {
	}

	private static final PlayerDriver DRIVER = GWT.create(PlayerDriver.class);

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

	PlayerEditor editor;

	@Inject
	PlayerFormView(Binder uiBinder, PlayerEditor editor) {
		initWidget(uiBinder.createAndBindUi(this));
		this.editor = editor;
		body.add(editor);
		DRIVER.initialize(editor);
	}

	@UiHandler("create")
	public void onLoginClick(ClickEvent event) {
		PlayerDto player = DRIVER.flush();

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PlayerDto>> violations = validator.validate(player, Default.class);
		if (violations.size() > 0) {
			DRIVER.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(violations));
		} else {
			getUiHandlers().save(player);
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
	public void load(PlayerDto team) {
		DRIVER.edit(team);
	}

	@Override
	public void setUpdateMode(boolean updateMode) {
		create.setText(updateMode ? "Save" : "Create");
		labelNotification.setText(updateMode ? "Player saved succesfully" : "Player created succesfully");
	}

	@Override
	public void loadTeams(List<ListItemDto> teams) {
		editor.setTeams(teams);
	}

}
