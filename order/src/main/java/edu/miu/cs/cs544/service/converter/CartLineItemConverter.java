package edu.miu.cs.cs544.service.converter;

import edu.miu.cs.cs544.contract.CartLineItemDto;
import edu.miu.cs.cs544.domain.CartLineItem;
import edu.miu.cs.cs544.service.Converter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {ProductConverter.class})
@Component
public interface CartLineItemConverter extends Converter<CartLineItem, CartLineItemDto> {
}
