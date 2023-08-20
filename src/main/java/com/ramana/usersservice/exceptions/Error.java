package com.ramana.usersservice.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Error {
    private LocalDateTime timestamp;
    private int statusCode;
    private String errorType;
    private String message;
    private String path;
    private String stacktrace;
}
