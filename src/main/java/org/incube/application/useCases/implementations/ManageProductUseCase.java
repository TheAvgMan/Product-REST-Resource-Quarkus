package org.incube.application.useCases.implementations;

import org.incube.application.useCases.abstractions.IManageProductUseCase;
import org.incube.domain.entities.Product;

public class ManageProductUseCase implements IManageProductUseCase {

    @Override
    public boolean createProduct(Product product) {
        return false;
    }

    @Override
    public Product updateProduct(long Id, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(long Id) {
        return false;
    }

}
