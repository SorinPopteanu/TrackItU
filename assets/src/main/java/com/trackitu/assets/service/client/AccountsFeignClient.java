package com.trackitu.assets.service.client;

import com.trackitu.assets.dto.accounts.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("accounts")
public interface AccountsFeignClient {

  @GetMapping("/api/v1/customer/fetch")
  ResponseEntity<CustomerDto> fetchCustomer(@RequestParam Long id);

}
