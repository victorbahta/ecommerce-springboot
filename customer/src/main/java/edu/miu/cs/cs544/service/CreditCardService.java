package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.dto.CreditCardDto;
import edu.miu.cs.cs544.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreditCardService {

    public List<CreditCardDto> findAll(Integer customerId);

    public boolean addNewCreditCard(Integer customerId, CreditCardDto creditCardDto);
}
