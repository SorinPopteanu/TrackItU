package com.trackitu.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountsDto {

    @NotNull(message = "Account number can not be a null or empty")
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be a null or empty")
    private String accountType;

}
