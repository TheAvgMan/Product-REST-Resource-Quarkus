package org.incube.application.useCases.abstractions;

import org.incube.domain.entities.Product;

public interface IShowSelectedProductDetailsUseCase {

    Product getProductDetails(long Id);

}
