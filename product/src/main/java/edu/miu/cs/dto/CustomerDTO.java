package edu.miu.cs.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
}