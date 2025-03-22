package com.trackitu.rooms.service;

import com.trackitu.rooms.dto.RoomDto;
import java.util.List;

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

  /**
   * @return List of all the Rooms
   */
  List<RoomDto> fetchAllRooms();

  /**
   * @param roomDto - RoomDto Object
   * @return boolean indicating if the update of the Room details is successful or not
   */
  boolean updateRoomDetails(RoomDto roomDto);

  /**
   * @param roomCode - Input room code
   * @return boolean indicating if the deletion of the Room is successful or not
   */
  boolean deleteRoom(String roomCode);
}
