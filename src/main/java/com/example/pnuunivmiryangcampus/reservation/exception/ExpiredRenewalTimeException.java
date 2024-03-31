package com.example.pnuunivmiryangcampus.reservation.exception;

import com.example.pnuunivmiryangcampus.support.exception.BadRequestException;

public class ExpiredRenewalTimeException extends BadRequestException {

    private static final String MESSAGE = "연장 가능 시간은 종료 시간의 30분 전입니다.";

    public ExpiredRenewalTimeException() {
        super(MESSAGE);
    }
}
