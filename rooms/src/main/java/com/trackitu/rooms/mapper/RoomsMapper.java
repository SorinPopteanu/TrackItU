package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.RoomsDto;
import com.trackitu.rooms.entity.Rooms;

public class RoomsMapper {

  public static RoomsDto mapToRoomsDto(Rooms rooms, RoomsDto roomsDto) {
    roomsDto.setRoomCode(rooms.getRoomCode());
    roomsDto.setRoomName(rooms.getRoomName());
    return roomsDto;
  }

  public static Rooms mapToRooms(RoomsDto roomsDto, Rooms rooms) {
    rooms.setRoomCode(roomsDto.getRoomCode());
    rooms.setRoomName(roomsDto.getRoomName());
    return rooms;
  }

}
