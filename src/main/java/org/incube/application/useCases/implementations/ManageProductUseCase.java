package org.incube.application.useCases.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.Result;
import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.application.useCases.abstractions.IManageProductUseCase;
import org.incube.domain.entities.Product;

public class ManageProductUseCase implements IManageProductUseCase {

    IProductValidator productValidator;
    IProductRepository productRepository;

    public ManageProductUseCase(IProductValidator productValidator,
                                IProductRepository productRepository) {
        this.productValidator = productValidator;
        this.productRepository = productRepository;
    }

    @Override
    public Result<Boolean, ErrorBody> createProduct(Product product) {
        Result<Boolean, ErrorBody> result = productValidator.validateProduct(product);

        if (result.isSuccess()) {
            productRepository.storeProduct(product);
            return Result.success(true);
        }

        return Result.failure(
                result.getFailureData()
        );
    }

    @Override
    public Result<Boolean, ErrorBody> updateProduct(long Id, Product product) {
        Result<Boolean, ErrorBody> result = productValidator.validateId(Id);

        if (result.isSuccess()) {
            result = productValidator.validateProduct(product);

            if (result.isSuccess()) {
                productRepository.updateProduct(Id, product);
                return Result.success(true);
            }

            return Result.failure(
                    result.getFailureData()
            );
        }

        return Result.failure(
                result.getFailureData()
        );
    }

    @Override
    public Result<Boolean, ErrorBody> deleteProduct(long Id) {
        Result<Boolean, ErrorBody> result = productValidator.validateId(Id);

        if (result.isSuccess()) {
            productRepository.deleteProduct(Id);
            return Result.success(true);
        }

        return Result.failure(
                result.getFailureData()
        );
    }
}
