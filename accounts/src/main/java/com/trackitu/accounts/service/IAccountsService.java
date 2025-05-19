package com.trackitu.accounts.service;

import com.trackitu.accounts.dto.accounts.CreateAccountDto;
import com.trackitu.accounts.dto.accounts.FetchCustomerAccountDto;
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
  FetchCustomerAccountDto fetchAccountDetails(String email);

  /**
   * @return List of all the Accounts
   */
  List<FetchCustomerAccountDto> fetchAllAccounts();

  /**
   * @param fetchCustomerAccountDto - FetchCustomerAccountDto Object
   * @return boolean indicating if the update of the Account details is successful or not
   */
  boolean updateAccount(FetchCustomerAccountDto fetchCustomerAccountDto);

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