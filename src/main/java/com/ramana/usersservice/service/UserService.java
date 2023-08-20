package com.ramana.usersservice.service;

import com.ramana.usersservice.dto.request.UserLoginRequestDto;
import com.ramana.usersservice.dto.request.UserRequestDto;
import com.ramana.usersservice.dto.response.UserLoginResponseDto;
import com.ramana.usersservice.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto userRequestDto);

    UserLoginResponseDto validateUser(UserLoginRequestDto userLoginRequestDto);

    UserResponseDto getUserById(String userId);

    UserResponseDto searchUser(String username, String email, String firstName, String lastName);

    List<UserResponseDto> getAllUsers();
}
