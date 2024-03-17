package com.example.pnuunivmiryangcampus.librarySeat.dto.response;

import com.example.pnuunivmiryangcampus.librarySeat.dto.LibrarySeatDto;
import java.io.Serializable;

public record LibrarySeatResponse(
        Long id,
        int seatNumber,
        String availability
) implements Serializable {

    public static LibrarySeatResponse of(Long id, int seatNumber, String availability) {
        return new LibrarySeatResponse(id, seatNumber, availability);
    }

    public static LibrarySeatResponse from(LibrarySeatDto dto) {
        return new LibrarySeatResponse(
                dto.id(),
                dto.seatNumber(),
                dto.availability()
        );
    }
}