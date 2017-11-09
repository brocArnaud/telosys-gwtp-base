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
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter.SynopsisFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.SynopsisResource;
import com.telosys.gwtp.base.shared.dto.SynopsisDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class SynopsisFormPresenterTest extends BasePresenterTest {

	@Inject
	SynopsisFormPresenter synopsisFormPresenter;

	@Inject
	ResourceDelegate<SynopsisResource> synopsisService;

	@Inject
	SynopsisFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.SYNOPSIS_FORM, TokenParameters.BOOK_ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		synopsisFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(SynopsisDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		SynopsisDto synopsis = new SynopsisDto();
		synopsis.setBookId(1);
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.SYNOPSIS_FORM, TokenParameters.BOOK_ID, synopsis.getBookId());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(synopsisService).useResource(SynopsisResource.class).and().succeed().withResult(synopsis).when().get(synopsis.getBookId());

		synopsisFormPresenter.onReveal();
		// when
		verify(myView).load(synopsis);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.SYNOPSIS_LIST).build();
		SynopsisDto synopsis = new SynopsisDto();
		synopsis.setBookId(1);
		givenDelegate(synopsisService).useResource(SynopsisResource.class).and().succeed().withResult((Void) null).when().create(synopsis);
		// When
		synopsisFormPresenter.save(synopsis);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.SYNOPSIS_LIST).build();
		SynopsisDto synopsis = new SynopsisDto();
		synopsis.setBookId(1);
		givenDelegate(synopsisService).useResource(SynopsisResource.class).and().succeed().withResult((Void) null).when().update(synopsis, synopsis.getBookId());
		// When
		synopsisFormPresenter.setUpdateMode(true);
		synopsisFormPresenter.save(synopsis);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}