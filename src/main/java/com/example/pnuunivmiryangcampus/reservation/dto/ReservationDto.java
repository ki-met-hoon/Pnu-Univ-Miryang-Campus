package com.example.pnuunivmiryangcampus.reservation.dto;

import com.example.pnuunivmiryangcampus.reservation.Reservation;
import java.io.Serializable;
import java.time.LocalDateTime;

public record ReservationDto(
        Long id,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        boolean isDeleted,
        Long userAccountId,
        Long librarySeatId,
        LocalDateTime startAt,
        LocalDateTime endAt,
        int renewalCount
        ) implements Serializable {

    public static ReservationDto of(Long id, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, boolean isDeleted, Long userAccountId, Long librarySeatId, LocalDateTime startAt, LocalDateTime endAt, int renewalCount) {
        return new ReservationDto(id, createdAt, createdBy, modifiedAt, modifiedBy, isDeleted, userAccountId, librarySeatId, startAt, endAt, renewalCount);
    }

    public static ReservationDto of(Long userAccountId, Long librarySeatId, LocalDateTime startAt, LocalDateTime endAt) {
        return new ReservationDto(null, null, null, null, null, false, userAccountId, librarySeatId, startAt, endAt, 0);
    }

    public static ReservationDto from(Reservation entity) {
        return new ReservationDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.isDeleted(),
                entity.getUserAccountId(),
                entity.getLibrarySeatId(),
                entity.getStartAt(),
                entity.getEndAt(),
                entity.getRenewalCount()
        );
    }

    public Reservation toEntity() {
        return Reservation.of(
                userAccountId,
                librarySeatId,
                startAt,
                endAt,
                renewalCount
        );
    }
}