package com.example.pnuunivmiryangcampus.support.token.exception;

import com.example.pnuunivmiryangcampus.support.exception.UnauthorizedException;

public class InvalidTokenException extends UnauthorizedException {

    private static final String MESSAGE = "유효하지 않는 Token 입니다.";

    public InvalidTokenException() {
        super(MESSAGE);
    }
}
