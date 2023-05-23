package edu.miu.cs.cs544.service.converter;

import edu.miu.cs.cs544.contract.ProductDto;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.service.Converter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductConverter extends Converter<Product, ProductDto> {
}