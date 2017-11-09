package com.telosys.gwtp.base.test.layout;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter.FooterView;
import com.telosys.gwtp.base.client.place.NameTokens;

@RunWith(JukitoRunner.class)
public class FooterPresenterTest {

	@Inject
	FooterPresenter footerPresenter;
	@Inject
	FooterView view;

	@Test
	public void onReviewListClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.REVIEW_LIST).build();
		// When
		footerPresenter.onReviewClick();
		;
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onShopListClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.SHOP_LIST).build();
		// When
		footerPresenter.onShopClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSynopsisListClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.SYNOPSIS_LIST).build();
		// When
		footerPresenter.onSynopsisClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onWorkGroupListClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.WORKGROUP_LIST).build();
		// When
		footerPresenter.onWorkgroupClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}