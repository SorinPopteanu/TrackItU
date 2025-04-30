package com.trackitu.assets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FundingSourceAlreadyExistsException extends RuntimeException {

  public FundingSourceAlreadyExistsException(String message) {
    super(message);
  }
}
