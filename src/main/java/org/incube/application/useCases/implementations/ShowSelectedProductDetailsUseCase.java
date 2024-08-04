package org.incube.application.useCases.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.Result;
import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.application.useCases.abstractions.IShowSelectedProductDetailsUseCase;
import org.incube.domain.entities.Product;

public class ShowSelectedProductDetailsUseCase implements IShowSelectedProductDetailsUseCase {

    IProductRepository productRepository;
    IProductValidator productValidator;

    public ShowSelectedProductDetailsUseCase(IProductRepository productRepository,
                                             IProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    @Override
    public Result<Product, ErrorBody> getProductDetails(long Id) {
        Result<Boolean, ErrorBody> result = productValidator.validateId(Id);

        if (result.isSuccess()) {
            return Result.success(
                    productRepository.fetchProductById(Id)
            );
        }

        return Result.failure(
                result.getFailureData()
        );
    }
}
