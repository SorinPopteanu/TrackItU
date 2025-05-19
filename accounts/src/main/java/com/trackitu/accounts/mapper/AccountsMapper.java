package com.trackitu.accounts.mapper;

import com.trackitu.accounts.dto.accounts.AccountsDto;
import com.trackitu.accounts.entity.Accounts;

public class AccountsMapper {

  public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
    accountsDto.setAccountNumber(accounts.getAccountNumber());
    accountsDto.setAccountType(accounts.getAccountType());
    accountsDto.setAccountStatus(accounts.getAccountStatus());
    return accountsDto;
  }

  public static void mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
    accounts.setAccountNumber(accountsDto.getAccountNumber());
    accounts.setAccountType(accountsDto.getAccountType());
    accounts.setAccountStatus(accountsDto.getAccountStatus());
  }
}
