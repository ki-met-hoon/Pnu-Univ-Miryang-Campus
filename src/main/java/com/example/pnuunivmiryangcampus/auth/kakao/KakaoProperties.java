package com.example.pnuunivmiryangcampus.auth.kakao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "feign.client.kakao")
public class KakaoProperties {

    private String name;
    private String baseUrl;
    private String authUrl;
    private String tokenUri;
    private String oicdOpenKeyUri;
    private String restApiKey;
    private String redirectUri;
    private String grantType;
}
