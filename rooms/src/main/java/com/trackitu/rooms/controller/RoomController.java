package com.trackitu.rooms.controller;

import com.trackitu.rooms.dto.ErrorResponseDto;
import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.dto.RoomsContactInfoDto;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

@Tag(name = "CRUD REST APIs pentru sali", description = "Rest APIs care permit crearea, actualizarea, stergerea si preluarea detaliilor salilor din facultate")
@RestController
@RequestMapping(path = "/api/v1/room", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class RoomController {

  private final IRoomService iRoomService;
  private final Environment environment;
  private final RoomsContactInfoDto roomsContactInfoDto;

  public RoomController(IRoomService iRoomService, Environment environment,
      RoomsContactInfoDto roomsContactInfoDto) {
    this.iRoomService = iRoomService;
    this.environment = environment;
    this.roomsContactInfoDto = roomsContactInfoDto;
  }

  @Value("${build.version}")
  private String buildVersion;

  @Operation(summary = "REST API pentru crearea unei sali", description = "Adauga o noua sala")
  @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody CreateRoomDto roomDto) {
    iRoomService.createRoom(roomDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @Operation(summary = "REST API pentru preluarea detaliilor salii", description = "REST API pentru preluarea detaliilor salii pe baza id-ului")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetch")
  public ResponseEntity<FetchRoomDto> fetchRoomDetails(@RequestParam Long id) {
    FetchRoomDto roomDto = iRoomService.fetchRoomDetails(id);
    return ResponseEntity.status(HttpStatus.OK).body(roomDto);
  }

  @Operation(summary = "REST API pentru preluarea tuturor salilor", description = "REST API pentru preluarea tuturor salilor si a detaliilor acestora")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchRoomDto>> fetchAllRooms() {
    List<FetchRoomDto> roomDtoList = iRoomService.fetchAllRooms();
    return ResponseEntity.status(HttpStatus.OK).body(roomDtoList);
  }

  @Operation(summary = "REST API pentru actualizarea detaliilor salii", description = "REST API pentru actualizarea detaliilor salii pe baza id-ului")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
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

  @Operation(summary = "REST API pentru stergerea unei sali", description = "REST API pentru stergerea unei sali pe baza id-ului")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteRoom(
      @RequestParam Long id) {
    boolean isDeleted = iRoomService.deleteRoom(id);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }

  @Operation(summary = "Informatii de contact", description = "Detalii de contact la care se poate apela in caz de probleme")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/contact-info")
  public ResponseEntity<RoomsContactInfoDto> getContactInfo() {
    return ResponseEntity.status(HttpStatus.OK).body(roomsContactInfoDto);
  }

  @Operation(summary = "Informatii despre versiune", description = "Informatii despre versiunea microserviciului accounts")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/build-info")
  public ResponseEntity<String> getBuildInfo() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(buildVersion);
  }

  @Operation(summary = "Versiunea Java", description = "Detalii despre versiunea Java instalata in microserviciul accounts")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/java-version")
  public ResponseEntity<String> getJavaVersion() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(environment.getProperty("JAVA_HOME"));
  }
}
