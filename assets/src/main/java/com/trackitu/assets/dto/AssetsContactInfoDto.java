package com.trackitu.assets.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "assets")
@Getter
@Setter
public class AssetsContactInfoDto {

  private String message;
  private Map<String, String> contactDetails;
  private String onCallSupport;
}