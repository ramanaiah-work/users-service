package com.ramana.usersservice.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserLoginResponseDto {
    private String responseMessage;
    private String responseCode;
    private String responseType;
}
