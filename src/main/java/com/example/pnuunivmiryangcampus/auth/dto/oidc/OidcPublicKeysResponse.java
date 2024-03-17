package com.example.pnuunivmiryangcampus.auth.dto.oidc;

import java.util.List;

public record OidcPublicKeysResponse(
        List<OidcPublicKeyDto> keys
) {

    public static OidcPublicKeysResponse of(List<OidcPublicKeyDto> keys) {
        return new OidcPublicKeysResponse(keys);
    }
}