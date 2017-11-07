package com.telosys.gwtp.base.test.layout;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.MyView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;

@RunWith(JukitoRunner.class)
public class HeaderPresenterTest {

	@Inject
	HeaderPresenter headerPresenter;
	@Inject
	MyView view;

//	@Test
//	public void onPlayerListClick(PlaceManager placeManager) {
//		// Given
//		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.PLAYER_LIST).build();
//		// When
//		headerPresenter.onPlayerClick();
//		// Then
//		verify(placeManager).revealPlace(eq(placeRequest));
//	}
//
//	@Test
//	public void onTeamListClick(PlaceManager placeManager) {
//		// Given
//		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.TEAM_LIST).build();
//		// When
//		headerPresenter.onTeamClick();
//		// Then
//		verify(placeManager).revealPlace(eq(placeRequest));
//	}

	@Test
	public void onHomeListClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.HOME).build();
		// When
		headerPresenter.onHomeClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onLoadingEventTrue() {
		// When
		headerPresenter.onLoadingEvent(new LoadingEvent(true));
		// Then
		verify(view).showSpinner(true);
	}

	@Test
	public void onLoadingEventFalse() {
		// When
		headerPresenter.onLoadingEvent(new LoadingEvent(false));
		// Then
		verify(view).showSpinner(false);
	}

}
