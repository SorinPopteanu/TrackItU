package com.trackitu.accounts.dto;

import lombok.Data;

@Data
public class CreateAccountDto {

    private CustomerDto customerDto;

    private String accountType;
}
