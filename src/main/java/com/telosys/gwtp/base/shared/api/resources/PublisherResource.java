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
import com.telosys.gwtp.base.shared.dto.PublisherDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.PUBLISHER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PublisherResource extends GenericResource<PublisherDto, Integer> {

	@Override
	@PUT
	@Path(Paths.CODE)
	void update(PublisherDto team, @PathParam(Paths.CODE_PARAM) Integer code);

	@Override
	@DELETE
	@Path(Paths.CODE)
	void delete(@PathParam(Paths.CODE_PARAM) Integer code);

	@Override
	@GET
	@Path(Paths.CODE)
	PublisherDto get(@PathParam(Paths.CODE_PARAM) Integer code);

	@GET
	@Path(Paths.PUBLISHER_LIST_ITEM)
	List<ListItemDto> listItems();
}
