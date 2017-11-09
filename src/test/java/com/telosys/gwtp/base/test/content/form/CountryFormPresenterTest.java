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
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter.CountryFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.dto.CountryDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class CountryFormPresenterTest extends BasePresenterTest {

	@Inject
	CountryFormPresenter countryFormPresenter;

	@Inject
	ResourceDelegate<CountryResource> countryService;

	@Inject
	CountryFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.COUNTRY_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		countryFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(CountryDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		CountryDto country = new CountryDto();
		country.setCode("1");
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.COUNTRY_FORM, TokenParameters.CODE, country.getCode());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(country).when().get(country.getCode());

		countryFormPresenter.onReveal();
		// when
		verify(myView).load(country);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.COUNTRY_LIST).build();
		CountryDto country = new CountryDto();
		country.setCode("1");
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult((Void) null).when().create(country);
		// When
		countryFormPresenter.save(country);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.COUNTRY_LIST).build();
		CountryDto country = new CountryDto();
		country.setCode("1");
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult((Void) null).when().update(country, country.getCode());
		// When
		countryFormPresenter.setUpdateMode(true);
		countryFormPresenter.save(country);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}