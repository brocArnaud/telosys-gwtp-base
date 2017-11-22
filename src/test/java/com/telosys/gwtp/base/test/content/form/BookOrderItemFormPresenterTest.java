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
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter.BookOrderItemFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BookOrderItemResource;
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BookOrderItemFormPresenterTest extends BasePresenterTest {

	@Inject
	BookOrderItemFormPresenter bookOrderItemFormPresenter;

	@Inject
	ResourceDelegate<BookOrderItemResource> bookOrderItemService;

	@Inject
	BookOrderItemFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildRequest(TokenParameters.DEFAULT_ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		bookOrderItemFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(BookOrderItemDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		BookOrderItemDto bookOrderItem = new BookOrderItemDto();
		bookOrderItem.setBookOrderId(1);
		bookOrderItem.setBookId(1);
		PlaceRequest requestCreation = buildRequest(bookOrderItem.getBookOrderId(), bookOrderItem.getBookId());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(bookOrderItemService).useResource(BookOrderItemResource.class).and().succeed().withResult(bookOrderItem).when().get(bookOrderItem.getBookOrderId(),
				bookOrderItem.getBookId());

		bookOrderItemFormPresenter.onReveal();
		// when
		verify(myView).load(bookOrderItem);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_ORDER_ITEM_LIST).build();
		BookOrderItemDto bookOrderItem = new BookOrderItemDto();
		bookOrderItem.setBookOrderId(1);
		bookOrderItem.setBookId(1);
		bookOrderItem.setPrice(2.0);
		bookOrderItem.setQuantity(1);
		givenDelegate(bookOrderItemService).useResource(BookOrderItemResource.class).and().succeed().withResult((Void) null).when().create(bookOrderItem);
		// When
		bookOrderItemFormPresenter.save(bookOrderItem);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_ORDER_ITEM_LIST).build();
		BookOrderItemDto bookOrderItem = new BookOrderItemDto();
		bookOrderItem.setBookOrderId(1);
		bookOrderItem.setBookId(1);
		bookOrderItem.setPrice(2.0);
		bookOrderItem.setQuantity(1);
		givenDelegate(bookOrderItemService).useResource(BookOrderItemResource.class).and().succeed().withResult((Void) null).when().update(bookOrderItem,
				bookOrderItem.getBookOrderId(), bookOrderItem.getBookId());
		// When
		bookOrderItemFormPresenter.setUpdateMode(true);
		bookOrderItemFormPresenter.save(bookOrderItem);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	private PlaceRequest buildRequest(Integer bookOrderId, Integer bookId) {
		return buildRequest(String.valueOf(bookOrderId), String.valueOf(bookId));
	}

	private PlaceRequest buildRequest(String bookOrderId, String bookId) {
		return buildPlaceRequest(NameTokens.BOOK_ORDER_ITEM_FORM, TokenParameters.BOOK_ORDER_ID, bookOrderId, TokenParameters.BOOK_ID, bookId);
	}
}