package com.example.pnuunivmiryangcampus.auth;

import com.example.pnuunivmiryangcampus.dto.KakaoTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.client.kakao.name}", url = "${feign.client.kakao.base-url}")
public interface KakaoApiCaller {

    @PostMapping("/${feign.client.kakao.token-uri}")
    KakaoTokenDto getKakaoToken(@RequestParam("client_id") String restApiKey,
                                @RequestParam("redirect_uri") String redirectUrl,
                                @RequestParam("code") String code,
                                @RequestParam("grant_type") String grantType);
}
