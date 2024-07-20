package org.incube.application.useCases.implementations;

import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.application.useCases.abstractions.IShowAllProductsUseCase;
import org.incube.domain.entities.Product;

import java.util.List;

public class ShowAllProductsUseCase implements IShowAllProductsUseCase {

    IProductRepository productRepository;

    public ShowAllProductsUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.fetchAllProducts();
    }

    @Override
    public List<Product> getPageProducts(int page) {
        return productRepository.fetchPageProducts(page);
    }

}
