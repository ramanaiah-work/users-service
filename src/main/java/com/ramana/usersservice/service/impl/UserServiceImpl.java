package com.ramana.usersservice.service.impl;

import com.ramana.usersservice.dto.request.UserLoginRequestDto;
import com.ramana.usersservice.dto.request.UserRequestDto;
import com.ramana.usersservice.dto.response.UserLoginResponseDto;
import com.ramana.usersservice.dto.response.UserResponseDto;
import com.ramana.usersservice.entity.User;
import com.ramana.usersservice.exceptions.BadRequestException;
import com.ramana.usersservice.exceptions.NotFoundException;
import com.ramana.usersservice.repository.UserRepository;
import com.ramana.usersservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    /**
     * @param userRequestDto
     * @return
     */
    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    /**
     * @param userLoginRequestDto
     * @return
     */
    @Override
    public UserLoginResponseDto validateUser(UserLoginRequestDto userLoginRequestDto) {
        Optional<User> user = userRepository.findByUsernameOrEmail(userLoginRequestDto.getUserNameOrEmail(), userLoginRequestDto.getUserNameOrEmail());
        if (user.isEmpty()) {
            throw new NotFoundException("'" + userLoginRequestDto.getUserNameOrEmail() + "' username Not Found in the system.");
        }
        String password = user.get().getPassword();
        if (!password.equals(userLoginRequestDto.getPassword())) {
            throw new BadRequestException("Username and Password not matched please try again");
        }
        return new UserLoginResponseDto("Logged in successfull", "200", "OK");
    }
}
