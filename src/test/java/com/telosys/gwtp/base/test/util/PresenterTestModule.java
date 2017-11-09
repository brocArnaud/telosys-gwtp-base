package com.telosys.gwtp.base.test.util;

import org.jukito.JukitoModule;

import com.gwtplatform.mvp.client.AutobindDisable;

/**
 * Base module to use while testing presenters. {@link AutomockingModule} is
 * used. Your configuration module must extends this class.
 */
public abstract class PresenterTestModule extends JukitoModule {
	@Override
	protected void configureTest() {
		configurePresenterTest();

		bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
	}

	protected abstract void configurePresenterTest();
}
