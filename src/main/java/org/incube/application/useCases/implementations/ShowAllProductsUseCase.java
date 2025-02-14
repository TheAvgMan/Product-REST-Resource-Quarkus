package org.incube.application.useCases.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.ErrorBodyStore;
import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.application.useCases.abstractions.IShowAllProductsUseCase;
import org.incube.domain.entities.Product;

import java.util.List;

public class ShowAllProductsUseCase implements IShowAllProductsUseCase {

    IProductRepository productRepository;
    IProductValidator productValidator;

    public ShowAllProductsUseCase(IProductRepository productRepository,
                                  IProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.fetchAllProducts();
        ErrorBody errorBody = productValidator.validateReturnedList(allProducts);

        if (errorBody == null) {
            return allProducts;
        } else {
            ErrorBodyStore.addErrorBody(errorBody);
        }

        return null;
    }

    @Override
    public List<Product> getPageProducts(int page) {
        ErrorBody errorBody = productValidator.validatePageNumber(page);

        if (errorBody == null) {
            List<Product> pageProducts = productRepository.fetchPageProducts(page);
            errorBody = productValidator.validateReturnedList(pageProducts);

            if (errorBody == null) {
                return pageProducts;
            } else {
                ErrorBodyStore.addErrorBody(errorBody);
            }

        } else {
            ErrorBodyStore.addErrorBody(errorBody);
        }

        return null;
    }

}
