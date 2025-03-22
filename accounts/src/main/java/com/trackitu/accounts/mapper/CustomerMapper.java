package com.trackitu.accounts.mapper;

import com.trackitu.accounts.dto.CustomerDto;
import com.trackitu.accounts.entity.Customer;

public class CustomerMapper {

  public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
    customerDto.setFirstName(customer.getFirstName());
    customerDto.setLastName(customer.getLastName());
    customerDto.setEmail(customer.getEmail());
    customerDto.setMobileNumber(customer.getMobileNumber());
    return customerDto;
  }

  public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
    customer.setFirstName(customerDto.getFirstName());
    customer.setLastName(customerDto.getLastName());
    customer.setEmail(customerDto.getEmail());
    customer.setMobileNumber(customerDto.getMobileNumber());
    return customer;
  }
}
