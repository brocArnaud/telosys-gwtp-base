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
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter.BookOrderListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BookOrderResource;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BookOrderListPresenterTest extends BasePresenterTest {

	@Inject
	BookOrderListPresenter bookOrderListPresenter;

	@Inject
	ResourceDelegate<BookOrderResource> bookOrderService;

	@Inject
	BookOrderListView myView;

	@Test
	public void onReveal() {
		// Given
		BookOrderDto bookOrder = mock(BookOrderDto.class);
		BookOrderDto bookOrder2 = mock(BookOrderDto.class);
		final List<BookOrderDto> bookOrders = new ArrayList<>();
		bookOrders.add(bookOrder);
		bookOrders.add(bookOrder2);
		givenDelegate(bookOrderService).useResource(BookOrderResource.class).and().succeed().withResult(bookOrders).when().getAll();

		bookOrderListPresenter.onReveal();
		// when
		verify(myView).display(bookOrders);
	}

	@Test
	public void onDeleteClick() {
		final BookOrderDto bookOrder = new BookOrderDto();
		bookOrder.setId(1);
		givenDelegate(bookOrderService).useResource(BookOrderResource.class).and().succeed().withResult((Void) null).when().delete(bookOrder.getId());
		bookOrderListPresenter.onDeleteClick(bookOrder);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.BOOK_ORDER_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		// When
		bookOrderListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		BookOrderDto bookOrder = mock(BookOrderDto.class);
		given(bookOrder.getId()).willReturn(1);
		PlaceRequest place = buildPlaceRequest(NameTokens.BOOK_ORDER_FORM, TokenParameters.ID, String.valueOf(bookOrder.getId()));
		// When
		bookOrderListPresenter.onUpdateClick(bookOrder);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}