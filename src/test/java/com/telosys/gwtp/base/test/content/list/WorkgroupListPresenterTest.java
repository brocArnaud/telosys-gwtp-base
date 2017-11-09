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
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter;
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter.WorkgroupListView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.WorkgroupResource;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class WorkgroupListPresenterTest extends BasePresenterTest {

	@Inject
	WorkgroupListPresenter workgroupListPresenter;

	@Inject
	ResourceDelegate<WorkgroupResource> workgroupService;

	@Inject
	WorkgroupListView myView;

	@Test
	public void onReveal() {
		// Given
		WorkgroupDto workgroup = mock(WorkgroupDto.class);
		WorkgroupDto workgroup2 = mock(WorkgroupDto.class);
		final List<WorkgroupDto> workgroups = new ArrayList<>();
		workgroups.add(workgroup);
		workgroups.add(workgroup2);
		givenDelegate(workgroupService).useResource(WorkgroupResource.class).and().succeed().withResult(workgroups).when().getAll();

		workgroupListPresenter.onReveal();
		// when
		verify(myView).display(workgroups);
	}

	@Test
	public void onDeleteClick() {
		final WorkgroupDto workgroup = new WorkgroupDto();
		workgroup.setId(1);
		givenDelegate(workgroupService).useResource(WorkgroupResource.class).and().succeed().withResult((Void) null).when().delete(workgroup.getId());
		workgroupListPresenter.onDeleteClick(workgroup);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.WORKGROUP_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		// When
		workgroupListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		WorkgroupDto workgroup = mock(WorkgroupDto.class);
		given(workgroup.getId()).willReturn(1);
		PlaceRequest place = buildPlaceRequest(NameTokens.WORKGROUP_FORM, TokenParameters.ID, String.valueOf(workgroup.getId()));
		// When
		workgroupListPresenter.onUpdateClick(workgroup);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}