package com.trackitu.rooms.controller;

import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.dto.RoomDto;
import com.trackitu.rooms.service.IRoomService;
import com.trackitu.rooms.constants.RoomsConstants;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/rooms/room", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class RoomController {

  private IRoomService iRoomService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody RoomDto roomDto) {
    iRoomService.createRoom(roomDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @GetMapping("/fetch")
  public ResponseEntity<RoomDto> fetchRoomDetails(@RequestParam Long roomId) {
    RoomDto roomDto = iRoomService.fetchRoomDetails(roomId);
    return ResponseEntity.status(HttpStatus.OK).body(roomDto);
  }

  @GetMapping("/fetchAll")
  public ResponseEntity<List<RoomDto>> fetchAllRooms() {
    List<RoomDto> roomDtoList = iRoomService.fetchAllRooms();
    return ResponseEntity.status(HttpStatus.OK).body(roomDtoList);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateRoomDetails(@RequestBody RoomDto roomDto) {
    boolean isUpdated = iRoomService.updateRoomDetails(roomDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteRoom(@RequestParam String roomCode) {
    boolean isDeleted = iRoomService.deleteRoom(roomCode);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }
}
