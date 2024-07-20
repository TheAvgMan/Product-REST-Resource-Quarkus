package org.incube.infrastructure.repositories.implementations;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import org.incube.application.infrastructureAbstractions.IProductRepository;
import org.incube.domain.entities.Product;
import org.incube.infrastructure.repositories.external.PanacheProductRepository;

import java.util.List;

public class ProductRepository implements IProductRepository {

    PanacheProductRepository panacheProductRepository = new PanacheProductRepository();

    @Override
    public List<Product> fetchAllProducts() {
        return panacheProductRepository.listAll();
    }

    @Override
    public List<Product> fetchPageProducts(int page) {
        PanacheQuery<Product> allProducts = panacheProductRepository.findAll(Sort.ascending("name"));
        List<Product> pageList = allProducts.page(Page.of(page - 1, 3)).list();

        return pageList;
    }

    @Override
    public Product fetchProductById(long Id) {
        return panacheProductRepository.findById(Id);
    }

    @Override
    public void storeProduct(Product product) {
        panacheProductRepository.persist(product);
    }

    @Override
    public void updateProduct(long Id, Product product) {
        Product retrievedProduct = panacheProductRepository.findById(Id);

        retrievedProduct.setName(product.getName());
        retrievedProduct.setDescription(product.getDescription());
        retrievedProduct.setPrice(product.getPrice());
        retrievedProduct.setImage(product.getImage());
    }

    @Override
    public void deleteProduct(long Id) {
        Product retrievedProduct = panacheProductRepository.findById(Id);
        panacheProductRepository.delete(retrievedProduct);
    }
}
