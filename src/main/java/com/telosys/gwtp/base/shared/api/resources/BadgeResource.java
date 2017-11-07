package com.telosys.gwtp.base.shared.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.telosys.gwtp.base.shared.api.Paths;
import com.telosys.gwtp.base.shared.api.resources.util.GenericResource;
import com.telosys.gwtp.base.shared.dto.BadgeDto;
import com.telosys.gwtp.base.shared.dto.CountryDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.BADGE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BadgeResource extends GenericResource<BadgeDto, Integer> {

	
	@Override
	@PUT
	@Path(Paths.BADGE_NUMBER)
	void update(BadgeDto team, @PathParam(Paths.BADGE_NUMBER_PARAM) Integer code);

	@Override
	@DELETE
	@Path(Paths.BADGE_NUMBER)
	void delete(@PathParam(Paths.BADGE_NUMBER_PARAM) Integer code);

	@Override
	@GET
	@Path(Paths.BADGE_NUMBER)
	BadgeDto get(@PathParam(Paths.BADGE_NUMBER_PARAM) Integer code);
	
	@GET
	@Path(Paths.BADGE_LIST_ITEM)
	List<ListItemDto> listItems();
}
