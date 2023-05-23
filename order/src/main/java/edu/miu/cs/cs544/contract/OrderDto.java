package edu.miu.cs.cs544.contract;

import edu.miu.cs.cs544.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Integer customerId;

    private Integer productId;

    private OrderStatus status;

    private LocalDate orderDate;

    private Double total;
}
