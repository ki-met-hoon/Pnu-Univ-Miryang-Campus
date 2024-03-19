package com.example.pnuunivmiryangcampus.auth;

import java.util.List;

public record OIDCPublicKeysResponse(
        List<OIDCPublicKeyDto> keys
) {

    public static OIDCPublicKeysResponse of(List<OIDCPublicKeyDto> keys) {
        return new OIDCPublicKeysResponse(keys);
    }
}