package com.example.pnuunivmiryangcampus.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "KakaoInfoClient",
        url = "${feign.client.kakao.oicd-base-url}")
public interface KakaoInfoClient {

    @GetMapping("${feign.client.kakao.oicd-userinfo-uri}")
    KakaoInformationResponse kakaoUserInfo(@RequestHeader("Authorization") String accessToken);
}