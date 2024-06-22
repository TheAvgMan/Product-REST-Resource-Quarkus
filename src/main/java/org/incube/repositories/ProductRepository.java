package org.incube.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.incube.entities.Product;

public interface ProductRepository extends PanacheRepository<Product> {
}
