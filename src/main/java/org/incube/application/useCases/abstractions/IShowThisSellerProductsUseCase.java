package org.incube.application.useCases.abstractions;

import org.incube.domain.entities.Product;

import java.util.List;

public interface IShowThisSellerProductsUseCase {

    List<Product> getSellerProducts();

}
