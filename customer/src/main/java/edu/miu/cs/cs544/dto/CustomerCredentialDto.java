package edu.miu.cs.cs544.dto;

import lombok.Data;

@Data
public class CustomerCredentialDto {
    private Integer id;
    private String email;
    private String password;
    private boolean admin;

}
