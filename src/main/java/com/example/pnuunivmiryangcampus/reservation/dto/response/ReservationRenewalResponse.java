package com.example.pnuunivmiryangcampus.reservation.dto.response;

import com.example.pnuunivmiryangcampus.reservation.Reservation;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ReservationRenewalResponse(
        LocalDateTime endAt,
        int renewalCount
) implements Serializable {

    public static ReservationRenewalResponse of(LocalDateTime endAt, int renewalCount) {
        return new ReservationRenewalResponse(endAt, renewalCount);
    }

    public static ReservationRenewalResponse from(Reservation entity) {
        return new ReservationRenewalResponse(
                entity.getEndAt(),
                entity.getRenewalCount()
        );
    }
}