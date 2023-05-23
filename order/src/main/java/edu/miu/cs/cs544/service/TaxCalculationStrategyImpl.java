package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Cart;
import org.springframework.stereotype.Component;

@Component
public class TaxCalculationStrategyImpl implements TaxCalculationStrategy {

    public static final double SEVEN_PERCENT = 0.07;

    @Override
    public Double calculateTax(Cart cart) {
        return SEVEN_PERCENT * cart.getSubTotal();
    }
}
