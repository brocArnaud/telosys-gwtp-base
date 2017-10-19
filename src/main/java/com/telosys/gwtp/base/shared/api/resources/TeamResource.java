package com.telosys.gwtp.base.shared.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.telosys.gwtp.base.shared.api.Paths;
import com.telosys.gwtp.base.shared.dto.ListItemDto;
import com.telosys.gwtp.base.shared.dto.TeamDto;

@Path(Paths.TEAM)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TeamResource {

	@GET
	RestAction<List<TeamDto>> getTeam();

	@POST
	RestAction<Void> create(TeamDto team);

	@PUT
	@Path("/{id}")
	RestAction<Void> update(TeamDto team, @PathParam("id") Long id);

	@DELETE
	@Path("/{id}")
	RestAction<Void> delete(@PathParam("id") Long id);

	@GET
	@Path("/{id}")
	RestAction<TeamDto> get(@PathParam("id") Long id);
	
	@GET
	@Path("/teams-list-items")
	RestAction<List<ListItemDto>> getTeamList();

}
