package edu.miu.cs.cs544.service.converter;

import edu.miu.cs.cs544.contract.CustomerDto;
import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.service.Converter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {OrderLineItemConverter.class})
@Component
public interface OrderConverter extends Converter<Order, OrderDto> {

    @AfterMapping
    default void afterMapping(@MappingTarget OrderDto orderDto, Order order) {
        if (orderDto.getCustomer() == null) {
            orderDto.setCustomer(new CustomerDto());
        }
        orderDto.getCustomer().setId(order.getCustomerId());
    }

}
