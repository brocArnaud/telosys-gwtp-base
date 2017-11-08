package com.telosys.gwtp.base.shared.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.telosys.gwtp.base.shared.api.Paths;
import com.telosys.gwtp.base.shared.api.resources.util.GenericResource;
import com.telosys.gwtp.base.shared.dto.book.BookDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

@Path(Paths.BOOK)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookResource extends GenericResource<BookDto, Integer> {

	@GET
	@Path(Paths.BOOK_LIST_ITEM)
	List<ListItemDto> listItems();

}
