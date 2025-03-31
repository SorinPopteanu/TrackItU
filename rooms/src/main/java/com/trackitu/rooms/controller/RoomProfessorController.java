package com.trackitu.rooms.controller;

import com.trackitu.rooms.constants.RoomsConstants;
import com.trackitu.rooms.dto.ErrorResponseDto;
import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.dto.RoomProfessorDto;
import com.trackitu.rooms.service.IRoomProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
    name = "CRUD REST APIs for Room-Professor Allocation",
    description = "CRUD REST APIs for managing room-professor allocation"
)
@RestController
@RequestMapping(path = "/api/v1/rooms/allocation", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class RoomProfessorController {

  private IRoomProfessorService iRoomProfessorService;

  @Operation(
      summary = "Create Room-Professor Allocation REST API",
      description = "Create a new room-professor allocation"
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
  public ResponseEntity<ResponseDto> createRoomProfessor(
      @Valid @RequestBody RoomProfessorDto roomProfessorDto) {
    iRoomProfessorService.createRoomProfessor(roomProfessorDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Professor by Room ID REST API",
      description = "Fetch Professor details based on Room ID"
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
  @GetMapping("/fetchByRoomId")
  public ResponseEntity<List<RoomProfessorDto>> fetchProfessorByRoomId(
      @Valid @RequestParam Long roomId) {
    List<RoomProfessorDto> roomProfessorDtoList = iRoomProfessorService.fetchProfessorByRoomId(
        roomId);
    return ResponseEntity.status(HttpStatus.OK).body(roomProfessorDtoList);
  }

  @Operation(
      summary = "Fetch Room by Professor ID REST API",
      description = "Fetch Room details based on Professor ID"
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
  @GetMapping("/fetchByProfessorId")
  public ResponseEntity<List<RoomProfessorDto>> fetchRoomByProfessorId(
      @Valid @RequestParam Long professorId) {
    List<RoomProfessorDto> roomProfessorDtoList = iRoomProfessorService.fetchRoomByProfessorId(
        professorId);
    return ResponseEntity.status(HttpStatus.OK).body(roomProfessorDtoList);
  }

  @Operation(
      summary = "Fetch All Room-Professor Allocation REST API",
      description = "REST API to fetch all room-professor allocations"
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
  public ResponseEntity<List<RoomProfessorDto>> fetchAllRoomProfessor() {
    List<RoomProfessorDto> roomProfessorDtoList = iRoomProfessorService.fetchAllRoomProfessor();
    return ResponseEntity.status(HttpStatus.OK).body(roomProfessorDtoList);
  }

  @Operation(
      summary = "Delete Room-Professor Allocation REST API",
      description = "Delete a room-professor allocation"
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