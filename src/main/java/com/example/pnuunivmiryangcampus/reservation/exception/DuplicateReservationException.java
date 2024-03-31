package com.example.pnuunivmiryangcampus.reservation.exception;

import com.example.pnuunivmiryangcampus.support.exception.BadRequestException;

public class DuplicateReservationException extends BadRequestException {

    private static final String MESSAGE = "이미 예약된 좌석이 존재합니다.";

    public DuplicateReservationException() {
        super(MESSAGE);
    }
}
