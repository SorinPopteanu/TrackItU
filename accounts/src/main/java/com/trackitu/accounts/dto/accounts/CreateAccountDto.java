package com.trackitu.accounts.dto.accounts;

import com.trackitu.accounts.dto.customer.CustomerDto;
import com.trackitu.accounts.enums.AccountStatus;
import com.trackitu.accounts.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
    name = "CreateAccount",
    description = "Schema to hold relevant information to create an account"
)
@Data
public class CreateAccountDto {

  @Schema(
      description = "Customer information"
  )
  @Valid
  @NotNull
  private CustomerDto customerDto;

  @Schema(
      description = "Account type", example = "ADMIN"
  )
  @NotNull
  private AccountType accountType;

  @Schema(
      description = "Account status", example = "ACTIVATED"
  )
  private AccountStatus accountStatus;
}
