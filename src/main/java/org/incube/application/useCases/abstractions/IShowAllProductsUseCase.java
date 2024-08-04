package org.incube.application.useCases.abstractions;

import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.Result;
import org.incube.domain.entities.Product;

import java.util.List;

public interface IShowAllProductsUseCase {

    Result<List<Product>, ErrorBody> getAllProducts();
    Result<List<Product>, ErrorBody> getPageProducts(int page);

}
