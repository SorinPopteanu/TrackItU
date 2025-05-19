package com.trackitu.accounts.dto.accounts;

import com.trackitu.accounts.enums.AccountStatus;
import com.trackitu.accounts.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Accounts",
    description = "Schema to hold Account information"
)
public class AccountsDto {

  @Schema(
      description = "Account number", example = "1234567890"
  )
  @NotNull(message = "Account number can not be a null or empty")
//  @Pattern(regexp="^[0-9]{10}$", message = "Account number must be 10 digits")
  private Long accountNumber;

  @Schema(
      description = "Account type", example = "ADMIN"
  )
  @NotNull(message = "Account type can not be a null or empty")
  private AccountType accountType;

  @Schema(
      description = "Account status", example = "ACTIVATED"
  )
  @NotNull(message = "Account status can not be a null or empty")
  private AccountStatus accountStatus;
}
