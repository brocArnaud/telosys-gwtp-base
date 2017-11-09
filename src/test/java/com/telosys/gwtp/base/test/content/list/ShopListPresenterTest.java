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
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter.ShopListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.ShopResource;
import com.telosys.gwtp.base.shared.dto.ShopDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class ShopListPresenterTest extends BasePresenterTest {

	@Inject
	ShopListPresenter shopListPresenter;

	@Inject
	ResourceDelegate<ShopResource> shopService;

	@Inject
	ShopListView myView;

	@Test
	public void onReveal() {
		// Given
		ShopDto shop = mock(ShopDto.class);
		ShopDto shop2 = mock(ShopDto.class);
		final List<ShopDto> shops = new ArrayList<>();
		shops.add(shop);
		shops.add(shop2);
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult(shops).when().getAll();

		shopListPresenter.onReveal();
		// when
		verify(myView).display(shops);
	}

	@Test
	public void onDeleteClick() {
		final ShopDto shop = new ShopDto();
		shop.setCode("1");
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult((Void) null).when().delete(shop.getCode());
		shopListPresenter.onDeleteClick(shop);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.SHOP_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		// When
		shopListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		ShopDto shop = mock(ShopDto.class);
		given(shop.getCode()).willReturn("1");
		PlaceRequest place = buildPlaceRequest(NameTokens.SHOP_FORM, TokenParameters.CODE, String.valueOf(shop.getCode()));
		// When
		shopListPresenter.onUpdateClick(shop);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}