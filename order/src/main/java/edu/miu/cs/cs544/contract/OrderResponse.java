package edu.miu.cs.cs544.contract;

import lombok.Data;

@Data
public class OrderResponse {

    private Double taxAmount;

    public OrderResponse(Double taxAmount, Double shippingCost) {
        this.taxAmount = taxAmount;
        this.shippingCost = shippingCost;
    }

    private Double shippingCost;

    public OrderResponse() {
    }
}
