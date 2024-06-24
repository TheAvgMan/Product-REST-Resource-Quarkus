package org.incube.resources;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.incube.entities.Product;
import org.incube.repositories.ProductRepository;
import java.util.List;
import java.util.Set;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @Inject
    Validator validator;


    @GET
    public Response getAll() {
        List<Product> allProducts = productRepository.listAll();

        if (allProducts.isEmpty()) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
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
        if (pageNumber < 1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        PanacheQuery<Product> allProducts = productRepository.findAll(Sort.ascending("name"));
        List<Product> page = allProducts.page(Page.of(pageNumber-1, 3)).list();

        if (page.isEmpty()) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(page)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/product/{id}")
    public Response getById(Long id) {
        if (id < 1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        Product product = productRepository.findById(id);

        if (product == null) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(product)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    public Response create(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (violations.isEmpty()) {
            productRepository.persist(product);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(violations)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(Long id, Product product) {
        if (id < 1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        Product entity = productRepository.findById(id);
        if(entity == null) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        // map all fields from the product parameter to the existing entity
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImage(product.getImage());

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        if (id < 1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        Product entity = productRepository.findById(id);
        if(entity == null) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        productRepository.delete(entity);

        return Response
                .status(Response.Status.OK)
                .build();
    }
}
