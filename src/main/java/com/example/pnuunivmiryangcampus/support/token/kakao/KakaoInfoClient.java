package com.example.pnuunivmiryangcampus.support.token.kakao;

import com.example.pnuunivmiryangcampus.auth.dto.kakao.KakaoUserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "KakaoInfoClient",
        url = "${feign.client.kakao.oicd-base-url}")
public interface KakaoInfoClient {

    @GetMapping("${feign.client.kakao.oicd-userinfo-uri}")
    KakaoUserInfoDto kakaoUserInfo(@RequestHeader("Authorization") String accessToken);
}