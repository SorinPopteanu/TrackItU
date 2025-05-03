package com.trackitu.assets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AssetAlreadyExistsException extends RuntimeException {

  public AssetAlreadyExistsException(String message) {
    super(message);
  }
}
