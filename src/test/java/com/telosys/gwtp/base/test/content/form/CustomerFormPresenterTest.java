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
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter.CustomerFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.api.resources.CustomerResource;
import com.telosys.gwtp.base.shared.dto.CustomerDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class CustomerFormPresenterTest extends BasePresenterTest {

	@Inject
	CustomerFormPresenter customerFormPresenter;

	@Inject
	ResourceDelegate<CustomerResource> customerService;
	@Inject
	ResourceDelegate<CountryResource> countryService;

	@Inject
	CustomerFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.CUSTOMER_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(getListItem()).when().listItems();

		customerFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(CustomerDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		CustomerDto customer = new CustomerDto();
		customer.setCode("1");
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.CUSTOMER_FORM, TokenParameters.CODE, customer.getCode());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(customerService).useResource(CustomerResource.class).and().succeed().withResult(customer).when().get(customer.getCode());
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(getListItem()).when().listItems();

		customerFormPresenter.onReveal();
		// when
		verify(myView).load(customer);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.CUSTOMER_LIST).build();
		CustomerDto customer = new CustomerDto();
		customer.setCode("1");
		givenDelegate(customerService).useResource(CustomerResource.class).and().succeed().withResult((Void) null).when().create(customer);
		// When
		customerFormPresenter.save(customer);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.CUSTOMER_LIST).build();
		CustomerDto customer = new CustomerDto();
		customer.setCode("1");
		givenDelegate(customerService).useResource(CustomerResource.class).and().succeed().withResult((Void) null).when().update(customer, customer.getCode());
		// When
		customerFormPresenter.setUpdateMode(true);
		customerFormPresenter.save(customer);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}