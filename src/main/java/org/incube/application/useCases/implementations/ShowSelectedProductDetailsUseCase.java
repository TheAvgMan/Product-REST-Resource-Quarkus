package org.incube.application.useCases.implementations;

import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.application.useCases.abstractions.IShowSelectedProductDetailsUseCase;
import org.incube.domain.entities.Product;

public class ShowSelectedProductDetailsUseCase implements IShowSelectedProductDetailsUseCase {

    IProductRepository productRepository;

    public ShowSelectedProductDetailsUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductDetails(long Id) {
        return productRepository.fetchProductById(Id);
    }

}
