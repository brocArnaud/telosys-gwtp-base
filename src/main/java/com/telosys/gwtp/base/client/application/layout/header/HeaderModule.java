package com.telosys.gwtp.base.client.application.layout.header;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class HeaderModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(HeaderPresenter.class, HeaderPresenter.MyView.class, HeaderView.class, HeaderPresenter.MyProxy.class);
	}
}
