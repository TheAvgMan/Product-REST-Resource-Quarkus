package org.incube.domain.policies;

import org.incube.domain.entities.Product;

import java.util.List;

public class Discount implements IDiscountPolicy{

    @Override
    public double discountForEvent(Product product) {
        // new product price after 20% off
        return product.getPrice() * 0.80;
    }

    @Override
    public double discountForTotalMoreThanSomeAmount(List<Product> products) {
        // new total price after 20% off of the old total price
        // that's only if the old total price was above a certain specified value
        // here, this certain specified value is 1000

        double sum = 0;
        for (var product : products) {
            sum += product.getPrice();
        }

        if (sum > 1000) {
            return sum * 0.80;
        }

        return sum;
    }

}
