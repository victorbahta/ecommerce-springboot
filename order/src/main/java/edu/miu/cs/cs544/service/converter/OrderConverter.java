package edu.miu.cs.cs544.service.converter;

import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.service.Converter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderConverter extends Converter<Order, OrderDto> {
}
