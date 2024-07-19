package org.incube.application.helpers.implementations;

import org.incube.application.helpers.abstractions.IProductValidator;
import org.incube.domain.entities.Product;

public class ProductValidator implements IProductValidator {

    @Override
    public ErrorBody validateId(long Id) {
        return null;
    }

    @Override
    public ErrorBody validateProduct(Product product) {
        return null;
    }

}
