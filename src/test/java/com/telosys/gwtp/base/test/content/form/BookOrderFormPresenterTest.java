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
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter.BookOrderFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BookOrderResource;
import com.telosys.gwtp.base.shared.api.resources.CustomerResource;
import com.telosys.gwtp.base.shared.api.resources.EmployeeResource;
import com.telosys.gwtp.base.shared.api.resources.ShopResource;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class BookOrderFormPresenterTest extends BasePresenterTest {

	@Inject
	BookOrderFormPresenter bookOrderFormPresenter;

	@Inject
	ResourceDelegate<BookOrderResource> bookOrderService;

	@Inject
	ResourceDelegate<ShopResource> shopService;
	@Inject
	ResourceDelegate<CustomerResource> customerService;
	@Inject
	ResourceDelegate<EmployeeResource> employeeService;

	@Inject
	BookOrderFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.BOOK_ORDER_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(customerService).useResource(CustomerResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult(getListItem()).when().listItems();

		bookOrderFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(BookOrderDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		BookOrderDto bookOrder = new BookOrderDto();
		bookOrder.setId(1);
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.BOOK_ORDER_FORM, TokenParameters.ID, bookOrder.getId());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(bookOrderService).useResource(BookOrderResource.class).and().succeed().withResult(bookOrder).when().get(bookOrder.getId());

		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(customerService).useResource(CustomerResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult(getListItem()).when().listItems();

		bookOrderFormPresenter.onReveal();
		// when
		verify(myView).load(bookOrder);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_ORDER_LIST).build();
		BookOrderDto bookOrder = new BookOrderDto();
		bookOrder.setId(1);
		givenDelegate(bookOrderService).useResource(BookOrderResource.class).and().succeed().withResult((Void) null).when().create(bookOrder);
		// When
		bookOrderFormPresenter.save(bookOrder);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.BOOK_ORDER_LIST).build();
		BookOrderDto bookOrder = new BookOrderDto();
		bookOrder.setId(1);
		givenDelegate(bookOrderService).useResource(BookOrderResource.class).and().succeed().withResult((Void) null).when().update(bookOrder, bookOrder.getId());
		// When
		bookOrderFormPresenter.setUpdateMode(true);
		bookOrderFormPresenter.save(bookOrder);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}