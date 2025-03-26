package com.trackitu.rooms.controller;

import com.trackitu.rooms.constants.RoomsConstants;
import com.trackitu.rooms.dto.BookingDto;
import com.trackitu.rooms.dto.ResponseDto;
import com.trackitu.rooms.service.IBookingService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/rooms/bookings")
@AllArgsConstructor
@Validated
public class BookingsController {

  private IBookingService iBookingsService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createBooking(@Valid @RequestBody BookingDto bookingDto) {
    iBookingsService.createBooking(bookingDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(RoomsConstants.STATUS_201, RoomsConstants.MESSAGE_201));
  }

  @GetMapping("/fetchAll")
  public ResponseEntity<List<BookingDto>> fetchAllBookings() {
    List<BookingDto> bookingDtoList = iBookingsService.fetchAllBookings();
    return ResponseEntity.status(HttpStatus.OK).body(bookingDtoList);
  }

  @PutMapping("/updateStatus")
  public ResponseEntity<ResponseDto> updateBookingStatus(@RequestBody BookingDto bookingDto) {
    boolean isUpdated = iBookingsService.updateBookingStatus(bookingDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteBooking(@RequestBody BookingDto bookingDto) {
    boolean isDeleted = iBookingsService.deleteBooking(bookingDto);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(RoomsConstants.STATUS_200, RoomsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(RoomsConstants.STATUS_500, RoomsConstants.MESSAGE_500));
    }
  }
}
