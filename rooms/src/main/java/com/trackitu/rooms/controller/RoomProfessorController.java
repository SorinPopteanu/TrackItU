package com.trackitu.rooms.controller;

import com.trackitu.rooms.constants.RoomsConstants;
import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.dto.RoomProfessorDto;
import com.trackitu.rooms.service.IRoomProfessorService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/rooms/allocation", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class RoomProfessorController {

  private IRoomProfessorService iRoomProfessorService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createRoomProfessor(
      @Valid @RequestBody RoomProfessorDto roomProfessorDto) {
    iRoomProfessorService.createRoomProfessor(roomProfessorDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @GetMapping("/fetchByRoomId")
  public ResponseEntity<List<RoomProfessorDto>> fetchProfessorByRoomId(
      @Valid @RequestParam Long roomId) {
    List<RoomProfessorDto> roomProfessorDtoList = iRoomProfessorService.fetchProfessorByRoomId(
        roomId);
    return ResponseEntity.status(HttpStatus.OK).body(roomProfessorDtoList);
  }

  @GetMapping("/fetchByProfessorId")
  public ResponseEntity<List<RoomProfessorDto>> fetchRoomByProfessorId(
      @Valid @RequestParam Long professorId) {
    List<RoomProfessorDto> roomProfessorDtoList = iRoomProfessorService.fetchRoomByProfessorId(
        professorId);
    return ResponseEntity.status(HttpStatus.OK).body(roomProfessorDtoList);
  }

  @GetMapping("/fetchAll")
  public ResponseEntity<List<RoomProfessorDto>> fetchAllRoomProfessor() {
    List<RoomProfessorDto> roomProfessorDtoList = iRoomProfessorService.fetchAllRoomProfessor();
    return ResponseEntity.status(HttpStatus.OK).body(roomProfessorDtoList);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteRoomProfessorAllocation(
      @Valid @RequestParam Long roomProfessorId) {
    boolean isDeleted = iRoomProfessorService.deleteRoomProfessor(roomProfessorId);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }

  @DeleteMapping("/deleteByProfessorId")
  public ResponseEntity<ResponseDto> deleteRoomProfessorByProfessorId(
      @Valid @RequestParam Long professorId) {
    boolean isDeleted = iRoomProfessorService.deleteRoomProfessorByProfessorId(professorId);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }

}