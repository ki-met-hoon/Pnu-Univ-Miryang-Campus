package com.example.pnuunivmiryangcampus.auth.dto.kakao;

public record KakaoUserInfoDto(
        String sub,
        String nickname,
        String email,
        boolean emailVerified
        ) {

        public static KakaoUserInfoDto of(String sub, String nickname, String email, boolean emailVerified) {
                return new KakaoUserInfoDto(sub, nickname, email, emailVerified);
        }
}
