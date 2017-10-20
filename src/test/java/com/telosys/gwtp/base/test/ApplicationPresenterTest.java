package com.telosys.gwtp.base.test;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {

	@Inject
	ApplicationPresenter applicationPresenter;

	@Test
	public void layoutSlotOk(HeaderPresenter headerPresenter, FooterPresenter footerPresenter, ApplicationPresenter.MyView myView) {
	}
}
