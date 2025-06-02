package com.trackitu.assets.dto.rooms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    name = "RoomProfessor",
    description = "Schema to hold Room-Professor allocation information"
)
@Data
public class RoomProfessorDto {

  private RoomDto roomDto;

  private Long professorId;

}
