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
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.HeaderView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;

@RunWith(JukitoRunner.class)
public class HeaderPresenterTest {

	@Inject
	HeaderPresenter headerPresenter;
	@Inject
	HeaderView view;

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

	@Test
	public void onAuthorClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.AUTHOR_LIST).build();
		// When
		headerPresenter.onAuthorClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onBadgeClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BADGE_LIST).build();
		// When
		headerPresenter.onBadgeClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onBookClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_LIST).build();
		// When
		headerPresenter.onBookClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onBookOrderClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_ORDER_LIST).build();
		// When
		headerPresenter.onBookOrderClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onBookOrderItemClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_ORDER_ITEM_LIST).build();
		// When
		headerPresenter.onBookOrderItemClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onCountryClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.COUNTRY_LIST).build();
		// When
		headerPresenter.onCountryClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onCustomerClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.CUSTOMER_LIST).build();
		// When
		headerPresenter.onCustomerClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onEmployeeClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.EMPLOYEE_LIST).build();
		// When
		headerPresenter.onEmployeeClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onEmployeeGroupClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.EMPLOYEE_GROUP_LIST).build();
		// When
		headerPresenter.onEmployeeGroupClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onPublisherClick(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.PUBLISHER_LIST).build();
		// When
		headerPresenter.onPublisherClick();
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}
