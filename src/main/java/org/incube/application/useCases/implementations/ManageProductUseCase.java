package org.incube.application.useCases.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.ErrorBodyStore;
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
    public boolean createProduct(Product product) {
        ErrorBody errorBody = productValidator.validateProduct(product);

        if (errorBody == null) {
            productRepository.storeProduct(product);
            return true;
        } else {
            ErrorBodyStore.addErrorBody(errorBody);
        }

        return false;
    }

    @Override
    public boolean updateProduct(long Id, Product product) {
        ErrorBody errorBody = productValidator.validateId(Id);

        if (errorBody == null) {
            errorBody = productValidator.validateProduct(product);

            if (errorBody == null) {
                productRepository.updateProduct(Id, product);
                return true;
            }

        } else {
            ErrorBodyStore.addErrorBody(errorBody);
        }

        return false;
    }

    @Override
    public boolean deleteProduct(long Id) {
        ErrorBody errorBody = productValidator.validateId(Id);

        if (errorBody == null) {
            productRepository.deleteProduct(Id);
            return true;
        } else {
            ErrorBodyStore.addErrorBody(errorBody);
        }

        return false;
    }

}
