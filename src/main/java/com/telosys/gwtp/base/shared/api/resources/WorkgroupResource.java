package com.telosys.gwtp.base.shared.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.telosys.gwtp.base.shared.api.Paths;
import com.telosys.gwtp.base.shared.api.resources.util.GenericResource;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.WORKGROUP)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface WorkgroupResource extends GenericResource<WorkgroupDto, Integer> {

	@GET
	@Path(Paths.WORKGROUP_LIST_ITEM)
	List<ListItemDto> getWorkgroup();
}
