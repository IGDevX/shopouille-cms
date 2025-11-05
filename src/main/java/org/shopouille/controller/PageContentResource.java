package org.shopouille.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.shopouille.dto.ErrorResponse;
import org.shopouille.dto.PageContentDTO;
import org.shopouille.dto.UpdatePageContentDTO;
import org.shopouille.model.PageContent;

import java.util.List;
import java.util.stream.Collectors;

@Path("/page-content")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PageContentResource {

    @GET
    public Response getAllPages() {
        List<PageContent> pages = PageContent.listAll();
        List<PageContentDTO> dtos = pages.stream()
            .map(PageContentDTO::fromEntity)
            .collect(Collectors.toList());
        
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    public Response getPageById(@PathParam("id") String id) {
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
                ErrorResponse error = ErrorResponse.fromMessage("Invalid ObjectId format", Response.Status.BAD_REQUEST.getStatusCode());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
        
        PageContent page = PageContent.findById(objectId);
        
        if (page == null) {
            ErrorResponse error = ErrorResponse.fromMessage("Page not found", Response.Status.NOT_FOUND.getStatusCode());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
        
        PageContentDTO dto = PageContentDTO.fromEntity(page);
        
        return Response.ok(dto).build();
    }

    @PATCH
    @Path("/{id}")
    public Response updatePage(@PathParam("id") String id, @Valid UpdatePageContentDTO updateDTO) {
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = ErrorResponse.fromMessage("Invalid ObjectId format", Response.Status.BAD_REQUEST.getStatusCode());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
        
        PageContent page = PageContent.findById(objectId);
        
        if (page == null) {
            ErrorResponse error = ErrorResponse.fromMessage("Page not found", Response.Status.NOT_FOUND.getStatusCode());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }

        page.setContent(updateDTO.getContent());
        page.update();

        PageContentDTO dto = PageContentDTO.fromEntity(page);

        return Response.ok(dto).build();
    }
}


