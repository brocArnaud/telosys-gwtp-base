package com.telosys.gwtp.base.test;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.test.util.PresenterTestModule;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {

	public static class Module extends PresenterTestModule {
		@Override
		protected void configurePresenterTest() {
		}
	}

	@Inject
	ApplicationPresenter applicationPresenter;

	@Test
	public void onBindTest() {
		applicationPresenter.onBind();
	}

}
