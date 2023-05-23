package edu.miu.cs.cs544.contract;

import edu.miu.cs.cs544.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;

    private List<OrderLineItemDto> lineItems;

    private OrderStatus status;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private Double subTotal;

    private Double taxAmount;

    private Double shippingCost;

    private Double total;

    private AddressDto shippingAddress;

    private CreditCardDto creditCard;

    private CustomerDto customer;
}
