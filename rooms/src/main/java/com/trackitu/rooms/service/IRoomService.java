package com.trackitu.rooms.service;

import com.trackitu.rooms.dto.RoomDto;

public interface IRoomService {

  /**
   * @param roomDto - RoomDto Object
   */
  void createRoom(RoomDto roomDto);

  /**
   * @param roomId - Input room id
   * @return Room Details based on the room id
   */
  RoomDto fetchRoomDetails(Long roomId);

}
