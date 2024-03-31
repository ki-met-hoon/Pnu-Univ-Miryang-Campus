package com.example.pnuunivmiryangcampus.reservation.exception;

import com.example.pnuunivmiryangcampus.support.exception.BadRequestException;

public class ReservationLimitExceededException extends BadRequestException {

    private static final String MESSAGE = "연장 가능 횟수를 초과했습니다.";

    public ReservationLimitExceededException() {
        super(MESSAGE);
    }
}
