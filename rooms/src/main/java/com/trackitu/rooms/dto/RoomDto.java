package com.trackitu.rooms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomDto {

  @NotNull(message = "Room code can not be a null or empty")
  private String roomCode;

  @NotNull
  private String roomName;

}
