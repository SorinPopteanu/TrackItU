package com.trackitu.accounts.service;

import com.trackitu.accounts.dto.customer.CustomerDto;
import com.trackitu.accounts.dto.customer.FetchCustomerDetailsDto;

public interface ICustomerService {

  /**
   *
   * @param email - Input email
   * @return Customer Details based on an email
   */
  FetchCustomerDetailsDto fetchCustomerDetails(String email);

  /**
   * @param id - Input customer id
   * @return Customer basic details based on their id
   */
  CustomerDto fetchCustomer(Long id);
}
