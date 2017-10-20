package com.telosys.gwtp.base.test;

import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {

	@Inject
	ApplicationPresenter applicationPresenter; 

	@Ignore
	@Test
	public void layoutSlotOk(HeaderPresenter headerPresenter,FooterPresenter footerPresenter,ApplicationPresenter.MyView myView) { 
		verify(myView).setInSlot(ApplicationPresenter.SLOT_HEADER, headerPresenter);
		verify(myView).setInSlot(ApplicationPresenter.SLOT_FOOTER, footerPresenter);
	}
}
