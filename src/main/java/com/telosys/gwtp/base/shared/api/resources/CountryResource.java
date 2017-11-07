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

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.telosys.gwtp.base.shared.api.Paths;
import com.telosys.gwtp.base.shared.api.resources.util.GenericResource;
import com.telosys.gwtp.base.shared.dto.CountryDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.COUNTRY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CountryResource extends GenericResource<CountryDto, String> {

	@Override
	@PUT
	@Path(Paths.CODE)
	void update(CountryDto team, @PathParam(Paths.CODE_PARAM) String code);

	@Override
	@DELETE
	@Path(Paths.CODE)
	void delete(@PathParam(Paths.CODE_PARAM) String code);

	@Override
	@GET
	@Path(Paths.CODE)
	CountryDto get(@PathParam(Paths.CODE_PARAM) String code);

	@GET
	@Path(Paths.COUNTRY_LIST_ITEM)
	List<ListItemDto> listItems();
}
