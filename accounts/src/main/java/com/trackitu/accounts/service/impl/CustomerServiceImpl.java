package com.trackitu.accounts.service.impl;

import com.trackitu.accounts.dto.accounts.AccountsDto;
import com.trackitu.accounts.dto.customer.CustomerDto;
import com.trackitu.accounts.dto.customer.FetchCustomerDetailsDto;
import com.trackitu.accounts.dto.rooms.FetchRoomProfessorDto;
import com.trackitu.accounts.entity.Accounts;
import com.trackitu.accounts.entity.Customer;
import com.trackitu.accounts.exception.ResourceNotFoundException;
import com.trackitu.accounts.mapper.AccountsMapper;
import com.trackitu.accounts.mapper.CustomerMapper;
import com.trackitu.accounts.repository.AccountsRepository;
import com.trackitu.accounts.repository.CustomerRepository;
import com.trackitu.accounts.service.ICustomerService;
import com.trackitu.accounts.service.client.RoomsFeignClient;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;
  private final RoomsFeignClient roomsFeignClient;

  /**
   * @param email - Input email
   * @return Customer Details based on an email
   */
  @Override
  public FetchCustomerDetailsDto fetchCustomerDetails(String email, String correlationId) {
    Customer customer = customerRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));
    Accounts accounts = accountsRepository.findByCustomerId(customer.getId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customerId",
            customer.getId().toString()));
    FetchCustomerDetailsDto fetchCustomerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(
        customer, new FetchCustomerDetailsDto());
    fetchCustomerDetailsDto.setAccountsDto(
        AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

    ResponseEntity<List<FetchRoomProfessorDto>> roomProfessorDtoResponseEntity = roomsFeignClient.fetchRoomByProfessorId(correlationId,
        customer.getId());
    fetchCustomerDetailsDto.setRoomProfessorDto(roomProfessorDtoResponseEntity.getBody());

    return fetchCustomerDetailsDto;
  }

  /**
   * @param id            - Input customer id
   * @return Customer basic details based on their id
   */
  @Override
  public CustomerDto fetchCustomer(Long id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id.toString()));
    return CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
  }
}
