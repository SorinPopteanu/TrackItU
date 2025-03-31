package com.trackitu.rooms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
    name = "Room",
    description = "Schema to hold Room information"
)
@Data
public class RoomDto {

  @Schema(
      description = "Room code", example = "EB212"
  )
  @NotNull(message = "Room code can not be a null or empty")
  @Pattern(regexp = "[A-Z]{2}\\d{3}", message = "Room code must be in the format 'ZZ999'")
  private String roomCode;

  @Schema(
      description = "Room name", example = "Informatics Lab"
  )
  private String roomName;

}
