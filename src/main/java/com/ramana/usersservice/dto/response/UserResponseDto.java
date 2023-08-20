package com.ramana.usersservice.dto.response;

import com.ramana.usersservice.dto.request.AddressDto;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserResponseDto {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<AddressDto> address;
}
