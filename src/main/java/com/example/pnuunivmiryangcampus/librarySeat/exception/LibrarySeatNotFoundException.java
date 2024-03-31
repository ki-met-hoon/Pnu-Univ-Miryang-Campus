package com.example.pnuunivmiryangcampus.librarySeat.exception;

import com.example.pnuunivmiryangcampus.support.exception.NotFoundException;

public class LibrarySeatNotFoundException extends NotFoundException {

    private static final String MESSAGE = "해당 좌석은 존재하지 않습니다.";

    public LibrarySeatNotFoundException() {
        super(MESSAGE);
    }
}
