package edu.miu.cs.cs544.service.converter;

import edu.miu.cs.cs544.contract.CartDto;
import edu.miu.cs.cs544.contract.OrderResponse;
import edu.miu.cs.cs544.domain.Cart;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.service.Converter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderConverter extends Converter<Order, OrderResponse> {
}
