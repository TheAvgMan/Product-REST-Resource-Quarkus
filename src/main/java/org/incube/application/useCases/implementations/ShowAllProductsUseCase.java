package org.incube.application.useCases.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.Result;
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
    public Result<List<Product>, ErrorBody> getAllProducts() {
        List<Product> allProducts = productRepository.fetchAllProducts();
        Result<Boolean, ErrorBody> result = productValidator.validateReturnedList(allProducts);

        if (result.isSuccess()) {
            return Result.success(allProducts);
        }

        return Result.failure(
                result.getFailureData()
        );
    }

    @Override
    public Result<List<Product>, ErrorBody> getPageProducts(int page) {
        Result<Boolean, ErrorBody> result = productValidator.validatePageNumber(page);

        if (result.isSuccess()) {
            List<Product> pageProducts = productRepository.fetchPageProducts(page);
            result = productValidator.validateReturnedList(pageProducts);

            if (result.isSuccess()) {
                return Result.success(pageProducts);
            }

            return Result.failure(
                    result.getFailureData()
            );
        }

        return Result.failure(
                result.getFailureData()
        );
    }
}
