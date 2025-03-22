package com.trackitu.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerAccountDto {

  @Valid
  @NotNull
  private CustomerDto customerDto;
  @Valid
  @NotNull
  private AccountsDto accountsDto;
}
