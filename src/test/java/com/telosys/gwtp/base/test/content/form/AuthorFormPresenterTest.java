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
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter.AuthorFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.AuthorResource;
import com.telosys.gwtp.base.shared.dto.AuthorDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class AuthorFormPresenterTest extends BasePresenterTest {

	@Inject
	AuthorFormPresenter authorFormPresenter;

	@Inject
	ResourceDelegate<AuthorResource> authorService;

	@Inject
	AuthorFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.AUTHOR_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		authorFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(AuthorDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		AuthorDto author = new AuthorDto();
		author.setId(1);
		author.setFirstName("firstname");
		author.setLastName("lastName");
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.AUTHOR_FORM, TokenParameters.ID, author.getId());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(authorService).useResource(AuthorResource.class).and().succeed().withResult(author).when().get(author.getId());

		authorFormPresenter.onReveal();
		// when
		verify(myView).load(author);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.AUTHOR_LIST).build();
		AuthorDto author = new AuthorDto();
		author.setId(1);
		author.setFirstName("firstname");
		author.setLastName("lastName");
		givenDelegate(authorService).useResource(AuthorResource.class).and().succeed().withResult((Void) null).when().create(author);
		// When
		authorFormPresenter.save(author);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.AUTHOR_LIST).build();
		AuthorDto author = new AuthorDto();
		author.setId(1);
		author.setFirstName("firstname");
		author.setLastName("lastName");
		givenDelegate(authorService).useResource(AuthorResource.class).and().succeed().withResult((Void) null).when().update(author, author.getId());
		// When
		authorFormPresenter.setUpdateMode(true);
		authorFormPresenter.save(author);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}