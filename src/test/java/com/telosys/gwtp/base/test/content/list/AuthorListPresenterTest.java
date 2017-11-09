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
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter.AuthorListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.AuthorResource;
import com.telosys.gwtp.base.shared.dto.AuthorDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class AuthorListPresenterTest extends BasePresenterTest {

	@Inject
	AuthorListPresenter authorListPresenter;

	@Inject
	ResourceDelegate<AuthorResource> authorService;

	@Inject
	AuthorListView myView;

	@Test
	public void onReveal() {
		// Given
		AuthorDto author = mock(AuthorDto.class);
		AuthorDto author2 = mock(AuthorDto.class);
		final List<AuthorDto> authors = new ArrayList<>();
		authors.add(author);
		authors.add(author2);
		givenDelegate(authorService).useResource(AuthorResource.class).and().succeed().withResult(authors).when().getAll();

		authorListPresenter.onReveal();
		// when
		verify(myView).display(authors);
	}

	@Test
	public void onDeleteClick() {
		final AuthorDto author = new AuthorDto();
		author.setId(1);
		author.setFirstName("firstName");
		author.setLastName("lastName");
		givenDelegate(authorService).useResource(AuthorResource.class).and().succeed().withResult((Void) null).when().delete(author.getId());
		authorListPresenter.onDeleteClick(author);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.AUTHOR_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		// When
		authorListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		AuthorDto author = mock(AuthorDto.class);
		given(author.getId()).willReturn(1);
		PlaceRequest place = buildPlaceRequest(NameTokens.AUTHOR_FORM, TokenParameters.ID, String.valueOf(author.getId()));
		// When
		authorListPresenter.onUpdateClick(author);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}