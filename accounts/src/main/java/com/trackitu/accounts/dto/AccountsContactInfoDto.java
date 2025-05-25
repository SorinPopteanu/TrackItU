package com.trackitu.accounts.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountsContactInfoDto {

  private String message;
  private Map<String, String> contactDetails;
  private String onCallSupport;
}
