package com.example.pnuunivmiryangcampus.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "feign.client.kakao")
public class KakaoProperties {

    private String name;
    private String baseUrl;
    private String authUri;
    private String tokenUri;
    private String restApiKey;
    private String redirectUri;
    private String grantType;
}
