package edu.miu.cs.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Collection;

@Data
@ToString
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
}