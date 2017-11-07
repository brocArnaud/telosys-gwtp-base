package com.telosys.gwtp.base.client.application.layout.header;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.NavbarCollapse;

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

	@UiField
	NavbarCollapse collapse;

	@Inject
	HeaderView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(HeaderPresenter.SLOT_CONTENT, container);
	}

	@UiHandler(value = "logo")
	public void onHomeClick(ClickEvent event) {
		collapse.hide();
		presenter.onHomeClick();
	}

	@UiHandler(value = "author")
	public void onAuthorClick(ClickEvent event) {
		collapse.hide();
		presenter.onAuthorClick();
	}

	@UiHandler(value = "badge")
	public void onBadgeClick(ClickEvent event) {
		collapse.hide();
		presenter.onBadgeClick();
	}

	@UiHandler(value = "book")
	public void onBookClick(ClickEvent event) {
		collapse.hide();
		presenter.onBookClick();
	}

	@UiHandler(value = "bookOrder")
	public void onBookOrderClick(ClickEvent event) {
		collapse.hide();
		presenter.onBookOrderClick();
	}

	@UiHandler(value = "bookOrderItem")
	public void onBookOrderItemClick(ClickEvent event) {
		collapse.hide();
		presenter.onBookOrderItemClick();
	}

	@UiHandler(value = "country")
	public void onCountryClick(ClickEvent event) {
		collapse.hide();
		presenter.onCountryClick();
	}

	@UiHandler(value = "customer")
	public void onCustomerClick(ClickEvent event) {
		collapse.hide();
		presenter.onCustomerClick();
	}

	@UiHandler(value = "employee")
	public void onEmployeeClick(ClickEvent event) {
		collapse.hide();
		presenter.onEmployeeClick();
	}

	@UiHandler(value = "employeeGroup")
	public void onEmployeeGroupClick(ClickEvent event) {
		collapse.hide();
		presenter.onEmployeeGroupClick();
	}

	@UiHandler(value = "publisher")
	public void onPublisherClick(ClickEvent event) {
		collapse.hide();
		presenter.onPublisherClick();
	}

	@UiHandler(value = "review")
	public void onReviewClick(ClickEvent event) {
		collapse.hide();
		presenter.onReviewClick();
	}

	@UiHandler(value = "shop")
	public void onShopClick(ClickEvent event) {
		collapse.hide();
		presenter.onShopClick();
	}

	@UiHandler(value = "synopsis")
	public void onSynopsisClick(ClickEvent event) {
		collapse.hide();
		presenter.onSynopsisClick();
	}

//	@UiHandler(value = "workgroup")
//	public void onWorkgroupClick(ClickEvent event) {
//		collapse.hide();
//		presenter.onWorkgroupClick();
//	}

	@Override
	public void showSpinner(boolean visible) {
		spinner.setVisible(visible);
	}

	@Override
	public void setPresenter(HeaderPresenter presenter) {
		this.presenter = presenter;
	}
}
