package com.telosys.gwtp.base.test.content.team;

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
import com.telosys.gwtp.base.client.application.content.team.list.TeamListPresenter;
import com.telosys.gwtp.base.client.application.content.team.list.TeamListPresenter.MyView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.TeamResource;
import com.telosys.gwtp.base.shared.dto.team.TeamDto;
import com.telosys.gwtp.base.test.util.BasePresenter;

@RunWith(JukitoRunner.class)
public class TeamListPresenterTest extends BasePresenter {

	@Inject
	TeamListPresenter teamListPresenter;

	@Inject
	ResourceDelegate<TeamResource> teamService;

	@Inject
	MyView myView;

	@Test
	public void onReveal() {
		// Given
		TeamDto team = mock(TeamDto.class);
		TeamDto team2 = mock(TeamDto.class);
		final List<TeamDto> teams = new ArrayList<>();
		teams.add(team);
		teams.add(team2);
		givenDelegate(teamService).useResource(TeamResource.class).and().succeed().withResult(teams).when().getAll();

		teamListPresenter.onReveal();
		// when
		verify(myView).display(teams);

	}

	@Test
	public void onDeleteClick() {
		final TeamDto team = new TeamDto(1L, "name1");
		givenDelegate(teamService).useResource(TeamResource.class).and().succeed().withResult((Void) null).when().delete(2L);
		teamListPresenter.onDeleteClick(team);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.TEAM_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		// When
		teamListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		TeamDto team = mock(TeamDto.class);
		given(team.getId()).willReturn(1L);
		PlaceRequest place = buildPlaceRequest(NameTokens.TEAM_FORM, TokenParameters.ID, String.valueOf(team.getId()));
		// When
		teamListPresenter.onUpdateClick(team.getId());
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}