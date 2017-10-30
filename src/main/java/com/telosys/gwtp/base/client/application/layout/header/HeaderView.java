package com.telosys.gwtp.base.client.application.layout.header;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.MyView;

public class HeaderView extends ViewImpl implements MyView {

	interface Binder extends UiBinder<Widget, HeaderView> {
	}

	private HeaderPresenter presenter;

	@UiField
	HTMLPanel container;

	@UiField
	HTMLPanel spinner;

	@Inject
	HeaderView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(HeaderPresenter.SLOT_CONTENT, container);
	}

	@UiHandler(value = "player")
	public void onPlayerClick(ClickEvent event) {
		presenter.onPlayerClick();
	}

	@UiHandler(value = "team")
	public void onTeamClick(ClickEvent event) {
		presenter.onTeamClick();
	}

	@UiHandler(value = "logo")
	// @UiHandler(value = "home")
	public void onHomeClick(ClickEvent event) {
		presenter.onHomeClick();
	}

	@Override
	public void showSpinner(boolean visible) {
		spinner.setVisible(visible);
	}

	@Override
	public void setPresenter(HeaderPresenter presenter) {
		this.presenter = presenter;
	}
}
