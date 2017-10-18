package com.telosys.gwtp.base.client.application.layout.footer;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

public class FooterPresenter extends Presenter<FooterPresenter.MyView, FooterPresenter.MyProxy> {
	interface MyView extends View {
	}

	@ProxyStandard
	interface MyProxy extends Proxy<FooterPresenter> {
	}

	public static final NestedSlot SLOT_CONTENT = new NestedSlot();

	@Inject
	FooterPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, SLOT_CONTENT);
	}
}
