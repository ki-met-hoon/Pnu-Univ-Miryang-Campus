package com.example.pnuunivmiryangcampus.auth.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoTokenDto(
        String tokenType,
        String accessToken,
        String idToken,
        Long expiresIn,
        String refreshToken,
        Long refreshTokenExpiresIn
        ) {
}
