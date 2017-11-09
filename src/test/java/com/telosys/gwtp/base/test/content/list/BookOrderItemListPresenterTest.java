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
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter.BookOrderItemListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BookOrderItemResource;
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BookOrderItemListPresenterTest extends BasePresenterTest {

	@Inject
	BookOrderItemListPresenter bookOrderItemListPresenter;

	@Inject
	ResourceDelegate<BookOrderItemResource> bookOrderItemService;

	@Inject
	BookOrderItemListView myView;

	@Test
	public void onReveal() {
		// Given
		BookOrderItemDto bookOrderItem = mock(BookOrderItemDto.class);
		BookOrderItemDto bookOrderItem2 = mock(BookOrderItemDto.class);
		final List<BookOrderItemDto> bookOrderItems = new ArrayList<>();
		bookOrderItems.add(bookOrderItem);
		bookOrderItems.add(bookOrderItem2);
		givenDelegate(bookOrderItemService).useResource(BookOrderItemResource.class).and().succeed().withResult(bookOrderItems).when().findAll();

		bookOrderItemListPresenter.onReveal();
		// when
		verify(myView).display(bookOrderItems);
	}

	@Test
	public void onDeleteClick() {
		final BookOrderItemDto bookOrderItem = new BookOrderItemDto();
		bookOrderItem.setBookOrderId(1);
		bookOrderItem.setBookId(1);
		givenDelegate(bookOrderItemService).useResource(BookOrderItemResource.class).and().succeed().withResult((Void) null).when().delete(bookOrderItem.getBookOrderId(),
				bookOrderItem.getBookId());
		bookOrderItemListPresenter.onDeleteClick(bookOrderItem);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		PlaceRequest place = buildRequest(TokenParameters.DEFAULT_ID, TokenParameters.DEFAULT_ID);
		// When
		bookOrderItemListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		BookOrderItemDto bookOrderItem = mock(BookOrderItemDto.class);
		given(bookOrderItem.getBookOrderId()).willReturn(1);
		PlaceRequest place = buildRequest(bookOrderItem.getBookOrderId(), bookOrderItem.getBookId());
		// When
		bookOrderItemListPresenter.onUpdateClick(bookOrderItem);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	private PlaceRequest buildRequest(Integer bookOrderId, Integer bookId) {
		return buildRequest(String.valueOf(bookOrderId), String.valueOf(bookId));
	}

	private PlaceRequest buildRequest(String bookOrderId, String bookId) {
		return buildPlaceRequest(NameTokens.BOOK_ORDER_ITEM_FORM, TokenParameters.BOOK_ORDER_ID, bookOrderId, TokenParameters.BOOK_ID, bookId);
	}
}