package org.incube.application.useCases.implementations;

import org.incube.application.useCases.abstractions.IManageProductUseCase;
import org.incube.domain.entities.Product;

public class ManageProductUseCase implements IManageProductUseCase {

    @Override
    public boolean createProduct(Product product) {
        // step 1: validate the product fields
        // if valid:
            // step 2: store the product in the database
            // step 3: return true
        // else:
            // step 2: use ErrorBody instance to communicate where the error was
            // step 2: return false

        return false;
    }

    @Override
    public boolean updateProduct(long Id, Product product) {
        // step 1: validate that the passed id exists
            // step 2: validate the product fields (if step 1 is true)
        // if valid:
            // step 3: store the product in the database
            // step 4: return true
        // else:
            // step 2: use ErrorBody instance to communicate where the error was
            // step 3: return false

        return false;
    }

    @Override
    public boolean deleteProduct(long Id) {
        // step 1: validate that the passed id exists
        // if valid:
            // step 3: delete the product from the database
            // step 4: return true
        // else:
            // step 2: use ErrorBody instance to communicate where the error was
            // step 3: return false

        return false;
    }

}
