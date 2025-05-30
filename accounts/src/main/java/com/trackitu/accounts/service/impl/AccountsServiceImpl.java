package com.trackitu.accounts.service.impl;


import com.trackitu.accounts.dto.accounts.AccountsDto;
import com.trackitu.accounts.dto.accounts.CreateAccountDto;
import com.trackitu.accounts.dto.accounts.FetchCustomerAccountDto;
import com.trackitu.accounts.dto.customer.CustomerDto;
import com.trackitu.accounts.entity.Accounts;
import com.trackitu.accounts.entity.Customer;
import com.trackitu.accounts.enums.AccountStatus;
import com.trackitu.accounts.enums.AccountType;
import com.trackitu.accounts.exception.CustomerAlreadyExistsException;
import com.trackitu.accounts.exception.ResourceNotFoundException;
import com.trackitu.accounts.mapper.AccountsMapper;
import com.trackitu.accounts.mapper.CustomerMapper;
import com.trackitu.accounts.repository.AccountsRepository;
import com.trackitu.accounts.repository.CustomerRepository;
import com.trackitu.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.*;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;

  private final Map<String, ScheduledFuture<?>> scheduledTask = new ConcurrentHashMap<>();
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  /**
   * Create a new account
   *
   * @param createAccountDto - Input CreateAccountDto Object
   */
  @Override
  public void createAccount(CreateAccountDto createAccountDto) {
    Customer customer = CustomerMapper.mapToCustomer(createAccountDto.getCustomerDto(),
        new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already exists with email: " + customer.getEmail());
    }
    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer, createAccountDto.getAccountType()));
  }

  /**
   * @param customer, accountType
   * @return the new account details
   */
  private Accounts createNewAccount(Customer customer, AccountType accountType) {
    Accounts newAccount = new Accounts();
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setCustomerId(customer.getId());
    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(accountType);
    newAccount.setAccountStatus(AccountStatus.ACTIVATED);
    return newAccount;
  }

  /**
   * @param email - Input email
   * @return Accounts Details based on the email
   */
  @Override
  public FetchCustomerAccountDto fetchAccountDetails(String email) {
    Customer customer = customerRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));
    Accounts accounts = accountsRepository.findByCustomerId(customer.getId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customerId",
            customer.getId().toString()));

    FetchCustomerAccountDto fetchCustomerAccountDto = new FetchCustomerAccountDto();
    fetchCustomerAccountDto.setCustomerDto(CustomerMapper.mapToCustomerDto(customer, new CustomerDto()));
    fetchCustomerAccountDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
    return fetchCustomerAccountDto;
  }

  /**
   * @return List of all the Accounts
   */
  @Override
  public List<FetchCustomerAccountDto> fetchAllAccounts() {
    List<Accounts> accountsList = accountsRepository.findAll();
    List<FetchCustomerAccountDto> fetchCustomerAccountDtoList = new ArrayList<>();

    for (Accounts accounts : accountsList) {
      Customer customer = customerRepository.findById(accounts.getCustomerId()).orElseThrow(
          () -> new ResourceNotFoundException("Customer", "customerId",
              accounts.getCustomerId().toString()));
      FetchCustomerAccountDto fetchCustomerAccountDto = new FetchCustomerAccountDto();
      fetchCustomerAccountDto.setCustomerDto(
          CustomerMapper.mapToCustomerDto(customer, new CustomerDto()));
      fetchCustomerAccountDto.setAccountsDto(
          AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
      fetchCustomerAccountDtoList.add(fetchCustomerAccountDto);
    }
    return fetchCustomerAccountDtoList;
  }

  /**
   * @param fetchCustomerAccountDto - FetchCustomerAccountDto Object
   * @return boolean indicating if the update of the Account details is successful or not
   */
  @Override
  public boolean updateAccount(FetchCustomerAccountDto fetchCustomerAccountDto) {
    boolean isUpdated = false;
    AccountsDto accountsDto = fetchCustomerAccountDto.getAccountsDto();
    if (accountsDto != null) {
      Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
          () -> new ResourceNotFoundException("Account", "AccountNumber",
              accountsDto.getAccountNumber().toString()));
      AccountsMapper.mapToAccounts(accountsDto, accounts);
      accounts = accountsRepository.save(accounts);

      Long customerId = accounts.getCustomerId();
      Customer customer = customerRepository.findById(customerId).orElseThrow(
          () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));

      CustomerMapper.mapToCustomer(fetchCustomerAccountDto.getCustomerDto(), customer);
      customerRepository.save(customer);
      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   * @param email - Input email
   * @return boolean indicating if the deletion of the Account is successful or not
   */
  @Override
  public boolean deleteAccount(String email) {
    Customer customer = customerRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));
    accountsRepository.deleteByCustomerId(customer.getId());
    customerRepository.deleteById(customer.getId());
    return true;
  }

  /**
   * @param email - Input email
   * @return boolean indicating if the status of the Account is changed or not
   */
  @Override
  public boolean changeStatusAccount(String email) {
    Customer customer = customerRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));
    Accounts accounts = accountsRepository.findByCustomerId(customer.getId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customerId",
            customer.getId().toString()));

    AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
    if (accountsDto.getAccountStatus().equals(AccountStatus.ACTIVATED)) {
      accountsDto.setAccountStatus(AccountStatus.DEACTIVATED);
      scheduleAccountDeletion(email);
    } else {
      accountsDto.setAccountStatus(AccountStatus.ACTIVATED);
      cancelScheduledDeletion(email);
    }
    AccountsMapper.mapToAccounts(accountsDto, accounts);
    accountsRepository.save(accounts);
    return true;
  }

  private void scheduleAccountDeletion(String email) {
    cancelScheduledDeletion(email);

    ScheduledFuture<?> future = scheduler.schedule(() -> {
      deleteAccount(email);
      scheduledTask.remove(email);
    }, 10, TimeUnit.SECONDS);

    scheduledTask.put(email, future);
  }

  private void cancelScheduledDeletion(String email) {
    ScheduledFuture<?> future = scheduledTask.remove(email);
    if (future != null) {
      future.cancel(false);
    }
  }
}
