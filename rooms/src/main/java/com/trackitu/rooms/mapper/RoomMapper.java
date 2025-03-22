package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.RoomDto;
import com.trackitu.rooms.entity.Room;

public class RoomMapper {

  public static RoomDto mapToRoomDto(Room room, RoomDto roomDto) {
    roomDto.setRoomCode(room.getRoomCode());
    roomDto.setRoomName(room.getRoomName());
    return roomDto;
  }

  public static Room mapToRoom(RoomDto roomDto, Room room) {
    room.setRoomCode(roomDto.getRoomCode());
    room.setRoomName(roomDto.getRoomName());
    return room;
  }

}
