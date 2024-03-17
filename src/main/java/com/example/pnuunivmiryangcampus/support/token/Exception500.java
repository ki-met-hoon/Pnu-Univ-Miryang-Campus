package com.example.pnuunivmiryangcampus.support.token;

import lombok.Getter;

@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }
}
