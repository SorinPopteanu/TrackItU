package com.trackitu.rooms.dto;

import com.trackitu.rooms.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Schema(
    name = "Booking",
    description = "Schema to hold Booking information"
)
@Data
public class BookingDto {

  @Schema(
      description = "Room ID", example = "15"
  )
  @NotNull(message = "Room id can not be a null or empty")
  private Long roomId;

  @Schema(
      description = "Customer ID", example = "10"
  )
  @NotNull(message = "Customer id can not be a null or empty")
  private Long customerId;

  @Schema(
      description = "Booking date", example = "2021-12-31"
  )
  @NotNull(message = "Booking date can not be a null or empty")
  private LocalDate bookingDate;

  @Schema(
      description = "Start time", example = "10:00"
  )
  @NotNull(message = "Start time can not be a null or empty")
  private LocalTime startTime;

  @Schema(
      description = "End time", example = "11:00"
  )
  @NotNull(message = "End time can not be a null or empty")
  private LocalTime endTime;

  @Schema(
      description = "Booking status", example = "RESERVED"
  )
  @NotNull(message = "Booking status can not be a null or empty")
  private BookingStatus bookingStatus;

}
