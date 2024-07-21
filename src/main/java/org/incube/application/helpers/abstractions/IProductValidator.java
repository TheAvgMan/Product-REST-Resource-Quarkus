package org.incube.application.helpers.abstractions;

import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.domain.entities.Product;

import java.util.List;

public interface IProductValidator {

    ErrorBody validateId(long Id);
    ErrorBody validatePageNumber(int page);
    ErrorBody validateProduct(Product product);
    ErrorBody validateReturnedList(List<Product> products);

}
