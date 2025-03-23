package com.trackitu.rooms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomDto {

  @NotNull(message = "Room code can not be a null or empty")
  @Pattern(regexp = "[A-Z]{2}\\d{3}", message = "Room code must be in the format 'ZZ999'")
  private String roomCode;

  private String roomName;

}
