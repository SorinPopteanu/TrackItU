package com.trackitu.accounts.service;

import com.trackitu.accounts.dto.CreateAccountDto;
import com.trackitu.accounts.dto.CustomerAccountDto;

public interface IAccountsService {

    /**
     *
     * @param createAccountDto - CreateAccountDto Object
     */
    void createAccount(CreateAccountDto createAccountDto);

    /**
     *
     * @param email - Input last name
     * @return Account Details based on the last name
     */
    CustomerAccountDto fetchAccountDetails(String email);

    boolean updateAccount(CustomerAccountDto customerAccountDto);
}
