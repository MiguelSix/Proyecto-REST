package com.itq.userService.service;

@SuppressWarnings("serial")
public class CustomUserException extends RuntimeException {

    public CustomUserException(String message) {
        super(message);
    }
}
