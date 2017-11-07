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
import com.telosys.gwtp.base.shared.dto.SynopsisDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.SYNOPSIS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SynopsisResource extends GenericResource<SynopsisDto, Integer> {

	@Override
	@PUT
	@Path(Paths.BOOK_ID)
	void update(SynopsisDto team, @PathParam(Paths.BOOK_ID_PARAM) Integer bookId);

	@Override
	@DELETE
	@Path(Paths.BOOK_ID)
	void delete(@PathParam(Paths.BOOK_ID_PARAM) Integer bookId);

	@Override
	@GET
	@Path(Paths.BOOK_ID)
	SynopsisDto get(@PathParam(Paths.BOOK_ID_PARAM) Integer bookId);

	@GET
	@Path(Paths.SYNOPSIS_LIST_ITEM)
	List<ListItemDto> listItems();
}
