package org.incube.application.useCases.abstractions;

import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.Result;
import org.incube.domain.entities.Product;

public interface IShowSelectedProductDetailsUseCase {

    Result<Product, ErrorBody> getProductDetails(long Id);

}
