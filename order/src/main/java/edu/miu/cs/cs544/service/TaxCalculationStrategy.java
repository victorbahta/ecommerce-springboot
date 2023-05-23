package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Cart;

public interface TaxCalculationStrategy {
    Double calculateTax(Cart cart);
}
