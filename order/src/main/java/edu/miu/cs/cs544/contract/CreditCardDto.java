package edu.miu.cs.cs544.contract;

import lombok.Data;

@Data
public class CreditCardDto {

    private String number;

    private Integer expiredMonth;
    private Integer expiredYear;

    private String securityCode;
}
