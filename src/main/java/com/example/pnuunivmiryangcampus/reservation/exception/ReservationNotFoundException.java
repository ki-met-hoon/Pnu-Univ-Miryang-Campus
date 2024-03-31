package com.example.pnuunivmiryangcampus.reservation.exception;

import com.example.pnuunivmiryangcampus.support.exception.NotFoundException;

public class ReservationNotFoundException extends NotFoundException {

    private static final String MESSAGE = "예약된 좌석을 찾을 수 없습니다.";

    public ReservationNotFoundException() {
        super(MESSAGE);
    }
}
