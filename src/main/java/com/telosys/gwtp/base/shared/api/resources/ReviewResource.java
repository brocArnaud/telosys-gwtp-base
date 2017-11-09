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
import com.telosys.gwtp.base.shared.dto.ReviewDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.REVIEW)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ReviewResource {

	@GET
	List<ReviewDto> findAll();

	@GET
	@Path("{customerCode}/{bookId}")
	ReviewDto get(@PathParam("customerCode") String customerCode, @PathParam("bookId") Integer bookId);

	@POST
	void create(ReviewDto review);

	@PUT
	@Path("{customerCode}/{bookId}")
	void update(ReviewDto review, @PathParam("customerCode") String customerCode, @PathParam("bookId") Integer bookId);

	@PUT
	void save(ReviewDto review);

	@DELETE
	@Path("{customerCode}/{bookId}")
	void delete(@PathParam("customerCode") String customerCode, @PathParam("bookId") Integer bookId);

	@GET
	@Path(Paths.REVIEW_LIST_ITEM)
	List<ListItemDto> listItems();
}
