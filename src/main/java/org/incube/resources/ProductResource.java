package org.incube.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.incube.entities.Product;
import org.incube.repositories.ProductRepository;

import java.net.URI;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @GET
    public List<Product> getAll() {
        return productRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    @POST
    public Response create(Product product) {
        productRepository.persist(product);
        return Response.created(URI.create("/products/" + product.getId())).build();
    }

    @PUT
    @Path("/{id}")
    public Product update(Long id, Product product) {
        Product entity = productRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
//        entity.name = person.name;
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImage(product.getImage());

        return entity;
    }

    @DELETE
    @Path("/{id}")
    public void delete(Long id) {
        Product entity = productRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        productRepository.delete(entity);
    }
}
