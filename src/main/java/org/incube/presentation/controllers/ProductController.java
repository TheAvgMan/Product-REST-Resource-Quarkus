package org.incube.presentation.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.incube.domain.entities.Product;
import org.incube.application.services.ProductService;


@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ProductController {

    @Inject
    private ProductService productService;


    @GET
    public Response getAll() {
        return productService.getAll();
    }

    @GET
    @Path("/page/{pageNumber}")
    public Response getPage(Integer pageNumber) {
        return productService.getPage(pageNumber);
    }

    @GET
    @Path("/product/{id}")
    public Response getById(Long id) {
        return productService.getById(id);
    }

    @POST
    public Response create(Product product) {
        return productService.create(product);
    }

    @PUT
    @Path("/{id}")
    public Response update(Long id, Product product) {
        return productService.update(id, product);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        return productService.delete(id);
    }
}
