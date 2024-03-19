package com.example.pnuunivmiryangcampus.support.token.kakao;

import com.example.pnuunivmiryangcampus.auth.dto.kakao.KakaoTokenDto;
import com.example.pnuunivmiryangcampus.auth.OIDCPublicKeysResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "KakaoOauthClient",
        url = "${feign.client.kakao.base-url}")
public interface KakaoOauthClient {

    @PostMapping("${feign.client.kakao.token-uri}")
    KakaoTokenDto getKakaoToken(@RequestParam("client_id") String restApiKey,
                                @RequestParam("redirect_uri") String redirectUrl,
                                @RequestParam("code") String code,
                                @RequestParam("grant_type") String grantType);

    @Cacheable(cacheNames = "KakaoOICD", cacheManager = "oidcCacheManager")
    @GetMapping("${feign.client.kakao.oicd-open-key-uri}")
    OIDCPublicKeysResponse getKakaoOIDCOpenKeys();
}
