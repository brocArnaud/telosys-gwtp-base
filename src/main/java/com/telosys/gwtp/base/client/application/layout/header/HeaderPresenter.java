package com.telosys.gwtp.base.client.application.layout.header;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.HeaderProxy;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.HeaderView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.common.BasePresenter;
import com.telosys.gwtp.base.client.util.common.SimpleView;

public class HeaderPresenter extends BasePresenter<HeaderView, HeaderProxy> implements LoadingEvent.LoadingHandler {
	public interface HeaderView extends SimpleView<HeaderPresenter> {
		void showSpinner(boolean visible);
	}

	@ProxyStandard
	public interface HeaderProxy extends Proxy<HeaderPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	HeaderPresenter(EventBus eventBus, HeaderView view, HeaderProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy, SLOT_CONTENT, placeManager);
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

}