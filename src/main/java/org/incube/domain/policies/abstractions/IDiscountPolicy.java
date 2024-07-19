package org.incube.domain.policies.abstractions;

import org.incube.domain.entities.Product;

import java.util.List;

public interface IDiscountPolicy {

    double discountForEvent(Product product);
    double discountForTotalMoreThanSomeAmount(List<Product> products);

}
