package org.incube.application.useCases.implementations;

import org.incube.application.useCases.abstractions.IShowAllProductsUseCase;
import org.incube.domain.entities.Product;

import java.util.List;

public class ShowAllProductsUseCase implements IShowAllProductsUseCase {

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getPageProducts(int page) {
        return List.of();
    }

}
