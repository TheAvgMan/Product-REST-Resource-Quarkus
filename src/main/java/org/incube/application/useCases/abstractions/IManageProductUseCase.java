package org.incube.application.useCases.abstractions;

import org.incube.domain.entities.Product;

public interface IManageProductUseCase {

    boolean createProduct(Product product);
    Product updateProduct(long Id, Product product);
    boolean deleteProduct(long Id);

}
