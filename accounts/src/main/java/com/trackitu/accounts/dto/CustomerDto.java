package com.trackitu.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

  @NotEmpty(message = "First name can not be a null or empty")
  @Size(min = 3, max = 20, message = "First name should be between 3 and 20 characters")
  private String firstName;

  @NotEmpty(message = "Last name can not be a null or empty")
  @Size(min = 3, max = 20, message = "Last name should be between 3 and 20 characters")
  private String lastName;

  @NotEmpty(message = "Email address can not be a null or empty")
  @Email(message = "Email address is not valid")
  private String email;

  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  private String mobileNumber;
}
