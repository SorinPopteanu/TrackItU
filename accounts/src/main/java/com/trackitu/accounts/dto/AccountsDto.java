package com.trackitu.accounts.dto;

import com.trackitu.accounts.enums.AccountStatus;
import com.trackitu.accounts.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountsDto {

  @NotNull(message = "Account number can not be a null or empty")
  private Long accountNumber;

  @NotNull(message = "Account type can not be a null or empty")
  private AccountType accountType;

  @NotNull(message = "Account status can not be a null or empty")
  private AccountStatus accountStatus;
}
