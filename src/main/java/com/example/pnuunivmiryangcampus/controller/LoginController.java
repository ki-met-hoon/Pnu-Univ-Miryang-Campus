package com.example.pnuunivmiryangcampus.controller;

import com.example.pnuunivmiryangcampus.auth.KakaoProperties;
import com.example.pnuunivmiryangcampus.dto.KakaoTokenDto;
import com.example.pnuunivmiryangcampus.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class LoginController {

    private final LoginService loginService;
    private final KakaoProperties kakaoProperties;

    @GetMapping("/kakao")
    public String kakaoLogin() {
        return "redirect:" + kakaoProperties.getAuthUrl();
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<KakaoTokenDto> getKakaoToken(@RequestParam String code) {

        KakaoTokenDto kakaoTokenDto = loginService.getKakaoToken(code);
        loginService.isUserRegistered(kakaoTokenDto);

        return ResponseEntity.ok(kakaoTokenDto);
    }
}
