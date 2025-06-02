package com.trackitu.accounts.dto.rooms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
    name = "RoomProfessor",
    description = "Schema to hold Room-Professor allocation information"
)
@Data
public class FetchRoomProfessorDto {

  private Long id;

  private FetchRoomDto roomDto;

  @Schema(
      description = "Professor ID", example = "10"
  )
  @NotNull(message = "Professor id can not be a null or empty")
  private Long professorId;

}
