package com.ramana.usersservice.service;

import com.ramana.usersservice.dto.request.UserLoginRequestDto;
import com.ramana.usersservice.dto.request.UserRequestDto;
import com.ramana.usersservice.dto.response.UserLoginResponseDto;
import com.ramana.usersservice.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto userRequestDto);

    UserLoginResponseDto validateUser(UserLoginRequestDto userLoginRequestDto);
}
