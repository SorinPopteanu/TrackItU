package com.trackitu.rooms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoomAllocationAlreadyExists extends RuntimeException{

  public RoomAllocationAlreadyExists(String message) {
    super(message);
  }
}
