package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.room.FetchRoomDto;
import com.trackitu.rooms.dto.room_professor.CreateRoomProfessorDto;
import com.trackitu.rooms.dto.room_professor.FetchRoomProfessorDto;
import com.trackitu.rooms.dto.room_professor.UpdateRoomProfessorDto;
import com.trackitu.rooms.entity.Room;
import com.trackitu.rooms.entity.RoomProfessor;

public class RoomProfessorMapper {

  public static RoomProfessor mapToRoomProfessor(CreateRoomProfessorDto roomProfessorDto, RoomProfessor roomProfessor, Room room) {
    roomProfessor.setRoom(room);
    roomProfessor.setProfessorId(roomProfessorDto.getProfessorId());
    return roomProfessor;
  }

  public static void mapToRoomProfessor(UpdateRoomProfessorDto roomProfessorDto, RoomProfessor roomProfessor, Room room) {
    roomProfessor.setRoom(room);
    roomProfessor.setProfessorId(roomProfessorDto.getProfessorId());
  }

  public static FetchRoomProfessorDto mapToRoomProfessorDto(RoomProfessor roomProfessor, FetchRoomProfessorDto roomProfessorDto) {
    roomProfessorDto.setId(roomProfessor.getRoomProfessorId());
    roomProfessorDto.setRoomDto(RoomMapper.mapToFetchRoomDto(roomProfessor.getRoom(), new FetchRoomDto()));
    roomProfessorDto.setProfessorId(roomProfessor.getProfessorId());
    return roomProfessorDto;
  }
}