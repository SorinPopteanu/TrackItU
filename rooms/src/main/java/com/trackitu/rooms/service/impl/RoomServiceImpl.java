package com.trackitu.rooms.service.impl;

import com.trackitu.rooms.dto.RoomDto;
import com.trackitu.rooms.entity.Room;
import com.trackitu.rooms.exception.ResourceNotFoundException;
import com.trackitu.rooms.exception.RoomAlreadyExistsException;
import com.trackitu.rooms.mapper.RoomMapper;
import com.trackitu.rooms.repository.RoomRepository;
import com.trackitu.rooms.service.IRoomService;
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


}
