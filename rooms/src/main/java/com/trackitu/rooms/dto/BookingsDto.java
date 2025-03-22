package com.trackitu.rooms.dto;

import com.trackitu.rooms.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class BookingsDto {

  @NotNull(message = "Room id can not be a null or empty")
  private Long roomId;

  @NotNull(message = "Customer id can not be a null or empty")
  private Long customerId;

  @NotNull(message = "Booking date can not be a null or empty")
  private LocalDate bookingDate;

  @NotNull(message = "Start time can not be a null or empty")
  private LocalTime startTime;

  @NotNull(message = "End time can not be a null or empty")
  private LocalTime endTime;

  @NotNull(message = "Booking status can not be a null or empty")
  private BookingStatus bookingStatus;

}
