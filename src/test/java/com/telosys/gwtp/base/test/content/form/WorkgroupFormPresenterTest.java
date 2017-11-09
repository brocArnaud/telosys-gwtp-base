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
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter.WorkgroupFormView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.WorkgroupResource;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;
import com.telosys.gwtp.base.test.util.BasePresenterTest;

@RunWith(JukitoRunner.class)
public class WorkgroupFormPresenterTest extends BasePresenterTest {

	@Inject
	WorkgroupFormPresenter workgroupFormPresenter;

	@Inject
	ResourceDelegate<WorkgroupResource> workgroupService;

	@Inject
	WorkgroupFormView myView;

	@Test
	public void onRevealCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.WORKGROUP_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);

		workgroupFormPresenter.onReveal();
		// when
		verify(myView).load(Mockito.any(WorkgroupDto.class));
	}

	@Test
	public void onRevealUpdateMode(PlaceManager placeManager) {
		// Given
		WorkgroupDto workgroup = new WorkgroupDto();
		workgroup.setId(1);
		PlaceRequest requestCreation = buildPlaceRequest(NameTokens.WORKGROUP_FORM, TokenParameters.ID, workgroup.getId());
		given(placeManager.getCurrentPlaceRequest()).willReturn(requestCreation);
		givenDelegate(workgroupService).useResource(WorkgroupResource.class).and().succeed().withResult(workgroup).when().get(workgroup.getId());

		workgroupFormPresenter.onReveal();
		// when
		verify(myView).load(workgroup);
	}

	@Test
	public void onSaveCreationMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.WORKGROUP_LIST).build();
		WorkgroupDto workgroup = new WorkgroupDto();
		workgroup.setId(1);
		givenDelegate(workgroupService).useResource(WorkgroupResource.class).and().succeed().withResult((Void) null).when().create(workgroup);
		// When
		workgroupFormPresenter.save(workgroup);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}

	@Test
	public void onSaveUpdateMode(PlaceManager placeManager) {
		// Given
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.WORKGROUP_LIST).build();
		WorkgroupDto workgroup = new WorkgroupDto();
		workgroup.setId(1);
		givenDelegate(workgroupService).useResource(WorkgroupResource.class).and().succeed().withResult((Void) null).when().update(workgroup, workgroup.getId());
		// When
		workgroupFormPresenter.setUpdateMode(true);
		workgroupFormPresenter.save(workgroup);
		// Then
		verify(placeManager).revealPlace(eq(placeRequest));
	}
}