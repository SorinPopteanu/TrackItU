package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.RoomProfessorDto;
import com.trackitu.rooms.entity.RoomProfessor;

public class RoomProfessorMapper {

  public static RoomProfessorDto mapToRoomProfessorDto(RoomProfessor roomProfessor,
      RoomProfessorDto roomProfessorDto) {
    roomProfessorDto.setRoomId(roomProfessor.getRoomId());
    roomProfessorDto.setProfessorId(roomProfessor.getProfessorId());
    return roomProfessorDto;
  }

  public static RoomProfessor mapToRoomProfessor(RoomProfessorDto roomProfessorDto,
      RoomProfessor roomProfessor) {
    roomProfessor.setRoomId(roomProfessorDto.getRoomId());
    roomProfessor.setProfessorId(roomProfessorDto.getProfessorId());
    return roomProfessor;
  }
}