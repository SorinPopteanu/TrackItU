package com.trackitu.rooms.service;

import com.trackitu.rooms.dto.room_professor.CreateRoomProfessorDto;
import com.trackitu.rooms.dto.room_professor.FetchRoomProfessorDto;
import com.trackitu.rooms.dto.room_professor.UpdateRoomProfessorDto;
import java.util.List;

public interface IRoomProfessorService {

  /**
   * @param createRoomProfessorDto - CreateRoomProfessorDto Object
   */
  void createRoomProfessor(CreateRoomProfessorDto createRoomProfessorDto);

  /**
   * @param roomId - Input room id
   * @return List of Professors based on the room id
   */
  List<FetchRoomProfessorDto> fetchProfessorByRoomId(Long roomId);

  /**
   * @param professorId - Input professor id
   * @return List of Rooms based on the professor id
   */
  List<FetchRoomProfessorDto> fetchRoomByProfessorId(Long professorId);

  /**
   * @return List of all the Room Professor details
   */
  List<FetchRoomProfessorDto> fetchAllRoomProfessor();

  /**
   * @param updateRoomProfessorDto - UpdateRoomProfessorDto Object
   * @return boolean indicating if the update of the Room Professor is successful or not
   */
  boolean updateRoomProfessor(UpdateRoomProfessorDto updateRoomProfessorDto);

  /**
   * @param id - Input room_professor id
   * @return boolean indicating if the deletion of the Room Professor is successful or not
   */
  boolean deleteRoomProfessor(Long id);

  /**
   * @param professorId - Input professor id
   * @return boolean indicating if the deletion of the Room Professor is successful or not
   */
  boolean deleteRoomProfessorByProfessorId(Long professorId);
}
