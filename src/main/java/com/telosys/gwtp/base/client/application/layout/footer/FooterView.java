package com.telosys.gwtp.base.client.application.layout.footer;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class FooterView extends ViewWithUiHandlers<FooterUiHandlers> implements FooterPresenter.MyView {

	interface Binder extends UiBinder<Widget, FooterView> {
	}

	@UiField
	HTMLPanel container;

	@Inject
	FooterView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(FooterPresenter.SLOT_CONTENT, container);
	}
	
	@UiHandler(value = "player")
	public void onPlayerClick(ClickEvent event) {
		getUiHandlers().onPlayerClick();
	}
	
	@UiHandler(value = "team")
	public void onTeamClick(ClickEvent event) {
		getUiHandlers().onTeamClick();
	}
}
