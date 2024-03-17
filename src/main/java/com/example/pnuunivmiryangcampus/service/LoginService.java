package com.example.pnuunivmiryangcampus.service;

import com.example.pnuunivmiryangcampus.auth.KakaoInfoClient;
import com.example.pnuunivmiryangcampus.auth.KakaoInformationResponse;
import com.example.pnuunivmiryangcampus.auth.KakaoOauthClient;
import com.example.pnuunivmiryangcampus.auth.KakaoProperties;
import com.example.pnuunivmiryangcampus.auth.OIDCDecodePayload;
import com.example.pnuunivmiryangcampus.auth.OauthOIDCHelper;
import com.example.pnuunivmiryangcampus.domain.UserAccount;
import com.example.pnuunivmiryangcampus.dto.KakaoTokenDto;
import com.example.pnuunivmiryangcampus.dto.UserAccountDto;
import com.example.pnuunivmiryangcampus.repository.UserAccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LoginService {

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

        KakaoInformationResponse kakaoInformationResponse = kakaoInfoClient.kakaoUserInfo(kakaoTokenDto.tokenType() + " " + kakaoTokenDto.accessToken());
        UserAccountDto userAccountDto = UserAccountDto.of(kakaoInformationResponse.nickname(), kakaoInformationResponse.email(), kakaoInformationResponse.sub());

        userAccountRepository.save(userAccountDto.toEntity());
    }
}
