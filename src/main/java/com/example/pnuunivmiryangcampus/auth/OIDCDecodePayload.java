package com.example.pnuunivmiryangcampus.auth;

public record OIDCDecodePayload(
        /* issuer ex https://kauth.kakao.com */
        String iss,
        /* client id */
        String aud,
        /* oauth provider account unique id */
        String sub,
        String email
) {

    public static OIDCDecodePayload of(String iss, String aud, String sub, String email) {
        return new OIDCDecodePayload(iss, aud, sub, email);
    }
}
