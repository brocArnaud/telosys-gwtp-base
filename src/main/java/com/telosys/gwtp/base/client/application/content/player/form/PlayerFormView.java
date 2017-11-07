package com.telosys.gwtp.base.client.application.content.player.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class PlayerFormView extends AbstractFormView<PlayerFormPresenter, PlayerDto> implements PlayerFormPresenter.MyView {
	interface Binder extends UiBinder<Widget, PlayerFormView> {
	}

	interface PlayerDriver extends BeanValidationEditorDriver<PlayerDto, PlayerFormView> {
	}

	@UiField
	protected TextBox name;

	@UiField(provided = true)
	protected ValueListBox<String> team;

	@Inject
	PlayerFormView(Binder uiBinder, final PlayerDriver driver) {
		super(driver);
		team = new ValueListBox<>(new AbstractRenderer<String>() {
			@Override
			public String render(String object) {
				return object == null ? "" : object;
			}
		});
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}

	@Override
	public void loadTeams(List<ListItemDto> teams) {
		team.setAcceptableValues(teams.stream().map(ListItemDto::getLabel).collect(Collectors.toList()));
	}
}