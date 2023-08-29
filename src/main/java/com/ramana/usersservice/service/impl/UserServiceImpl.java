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
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param userRequestDto
     * @return
     */
    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
        if (!bCryptPasswordEncoder.matches(userLoginRequestDto.getPassword(),password)) {
            throw new BadRequestException("Username and Password not matched please try again");
        }
        return new UserLoginResponseDto("Logged in successfull", "200", "OK");
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public UserResponseDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with this Id: " + userId));
        return modelMapper.map(user, UserResponseDto.class);
    }

    /**
     * @param username
     * @param email
     * @param firstName
     * @param lastName
     * @return
     */
    @Override
    public UserResponseDto searchUser(String username, String email, String firstName, String lastName) {
        User user = null;
        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(email) && StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)) {
            throw new BadRequestException("Please provide at least one search parameter");
        }

        return null;
    }

    /**
     * @return
     */
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
    }
}
