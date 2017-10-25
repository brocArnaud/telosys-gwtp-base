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
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.MyView;
import com.telosys.gwtp.base.client.place.NameTokens;

@RunWith(JukitoRunner.class)
public class FooterPresenterTest {

	@Inject
	FooterPresenter footerPresenter;
	@Inject
	MyView view;

	@Test
	public void onPlayerListClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.PLAYER_LIST).build();
		// When
		footerPresenter.onPlayerClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onTeamListClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.TEAM_LIST).build();
		// When
		footerPresenter.onTeamClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}