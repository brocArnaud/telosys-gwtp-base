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
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter.PublisherFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.api.resources.PublisherResource;
import com.telosys.gwtp.base.shared.dto.PublisherDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class PublisherFormPresenterTest extends BasePresenterTest {

	@Inject
	PublisherFormPresenter publisherFormPresenter;

	@Inject
	ResourceDelegate<PublisherResource> publisherService;
	@Inject
	ResourceDelegate<CountryResource> countryService;

	@Inject
	PublisherFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.PUBLISHER_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(getListItem()).when().listItems();

		publisherFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(PublisherDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		PublisherDto publisher = new PublisherDto();
		publisher.setCode(1);
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.PUBLISHER_FORM, TokenParameters.CODE, publisher.getCode());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(publisherService).useResource(PublisherResource.class).and().succeed().withResult(publisher).when().get(publisher.getCode());
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(getListItem()).when().listItems();

		publisherFormPresenter.onReveal();
		// when
		verify(myView).load(publisher);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.PUBLISHER_LIST).build();
		PublisherDto publisher = new PublisherDto();
		publisher.setCode(1);
		publisher.setCountryCode("FR");
		givenDelegate(publisherService).useResource(PublisherResource.class).and().succeed().withResult((Void) null).when().create(publisher);
		// When
		publisherFormPresenter.save(publisher);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.PUBLISHER_LIST).build();
		PublisherDto publisher = new PublisherDto();
		publisher.setCode(1);
		publisher.setCountryCode("FR");
		givenDelegate(publisherService).useResource(PublisherResource.class).and().succeed().withResult((Void) null).when().update(publisher, publisher.getCode());
		// When
		publisherFormPresenter.setUpdateMode(true);
		publisherFormPresenter.save(publisher);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}