package com.trackitu.accounts.service;

import com.trackitu.accounts.dto.CreateAccountDto;
import com.trackitu.accounts.dto.CustomerAccountDto;

public interface IAccountsService {

    /**
     * @param createAccountDto - CreateAccountDto Object
     */
    void createAccount(CreateAccountDto createAccountDto);

    /**
     * @param email - Input last name
     * @return Account Details based on the last name
     */
    CustomerAccountDto fetchAccountDetails(String email);

    /**
     * @param customerAccountDto - CustomerAccountDto Object
     * @return boolean indicating if the update of the Account details is successful or not
     */
    boolean updateAccount(CustomerAccountDto customerAccountDto);

    /**
     * @param email - Input email
     * @return boolean indicating if the deletion of the Account is successful or not
     */
    boolean deleteAccount(String email);

}