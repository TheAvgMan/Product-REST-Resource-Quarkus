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

    @Inject
    PanacheProductRepository panacheProductRepository;

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
    public boolean storeProduct(Product product) {
        panacheProductRepository.persist(product);
        return true;
    }

    @Override
    public boolean updateProduct(long Id, Product product) {
        Product entity = panacheProductRepository.findById(Id);

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImage(product.getImage());

        return true;
    }

    @Override
    public boolean deleteProduct(long Id) {
        Product entity = panacheProductRepository.findById(Id);
        panacheProductRepository.delete(entity);

        return true;
    }
}
