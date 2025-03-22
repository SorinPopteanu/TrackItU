package com.trackitu.accounts.dto;

import com.trackitu.accounts.enums.AccountStatus;
import com.trackitu.accounts.enums.AccountType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAccountDto {

  @Valid
  @NotNull
  private CustomerDto customerDto;

  @NotNull
  private AccountType accountType;

  private AccountStatus accountStatus;
}
