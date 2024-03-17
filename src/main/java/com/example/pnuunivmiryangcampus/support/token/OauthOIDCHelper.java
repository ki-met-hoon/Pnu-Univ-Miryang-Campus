package com.example.pnuunivmiryangcampus.support.token;

import com.example.pnuunivmiryangcampus.auth.kakao.KakaoOauthClient;
import com.example.pnuunivmiryangcampus.auth.kakao.KakaoProperties;
import com.example.pnuunivmiryangcampus.auth.dto.oidc.OidcDecodePayload;
import com.example.pnuunivmiryangcampus.auth.dto.oidc.OidcPublicKeyDto;
import com.example.pnuunivmiryangcampus.auth.dto.oidc.OidcPublicKeysResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthOIDCHelper {

    private final JwtOIDCProvider jwtOIDCProvider;
    private final KakaoOauthClient kakaoOauthClient;
    private final KakaoProperties kakaoProperties;

    private OidcDecodePayload getPayloadFromIdToken(String token, String iss, String aud, OidcPublicKeysResponse oidcPublicKeysResponse) {
        String kid = jwtOIDCProvider.getKidFromTokenHeader(token);

        OidcPublicKeyDto oidcPublicKeyDto = oidcPublicKeysResponse.keys().stream()
                        .filter(o -> o.kid().equals(kid))
                        .findFirst()
                        .orElseThrow();

        return jwtOIDCProvider.getOIDCTokenBody(token, oidcPublicKeyDto.n(), oidcPublicKeyDto.e(), iss, aud);
    }

    public OidcDecodePayload getKakaoOIDCDecodePayload(String token) {

        OidcPublicKeysResponse oidcPublicKeysResponse = kakaoOauthClient.getKakaoOIDCOpenKeys();
        return getPayloadFromIdToken(
                token,
                kakaoProperties.getBaseUrl(),
                kakaoProperties.getRestApiKey(),
                oidcPublicKeysResponse);
    }
}
