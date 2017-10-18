package com.telosys.gwtp.base.client.application.player.form;

import java.util.List;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.shared.dto.PlayerDto;

public class PlayerFormView extends ViewImpl implements PlayerFormPresenter.MyView {
	interface Binder extends UiBinder<Widget, PlayerFormView> {
	}

	@Inject
	PlayerFormView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void display(PlayerDto player) {
	}

}
