package com.telosys.gwtp.base.client.application.layout.footer;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter.FooterProxy;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter.FooterView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.util.common.BasePresenter;
import com.telosys.gwtp.base.client.util.common.SimpleView;

public class FooterPresenter extends BasePresenter<FooterView, FooterProxy> {
	public interface FooterView extends SimpleView<FooterPresenter> {
	}

	@ProxyStandard
	public interface FooterProxy extends Proxy<FooterPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	FooterPresenter(EventBus eventBus, FooterView view, FooterProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, SLOT_CONTENT, placeManager, dispatcher);
		getView().setPresenter(this);
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
