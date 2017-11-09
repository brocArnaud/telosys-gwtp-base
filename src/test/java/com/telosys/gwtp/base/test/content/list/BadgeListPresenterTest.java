package com.telosys.gwtp.base.test.content.list;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter.BadgeListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BadgeResource;
import com.telosys.gwtp.base.shared.dto.BadgeDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BadgeListPresenterTest extends BasePresenterTest {

	@Inject
	BadgeListPresenter badgeListPresenter;

	@Inject
	ResourceDelegate<BadgeResource> badgeService;

	@Inject
	BadgeListView myView;

	@Test
	public void onReveal() {
		// Given
		BadgeDto badge = mock(BadgeDto.class);
		BadgeDto badge2 = mock(BadgeDto.class);
		final List<BadgeDto> badges = new ArrayList<>();
		badges.add(badge);
		badges.add(badge2);
		givenDelegate(badgeService).useResource(BadgeResource.class).and().succeed().withResult(badges).when().getAll();

		badgeListPresenter.onReveal();
		// when
		verify(myView).display(badges);
	}

	@Test
	public void onDeleteClick() {
		final BadgeDto badge = new BadgeDto();
		badge.setBadgeNumber(1);
		badge.setAuthorizationLevel(1);
		badge.setEndOfValidity(new Date());
		givenDelegate(badgeService).useResource(BadgeResource.class).and().succeed().withResult((Void) null).when().delete(badge.getBadgeNumber());
		badgeListPresenter.onDeleteClick(badge);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.BADGE_FORM, TokenParameters.BADGE_NUMBER, TokenParameters.DEFAULT_ID);
		// When
		badgeListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		BadgeDto badge = mock(BadgeDto.class);
		given(badge.getBadgeNumber()).willReturn(1);
		PlaceRequest place = buildPlaceRequest(NameTokens.BADGE_FORM, TokenParameters.BADGE_NUMBER, String.valueOf(badge.getBadgeNumber()));
		// When
		badgeListPresenter.onUpdateClick(badge);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}