package com.telosys.gwtp.base.test.content.form;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter.BadgeFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BadgeResource;
import com.telosys.gwtp.base.shared.dto.BadgeDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BadgeFormPresenterTest extends BasePresenterTest {

	@Inject
	BadgeFormPresenter badgeFormPresenter;

	@Inject
	ResourceDelegate<BadgeResource> badgeService;

	@Inject
	BadgeFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.BADGE_FORM, TokenParameters.BADGE_NUMBER, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		badgeFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(BadgeDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		BadgeDto badge = new BadgeDto();
		badge.setBadgeNumber(1);
		badge.setAuthorizationLevel(1);
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.BADGE_FORM, TokenParameters.BADGE_NUMBER, badge.getBadgeNumber());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(badgeService).useResource(BadgeResource.class).and().succeed().withResult(badge).when().get(badge.getBadgeNumber());

		badgeFormPresenter.onReveal();
		// when
		verify(myView).load(badge);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BADGE_LIST).build();
		BadgeDto badge = new BadgeDto();
		badge.setBadgeNumber(1);
		badge.setAuthorizationLevel(1);
		givenDelegate(badgeService).useResource(BadgeResource.class).and().succeed().withResult((Void) null).when().create(badge);
		// When
		badgeFormPresenter.save(badge);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BADGE_LIST).build();
		BadgeDto badge = new BadgeDto();
		badge.setBadgeNumber(1);
		badge.setAuthorizationLevel(1);
		givenDelegate(badgeService).useResource(BadgeResource.class).and().succeed().withResult((Void) null).when().update(badge, badge.getBadgeNumber());
		// When
		badgeFormPresenter.setUpdateMode(true);
		badgeFormPresenter.save(badge);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}