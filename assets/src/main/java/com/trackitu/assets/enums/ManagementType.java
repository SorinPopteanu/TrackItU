package com.trackitu.assets.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ManagementType {
  FIXED("GEST.6039"),
  INVENTORY("GEST.3039");

  private final String code;

  ManagementType(String code) {
    this.code = code;
  }

  @JsonValue
  public String getCode() {
    return code;
  }
}
