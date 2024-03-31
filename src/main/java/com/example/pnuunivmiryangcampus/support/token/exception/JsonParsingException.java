package com.example.pnuunivmiryangcampus.support.token.exception;

import com.example.pnuunivmiryangcampus.support.exception.InternalException;

public class JsonParsingException extends InternalException {

    private static final String MESSAGE = "JSON Parsing 과정에서 오류가 발생했습니다.";

    public JsonParsingException(String message) {
        super(MESSAGE + ": " + message);
    }
}
