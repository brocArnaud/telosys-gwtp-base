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
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.BOOK_ORDER_ITEM)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookOrderItemResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<BookOrderItemDto> findAll();

	@GET
	@Path("{bookOrderId}/{bookId}")
	@Produces({ MediaType.APPLICATION_JSON })
	BookOrderItemDto get(@PathParam("bookOrderId") Integer bookOrderId, @PathParam("bookId") Integer bookId);

	@POST
	void create(BookOrderItemDto bookOrderItem);

	@PUT
	@Path("{bookOrderId}/{bookId}")
	void update(BookOrderItemDto bookOrderItem, @PathParam("bookOrderId") Integer bookOrderId, @PathParam("bookId") Integer bookId);

	@PUT
	void save(BookOrderItemDto bookOrderItem);

	@DELETE
	@Path("{bookOrderId}/{bookId}")
	void delete(@PathParam("bookOrderId") Integer bookOrderId, @PathParam("bookId") Integer bookId);

	@GET
	@Path(Paths.BOOK_ORDER_ITEM_LIST_ITEM)
	List<ListItemDto> listItems();

}