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
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter;
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter.CountryListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.dto.CountryDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class CountryListPresenterTest extends BasePresenterTest {

	@Inject
	CountryListPresenter countryListPresenter;

	@Inject
	ResourceDelegate<CountryResource> countryService;

	@Inject
	CountryListView myView;

	@Test
	public void onReveal() {
		// Given
		CountryDto country = mock(CountryDto.class);
		CountryDto country2 = mock(CountryDto.class);
		final List<CountryDto> countrys = new ArrayList<>();
		countrys.add(country);
		countrys.add(country2);
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(countrys).when().getAll();

		countryListPresenter.onReveal();
		// when
		verify(myView).display(countrys);
	}

	@Test
	public void onDeleteClick() {
		final CountryDto country = new CountryDto();
		country.setCode("1");
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult((Void) null).when().delete(country.getCode());
		countryListPresenter.onDeleteClick(country);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.COUNTRY_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		// When
		countryListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		CountryDto country = mock(CountryDto.class);
		given(country.getCode()).willReturn("1");
		PlaceRequest place = buildPlaceRequest(NameTokens.COUNTRY_FORM, TokenParameters.CODE, String.valueOf(country.getCode()));
		// When
		countryListPresenter.onUpdateClick(country);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}