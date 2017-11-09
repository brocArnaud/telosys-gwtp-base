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
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter;
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter.EmployeeListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.EmployeeResource;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class EmployeeListPresenterTest extends BasePresenterTest {

	@Inject
	EmployeeListPresenter employeeListPresenter;

	@Inject
	ResourceDelegate<EmployeeResource> employeeService;

	@Inject
	EmployeeListView myView;

	@Test
	public void onReveal() {
		// Given
		EmployeeDto employee = mock(EmployeeDto.class);
		EmployeeDto employee2 = mock(EmployeeDto.class);
		final List<EmployeeDto> employees = new ArrayList<>();
		employees.add(employee);
		employees.add(employee2);
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult(employees).when().getAll();

		employeeListPresenter.onReveal();
		// when
		verify(myView).display(employees);
	}

	@Test
	public void onDeleteClick() {
		final EmployeeDto employee = new EmployeeDto();
		employee.setCode("1");
		givenDelegate(employeeService).useResource(EmployeeResource.class).and().succeed().withResult((Void) null).when().delete(employee.getCode());
		employeeListPresenter.onDeleteClick(employee);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.EMPLOYEE_FORM, TokenParameters.CODE, TokenParameters.DEFAULT_ID);
		// When
		employeeListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		EmployeeDto employee = mock(EmployeeDto.class);
		given(employee.getCode()).willReturn("1");
		PlaceRequest place = buildPlaceRequest(NameTokens.EMPLOYEE_FORM, TokenParameters.CODE, employee.getCode());
		// When
		employeeListPresenter.onUpdateClick(employee);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}