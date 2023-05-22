package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.dto.CreditCardDto;
import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.repository.CreditCardRepository;
import edu.miu.cs.cs544.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<CreditCardDto> findAll(Integer customerId){

        var creditCardDtoArrayList = new ArrayList<CreditCardDto>();
        customerRepository.findById(customerId).ifPresent(customer -> {
            customer.getCreditCards().forEach(cc -> {
                creditCardDtoArrayList.add(modelMapper.map(cc, CreditCardDto.class));
            });
        });

        return creditCardDtoArrayList;
    }

    public CreditCardDto findAllByCardId(Integer customerId, Integer cardId){

        var creditCard = new CreditCard();
        creditCard = creditCardRepository.findAllByCardId(customerId, cardId);
        return modelMapper.map(creditCard, CreditCardDto.class);

    }

    @Transactional
    public boolean addNewCreditCard(Integer customerId, CreditCardDto creditCardDto){
        try{
            AtomicBoolean isSaved = new AtomicBoolean(false);
            var creditCard = modelMapper.map(creditCardDto, CreditCard.class);

            customerRepository.findById(customerId).ifPresent(customer -> {
                        customer.getCreditCards().add(creditCard);
                        customerRepository.save(customer);
                        isSaved.set(true);
                    });

            return isSaved.get();

        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

}
