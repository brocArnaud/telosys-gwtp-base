package com.telosys.gwtp.base.client.application.layout.footer;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class FooterView extends ViewImpl implements FooterPresenter.MyView {

	interface Binder extends UiBinder<Widget, FooterView> {
	}
	
	private FooterPresenter presenter;

	@UiField
	HTMLPanel container;

	@Inject
	FooterView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(FooterPresenter.SLOT_CONTENT, container);
	}
	
	@UiHandler(value = "player")
	public void onPlayerClick(ClickEvent event) {
		presenter.onPlayerClick();
	}
	
	@UiHandler(value = "team")
	public void onTeamClick(ClickEvent event) {
		presenter.onTeamClick();
	}

	@Override
	public void setPresenter(FooterPresenter presenter) {
		this.presenter =presenter;
	}
}
