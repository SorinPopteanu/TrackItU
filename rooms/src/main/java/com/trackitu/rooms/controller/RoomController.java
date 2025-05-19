package com.trackitu.rooms.controller;

import com.trackitu.rooms.dto.ErrorResponseDto;
import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.dto.room.CreateRoomDto;
import com.trackitu.rooms.dto.room.FetchRoomDto;
import com.trackitu.rooms.dto.room.UpdateRoomDto;
import com.trackitu.rooms.service.IRoomService;
import com.trackitu.rooms.constants.RoomsConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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

@Tag(
    name ="CRUD REST APIs for Rooms",
    description = "CRUD REST APIs for managing rooms"
)
@RestController
@RequestMapping(path = "/api/v1/rooms/room", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class RoomController {

  private IRoomService iRoomService;

  @Operation(
      summary = "Create Room REST API",
      description = "Create a new room"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status CREATED"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody CreateRoomDto roomDto) {
    iRoomService.createRoom(roomDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Room Details REST API",
      description = "REST API to fetch room details based on room ID"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @GetMapping("/fetch")
  public ResponseEntity<FetchRoomDto> fetchRoomDetails(@RequestParam Long id) {
    FetchRoomDto roomDto = iRoomService.fetchRoomDetails(id);
    return ResponseEntity.status(HttpStatus.OK).body(roomDto);
  }

  @Operation(
      summary = "Fetch All Rooms REST API",
      description = "REST API to fetch all room details"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchRoomDto>> fetchAllRooms() {
    List<FetchRoomDto> roomDtoList = iRoomService.fetchAllRooms();
    return ResponseEntity.status(HttpStatus.OK).body(roomDtoList);
  }

  @Operation(
      summary = "Update Room Details REST API",
      description = "REST API to update the details of a room"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateRoomDetails(@Valid @RequestBody UpdateRoomDto roomDto) {
    boolean isUpdated = iRoomService.updateRoomDetails(roomDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }

  @Operation(
      summary = "Delete Room REST API",
      description = "REST API to delete a room"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status OK"
  )
  @ApiResponse(
      responseCode = "500",
      description = "HTTP Status INTERNAL SERVER ERROR",
      content = @Content(
          schema = @Schema(implementation = ErrorResponseDto.class)
      )
  )
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteRoom(
      @Pattern(regexp = "[A-Z]{2}\\d{3}", message = "Room code must be in the format 'ZZ999'")
      @RequestParam String roomCode) {
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
