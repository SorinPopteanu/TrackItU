package com.trackitu.rooms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomProfessorDto {

  @NotNull(message = "Room id can not be a null or empty")
  private Long roomId;

  @NotNull(message = "Professor id can not be a null or empty")
  private Long professorId;

}
