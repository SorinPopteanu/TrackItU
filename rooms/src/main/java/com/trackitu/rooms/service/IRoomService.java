package com.trackitu.rooms.service;

import com.trackitu.rooms.dto.room.CreateRoomDto;
import com.trackitu.rooms.dto.room.FetchRoomDto;
import com.trackitu.rooms.dto.room.UpdateRoomDto;
import java.util.List;

public interface IRoomService {

  /**
   * @param roomDto - CreateRoomDto Object
   */
  void createRoom(CreateRoomDto roomDto);

  /**
   * @param id - Input room id
   * @return Room Details based on the room id
   */
  FetchRoomDto fetchRoomDetails(Long id);

  /**
   * @return List of all the Rooms
   */
  List<FetchRoomDto> fetchAllRooms();

  /**
   * @param roomDto - UpdateRoomDto Object
   * @return boolean indicating if the update of the Room details is successful or not
   */
  boolean updateRoomDetails(UpdateRoomDto roomDto);

  /**
   * @param roomCode - Input room code
   * @return boolean indicating if the deletion of the Room is successful or not
   */
  boolean deleteRoom(String roomCode);
}
