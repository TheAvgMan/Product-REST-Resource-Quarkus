package org.incube.application.helpers.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.domain.entities.Product;

import java.util.List;

public class ProductValidator implements IProductValidator {

    IProductRepository productRepository;

    public ProductValidator(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ErrorBody validateId(long Id) {

        if (Id < 1) {
            return new ErrorBody(
                    "Invalid ID Number",
                    "ID number should start from 1"
            );
        }

        Product retrievedProduct = productRepository.fetchProductById(Id);

        if (retrievedProduct == null) {
            return new ErrorBody(
                    "Product does not exist",
                    "No Product with such ID"
            );
        }

        return null;
    }

    @Override
    public ErrorBody validatePageNumber(int page) {

        if (page < 1) {
            return new ErrorBody(
                    "Invalid Page Number",
                    "Page number should start from 1"
            );
        }

        return null;
    }

    @Override
    public ErrorBody validateProduct(Product product) {

        if (product.getName().isBlank()) {
            return new ErrorBody(
                    "Invalid Product Name",
                    "Name should not be left out or empty"
            );
        } else if (product.getDescription().isBlank()) {
            return new ErrorBody(
                    "Invalid Product Description",
                    "Description should not be left out or empty"
            );
        } else if (product.getPrice() == null || product.getPrice() < 1.00) {
            return new ErrorBody(
                    "Invalid Product Price",
                    "Price should not be left out or be less than EGP 1.00"
            );
        } else if (product.getImage().isBlank()) {
            return new ErrorBody(
                    "Invalid Product Image",
                    "Image should not be left out or empty"
            );
        }

        return null;
    }

    @Override
    public ErrorBody validateReturnedList(List<Product> products) {

        if (products.isEmpty()) {
            return new ErrorBody(
                    "Product(s) Unavailable Here",
                    "No available product(s) data here"
            );
        }

        return null;
    }

}
