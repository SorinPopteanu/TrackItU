package com.trackitu.accounts.service;

import com.trackitu.accounts.dto.CreateAccountDto;
import com.trackitu.accounts.dto.CustomerAccountDto;
import java.util.List;

public interface IAccountsService {

  /**
   * @param createAccountDto - CreateAccountDto Object
   */
  void createAccount(CreateAccountDto createAccountDto);

  /**
   * @param email - Input last name
   * @return Account Details based on the email
   */
  CustomerAccountDto fetchAccountDetails(String email);

  /**
   * @return List of all the Accounts
   */
  List<CustomerAccountDto> fetchAllAccounts();

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

  /**
   * @param email - Input email
   * @return boolean indicating if the status of the Account is changed or not
   */
  boolean changeStatusAccount(String email);
}