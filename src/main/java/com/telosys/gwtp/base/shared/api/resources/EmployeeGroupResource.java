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

import com.telosys.gwtp.base.shared.api.Paths;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.EMPLOYEE_GROUP)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface EmployeeGroupResource {

	@GET
	public List<EmployeeGroupDto> findAll();

	@GET
	@Path("{employeeCode}/{groupId}")
	EmployeeGroupDto get(@PathParam("employeeCode") String employeeCode, @PathParam("groupId") Integer groupId);

	@POST
	void create(EmployeeGroupDto employeeGroup);

	@PUT
	@Path("{employeeCode}/{groupId}")
	void update(EmployeeGroupDto employeeGroup, @PathParam("employeeCode") String employeeCode, @PathParam("groupId") Integer groupId);

	@PUT
	void save(EmployeeGroupDto employeeGroup);

	@DELETE
	@Path("{employeeCode}/{groupId}")
	void delete(@PathParam("employeeCode") String employeeCode, @PathParam("groupId") Integer groupId);

	@GET
	@Path(Paths.EMPLOYES_GROUP_LIST_ITEM)
	List<ListItemDto> listItems();
}
