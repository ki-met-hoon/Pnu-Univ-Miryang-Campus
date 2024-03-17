package com.example.pnuunivmiryangcampus.auth.dto.oidc;

public record OidcPublicKeyDto(
        String kid,
        String alg,
        String use,
        String n,
        String e
) {

    public static OidcPublicKeyDto of(String kid, String alg, String use, String n, String e) {
        return new OidcPublicKeyDto(kid, alg, use, n, e);
    }
}
