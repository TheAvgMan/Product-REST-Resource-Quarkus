package org.incube.application.helpers.abstractions;

import org.incube.application.helpers.implementations.ErrorBody;
import org.incube.domain.entities.Product;

public interface IProductValidator {

    ErrorBody validateId(long Id);
    ErrorBody validateProduct(Product product);

}
