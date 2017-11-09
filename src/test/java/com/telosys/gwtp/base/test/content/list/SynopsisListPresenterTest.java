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
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter;
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter.SynopsisListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.SynopsisResource;
import com.telosys.gwtp.base.shared.dto.SynopsisDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class SynopsisListPresenterTest extends BasePresenterTest {

	@Inject
	SynopsisListPresenter synopsisListPresenter;

	@Inject
	ResourceDelegate<SynopsisResource> synopsisService;

	@Inject
	SynopsisListView myView;

	@Test
	public void onReveal() {
		// Given
		SynopsisDto synopsis = mock(SynopsisDto.class);
		SynopsisDto synopsis2 = mock(SynopsisDto.class);
		final List<SynopsisDto> synopsiss = new ArrayList<>();
		synopsiss.add(synopsis);
		synopsiss.add(synopsis2);
		givenDelegate(synopsisService).useResource(SynopsisResource.class).and().succeed().withResult(synopsiss).when().getAll();

		synopsisListPresenter.onReveal();
		// when
		verify(myView).display(synopsiss);
	}

	@Test
	public void onDeleteClick() {
		final SynopsisDto synopsis = new SynopsisDto();
		synopsis.setBookId(1);
		givenDelegate(synopsisService).useResource(SynopsisResource.class).and().succeed().withResult((Void) null).when().delete(synopsis.getBookId());
		synopsisListPresenter.onDeleteClick(synopsis);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.SYNOPSIS_FORM, TokenParameters.BOOK_ID, TokenParameters.DEFAULT_ID);
		// When
		synopsisListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		SynopsisDto synopsis = mock(SynopsisDto.class);
		given(synopsis.getBookId()).willReturn(1);
		PlaceRequest place = buildPlaceRequest(NameTokens.SYNOPSIS_FORM, TokenParameters.BOOK_ID, String.valueOf(synopsis.getBookId()));
		// When
		synopsisListPresenter.onUpdateClick(synopsis);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}