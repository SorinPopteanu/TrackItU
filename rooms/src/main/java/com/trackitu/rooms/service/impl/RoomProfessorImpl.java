package com.trackitu.rooms.service.impl;

import com.trackitu.rooms.dto.RoomProfessorDto;
import com.trackitu.rooms.entity.RoomProfessor;
import com.trackitu.rooms.exception.ResourceNotFoundException;
import com.trackitu.rooms.exception.RoomAllocationAlreadyExists;
import com.trackitu.rooms.mapper.RoomProfessorMapper;
import com.trackitu.rooms.repository.RoomProfessorRepository;
import com.trackitu.rooms.service.IRoomProfessorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomProfessorImpl implements IRoomProfessorService {

  private RoomProfessorRepository roomProfessorRepository;


  /**
   * @param roomProfessorDto - RoomProfessorDto Object
   */
  @Override
  public void createRoomProfessor(RoomProfessorDto roomProfessorDto) {
    RoomProfessor roomProfessor = RoomProfessorMapper.mapToRoomProfessor(roomProfessorDto,
        new RoomProfessor());
    Optional<RoomProfessor> optionalRoomProfessor = roomProfessorRepository.findByRoomIdAndProfessorId(
        roomProfessorDto.getRoomId(), roomProfessorDto.getProfessorId());
    if (optionalRoomProfessor.isPresent()) {
      throw new RoomAllocationAlreadyExists(
          "Room already allocated to professor with room id: " + roomProfessorDto.getRoomId()
              + " and professor id: " + roomProfessorDto.getProfessorId());
    } else {
      roomProfessorRepository.save(roomProfessor);
    }
  }

  /**
   * @param roomId - Input room id
   * @return List of Professors based on the room id
   */
  @Override
  public List<RoomProfessorDto> fetchProfessorByRoomId(Long roomId) {
    List<RoomProfessor> roomProfessorList = roomProfessorRepository.findByRoomId(roomId);
    List<RoomProfessorDto> roomProfessorDtoList = new ArrayList<>();

    for (RoomProfessor roomProfessor : roomProfessorList) {
      roomProfessorDtoList.add(
          RoomProfessorMapper.mapToRoomProfessorDto(roomProfessor, new RoomProfessorDto()));
    }
    return roomProfessorDtoList;
  }

  /**
   * @param professorId - Input professor id
   * @return List of Rooms based on the professor id
   */
  @Override
  public List<RoomProfessorDto> fetchRoomByProfessorId(Long professorId) {
    List<RoomProfessor> roomProfessorList = roomProfessorRepository.findByProfessorId(professorId);
    List<RoomProfessorDto> roomProfessorDtoList = new ArrayList<>();

    for (RoomProfessor roomProfessor : roomProfessorList) {
      roomProfessorDtoList.add(
          RoomProfessorMapper.mapToRoomProfessorDto(roomProfessor, new RoomProfessorDto()));
    }
    return roomProfessorDtoList;
  }

  /**
   * @return List of all the Room Professor details
   */
  @Override
  public List<RoomProfessorDto> fetchAllRoomProfessor() {
    List<RoomProfessor> roomProfessorList = roomProfessorRepository.findAll();
    List<RoomProfessorDto> roomProfessorDtoList = new ArrayList<>();

    for (RoomProfessor roomProfessor : roomProfessorList) {
      roomProfessorDtoList.add(
          RoomProfessorMapper.mapToRoomProfessorDto(roomProfessor, new RoomProfessorDto()));
    }
    return roomProfessorDtoList;
  }

  /**
   * @param roomProfessorId - Input room professor id
   * @return boolean indicating if the deletion of the Room Professor is successful or not
   */
  @Override
  public boolean deleteRoomProfessor(Long roomProfessorId) {
    RoomProfessor roomProfessor = roomProfessorRepository.findByRoomProfessorId(roomProfessorId)
        .orElseThrow(
            () -> new ResourceNotFoundException("RoomProfessor", "RoomProfessor", roomProfessorId.toString()));
    roomProfessorRepository.deleteByRoomProfessorId(roomProfessor.getRoomProfessorId());
    return true;
  }

  /**
   * @param professorId - Input professor id
   * @return boolean indicating if the deletion of the Room Professor is successful or not
   */
  @Override
  public boolean deleteRoomProfessorByProfessorId(Long professorId) {
    List<RoomProfessor> roomProfessorList = roomProfessorRepository.findByProfessorId(professorId);
    if (roomProfessorList.isEmpty()) {
      throw new ResourceNotFoundException("RoomProfessor", "ProfessorId", professorId.toString());
    } else {
      roomProfessorRepository.deleteByProfessorId(professorId);
      return true;
    }
  }


}
