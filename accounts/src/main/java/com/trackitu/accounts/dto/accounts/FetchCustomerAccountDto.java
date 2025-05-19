package com.trackitu.accounts.dto.accounts;

import com.trackitu.accounts.dto.customer.CustomerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
    name = "CustomerAccount",
    description = "Schema to hold Customer and Account information"
)
@Data
public class FetchCustomerAccountDto {

  @Schema(
      description = "Customer information"
  )
  @Valid
  @NotNull
  private CustomerDto customerDto;

  @Schema(
      description = "Account information"
  )
  @Valid
  @NotNull
  private AccountsDto accountsDto;
}
