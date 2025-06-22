package com.trackitu.rooms.controller;

import com.trackitu.rooms.constants.RoomsConstants;
import com.trackitu.rooms.dto.booking.CreateBookingDto;
import com.trackitu.rooms.dto.ErrorResponseDto;
import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.dto.booking.FetchBookingDto;
import com.trackitu.rooms.dto.booking.UpdateBookingDto;
import com.trackitu.rooms.service.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

@Tag(name = "CRUD REST APIs pentru rezervari", description = "Rest APIs care permit crearea, actualizarea, stergerea si preluarea detaliilor unei rezervari de sala")
@RestController
@RequestMapping(path = "/api/v1/bookings")
@AllArgsConstructor
@Validated
public class BookingsController {

  private IBookingService iBookingsService;

  @Operation(summary = "REST API pentru crearea unei rezervari", description = "Creeaza o rezervare noua pentru o sala")
  @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createBooking(
      @Valid @RequestBody CreateBookingDto createBookingDto) {
    iBookingsService.createBooking(createBookingDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @Operation(summary = "REST API pentru preluarea tuturor rezervarilor", description = "REST API pentru preluarea tuturor rezervarilor si a detaliilor acestora")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @GetMapping("/fetchAll")
  public ResponseEntity<List<FetchBookingDto>> fetchAllBookings() {
    List<FetchBookingDto> fetchBookingDtoList = iBookingsService.fetchAllBookings();
    return ResponseEntity.status(HttpStatus.OK).body(fetchBookingDtoList);
  }

  @Operation(summary = "REST API pentru schimbarea statusului rezervarii", description = "REST API pentru schimbarea statusului unei rezervari pe baza id-ului acesteia")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @PutMapping("/updateStatus")
  public ResponseEntity<ResponseDto> updateStatus(@RequestBody UpdateBookingDto updateBookingDto) {
    boolean isUpdated = iBookingsService.updateStatus(updateBookingDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }

  @Operation(summary = "REST API pentru stergerea unei rezervari", description = "REST API pentru stergerea unei rezervari pe baza id-ului acesteia")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteBooking(@RequestParam Long id) {
    boolean isDeleted = iBookingsService.deleteBooking(id);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }
}
