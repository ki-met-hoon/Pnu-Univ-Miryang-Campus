package com.example.pnuunivmiryangcampus.auth.dto.oidc;

public record OidcDecodePayload(
        /* issuer ex https://kauth.kakao.com */
        String iss,
        /* client id */
        String aud,
        /* oauth provider account unique id */
        String sub,
        String email
) {

    public static OidcDecodePayload of(String iss, String aud, String sub, String email) {
        return new OidcDecodePayload(iss, aud, sub, email);
    }
}
