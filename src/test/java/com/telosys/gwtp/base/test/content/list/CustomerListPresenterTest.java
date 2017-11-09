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
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter.CustomerListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.CustomerResource;
import com.telosys.gwtp.base.shared.dto.CustomerDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class CustomerListPresenterTest extends BasePresenterTest {

	@Inject
	CustomerListPresenter customerListPresenter;

	@Inject
	ResourceDelegate<CustomerResource> customerService;

	@Inject
	CustomerListView myView;

	@Test
	public void onReveal() {
		// Given
		CustomerDto customer = mock(CustomerDto.class);
		CustomerDto customer2 = mock(CustomerDto.class);
		final List<CustomerDto> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer2);
		givenDelegate(customerService).useResource(CustomerResource.class).and().succeed().withResult(customers).when().getAll();

		customerListPresenter.onReveal();
		// when
		verify(myView).display(customers);
	}

	@Test
	public void onDeleteClick() {
		final CustomerDto customer = new CustomerDto();
		customer.setCode("1");
		givenDelegate(customerService).useResource(CustomerResource.class).and().succeed().withResult((Void) null).when().delete(customer.getCode());
		customerListPresenter.onDeleteClick(customer);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.CUSTOMER_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		// When
		customerListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		CustomerDto customer = mock(CustomerDto.class);
		given(customer.getCode()).willReturn("1");
		PlaceRequest place = buildPlaceRequest(NameTokens.CUSTOMER_FORM, TokenParameters.CODE, String.valueOf(customer.getCode()));
		// When
		customerListPresenter.onUpdateClick(customer);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}