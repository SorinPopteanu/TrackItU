package com.trackitu.accounts.controller;

import com.trackitu.accounts.constants.AccountsConstants;
import com.trackitu.accounts.dto.*;
import com.trackitu.accounts.dto.accounts.CreateAccountDto;
import com.trackitu.accounts.dto.accounts.FetchCustomerAccountDto;
import com.trackitu.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "CRUD REST APIs for Accounts", description = "CRUD REST APIs for managing customer accounts")
@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

  private final AccountsContactInfoDto accountsContactInfoDto;
  private final IAccountsService iAccountsService;
  private final Environment environment;
  private final Logger logger = LoggerFactory.getLogger(AccountsController.class);

  public AccountsController(IAccountsService iAccountsService, AccountsContactInfoDto accountsContactInfoDto, Environment environment) {
    this.iAccountsService = iAccountsService;
    this.accountsContactInfoDto = accountsContactInfoDto;
    this.environment = environment;
  }

  @Value("${build.version}")
  private String buildVersion;

  @Operation(summary = "Create Account REST API", description = "Create a new customer account")
  @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(
      @Valid @RequestBody CreateAccountDto createAccountDto) {
    iAccountsService.createAccount(createAccountDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }

  @Operation(summary = "Fetch Account Details REST API", description = "REST API to fetch Customer & Account details based on email")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetch")
  public ResponseEntity<FetchCustomerAccountDto> fetchAccountDetails(
      @Email(message = "Email address is not valid") @RequestParam String email) {
    FetchCustomerAccountDto fetchCustomerAccountDto = iAccountsService.fetchAccountDetails(email);
    return ResponseEntity.status(HttpStatus.OK).body(fetchCustomerAccountDto);
  }

  @Operation(summary = "Fetch All Accounts REST API", description = "REST API to fetch all Customer & Account details")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchCustomerAccountDto>> fetchAllAccounts() {
    List<FetchCustomerAccountDto> fetchCustomerAccountDtoList = iAccountsService.fetchAllAccounts();
    return ResponseEntity.status(HttpStatus.OK).body(fetchCustomerAccountDtoList);
  }

  @Operation(summary = "Update Account Details REST API", description = "REST API to update Customer & Account details based on an account number")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccountDetails(
      @Valid @RequestBody FetchCustomerAccountDto fetchCustomerAccountDto) {
    boolean isUpdated = iAccountsService.updateAccount(fetchCustomerAccountDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
  }

  @Operation(summary = "Change Account Status REST API", description = "REST API to change the status of an account based on email")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @PutMapping("/changeStatus")
  public ResponseEntity<ResponseDto> changeStatusAccount(
      @Email(message = "Email address is not valid") @RequestParam String email) {
    boolean isChanged = iAccountsService.changeStatusAccount(email);
    if (isChanged) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
  }

  @Operation(summary = "Delete Account & Customer Details REST API", description = "REST API to delete Customer & Account details based on email")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
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

  @Operation(summary = "Get Contact Info", description = "Contact Info details that can be reached out in case of any issues")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/contact-info")
  public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
    logger.info("Invoked contact-info endpoint to fetch contact information");
    return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
  }

  @Operation(summary = "Get Build information", description = "Get Build information that is deployed into accounts microservice")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/build-info")
  public ResponseEntity<String> getBuildInfo() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(buildVersion);
  }

  @Operation(summary = "Get Java version", description = "Get Java versions details that is installed into accounts microservice")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/java-version")
  public ResponseEntity<String> getJavaVersion() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(environment.getProperty("JAVA_HOME"));
  }

}
