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

@Tag(name = "CRUD REST APIs pentru utilizatori", description = "REST APIs care permit preluarea detaliilor clientilor")
@RestController
@RequestMapping(path = "/api/v1/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

  private final ICustomerService iCustomerService;
  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  public CustomerController(ICustomerService iCustomerService) {
    this.iCustomerService = iCustomerService;
  }

  @Operation(summary = "REST API pentru preluarea detaliilor profesorilor", description = "REST API pentru preluarea detaliilor profesorilor pe baza adresei de email (detaliile utilizatorului + salile alocate acestora)")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetchCustomerDetails")
  public ResponseEntity<FetchCustomerDetailsDto> fetchCustomerDetails(
      @Email(message = "Email address is not valid") @RequestParam String email) {
    logger.debug("fetchCustomerDetails method start");
    FetchCustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(email);
    logger.debug("fetchCustomerDetails method end");
    return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
  }

  @Operation(summary = "REST API pentru preluarea detaliilor utilizatorilor", description = "REST API pentru preluarea detaliilor utilizatorului pe baza id-ului")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetch")
  public ResponseEntity<CustomerDto> fetchCustomer(@RequestParam Long id) {
    CustomerDto customerDto = iCustomerService.fetchCustomer(id);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }
}
