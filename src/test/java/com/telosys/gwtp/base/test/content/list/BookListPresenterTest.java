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
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter.BookListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BookResource;
import com.telosys.gwtp.base.shared.dto.book.BookDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BookListPresenterTest extends BasePresenterTest {

	@Inject
	BookListPresenter bookListPresenter;

	@Inject
	ResourceDelegate<BookResource> bookService;

	@Inject
	BookListView myView;

	@Test
	public void onReveal() {
		// Given
		BookDto book = mock(BookDto.class);
		BookDto book2 = mock(BookDto.class);
		final List<BookDto> books = new ArrayList<>();
		books.add(book);
		books.add(book2);
		givenDelegate(bookService).useResource(BookResource.class).and().succeed().withResult(books).when().getAll();

		bookListPresenter.onReveal();
		// when
		verify(myView).display(books);
	}

	@Test
	public void onDeleteClick() {
		final BookDto book = new BookDto();
		book.setId(1);
		givenDelegate(bookService).useResource(BookResource.class).and().succeed().withResult((Void) null).when().delete(book.getId());
		bookListPresenter.onDeleteClick(book);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.BOOK_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		// When
		bookListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		BookDto book = mock(BookDto.class);
		given(book.getId()).willReturn(1);
		PlaceRequest place = buildPlaceRequest(NameTokens.BOOK_FORM, TokenParameters.ID, String.valueOf(book.getId()));
		// When
		bookListPresenter.onUpdateClick(book);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}