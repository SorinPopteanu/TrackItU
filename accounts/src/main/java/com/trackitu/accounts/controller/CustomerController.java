package com.trackitu.accounts.controller;

import com.trackitu.accounts.dto.ErrorResponseDto;
import com.trackitu.accounts.dto.customer.CustomerDto;
import com.trackitu.accounts.dto.customer.FetchCustomerDetailsDto;
import com.trackitu.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "REST API for Customers in TrackItU", description = "REST API to Fetch customer details")
@RestController
@RequestMapping(path = "/api/v1/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

  private final ICustomerService iCustomerService;
  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  public CustomerController(ICustomerService iCustomerService) {
    this.iCustomerService = iCustomerService;
  }

  @Operation(summary = "Fetch Customer Details REST API", description = "REST API to fetch Customer details based on email")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetchCustomerDetails")
  public ResponseEntity<FetchCustomerDetailsDto> fetchCustomerDetails(
      @Email(message = "Email address is not valid") @RequestParam String email,
      @RequestHeader("trackItU-correlation-id") String correlationId) {
    logger.debug("trackItU-correlation-id found: {}", correlationId);
    FetchCustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(email, correlationId);
    return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
  }

  @GetMapping("/fetch")
  public ResponseEntity<CustomerDto> fetchCustomer(@RequestParam Long id) {
    CustomerDto customerDto = iCustomerService.fetchCustomer(id);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }
}
