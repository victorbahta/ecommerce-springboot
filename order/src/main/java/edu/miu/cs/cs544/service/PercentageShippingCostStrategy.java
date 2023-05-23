package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Cart;
import org.springframework.stereotype.Component;

@Component
public class PercentageShippingCostStrategy implements ShippingCostStrategy {

    public static final double FIVE_PERCENT = 0.05;

    @Override
    public Double calculateShippingCost(Cart cart) {
        return FIVE_PERCENT * cart.getSubTotal();
    }
}
