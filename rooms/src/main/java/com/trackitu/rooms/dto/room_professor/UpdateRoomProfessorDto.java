package com.trackitu.rooms.dto.room_professor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
    name = "RoomProfessor",
    description = "Schema to hold Room-Professor allocation information"
)
@Data
public class UpdateRoomProfessorDto {

  private Long id;

  private Long roomId;

  @Schema(
      description = "Professor ID", example = "10"
  )
  @NotNull(message = "Professor id can not be a null or empty")
  private Long professorId;

}
