package com.example.pnuunivmiryangcampus.reservation.exception;

public class ReservationLimitExceededException extends IllegalArgumentException{

    private static final String MESSAGE = "연장 가능 횟수를 초과했습니다.";

    public ReservationLimitExceededException() {
        super(MESSAGE);
    }
}
