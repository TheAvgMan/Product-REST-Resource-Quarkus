package org.incube.infrastructure.repositories.external;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.incube.domain.entities.Product;

@ApplicationScoped
public class PanacheProductRepository implements PanacheRepository<Product> {}
