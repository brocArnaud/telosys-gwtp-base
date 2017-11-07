package com.telosys.gwtp.base.client.application.layout.header;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.common.BasePresenter;
import com.telosys.gwtp.base.client.util.common.SimpleView;

public class HeaderPresenter extends BasePresenter<HeaderPresenter.MyView, HeaderPresenter.MyProxy> implements LoadingEvent.LoadingHandler {
	public interface MyView extends SimpleView<HeaderPresenter> {
		void showSpinner(boolean visible);
	}

	@ProxyStandard
	public interface MyProxy extends Proxy<HeaderPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	HeaderPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, SLOT_CONTENT, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(LoadingEvent.TYPE, this);
	}

	public void onHomeClick() {
		revealPlace(NameTokens.HOME);
	}

	@Override
	public void onLoadingEvent(LoadingEvent event) {
		getView().showSpinner(event.isShow());
	}

	public void onAuthorClick() {
		revealPlace(NameTokens.AUTHOR_LIST);
	}

	public void onBadgeClick() {
		revealPlace(NameTokens.BADGE_LIST);
	}

	public void onBookClick() {
		revealPlace(NameTokens.BOOK_LIST);
	}

	public void onBookOrderClick() {
		revealPlace(NameTokens.BOOK_ORDER_LIST);
	}

	public void onBookOrderItemClick() {
		revealPlace(NameTokens.BOOK_ORDER_ITEM_LIST);
	}

	public void onCountryClick() {
		revealPlace(NameTokens.COUNTRY_LIST);
	}

	public void onCustomerClick() {
		revealPlace(NameTokens.CUSTOMER_LIST);
	}

	public void onEmployeeClick() {
		revealPlace(NameTokens.EMPLOYEE_LIST);
	}

	public void onEmployeeGroupClick() {
		revealPlace(NameTokens.EMPLOYEE_GROUP_LIST);
	}

	public void onPublisherClick() {
		revealPlace(NameTokens.PUBLISHER_LIST);
	}

	public void onReviewClick() {
		revealPlace(NameTokens.REVIEW_LIST);
	}

	public void onShopClick() {
		revealPlace(NameTokens.SHOP_LIST);
	}

	public void onSynopsisClick() {
		revealPlace(NameTokens.SYNOPSIS_LIST);
	}

	public void onWorkgroupClick() {
		revealPlace(NameTokens.WORKGROUP_LIST);
	}
}