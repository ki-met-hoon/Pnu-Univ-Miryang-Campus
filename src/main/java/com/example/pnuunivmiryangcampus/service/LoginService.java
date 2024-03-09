package com.example.pnuunivmiryangcampus.service;

import com.example.pnuunivmiryangcampus.auth.KakaoApiCaller;
import com.example.pnuunivmiryangcampus.auth.KakaoProperties;
import com.example.pnuunivmiryangcampus.dto.KakaoTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LoginService {

    private final KakaoApiCaller kakaoApiCaller;
    private final KakaoProperties kakaoProperties;

    public KakaoTokenDto getKakaoToken(String code) {
        return kakaoApiCaller.getKakaoToken(
                kakaoProperties.getRestApiKey(),
                kakaoProperties.getRedirectUri(),
                code,
                kakaoProperties.getGrantType()
        );
    }
}
