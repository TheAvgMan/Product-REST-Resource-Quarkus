package org.incube.application.useCases.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.ErrorBodyStore;
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
    public Product getProductDetails(long Id) {
        ErrorBody errorBody = productValidator.validateId(Id);

        if (errorBody == null) {
            return productRepository.fetchProductById(Id);
        } else {
            ErrorBodyStore.addErrorBody(errorBody);
        }

        return null;
    }

}
