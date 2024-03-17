package com.example.pnuunivmiryangcampus.librarySeat.dto;

import com.example.pnuunivmiryangcampus.librarySeat.LibrarySeat;
import java.time.LocalDateTime;

public record LibrarySeatDto(
        Long id,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        boolean isDeleted,
        int seatNumber,
        String availability
) {

    public static LibrarySeatDto of(Long id, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
                                    String modifiedBy, boolean isDeleted, int seatNumber, String availability) {
        return new LibrarySeatDto(id, createdAt, createdBy, modifiedAt, modifiedBy, isDeleted, seatNumber,
                availability);
    }

    public static LibrarySeatDto from(LibrarySeat entity) {
        return new LibrarySeatDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.isDeleted(),
                entity.getSeatNumber(),
                entity.getAvailability()
        );
    }

    public LibrarySeat toEntity() {
        return LibrarySeat.of(
                seatNumber,
                availability
        );
    }
}