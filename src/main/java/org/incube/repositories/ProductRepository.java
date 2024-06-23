package org.incube.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.incube.entities.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {}
