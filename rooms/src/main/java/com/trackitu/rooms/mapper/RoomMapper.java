package com.trackitu.rooms.mapper;

import com.trackitu.rooms.dto.room.CreateRoomDto;
import com.trackitu.rooms.dto.room.FetchRoomDto;
import com.trackitu.rooms.dto.room.UpdateRoomDto;
import com.trackitu.rooms.entity.Room;

public class RoomMapper {

  public static Room mapToRoom(CreateRoomDto roomDto, Room room) {
    room.setRoomCode(roomDto.getRoomCode());
    room.setName(roomDto.getName());
    return room;
  }

  public static void mapToRoom(UpdateRoomDto roomDto, Room room) {
    room.setRoomCode(roomDto.getRoomCode());
    room.setName(roomDto.getName());
  }

  public static FetchRoomDto mapToFetchRoomDto(Room room, FetchRoomDto fetchRoomDto) {
    fetchRoomDto.setId(room.getId());
    fetchRoomDto.setRoomCode(room.getRoomCode());
    fetchRoomDto.setName(room.getName());
    return fetchRoomDto;
  }

}
