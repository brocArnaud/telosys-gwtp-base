package com.telosys.gwtp.base.client.application.content.player.form.editor;

import java.util.List;
import java.util.stream.Collectors;

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import com.telosys.gwtp.base.shared.dto.ListItemDto;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerEditor extends Composite implements Editor<PlayerDto> {

	public interface Binder extends UiBinder<Form, PlayerEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@UiField
	protected TextBox name;

	@UiField(provided = true)
	protected ValueListBox<String> team;

	@Inject
	public PlayerEditor() {
		team = new ValueListBox<>(new AbstractRenderer<String>() {
			@Override
			public String render(String object) {
				return object == null ? "" : object;
			}
		});
		initWidget(BINDER.createAndBindUi(this));
	}

	public void setTeams(List<ListItemDto> teams) {
		team.setAcceptableValues(teams.stream().map(ListItemDto::getLabel).collect(Collectors.toList()));
	}
}