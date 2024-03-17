package com.example.pnuunivmiryangcampus.auth;

public record KakaoInformationResponse(
        String sub,
        String nickname,
        String email,
        boolean emailVerified
        ) {

        public static KakaoInformationResponse of(String sub, String nickname, String email, boolean emailVerified) {
                return new KakaoInformationResponse(sub, nickname, email, emailVerified);
        }
}
