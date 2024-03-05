package com.example.pnuunivmiryangcampus.dto.response;

import com.example.pnuunivmiryangcampus.dto.LibrarySeatDto;
import java.io.Serializable;

public record LibrarySeatResponse(
        int seatNumber,
        String availability
) implements Serializable {

    public static LibrarySeatResponse of(int seatNumber, String availability) {
        return new LibrarySeatResponse(seatNumber, availability);
    }

    public static LibrarySeatResponse from(LibrarySeatDto dto) {
        return new LibrarySeatResponse(
                dto.seatNumber(),
                dto.availability()
        );
    }
}