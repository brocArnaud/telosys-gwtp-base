package com.telosys.gwtp.base.test.content.player;

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
import com.telosys.gwtp.base.client.application.content.player.list.PlayerListPresenter;
import com.telosys.gwtp.base.client.application.content.player.list.PlayerListPresenter.MyView;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.shared.api.resources.PlayerResource;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;
import com.telosys.gwtp.base.test.util.BasePresenter;

@RunWith(JukitoRunner.class)
public class PlayerListPresenterTest extends BasePresenter {

	@Inject
	PlayerListPresenter playerListPresenter;

	@Inject
	ResourceDelegate<PlayerResource> playerService;

	@Inject
	MyView myView;

	@Test
	public void onReveal() {
		// Given
		PlayerDto player = mock(PlayerDto.class);
		PlayerDto player2 = mock(PlayerDto.class);
		final List<PlayerDto> players = new ArrayList<>();
		players.add(player);
		players.add(player2);
		givenDelegate(playerService).useResource(PlayerResource.class).and().succeed().withResult(players).when().getAll();

		playerListPresenter.onReveal();
		// when
		verify(myView).display(players);
	}

	@Test
	public void onDeleteClick() {
		final PlayerDto player = new PlayerDto(1L, "name1", "1");
		givenDelegate(playerService).useResource(PlayerResource.class).and().succeed().withResult((Void) null).when().delete(player.getId());
		playerListPresenter.onDeleteClick(player);
	}

	@Test
	public void onCreateClick(PlaceManager placeManager) {
		// Given
		PlaceRequest place = buildPlaceRequest(NameTokens.PLAYER_FORM, TokenParameters.ID, TokenParameters.DEFAULT_ID);
		// When
		playerListPresenter.onCreateClick();
		// Then
		verify(placeManager).revealPlace(eq(place));
	}

	@Test
	public void onUpdateClick(PlaceManager placeManager) {
		// Given
		PlayerDto player = mock(PlayerDto.class);
		given(player.getId()).willReturn(1L);
		PlaceRequest place = buildPlaceRequest(NameTokens.PLAYER_FORM, TokenParameters.ID, String.valueOf(player.getId()));
		// When
		playerListPresenter.onUpdateClick(player);
		// Then
		verify(placeManager).revealPlace(eq(place));
	}
}