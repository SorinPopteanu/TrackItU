package com.trackitu.assets.dto.rooms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    name = "Room",
    description = "Schema to hold Room information"
)
@Data
public class RoomDto {

  private String roomCode;

  private String name;

}