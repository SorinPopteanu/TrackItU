package com.trackitu.rooms.service.impl;

import com.trackitu.rooms.dto.RoomDto;
import com.trackitu.rooms.entity.Room;
import com.trackitu.rooms.exception.ResourceNotFoundException;
import com.trackitu.rooms.exception.RoomAlreadyExistsException;
import com.trackitu.rooms.mapper.RoomMapper;
import com.trackitu.rooms.repository.RoomRepository;
import com.trackitu.rooms.service.IRoomService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements IRoomService {

  private RoomRepository roomRepository;

  /**
   * @param roomDto - RoomDto Object
   */
  @Override
  public void createRoom(RoomDto roomDto) {
    Room room = RoomMapper.mapToRoom(roomDto, new Room());
    Optional<Room> optionalRoom = roomRepository.findByRoomCode(roomDto.getRoomCode());
    if (optionalRoom.isPresent()) {
      throw new RoomAlreadyExistsException(
          "Room already exists with room code: " + roomDto.getRoomCode());
    } else {
      roomRepository.save(room);
    }
  }

  /**
   * @param roomId - Input room id
   * @return Room Details based on the room id
   */
  @Override
  public RoomDto fetchRoomDetails(Long roomId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Room", "roomId", roomId.toString()));
    return RoomMapper.mapToRoomDto(room, new RoomDto());
  }

  /**
   * @return List of all the Rooms
   */
  @Override
  public List<RoomDto> fetchAllRooms() {
    List<Room> roomList = roomRepository.findAll();
    List<RoomDto> roomDtoList = new ArrayList<>();

    for (Room room : roomList) {
      roomDtoList.add(RoomMapper.mapToRoomDto(room, new RoomDto()));
    }
    return roomDtoList;
  }

  /**
   * @param roomDto - RoomDto Object
   * @return boolean indicating if the update of the Room details is successful or not
   */
  @Override
  public boolean updateRoomDetails(RoomDto roomDto) {
    boolean isUpdated = false;
    if (roomDto != null) {
      Room room = roomRepository.findByRoomCode(roomDto.getRoomCode()).orElseThrow(
          () -> new ResourceNotFoundException("Room", "RoomCode", roomDto.getRoomCode()));
      RoomMapper.mapToRoom(roomDto, room);
      roomRepository.save(room);
      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   * @param roomCode - Input room code
   * @return boolean indicating if the deletion of the Room is successful or not
   */
  @Override
  public boolean deleteRoom(String roomCode) {
    Room room = roomRepository.findByRoomCode(roomCode)
        .orElseThrow(() -> new ResourceNotFoundException("Room", "RoomCode", roomCode));
    roomRepository.deleteByRoomId(room.getRoomId());
    return true;
  }
}
