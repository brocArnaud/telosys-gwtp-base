package com.telosys.gwtp.base.shared.api.resources.util;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.telosys.gwtp.base.shared.api.Paths;

public interface GenericResource<T, I> {

	@GET
	List<T> getAll();

	@POST
	void create(T team);

	@PUT
	@Path(Paths.ID)
	void update(T team, @PathParam(Paths.ID_PARAM) I id);

	@DELETE
	@Path(Paths.ID)
	void delete(@PathParam(Paths.ID_PARAM) I id);

	@GET
	@Path(Paths.ID)
	T get(@PathParam(Paths.ID_PARAM) I id);
}