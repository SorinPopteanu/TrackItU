package com.trackitu.accounts.mapper;

import com.trackitu.accounts.dto.customer.CustomerDto;
import com.trackitu.accounts.dto.customer.FetchCustomerDetailsDto;
import com.trackitu.accounts.entity.Customer;

public class CustomerMapper {

  public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
    customerDto.setId(customer.getId());
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

  public static FetchCustomerDetailsDto mapToCustomerDetailsDto(Customer customer, FetchCustomerDetailsDto customerDetailsDto) {
    customerDetailsDto.setCustomerDto(mapToCustomerDto(customer, new CustomerDto()));
    return customerDetailsDto;
  }

}
