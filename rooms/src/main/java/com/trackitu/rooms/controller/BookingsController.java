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

@Tag(
    name = "CRUD REST APIs for Bookings",
    description = "CRUD REST APIs for managing room bookings"
)
@RestController
@RequestMapping(path = "/api/v1/rooms/bookings")
@AllArgsConstructor
@Validated
public class BookingsController {

  private IBookingService iBookingsService;

  @Operation(
      summary = "Create Booking REST API",
      description = "Create a new room booking"
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
  public ResponseEntity<ResponseDto> createBooking(@Valid @RequestBody CreateBookingDto createBookingDto) {
    iBookingsService.createBooking(createBookingDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Booking Details REST API",
      description = "REST API to fetch all booking details"
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
  public ResponseEntity<List<FetchBookingDto>> fetchAllBookings() {
    List<FetchBookingDto> fetchBookingDtoList = iBookingsService.fetchAllBookings();
    return ResponseEntity.status(HttpStatus.OK).body(fetchBookingDtoList);
  }

  @Operation(
      summary = "Update Booking Status REST API",
      description = "Update the status of a booking"
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

  @Operation(
      summary = "Delete Booking REST API",
      description = "Delete a room booking"
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
