package com.trackitu.assets.service.client;


import com.trackitu.assets.dto.accounts.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountsFallback implements AccountsFeignClient {

  @Override
  public ResponseEntity<CustomerDto> fetchCustomer(Long id) {
    return null;
  }
}

