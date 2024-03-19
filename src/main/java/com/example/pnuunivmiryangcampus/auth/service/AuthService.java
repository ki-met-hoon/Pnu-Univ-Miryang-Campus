package com.example.pnuunivmiryangcampus.auth.service;

import com.example.pnuunivmiryangcampus.support.token.kakao.KakaoInfoClient;
import com.example.pnuunivmiryangcampus.auth.dto.kakao.KakaoUserInfoDto;
import com.example.pnuunivmiryangcampus.support.token.kakao.KakaoOauthClient;
import com.example.pnuunivmiryangcampus.support.token.kakao.KakaoProperties;
import com.example.pnuunivmiryangcampus.auth.OIDCDecodePayload;
import com.example.pnuunivmiryangcampus.support.token.OauthOIDCHelper;
import com.example.pnuunivmiryangcampus.userAccount.UserAccount;
import com.example.pnuunivmiryangcampus.auth.dto.kakao.KakaoTokenDto;
import com.example.pnuunivmiryangcampus.userAccount.dto.UserAccountDto;
import com.example.pnuunivmiryangcampus.userAccount.repository.UserAccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final KakaoOauthClient kakaoOauthClient;
    private final KakaoInfoClient kakaoInfoClient;
    private final KakaoProperties kakaoProperties;
    private final OauthOIDCHelper oauthOIDCHelper;
    private final UserAccountRepository userAccountRepository;

    public KakaoTokenDto getKakaoToken(String code) {

        return kakaoOauthClient.getKakaoToken(
                kakaoProperties.getRestApiKey(),
                kakaoProperties.getRedirectUri(),
                code,
                kakaoProperties.getGrantType()
        );
    }

    public void isUserRegistered(KakaoTokenDto kakaoTokenDto) {

        OIDCDecodePayload oidcDecodePayload = oauthOIDCHelper.getKakaoOIDCDecodePayload(kakaoTokenDto.idToken());
        Optional<UserAccount> userAccount = userAccountRepository.findBySub(oidcDecodePayload.sub());

        if (userAccount.isEmpty()) {
            saveUserAccount(kakaoTokenDto);
        }
    }

    private void saveUserAccount(KakaoTokenDto kakaoTokenDto) {

        KakaoUserInfoDto kakaoInformationResponse = kakaoInfoClient.kakaoUserInfo(kakaoTokenDto.tokenType() + " " + kakaoTokenDto.accessToken());
        UserAccountDto userAccountDto = UserAccountDto.of(kakaoInformationResponse.nickname(), kakaoInformationResponse.email(), kakaoInformationResponse.sub());

        userAccountRepository.save(userAccountDto.toEntity());
    }
}
