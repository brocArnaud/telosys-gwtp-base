package com.telosys.gwtp.base.shared.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.telosys.gwtp.base.shared.api.Paths;
import com.telosys.gwtp.base.shared.dto.TeamDto;

@Path(Paths.TEAM)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TeamResource {

	@GET
	RestAction<List<TeamDto>> getTeam();
}