package com.trackitu.rooms.dto.booking;

import com.trackitu.rooms.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
    name = "Booking",
    description = "Schema to hold Booking information"
)
@Data
public class UpdateBookingDto {

  @Schema(
      description = "Booking ID", example = "1"
  )
  @NotNull(message = "Booking id can not be a null or empty")
  private Long id;

  @Schema(
      description = "Booking status", example = "RESERVED"
  )
  @NotNull(message = "Booking status can not be a null or empty")
  private Status status;

}
