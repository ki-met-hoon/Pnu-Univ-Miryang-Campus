# Pnu-Univ-Miryang-Campus
부산대학교 밀양 캠퍼스 학생 편의 웹 사이트 프로젝트
```
📦 
└─ src
   ├─ main
   ├─ java
     │  └─ com
     │     └─ example
     │        └─ pnuunivmiryangcampus
     │           ├─ AuditingFields.java
     │           ├─ PnuUnivMiryangCampusApplication.java
     │           ├─ 🗂️auth
     │           │  ├─ OIDCDecodePayload.java
     │           │  ├─ OIDCPublicKeyDto.java
     │           │  ├─ OIDCPublicKeysResponse.java
     │           │  ├─ controller
     │           │  │  └─ AuthController.java
     │           │  ├─ dto
     │           │  │  └─ kakao
     │           │  │     ├─ KakaoTokenDto.java
     │           │  │     └─ KakaoUserInfoDto.java
     │           │  └─ service
     │           │     └─ AuthService.java
     │           ├─ 🗂️config
     │           │  ├─ RedisCacheConfig.java
     │           │  ├─ SwaggerConfig.java
     │           │  └─ security
     │           │     ├─ CustomUserDetails.java
     │           │     ├─ CustomUserDetailsService.java
     │           │     ├─ JwtAuthenticationFilter.java
     │           │     └─ SecurityConfig.java
     │           ├─ 🗂️librarySeat
     │           │  ├─ LibrarySeat.java
     │           │  ├─ controller
     │           │  │  ├─ LibrarySeatController.java
     │           │  │  └─ LibrarySeatControllerDocs.java
     │           │  ├─ dto
     │           │  │  ├─ LibrarySeatDto.java
     │           │  │  └─ response
     │           │  │     └─ LibrarySeatResponse.java
     │           │  ├─ exception
     │           │  │  └─ LibrarySeatNotFoundException.java
     │           │  ├─ repository
     │           │  │  └─ LibrarySeatRepository.java
     │           │  └─ service
     │           │     └─ LibrarySeatService.java
     │           ├─ 🗂️reservation
     │           │  ├─ Reservation.java
     │           │  ├─ controller
     │           │  │  ├─ ReservationController.java
     │           │  │  └─ ReservationControllerDocs.java
     │           │  ├─ dto
     │           │  │  ├─ ReservationDto.java
     │           │  │  └─ response
     │           │  │     ├─ ReservationRenewalResponse.java
     │           │  │     └─ ReservationResponse.java
     │           │  ├─ exception
     │           │  │  ├─ DuplicateReservationException.java
     │           │  │  ├─ ExpiredRenewalTimeException.java
     │           │  │  ├─ ReservationLimitExceededException.java
     │           │  │  └─ ReservationNotFoundException.java
     │           │  ├─ repository
     │           │  │  └─ ReservationRepository.java
     │           │  └─ service
     │           │     └─ ReservationService.java
     │           ├─ 🗂️support
     │           │  ├─ AuditorAwareImpl.java
     │           │  ├─ exception
     │           │  │  ├─ BadRequestException.java
     │           │  │  ├─ BusinessException.java
     │           │  │  ├─ ControllerAdvice.java
     │           │  │  ├─ ErrorResponse.java
     │           │  │  ├─ ForbiddenException.java
     │           │  │  ├─ InternalException.java
     │           │  │  ├─ NotFoundException.java
     │           │  │  └─ UnauthorizedException.java
     │           │  └─ token
     │           │     ├─ JwtOIDCProvider.java
     │           │     ├─ OauthOIDCHelper.java
     │           │     ├─ exception
     │           │     │  ├─ ExpiredTokenException.java
     │           │     │  ├─ InvalidTokenException.java
     │           │     │  └─ JsonParsingException.java
     │           │     └─ kakao
     │           │        ├─ KakaoInfoClient.java
     │           │        ├─ KakaoOauthClient.java
     │           │        └─ KakaoProperties.java
     │           └─ 🗂️userAccount
     │              ├─ UserAccount.java
     │              ├─ dto
     │              │  └─ UserAccountDto.java
     │              └─ repository
     │                 └─ UserAccountRepository.java
     └─ resources
```
