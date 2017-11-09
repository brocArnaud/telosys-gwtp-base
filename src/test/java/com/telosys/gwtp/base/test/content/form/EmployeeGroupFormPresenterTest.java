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
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter.EmployeeGroupFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.EmployeeGroupResource;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class EmployeeGroupFormPresenterTest extends BasePresenterTest {

	@Inject
	EmployeeGroupFormPresenter employeeGroupFormPresenter;

	@Inject
	ResourceDelegate<EmployeeGroupResource> employeeGroupService;

	@Inject
	EmployeeGroupFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildRequest(TokenParameters.DEFAULT_ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		employeeGroupFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(EmployeeGroupDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		EmployeeGroupDto employeeGroup = new EmployeeGroupDto();
		employeeGroup.setEmployeeCode("1");
		employeeGroup.setGroupId(1);
		PlaceRequest requestCreation = buildRequest(employeeGroup.getEmployeeCode(), String.valueOf(employeeGroup.getGroupId()));
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(employeeGroupService).useResource(EmployeeGroupResource.class).and().succeed().withResult(employeeGroup).when().get(employeeGroup.getEmployeeCode(),
				employeeGroup.getGroupId());

		employeeGroupFormPresenter.onReveal();
		// when
		verify(myView).load(employeeGroup);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.EMPLOYEE_GROUP_LIST).build();
		EmployeeGroupDto employeeGroup = new EmployeeGroupDto();
		employeeGroup.setEmployeeCode("1");
		employeeGroup.setGroupId(1);
		givenDelegate(employeeGroupService).useResource(EmployeeGroupResource.class).and().succeed().withResult((Void) null).when().create(employeeGroup);
		// When
		employeeGroupFormPresenter.save(employeeGroup);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.EMPLOYEE_GROUP_LIST).build();
		EmployeeGroupDto employeeGroup = new EmployeeGroupDto();
		employeeGroup.setEmployeeCode("1");
		employeeGroup.setGroupId(1);
		givenDelegate(employeeGroupService).useResource(EmployeeGroupResource.class).and().succeed().withResult((Void) null).when().update(employeeGroup,
				employeeGroup.getEmployeeCode(), employeeGroup.getGroupId());
		// When
		employeeGroupFormPresenter.setUpdateMode(true);
		employeeGroupFormPresenter.save(employeeGroup);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	private PlaceRequest buildRequest(String employeeCode, String groupId) {
		return buildPlaceRequest(NameTokens.EMPLOYEE_GROUP_FORM, TokenParameters.EMPLOYEE_CODE, employeeCode, TokenParameters.GROUP_ID, groupId);
	}
}