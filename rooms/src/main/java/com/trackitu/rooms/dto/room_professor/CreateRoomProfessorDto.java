package com.trackitu.rooms.dto.room_professor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
    name = "RoomProfessor",
    description = "Schema to hold Room-Professor allocation information"
)
@Data
public class CreateRoomProfessorDto {

  @Schema(
      description = "Room ID", example = "15"
  )
  @NotNull(message = "Room id can not be a null or empty")
  @JoinColumn(name = "room_id", nullable = false)
  private Long roomId;

  @Schema(
      description = "Professor ID", example = "10"
  )
  @NotNull(message = "Professor id can not be a null or empty")
  private Long professorId;

}
