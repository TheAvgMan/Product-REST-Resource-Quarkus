package org.incube.presentation.controllers;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.helpers.implementations.ProductValidator;
import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.application.useCases.abstractions.IManageProductUseCase;
import org.incube.application.useCases.abstractions.IShowAllProductsUseCase;
import org.incube.application.useCases.abstractions.IShowSelectedProductDetailsUseCase;
import org.incube.application.useCases.implementations.ManageProductUseCase;
import org.incube.application.useCases.implementations.ShowAllProductsUseCase;
import org.incube.application.useCases.implementations.ShowSelectedProductDetailsUseCase;
import org.incube.domain.entities.Product;
import org.incube.infrastructure.repositories.implementations.ProductRepository;

import java.util.List;


@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ProductController {

    IProductRepository productRepository = new ProductRepository();
    IProductValidator productValidator = new ProductValidator(productRepository);

    IManageProductUseCase manageProductUseCase = new ManageProductUseCase(
            productValidator,
            productRepository
    );

    IShowAllProductsUseCase showAllProductsUseCase = new ShowAllProductsUseCase(
            productRepository,
            productValidator
    );

    IShowSelectedProductDetailsUseCase showSelectedProductDetailsUseCase = new ShowSelectedProductDetailsUseCase(
            productRepository,
            productValidator
    );


    @GET
    public Response getAll() {
        List<Product> allProducts = showAllProductsUseCase.getAllProducts();

        if (allProducts == null) {
            // get the error body object passed from the above method call
            // then return it as response object
        }

        return Response
                .status(Response.Status.OK)
                .entity(allProducts)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/page/{pageNumber}")
    public Response getPage(Integer pageNumber) {
        List<Product> pageProducts = showAllProductsUseCase.getPageProducts(pageNumber);

        if (pageProducts == null) {
            // get the error body object passed from the above method call
            // then return it as response object
        }

        return Response
                .status(Response.Status.OK)
                .entity(pageProducts)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/product/{id}")
    public Response getById(Long id) {
        Product retrievedProduct = showSelectedProductDetailsUseCase.getProductDetails(id);

        if (retrievedProduct == null) {
            // get the error body object passed from the above method call
            // then return it as response object
        }

        return Response
                .status(Response.Status.OK)
                .entity(retrievedProduct)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    public Response create(Product product) {
        boolean productCreated = manageProductUseCase.createProduct(product);

        if (!productCreated) {
            // get the error body object passed from the above method call
            // then return it as response object
        }

        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(Long id, Product product) {
        boolean productUpdated = manageProductUseCase.updateProduct(id, product);

        if (!productUpdated) {
            // get the error body object passed from the above method call
            // then return it as response object
        }

        return Response
                .status(Response.Status.OK)
                .entity("Product Updated Successfully")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        boolean productDeleted = manageProductUseCase.deleteProduct(id);

        if (!productDeleted) {
            // get the error body object passed from the above method call
            // then return it as response object
        }

        return Response
                .status(Response.Status.OK)
                .entity("Product Deleted Successfully")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
