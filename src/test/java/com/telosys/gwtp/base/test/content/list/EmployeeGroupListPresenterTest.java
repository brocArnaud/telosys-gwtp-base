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
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter.EmployeeGroupListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.EmployeeGroupResource;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class EmployeeGroupListPresenterTest extends BasePresenterTest {

	@Inject
	EmployeeGroupListPresenter employeeGroupListPresenter;

	@Inject
	ResourceDelegate<EmployeeGroupResource> employeeGroupService;

	@Inject
	EmployeeGroupListView myView;

	@Test
	public void onReveal() {
		// Given
		EmployeeGroupDto employeeGroup = mock(EmployeeGroupDto.class);
		EmployeeGroupDto employeeGroup2 = mock(EmployeeGroupDto.class);
		final List<EmployeeGroupDto> employeeGroups = new ArrayList<>();
		employeeGroups.add(employeeGroup);
		employeeGroups.add(employeeGroup2);
		givenDelegate(employeeGroupService).useResource(EmployeeGroupResource.class).and().succeed().withResult(employeeGroups).when().findAll();

		employeeGroupListPresenter.onReveal();
		// when
		verify(myView).display(employeeGroups);
	}

	@Test
	public void onDeleteClick() {
		final EmployeeGroupDto employeeGroup = new EmployeeGroupDto();
		employeeGroup.setEmployeeCode("A");
		employeeGroup.setGroupId(1);
		givenDelegate(employeeGroupService).useResource(EmployeeGroupResource.class).and().succeed().withResult((Void) null).when().delete(employeeGroup.getEmployeeCode(),
				employeeGroup.getGroupId());
		employeeGroupListPresenter.onDeleteClick(employeeGroup);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildRequest(TokenParameters.DEFAULT_ID, TokenParameters.DEFAULT_ID);
		// When
		employeeGroupListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		EmployeeGroupDto employeeGroup = mock(EmployeeGroupDto.class);
		given(employeeGroup.getEmployeeCode()).willReturn("1");
		given(employeeGroup.getGroupId()).willReturn(1);
		PlaceRequest place = buildRequest(employeeGroup.getEmployeeCode(), String.valueOf(employeeGroup.getGroupId()));
		// When
		employeeGroupListPresenter.onUpdateClick(employeeGroup);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	private PlaceRequest buildRequest(String employeeCode, String groupId) {
		return buildPlaceRequest(NameTokens.EMPLOYEE_GROUP_FORM, TokenParameters.EMPLOYEE_CODE, employeeCode, TokenParameters.GROUP_ID, groupId);
	}
}