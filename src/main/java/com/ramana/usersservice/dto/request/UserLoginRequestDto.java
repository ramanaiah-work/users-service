package com.ramana.usersservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginRequestDto {
    @NotNull(message = "Username/Email Must Not be Null or Empty")
    private String userNameOrEmail;
    @NotNull(message = "Password Must not be Null or Empty")
    private String password;
}
