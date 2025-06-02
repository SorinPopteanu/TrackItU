package com.trackitu.accounts.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold Customer information"
)
public class CustomerDto {

  private Long id;

  @Schema(
      description = "First Name of the customer", example = "Sorin"
  )
  @NotEmpty(message = "First name can not be a null or empty")
  @Size(min = 3, max = 20, message = "First name should be between 3 and 20 characters")
  private String firstName;

  @Schema(
      description = "Last Name of the customer", example = "Popteanu"
  )
  @NotEmpty(message = "Last name can not be a null or empty")
  @Size(min = 3, max = 20, message = "Last name should be between 3 and 20 characters")
  private String lastName;

  @Schema(
      description = "Email address of the customer", example = "sorin_popteanu@gmail.com"
  )
  @NotEmpty(message = "Email address can not be a null or empty")
  @Email(message = "Email address is not valid")
  private String email;

  @Schema(
      description = "Mobile number of the customer", example = "0733118888"
  )
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  private String mobileNumber;
}
