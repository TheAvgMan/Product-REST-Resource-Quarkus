package org.incube.application.infrastructureAbstractions;

import org.incube.domain.entities.Product;

import java.util.List;

public interface IProductRepository {

    List<Product> fetchAllProducts();
    List<Product> fetchPageProducts(int page);
    Product fetchProductById(long Id);
    void storeProduct(Product product);
    void updateProduct(long Id, Product product);
    void deleteProduct(long Id);

}
