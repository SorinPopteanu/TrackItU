package com.trackitu.rooms.service;

import com.trackitu.rooms.dto.RoomProfessorDto;
import java.util.List;

public interface IRoomProfessorService {

  /**
   * @param roomProfessorDto - RoomProfessorDto Object
   */
  void createRoomProfessor(RoomProfessorDto roomProfessorDto);

  /**
   * @param roomId - Input room id
   * @return List of Professors based on the room id
   */
  List<RoomProfessorDto> fetchProfessorByRoomId(Long roomId);

  /**
   * @param professorId - Input professor id
   * @return List of Rooms based on the professor id
   */
  List<RoomProfessorDto> fetchRoomByProfessorId(Long professorId);

  /**
   * @return List of all the Room Professor details
   */
  List<RoomProfessorDto> fetchAllRoomProfessor();

  /**
   * @param roomProfessorId - Input room professor id
   * @return boolean indicating if the deletion of the Room Professor is successful or not
   */
  boolean deleteRoomProfessor(Long roomProfessorId);

  /**
   * @param professorId - Input professor id
   * @return boolean indicating if the deletion of the Room Professor is successful or not
   */
  boolean deleteRoomProfessorByProfessorId(Long professorId);
}
