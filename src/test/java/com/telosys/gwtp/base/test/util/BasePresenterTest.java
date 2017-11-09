package com.telosys.gwtp.base.test.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

public class BasePresenterTest {

	public static class Module extends PresenterTestModule {
		@Override
		protected void configurePresenterTest() {
		}
	}

	@Before
	public void initUtils() {
		DelegateTestUtils.init();
	}

	protected PlaceRequest buildPlaceRequest(String nameTokens) {
		return new PlaceRequest.Builder().nameToken(nameTokens).build();
	}

	protected PlaceRequest buildPlaceRequest(String nameTokens, String param, String value) {
		return new PlaceRequest.Builder().nameToken(nameTokens).with(param, value).build();
	}

	protected PlaceRequest buildPlaceRequest(String nameTokens, String param, Integer value) {
		return new PlaceRequest.Builder().nameToken(nameTokens).with(param, String.valueOf(value)).build();
	}

	protected PlaceRequest buildPlaceRequest(String nameTokens, String param1, String value1, String param2, String value2) {
		return new PlaceRequest.Builder().nameToken(nameTokens).with(param1, value1).with(param2, value2).build();
	}

	protected PlaceRequest buildPlaceRequest(String nameTokens, String param1, Integer value1, String param2, Integer value2) {
		return new PlaceRequest.Builder().nameToken(nameTokens).with(param1, String.valueOf(value1)).with(param2, String.valueOf(value2)).build();
	}

	protected List<ListItemDto> getListItem() {
		final List<ListItemDto> result = new ArrayList<>();
		result.add(new ListItemDto("value1", "label1"));
		result.add(new ListItemDto("value2", "label2"));
		return result;
	}
}