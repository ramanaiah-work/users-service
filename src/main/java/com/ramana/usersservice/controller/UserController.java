package com.ramana.usersservice.controller;

import com.ramana.usersservice.dto.request.UserLoginRequestDto;
import com.ramana.usersservice.dto.request.UserRequestDto;
import com.ramana.usersservice.dto.response.UserLoginResponseDto;
import com.ramana.usersservice.dto.response.UserResponseDto;
import com.ramana.usersservice.exceptions.Error;
import com.ramana.usersservice.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@Slf4j
@OpenAPIDefinition(
        info = @Info(
                title = "User API",
                version = "1.0.0",
                description = "API Documentation for User Management Application"
        )
)
public class UserController {

    private UserService userService;

    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created Successfully", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            ))
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.debug("Request: {}", userRequestDto);
        UserResponseDto userResponseDto = userService.saveUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "User Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Logged in Successfully", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserLoginResponseDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            )),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            ))
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        UserLoginResponseDto userLoginResponseDto = userService.validateUser(userLoginRequestDto);
        return ResponseEntity.ok(userLoginResponseDto);
    }

    @Operation(summary = "Get User By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Details Fetched Successfully", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            )),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            ))
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserBydId(@Parameter(name = "userId", description = "Enter Registered User Id", required = true) @PathVariable("userId") String userId) {
        UserResponseDto userResponseDto = userService.getUserById(userId);
        return ResponseEntity.ok(userResponseDto);
    }

    @Operation(summary = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch All Users", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            )),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class)
            ))
    })
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponseDto>> searchUser() {
        List<UserResponseDto> userResponseDto = userService.getAllUsers();
        return ResponseEntity.ok(userResponseDto);
    }
}
