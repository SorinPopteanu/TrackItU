package com.trackitu.accounts.controller;

import com.trackitu.accounts.constants.AccountsConstants;
import com.trackitu.accounts.dto.*;
import com.trackitu.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(
            @RequestBody CreateAccountDto createAccountDto) {
        iAccountsService.createAccount(createAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerAccountDto> fetchAccountDetails(
            @RequestParam String email) {
        CustomerAccountDto customerAccountDto = iAccountsService.fetchAccountDetails(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerAccountDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(
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
}
