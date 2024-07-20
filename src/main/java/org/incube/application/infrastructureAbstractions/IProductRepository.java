package org.incube.application.infrastructureAbstractions;

import org.incube.domain.entities.Product;

import java.util.List;

public interface IProductRepository {

    List<Product> fetchAllProducts();
    List<Product> fetchPageProducts(int page);
    Product fetchProductById(long Id);
    boolean storeProduct(Product product);
    boolean updateProduct(long Id, Product product);
    boolean deleteProduct(long Id);

}
