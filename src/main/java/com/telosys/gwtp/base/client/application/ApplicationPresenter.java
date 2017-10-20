package com.telosys.gwtp.base.client.application;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
	public interface MyView extends View {
	}

	@ProxyStandard
	interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	public static final NestedSlot SLOT_MAIN = new NestedSlot();

	public static final PermanentSlot<HeaderPresenter> SLOT_HEADER = new PermanentSlot<>();
	public static final PermanentSlot<FooterPresenter> SLOT_FOOTER = new PermanentSlot<>();

	@Inject
	private HeaderPresenter headerPresenter;

	@Inject
	private FooterPresenter footerPresenter;

	@Inject
	ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);
	}

	@Override
	public void onBind() {
		super.onBind();
		setInSlot(SLOT_HEADER, headerPresenter);
		setInSlot(SLOT_FOOTER, footerPresenter);
	}
}
