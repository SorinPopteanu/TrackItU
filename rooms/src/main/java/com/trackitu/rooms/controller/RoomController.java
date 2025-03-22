package com.trackitu.rooms.controller;

import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.dto.RoomDto;
import com.trackitu.rooms.service.IRoomService;
import com.trackitu.rooms.constants.RoomsConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/rooms", produces = {MediaType.APPLICATION_JSON_VALUE})
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
  public ResponseEntity<RoomDto> fetchRoomDetails(
      @RequestParam Long roomId) {
    RoomDto roomDto = iRoomService.fetchRoomDetails(roomId);
    return ResponseEntity.status(HttpStatus.OK).body(roomDto);
  }
}
