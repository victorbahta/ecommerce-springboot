package edu.miu.cs.cs544.service.converter;

import edu.miu.cs.cs544.contract.CreditCardDto;
import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.service.Converter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CreditCardConverter extends Converter<CreditCard, CreditCardDto> {
}
