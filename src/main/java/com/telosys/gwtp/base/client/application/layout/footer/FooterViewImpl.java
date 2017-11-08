package com.telosys.gwtp.base.client.application.layout.footer;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter.FooterView;

public class FooterViewImpl extends ViewImpl implements FooterView {

	interface Binder extends UiBinder<Widget, FooterViewImpl> {
	}

	@SuppressWarnings("unused")
	private FooterPresenter presenter;

	@UiField
	HTMLPanel container;

	@Inject
	FooterViewImpl(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(FooterPresenter.SLOT_CONTENT, container);
	}

	@Override
	public void setPresenter(FooterPresenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler(value = "review")
	public void onReviewClick(ClickEvent event) {
		presenter.onReviewClick();
	}

	@UiHandler(value = "shop")
	public void onShopClick(ClickEvent event) {
		presenter.onShopClick();
	}

	@UiHandler(value = "synopsis")
	public void onSynopsisClick(ClickEvent event) {
		presenter.onSynopsisClick();
	}

	@UiHandler(value = "workgroup")
	public void onWorkgroupClick(ClickEvent event) {
		presenter.onWorkgroupClick();
	}
}
