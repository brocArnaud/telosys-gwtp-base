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
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter.ReviewFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.ReviewResource;
import com.telosys.gwtp.base.shared.dto.ReviewDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class ReviewFormPresenterTest extends BasePresenterTest {

	@Inject
	ReviewFormPresenter reviewFormPresenter;

	@Inject
	ResourceDelegate<ReviewResource> reviewService;

	@Inject
	ReviewFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildRequest(TokenParameters.DEFAULT_ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		reviewFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(ReviewDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		ReviewDto review = new ReviewDto();
		review.setCustomerCode("1");
		review.setBookId(1);
		PlaceRequest requestCreation = buildRequest(review.getCustomerCode(), String.valueOf(review.getBookId()));
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(reviewService).useResource(ReviewResource.class).and().succeed().withResult(review).when().get(review.getCustomerCode(), review.getBookId());

		reviewFormPresenter.onReveal();
		// when
		verify(myView).load(review);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.REVIEW_LIST).build();
		ReviewDto review = new ReviewDto();
		review.setCustomerCode("1");
		review.setBookId(1);
		givenDelegate(reviewService).useResource(ReviewResource.class).and().succeed().withResult((Void) null).when().create(review);
		// When
		reviewFormPresenter.save(review);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.REVIEW_LIST).build();
		ReviewDto review = new ReviewDto();
		review.setCustomerCode("1");
		review.setBookId(1);
		givenDelegate(reviewService).useResource(ReviewResource.class).and().succeed().withResult((Void) null).when().update(review, review.getCustomerCode(), review.getBookId());
		// When
		reviewFormPresenter.setUpdateMode(true);
		reviewFormPresenter.save(review);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	private PlaceRequest buildRequest(String customerCode, String bookId) {
		return buildPlaceRequest(NameTokens.REVIEW_FORM, TokenParameters.CUSTOMER_CODE, customerCode, TokenParameters.BOOK_ID, bookId);
	}
}