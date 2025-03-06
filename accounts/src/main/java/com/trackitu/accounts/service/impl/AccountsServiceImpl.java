package com.trackitu.accounts.service.impl;


import com.trackitu.accounts.dto.AccountsDto;
import com.trackitu.accounts.dto.CreateAccountDto;
import com.trackitu.accounts.dto.CustomerAccountDto;
import com.trackitu.accounts.dto.CustomerDto;
import com.trackitu.accounts.entity.Accounts;
import com.trackitu.accounts.entity.Customer;
import com.trackitu.accounts.exception.CustomerAlreadyExistsException;
import com.trackitu.accounts.exception.ResourceNotFoundException;
import com.trackitu.accounts.mapper.AccountsMapper;
import com.trackitu.accounts.mapper.CustomerMapper;
import com.trackitu.accounts.repository.AccountsRepository;
import com.trackitu.accounts.repository.CustomerRepository;
import com.trackitu.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * Create a new account
     *
     * @param createAccountDto
     */
    @Override
    public void createAccount(CreateAccountDto createAccountDto) {
        Customer customer = CustomerMapper.mapToCustomer(createAccountDto.getCustomerDto(), new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + customer.getEmail());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("System");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer, createAccountDto.getAccountType()));
    }

    /**
     * @param customer, accountType
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer, String accountType) {
        Accounts newAccount = new Accounts();
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("System");

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(accountType);
        return newAccount;
    }

    /**
     * @param email - Input last name
     * @return Accounts Details based on the last name
     */
    @Override
    public CustomerAccountDto fetchAccountDetails(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(
                "Customer",
                "email",
                email));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                                              .orElseThrow(() -> new ResourceNotFoundException("Account",
                                                                                               "customerId",
                                                                                               customer.getCustomerId()
                                                                                                       .toString()));

        CustomerAccountDto customerAccountDto = new CustomerAccountDto();
        customerAccountDto.setCustomerDto(CustomerMapper.mapToCustomerDto(customer, new CustomerDto()));
        customerAccountDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerAccountDto;
    }

    /**
     * @param customerAccountDto - CustomerAccountDto Object
     * @return boolean indicating if the update of the Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerAccountDto customerAccountDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerAccountDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                                                  .orElseThrow(() -> new ResourceNotFoundException("Account",
                                                                                                   "AccountNumber",
                                                                                                   accountsDto.getAccountNumber()
                                                                                                              .toString()));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException(
                    "Customer",
                    "CustomerID",
                    customerId.toString()));

            CustomerMapper.mapToCustomer(customerAccountDto.getCustomerDto(),  customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

}
