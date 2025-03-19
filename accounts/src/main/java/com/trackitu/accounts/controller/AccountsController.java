package com.trackitu.accounts.controller;

import com.trackitu.accounts.constants.AccountsConstants;
import com.trackitu.accounts.dto.*;
import com.trackitu.accounts.service.IAccountsService;
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

@RestController
@RequestMapping(path = "/api/v1/accounts", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(
            @Valid
            @RequestBody CreateAccountDto createAccountDto) {
        iAccountsService.createAccount(createAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerAccountDto> fetchAccountDetails(
            @Email(message = "Email address is not valid")
            @RequestParam String email) {
        CustomerAccountDto customerAccountDto = iAccountsService.fetchAccountDetails(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerAccountDto);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<CustomerAccountDto>> fetchAllAccounts() {
        List<CustomerAccountDto> customerAccountDtoList = iAccountsService.fetchAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(customerAccountDtoList);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(
            @Valid
            @RequestBody CustomerAccountDto customerAccountDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerAccountDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

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

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @Email(message = "Email address is not valid")
            @RequestParam String email) {
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
