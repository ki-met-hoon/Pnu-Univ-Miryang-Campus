package com.example.pnuunivmiryangcampus.reservation.dto.response;

import com.example.pnuunivmiryangcampus.reservation.Reservation;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ReservationResponse(
        Long ReservationId,
        Long seatId,
        LocalDateTime startAt,
        LocalDateTime endAt,
        int renewalCount
) implements Serializable {

    public static ReservationResponse of(Long ReservationId, Long seatId, LocalDateTime startAt, LocalDateTime endAt, int renewalCount) {
        return new ReservationResponse(ReservationId, seatId, startAt, endAt, renewalCount);
    }

    public static ReservationResponse from(Reservation entity, Long seatId) {
        return new ReservationResponse(
                entity.getId(),
                seatId,
                entity.getStartAt(),
                entity.getEndAt(),
                entity.getRenewalCount()
        );
    }
}
