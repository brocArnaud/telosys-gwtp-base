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
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter;
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter.PublisherListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.PublisherResource;
import com.telosys.gwtp.base.shared.dto.PublisherDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class PublisherListPresenterTest extends BasePresenterTest {

	@Inject
	PublisherListPresenter publisherListPresenter;

	@Inject
	ResourceDelegate<PublisherResource> publisherService;

	@Inject
	PublisherListView myView;

	@Test
	public void onReveal() {
		// Given
		PublisherDto publisher = mock(PublisherDto.class);
		PublisherDto publisher2 = mock(PublisherDto.class);
		final List<PublisherDto> publishers = new ArrayList<>();
		publishers.add(publisher);
		publishers.add(publisher2);
		givenDelegate(publisherService).useResource(PublisherResource.class).and().succeed().withResult(publishers).when().getAll();

		publisherListPresenter.onReveal();
		// when
		verify(myView).display(publishers);
	}

	@Test
	public void onDeleteClick() {
		final PublisherDto publisher = new PublisherDto();
		publisher.setCode(1);
		givenDelegate(publisherService).useResource(PublisherResource.class).and().succeed().withResult((Void) null).when().delete(publisher.getCode());
		publisherListPresenter.onDeleteClick(publisher);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.PUBLISHER_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		// When
		publisherListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		PublisherDto publisher = mock(PublisherDto.class);
		given(publisher.getCode()).willReturn(1);
		PlaceRequest place = buildPlaceRequest(NameTokens.PUBLISHER_FORM, TokenParameters.CODE, String.valueOf(publisher.getCode()));
		// When
		publisherListPresenter.onUpdateClick(publisher);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}