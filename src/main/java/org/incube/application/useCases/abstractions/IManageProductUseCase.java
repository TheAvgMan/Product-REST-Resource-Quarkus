package org.incube.application.useCases.abstractions;

import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.Result;
import org.incube.domain.entities.Product;

public interface IManageProductUseCase {

    Result<Boolean, ErrorBody> createProduct(Product product);
    Result<Boolean, ErrorBody> updateProduct(long Id, Product product);
    Result<Boolean, ErrorBody> deleteProduct(long Id);

}
