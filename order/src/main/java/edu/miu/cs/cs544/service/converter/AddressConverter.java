package edu.miu.cs.cs544.service.converter;

import edu.miu.cs.cs544.contract.AddressDto;
import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.service.Converter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AddressConverter extends Converter<Address, AddressDto> {
}
