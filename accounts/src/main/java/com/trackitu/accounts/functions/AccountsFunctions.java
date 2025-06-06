package com.trackitu.accounts.functions;

import com.trackitu.accounts.dto.AccountsNotificationDto;
import com.trackitu.accounts.service.IAccountsService;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountsFunctions {

  private static final Logger logger = LoggerFactory.getLogger(AccountsFunctions.class);

  @Bean
  public Consumer<AccountsNotificationDto> updateCommunication(IAccountsService accountsService) {
    return dto -> {
      logger.info("Updating communication for account number: {}", dto.accountNumber());
      accountsService.updateCommunicationStatus(dto.accountNumber());
    };
  }
}
