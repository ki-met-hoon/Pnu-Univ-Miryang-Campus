package com.example.pnuunivmiryangcampus.support.token.exception;

import com.example.pnuunivmiryangcampus.support.exception.UnauthorizedException;

public class ExpiredTokenException extends UnauthorizedException {

    private static final String MESSAGE = "Token의 유효기간이 만료되었습니다.";

    public ExpiredTokenException() {
        super(MESSAGE);
    }
}
