package com.trackitu.assets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Schema(
    name = "ErrorResponse",
    description = "Schema to hold Error Response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

  @Schema(
      description = "API path invoked by the client"
  )
  private String apiPath;

  @Schema(
      description = "Error code representing the HTTP status code",
      example = "500"
  )
  private HttpStatus errorCode;

  @Schema(
      description = "Error message describing the error",
      example = "Internal Server Error"
  )
  private String errorMessage;

  @Schema(
      description = "Time when the error occurred"
  )
  private LocalDateTime errorTime;
}
