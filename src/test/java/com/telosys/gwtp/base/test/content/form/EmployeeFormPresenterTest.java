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
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter.EmployeeFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.BadgeResource;
import com.telosys.gwtp.base.shared.api.resources.EmployeeResource;
import com.telosys.gwtp.base.shared.api.resources.ShopResource;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class EmployeeFormPresenterTest extends BasePresenterTest {

	@Inject
	EmployeeFormPresenter employeeFormPresenter;

	@Inject
	ResourceDelegate<EmployeeResource> employeeService;
	@Inject
	ResourceDelegate<ShopResource> shopService;
	@Inject
	ResourceDelegate<BadgeResource> badgeService;

	@Inject
	EmployeeFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.EMPLOYEE_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(badgeService).useResource(BadgeResource.class).and().succeed().withResult(getListItem()).when().listItems();

		employeeFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(EmployeeDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		EmployeeDto employee = new EmployeeDto();
		employee.setCode("1");
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.EMPLOYEE_FORM, TokenParameters.CODE, employee.getCode());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult(employee).when().get(employee.getCode());
		givenDelegate(shopService).useResource(ShopResource.class).and().succeed().withResult(getListItem()).when().listItems();
		givenDelegate(badgeService).useResource(BadgeResource.class).and().succeed().withResult(getListItem()).when().listItems();

		employeeFormPresenter.onReveal();
		// when
		verify(myView).load(employee);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.EMPLOYEE_LIST).build();
		EmployeeDto employee = new EmployeeDto();
		employee.setCode("1");
		employee.setShopCode("SH1");
		employee.setLastName("lastname");
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult((Void) null).when().create(employee);
		// When
		employeeFormPresenter.save(employee);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.EMPLOYEE_LIST).build();
		EmployeeDto employee = new EmployeeDto();
		employee.setCode("1");
		employee.setShopCode("SH1");
		employee.setLastName("lastname");
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult((Void) null).when().update(employee, employee.getCode());
		// When
		employeeFormPresenter.setUpdateMode(true);
		employeeFormPresenter.save(employee);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}