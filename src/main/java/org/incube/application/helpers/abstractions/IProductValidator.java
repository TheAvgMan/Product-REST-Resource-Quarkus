package org.incube.application.helpers.abstractions;

import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.application.helpers.implementations.Result;
import org.incube.domain.entities.Product;

import java.util.List;

public interface IProductValidator {

    Result<Boolean, ErrorBody> validateId(long Id);
    Result<Boolean, ErrorBody> validatePageNumber(int page);
    Result<Boolean, ErrorBody> validateProduct(Product product);
    Result<Boolean, ErrorBody> validateReturnedList(List<Product> products);

}
