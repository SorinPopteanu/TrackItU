package com.trackitu.accounts.controller;

import com.trackitu.accounts.constants.AccountsConstants;
import com.trackitu.accounts.dto.*;
import com.trackitu.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(
    name = "CRUD REST APIs for Accounts",
    description = "CRUD REST APIs for managing customer accounts"
)
@RestController
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

  private IAccountsService iAccountsService;

  @Operation(
      summary = "Create Account REST API",
      description = "Create a new customer account"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status CREATED"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(
      @Valid @RequestBody CreateAccountDto createAccountDto) {
    iAccountsService.createAccount(createAccountDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Account Details REST API",
      description = "REST API to fetch Customer & Account details based on email"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @GetMapping("/fetch")
  public ResponseEntity<CustomerAccountDto> fetchAccountDetails(
      @Email(message = "Email address is not valid") @RequestParam String email) {
    CustomerAccountDto customerAccountDto = iAccountsService.fetchAccountDetails(email);
    return ResponseEntity.status(HttpStatus.OK).body(customerAccountDto);
  }

  @Operation(
      summary = "Fetch All Accounts REST API",
      description = "REST API to fetch all Customer & Account details"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @GetMapping("/fetchAll")
  public ResponseEntity<List<CustomerAccountDto>> fetchAllAccounts() {
    List<CustomerAccountDto> customerAccountDtoList = iAccountsService.fetchAllAccounts();
    return ResponseEntity.status(HttpStatus.OK).body(customerAccountDtoList);
  }

  @Operation(
      summary = "Update Account Details REST API",
      description = "REST API to update Customer & Account details based on an account number"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccountDetails(
      @Valid @RequestBody CustomerAccountDto customerAccountDto) {
    boolean isUpdated = iAccountsService.updateAccount(customerAccountDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
  }

  @Operation(
      summary = "Change Account Status REST API",
      description = "REST API to change the status of an account based on email"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @PutMapping("/changeStatus")
  public ResponseEntity<ResponseDto> changeStatusAccount(
      @Email(message = "Email address is not valid")
      @RequestParam String email) {
    boolean isChanged = iAccountsService.changeStatusAccount(email);
    if (isChanged) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
  }

  @Operation(
      summary = "Delete Account & Customer Details REST API",
      description = "REST API to delete Customer & Account details based on email"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAccount(
      @Email(message = "Email address is not valid") @RequestParam String email) {
    boolean isDeleted = iAccountsService.deleteAccount(email);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
  }
}
