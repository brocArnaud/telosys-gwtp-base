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
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter.BookFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.AuthorResource;
import com.telosys.gwtp.base.shared.api.resources.BookResource;
import com.telosys.gwtp.base.shared.api.resources.PublisherResource;
import com.telosys.gwtp.base.shared.dto.book.BookDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BookFormPresenterTest extends BasePresenterTest {

	@Inject
	BookFormPresenter bookFormPresenter;

	@Inject
	ResourceDelegate<BookResource> bookService;
	@Inject
	ResourceDelegate<PublisherResource> publisherService;
	@Inject
	ResourceDelegate<AuthorResource> authorService;

	@Inject
	BookFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.BOOK_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(publisherService).useResource(PublisherResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(authorService).useResource(AuthorResource.class).and().succeed().withResult(getListItem()).when().listItems();

		bookFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(BookDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		BookDto book = new BookDto();
		book.setId(1);
		book.setAuthorId(1);
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.BOOK_FORM, TokenParameters.ID, book.getId());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(bookService).useResource(BookResource.class).and().succeed().withResult(book).when().get(book.getId());
		givenDelegate(publisherService).useResource(PublisherResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(authorService).useResource(AuthorResource.class).and().succeed().withResult(getListItem()).when().listItems();

		bookFormPresenter.onReveal();
		// when
		verify(myView).load(book);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_LIST).build();
		BookDto book = new BookDto();
		book.setId(1);
		book.setAuthorId(1);
		givenDelegate(bookService).useResource(BookResource.class).and().succeed().withResult((Void) null).when().create(book);
		// When
		bookFormPresenter.save(book);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_LIST).build();
		BookDto book = new BookDto();
		book.setId(1);
		book.setAuthorId(1);
		givenDelegate(bookService).useResource(BookResource.class).and().succeed().withResult((Void) null).when().update(book, book.getId());
		// When
		bookFormPresenter.setUpdateMode(true);
		bookFormPresenter.save(book);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}