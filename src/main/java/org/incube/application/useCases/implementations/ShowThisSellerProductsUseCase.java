package org.incube.application.useCases.implementations;

import org.incube.application.useCases.abstractions.IShowThisSellerProductsUseCase;
import org.incube.domain.entities.Product;

import java.util.List;

public class ShowThisSellerProductsUseCase implements IShowThisSellerProductsUseCase {

    @Override
    public List<Product> getSellerProducts() {
        return List.of();
    }

}
