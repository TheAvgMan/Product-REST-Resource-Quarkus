package org.incube.application.useCases.implementations;

import org.incube.application.useCases.abstractions.IShowProductsInCartUseCase;
import org.incube.domain.entities.Product;

import java.util.List;

public class ShowProductsInCartUseCase implements IShowProductsInCartUseCase {

    @Override
    public List<Product> getCartProducts() {
        return List.of();
    }

}
