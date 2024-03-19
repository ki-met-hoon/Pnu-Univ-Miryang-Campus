package com.example.pnuunivmiryangcampus.auth;

public record OIDCPublicKeyDto(
        String kid,
        String alg,
        String use,
        String n,
        String e
) {

    public static OIDCPublicKeyDto of(String kid, String alg, String use, String n, String e) {
        return new OIDCPublicKeyDto(kid, alg, use, n, e);
    }
}
