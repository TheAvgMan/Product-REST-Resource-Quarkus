package org.incube.application.useCases;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.incube.domain.entities.Product;
import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.infrastructure.repositories.ProductRepository;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private Validator validator;


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

    public Response getPage(Integer pageNumber) {
        if (pageNumber < 1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(
                            new ErrorBody(
                                    "Invalid Page Number",
                                    "Page number should start from 1"
                            )
                    )
                    .type(MediaType.APPLICATION_JSON)
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

    public Response getById(Long id) {
        return operationSimplifier(0, id, null);
    }

    public Response create(Product product) {
        return productCreator(0, product, -1L);
    }

    public Response update(Long id, Product product) {
        return operationSimplifier(1, id, product);
    }

    public Response delete(Long id) {
        return  operationSimplifier(2, id, null);
    }


    private Response operationSimplifier(int operationNumber, Long id, Product product) {
        if (id < 1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(
                            new ErrorBody(
                                    "Invalid ID Number",
                                    "ID number should start from 1"
                            )
                    )
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        Product entity = productRepository.findById(id);
        if(entity == null) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        // operation
        switch (operationNumber) {
            case 0:
                //first operation - getById
                //nothing more to do here in this operation
                break;
            case 1:
                //second operation - update
                return productCreator(1, product, id);
            case 2:
                //third operation - delete
                productRepository.delete(entity);
                entity = null;
                break;
        }

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response productCreator(int operationNumber, Product product, Long id) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (violations.isEmpty()) {
            switch (operationNumber) {
                case 0:
                    // create product
                    productRepository.persist(product);
                    return Response
                            .status(Response.Status.CREATED)
                            .build();
                case 1:
                    // update product
                    Product entity = productRepository.findById(id);
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
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(
                        new ErrorBody(
                                "Invalid Product Values",
                                "Name, Description, Price and Image link are required. " +
                                        "And the minimum value of Price is 1.00"
                        )
                )
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
