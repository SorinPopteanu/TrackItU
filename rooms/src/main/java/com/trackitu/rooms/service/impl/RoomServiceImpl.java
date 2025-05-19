package com.trackitu.rooms.service.impl;

import com.trackitu.rooms.dto.room.CreateRoomDto;
import com.trackitu.rooms.dto.room.FetchRoomDto;
import com.trackitu.rooms.dto.room.UpdateRoomDto;
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
   * @param roomDto - CreateRoomDto Object
   */
  @Override
  public void createRoom(CreateRoomDto roomDto) {
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
   * @param id - Input room id
   * @return Room Details based on the room id
   */
  @Override
  public FetchRoomDto fetchRoomDetails(Long id) {
    Room room = roomRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id.toString()));
    return RoomMapper.mapToFetchRoomDto(room, new FetchRoomDto());
  }

  /**
   * @return List of all the Rooms
   */
  @Override
  public List<FetchRoomDto> fetchAllRooms() {
    List<Room> roomList = roomRepository.findAll();
    List<FetchRoomDto> roomDtoList = new ArrayList<>();

    for (Room room : roomList) {
      roomDtoList.add(RoomMapper.mapToFetchRoomDto(room, new FetchRoomDto()));
    }
    return roomDtoList;
  }

  /**
   * @param roomDto - UpdateRoomDto Object
   * @return boolean indicating if the update of the Room details is successful or not
   */
  @Override
  public boolean updateRoomDetails(UpdateRoomDto roomDto) {
    boolean isUpdated = false;
    if (roomDto != null) {
      Room room = roomRepository.findById(roomDto.getId()).orElseThrow(
          () -> new ResourceNotFoundException("Room", "id", roomDto.getId().toString()));
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
    roomRepository.deleteById(room.getId());
    return true;
  }
}
