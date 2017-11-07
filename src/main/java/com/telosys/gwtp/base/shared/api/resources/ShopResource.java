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
import com.telosys.gwtp.base.shared.dto.ShopDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.SHOP)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ShopResource extends GenericResource<ShopDto, String> {

	@Override
	@PUT
	@Path(Paths.CODE)
	void update(ShopDto team, @PathParam(Paths.CODE_PARAM) String code);

	@Override
	@DELETE
	@Path(Paths.CODE)
	void delete(@PathParam(Paths.CODE_PARAM) String code);

	@Override
	@GET
	@Path(Paths.CODE)
	ShopDto get(@PathParam(Paths.CODE_PARAM) String code);
	
	@GET
	@Path(Paths.SHOP_LIST_ITEM)
	List<ListItemDto> listItems();
}
