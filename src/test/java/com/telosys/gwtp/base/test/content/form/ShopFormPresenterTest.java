package com.telosys.gwtp.base.test.content.form;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter.ShopFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.CountryResource;
import com.telosys.gwtp.base.shared.api.resources.EmployeeResource;
import com.telosys.gwtp.base.shared.api.resources.ShopResource;
import com.telosys.gwtp.base.shared.dto.ShopDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class ShopFormPresenterTest extends BasePresenterTest {

	@Inject
	ShopFormPresenter shopFormPresenter;
	@Inject
	ResourceDelegate<ShopResource> shopService;
	@Inject
	ResourceDelegate<CountryResource> countryService;
	@Inject
	ResourceDelegate<EmployeeResource> employeeService;
	@Inject
	ShopFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.SHOP_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult(getListItem()).when().listItems();

		shopFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(ShopDto.class));
		Assert.assertEquals(false, shopFormPresenter.isUpdateMode());
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		ShopDto shop = new ShopDto();
		shop.setCode("1");
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.SHOP_FORM, TokenParameters.CODE, shop.getCode());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult(shop).when().get(shop.getCode());
		givenDelegate(countryService).useResource(CountryResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult(getListItem()).when().listItems();

		shopFormPresenter.onReveal();
		// when
		verify(myView).load(shop);
		Assert.assertEquals(true, shopFormPresenter.isUpdateMode());
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.SHOP_LIST).build();
		ShopDto shop = new ShopDto();
		shop.setCode("1");
		shop.setCountryCode("FR");
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult((Void) null).when().create(shop);
		// When
		shopFormPresenter.save(shop);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.SHOP_LIST).build();
		ShopDto shop = new ShopDto();
		shop.setCode("1");
		shop.setCountryCode("FR");
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult((Void) null).when().update(shop, shop.getCode());
		// When
		shopFormPresenter.setUpdateMode(true);
		shopFormPresenter.save(shop);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}