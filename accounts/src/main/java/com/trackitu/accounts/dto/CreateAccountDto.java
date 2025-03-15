package com.trackitu.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAccountDto {

    @Valid
    @NotNull
    private CustomerDto customerDto;

    @NotNull
    private String accountType;
}
