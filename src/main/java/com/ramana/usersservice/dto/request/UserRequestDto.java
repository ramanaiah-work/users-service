package com.ramana.usersservice.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRequestDto {
    @NotNull(message = "Username should not be empty")
    @Size(min = 8, max = 50, message = "The username should consist of at least 8 characters and no more than 20 characters.")
    private String username;
    @NotNull(message = "Email should not be empty")
    @Email(message = "Email must be in this format abc@maiil.com")
    private String email;
    @NotNull(message = "First Name should not be empty")
    @Size(min = 2, max = 50, message = "The firstName should consist of at least 2 characters and no more than 20 characters.")
    private String firstName;
    @NotNull(message = "Last Name should not be empty")
    @Size(min = 2, max = 50, message = "The lastName should consist of at least 2 characters and no more than 20 characters.")
    private String lastName;
    @NotNull(message = "Password should not be empty")
    @Size(min = 8, max = 20, message = "The password should consist of at least 8 characters and no more than 15 characters.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and be at least 8 characters in length.")
    private String password;
    @NotNull(message = "Phone number should not be empty")
    @Size(min = 10, max = 10, message = "The Phone Number should consist of only 10 numeric digits.")
    private String phoneNumber;
    private Set<AddressDto> address;
}
