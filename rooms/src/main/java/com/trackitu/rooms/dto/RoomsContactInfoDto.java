package com.trackitu.rooms.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rooms")
@Getter
@Setter
public class RoomsContactInfoDto {

  private String message;
  private Map<String, String> contactDetails;
  private String onCallSupport;
}
