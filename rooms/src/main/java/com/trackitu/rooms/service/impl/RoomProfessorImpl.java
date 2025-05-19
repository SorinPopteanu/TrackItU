package com.trackitu.rooms.service.impl;

import com.trackitu.rooms.dto.room_professor.CreateRoomProfessorDto;
import com.trackitu.rooms.dto.room_professor.FetchRoomProfessorDto;
import com.trackitu.rooms.dto.room_professor.UpdateRoomProfessorDto;
import com.trackitu.rooms.entity.Room;
import com.trackitu.rooms.entity.RoomProfessor;
import com.trackitu.rooms.exception.ResourceNotFoundException;
import com.trackitu.rooms.exception.RoomAllocationAlreadyExists;
import com.trackitu.rooms.mapper.RoomProfessorMapper;
import com.trackitu.rooms.repository.RoomProfessorRepository;
import com.trackitu.rooms.repository.RoomRepository;
import com.trackitu.rooms.service.IRoomProfessorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomProfessorImpl implements IRoomProfessorService {

  private final RoomRepository roomRepository;
  private RoomProfessorRepository roomProfessorRepository;


  /**
   * @param createRoomProfessorDto - CreateRoomProfessorDto Object
   */
  @Override
  public void createRoomProfessor(CreateRoomProfessorDto createRoomProfessorDto) {
    Room room = roomRepository.findById(createRoomProfessorDto.getRoomId()).orElseThrow(
        () -> new ResourceNotFoundException("Room", "id",
            createRoomProfessorDto.getRoomId().toString()));
    RoomProfessor roomProfessor = RoomProfessorMapper.mapToRoomProfessor(createRoomProfessorDto,
        new RoomProfessor(), room);
    Optional<RoomProfessor> optionalRoomProfessor = roomProfessorRepository.findByRoomIdAndProfessorId(
        createRoomProfessorDto.getRoomId(), createRoomProfessorDto.getProfessorId());
    if (optionalRoomProfessor.isPresent()) {
      throw new RoomAllocationAlreadyExists(
          "Room already allocated to professor with room id: " + createRoomProfessorDto.getRoomId()
              + " and professor id: " + createRoomProfessorDto.getProfessorId());
    } else {
      roomProfessorRepository.save(roomProfessor);
    }
  }

  /**
   * @param id - Input room id
   * @return List of Professors based on the room id
   */
  @Override
  public List<FetchRoomProfessorDto> fetchProfessorByRoomId(Long id) {
    List<RoomProfessor> roomProfessorList = roomProfessorRepository.findByRoomId(id);
    List<FetchRoomProfessorDto> fetchRoomProfessorDtoList = new ArrayList<>();

    for (RoomProfessor roomProfessor : roomProfessorList) {
      fetchRoomProfessorDtoList.add(
          RoomProfessorMapper.mapToRoomProfessorDto(roomProfessor, new FetchRoomProfessorDto()));
    }
    return fetchRoomProfessorDtoList;
  }

  /**
   * @param professorId - Input professor id
   * @return List of Rooms based on the professor id
   */
  @Override
  public List<FetchRoomProfessorDto> fetchRoomByProfessorId(Long professorId) {
    List<RoomProfessor> roomProfessorList = roomProfessorRepository.findByProfessorId(professorId);
    List<FetchRoomProfessorDto> fetchRoomProfessorDtoList = new ArrayList<>();

    for (RoomProfessor roomProfessor : roomProfessorList) {
      fetchRoomProfessorDtoList.add(
          RoomProfessorMapper.mapToRoomProfessorDto(roomProfessor, new FetchRoomProfessorDto()));
    }
    return fetchRoomProfessorDtoList;
  }

  /**
   * @return List of all the Room Professor details
   */
  @Override
  public List<FetchRoomProfessorDto> fetchAllRoomProfessor() {
    List<RoomProfessor> roomProfessorList = roomProfessorRepository.findAll();
    List<FetchRoomProfessorDto> fetchRoomProfessorDtoList = new ArrayList<>();

    for (RoomProfessor roomProfessor : roomProfessorList) {
      fetchRoomProfessorDtoList.add(
          RoomProfessorMapper.mapToRoomProfessorDto(roomProfessor, new FetchRoomProfessorDto()));
    }
    return fetchRoomProfessorDtoList;
  }

  /**
   * @param updateRoomProfessorDto - UpdateRoomProfessorDto Object
   * @return boolean indicating if the update of the Room Professor is successful or not
   */
  @Override
  public boolean updateRoomProfessor(UpdateRoomProfessorDto updateRoomProfessorDto) {
    boolean isUpdated = false;
    if (updateRoomProfessorDto != null) {
      Room room = roomRepository.findById(updateRoomProfessorDto.getRoomId()).orElseThrow(
          () -> new ResourceNotFoundException("Room", "id",
              updateRoomProfessorDto.getRoomId().toString()));
      RoomProfessor roomProfessor = roomProfessorRepository.findByRoomProfessorId(
          updateRoomProfessorDto.getId()).orElseThrow(
          () -> new ResourceNotFoundException("Room Professor", "id",
              updateRoomProfessorDto.getId().toString()));
      RoomProfessorMapper.mapToRoomProfessor(updateRoomProfessorDto, roomProfessor, room);
      roomProfessorRepository.save(roomProfessor);
      isUpdated = true;
    }
    return isUpdated;
  }


  /**
   * @param roomProfessorId - Input room professor id
   * @return boolean indicating if the deletion of the Room Professor is successful or not
   */
  @Override
  public boolean deleteRoomProfessor(Long roomProfessorId) {
    RoomProfessor roomProfessor = roomProfessorRepository.findByRoomProfessorId(roomProfessorId)
        .orElseThrow(
            () -> new ResourceNotFoundException("RoomProfessor", "RoomProfessor",
                roomProfessorId.toString()));
    roomProfessorRepository.deleteById(roomProfessor.getRoomProfessorId());
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
