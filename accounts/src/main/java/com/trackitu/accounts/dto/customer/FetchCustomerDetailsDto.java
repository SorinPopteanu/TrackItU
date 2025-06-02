package com.trackitu.accounts.dto.customer;

import com.trackitu.accounts.dto.accounts.AccountsDto;
import com.trackitu.accounts.dto.rooms.FetchRoomProfessorDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Schema(
    name = "CustomerDetails",
    description = "Schema to hold Customer, Account and Rooms information"
)
@Data
public class FetchCustomerDetailsDto {

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

  @Schema(
      description = "Room allocation of the professor"
  )
  @Valid
  @NotNull
  private List<FetchRoomProfessorDto> roomProfessorDto;

}
