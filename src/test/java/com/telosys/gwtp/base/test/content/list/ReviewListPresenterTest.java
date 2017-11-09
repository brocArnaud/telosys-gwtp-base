package com.telosys.gwtp.base.test.content.list;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter.ReviewListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.ReviewResource;
import com.telosys.gwtp.base.shared.dto.ReviewDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class ReviewListPresenterTest extends BasePresenterTest {

	@Inject
	ReviewListPresenter reviewListPresenter;

	@Inject
	ResourceDelegate<ReviewResource> reviewService;

	@Inject
	ReviewListView myView;

	@Test
	public void onReveal() {
		// Given
		ReviewDto review = mock(ReviewDto.class);
		ReviewDto review2 = mock(ReviewDto.class);
		final List<ReviewDto> reviews = new ArrayList<>();
		reviews.add(review);
		reviews.add(review2);
		givenDelegate(reviewService).useResource(ReviewResource.class).and().succeed().withResult(reviews).when().findAll();

		reviewListPresenter.onReveal();
		// when
		verify(myView).display(reviews);
	}

	@Test
	public void onDeleteClick() {
		final ReviewDto review = new ReviewDto();
		review.setCustomerCode("1");
		review.setBookId(1);
		givenDelegate(reviewService).useResource(ReviewResource.class).and().succeed().withResult((Void) null).when().delete(review.getCustomerCode(), review.getBookId());
		reviewListPresenter.onDeleteClick(review);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildRequest(TokenParameters.DEFAULT_ID, TokenParameters.DEFAULT_ID);
		// When
		reviewListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		ReviewDto review = mock(ReviewDto.class);
		given(review.getCustomerCode()).willReturn("1");
		given(review.getBookId()).willReturn(1);
		PlaceRequest place = buildRequest(review.getCustomerCode(), String.valueOf(review.getBookId()));
		// When
		reviewListPresenter.onUpdateClick(review);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	private PlaceRequest buildRequest(String customerCode, String bookId) {
		return buildPlaceRequest(NameTokens.REVIEW_FORM, TokenParameters.CUSTOMER_CODE, customerCode, TokenParameters.BOOK_ID, bookId);
	}
}