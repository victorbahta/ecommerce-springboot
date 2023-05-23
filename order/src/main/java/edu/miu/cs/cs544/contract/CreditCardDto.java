package edu.miu.cs.cs544.contract;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditCardDto {

    private Integer id;
    private String name;
    private String creditNumber;
    private LocalDate expirationDate;
    private String securityCode;
}
