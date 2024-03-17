package com.example.pnuunivmiryangcampus.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthOIDCHelper {

    private final JwtOIDCProvider jwtOIDCProvider;
    private final KakaoOauthClient kakaoOauthClient;
    private final KakaoProperties kakaoProperties;

    private OIDCDecodePayload getPayloadFromIdToken(String token, String iss, String aud, OIDCPublicKeysResponse oidcPublicKeysResponse) {
        String kid = jwtOIDCProvider.getKidFromTokenHeader(token);

        OIDCPublicKeyDto oidcPublicKeyDto = oidcPublicKeysResponse.keys().stream()
                        .filter(o -> o.kid().equals(kid))
                        .findFirst()
                        .orElseThrow();

        return jwtOIDCProvider.getOIDCTokenBody(token, oidcPublicKeyDto.n(), oidcPublicKeyDto.e(), iss, aud);
    }

    public OIDCDecodePayload getKakaoOIDCDecodePayload(String token) {

        OIDCPublicKeysResponse oidcPublicKeysResponse = kakaoOauthClient.getKakaoOIDCOpenKeys();
        return getPayloadFromIdToken(
                token,
                kakaoProperties.getBaseUrl(),
                kakaoProperties.getRestApiKey(),
                oidcPublicKeysResponse);
    }
}
