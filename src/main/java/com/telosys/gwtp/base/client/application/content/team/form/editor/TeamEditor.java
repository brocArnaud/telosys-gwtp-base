package com.telosys.gwtp.base.client.application.content.team.form.editor;

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import com.telosys.gwtp.base.shared.dto.TeamDto;

public class TeamEditor extends Composite implements Editor<TeamDto> {
	public interface Binder extends UiBinder<Form, TeamEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@UiField
	protected TextBox name;

	@Inject
	public TeamEditor() {
		initWidget(BINDER.createAndBindUi(this));
	}
}