package com.telosys.gwtp.base.test.util;

import org.junit.Before;

import com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class BasePresenter {

	public static class Module extends PresenterTestModule {
		@Override
		protected void configurePresenterTest() {
		}
	}
	
	@Before
	public void initUtils() {
		DelegateTestUtils.init();
	}

	protected PlaceRequest buildPlaceRequest(String nameTokens) {
		return new PlaceRequest.Builder().nameToken(nameTokens).build();
	}

	protected PlaceRequest buildPlaceRequest(String nameTokens, String param, String value) {
		return new PlaceRequest.Builder().nameToken(nameTokens).with(param, value).build();
	}
}