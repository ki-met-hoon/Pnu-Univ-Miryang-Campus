# Campus Connect
부산대학교 밀양 캠퍼스 학생 편의 웹 사이트 프로젝트

## 🙋‍♀️ 프로젝트 소개

 부산대학교 밀양 캠퍼스 학생들을 위해 다양한 정보를 한 곳에 모아 제공하는 **플랫폼 서비스** 입니다.😊
<br>

### 개발 기간
2024.2.14 ~ 2024.4.01
<br>

### BE 사용 기술 및 협업 스텍
- `jdk 11`
- `springboot`
- `mysql`
- `SpringDataJPA`
- `spring security`
- `open feign`
- `aws elastic beanStalk`
- `github action`
- `swagger ui`
- `figma`
- `notion`
<br>

## 🔅 서비스 소개

**✏️ 통합된 오픈채팅방**   
   - 생명자원과학대학의 분산된 오픈 채팅방을 **한 곳에서** 관리하고 볼 수 있어요!
     
     <img width="400" alt="KakaoTalk_20240526_172845840_01" src="https://github.com/ki-met-hoon/Pnu-Univ-Miryang-Campus/assets/101192772/26abd271-e292-48b2-874d-fa26ae2de732">
     
**📜 버스 시간표 조회**
   - 교내 및 교외 **버스 시간표 정보** 제공

     <img width="400" alt="KakaoTalk_20240526_172845840_02" src="https://github.com/ki-met-hoon/Pnu-Univ-Miryang-Campus/assets/101192772/81b5b32a-cabe-4148-8277-9d100ae58ec6">
     
**💻  생명자원과학대학 도서관 좌석 예약**
   - 시험 기간에 도서관 좌석때문에 스트레스 받으셨죠?
   - 새롭게 리뉴얼된 생명자원과학대학 도서관 좌석을 쉽고 간편하게 **예약**할 수 있습니다!

     <img width="400" alt="image" src="https://github.com/ki-met-hoon/Pnu-Univ-Miryang-Campus/assets/101192772/a99b5312-f50c-4a2c-87eb-980a5410c55b">
<br>

### 프로젝트 구조
```
(🗂️ = directory)

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
     │           │  ├─ 📂controller
     │           │  ├─ 📂dto
     │           │  │  └─ 📂kakao
     │           │  └─ 📂service
     │           ├─ 🗂️config
     │           │  ├─ RedisCacheConfig.java
     │           │  ├─ SwaggerConfig.java
     │           │  └─ 📂security
     │           ├─ 🗂️librarySeat
     │           │  ├─ LibrarySeat.java
     │           │  ├─ 📂controller
     │           │  ├─ 📂dto
     │           │  │  └─ 📂response
     │           │  ├─ 📂exception
     │           │  ├─ 📂repository
     │           │  └─ 📂service
     │           ├─ 🗂️reservation
     │           │  ├─ Reservation.java
     │           │  ├─ 📂controller
     │           │  ├─📂 dto
     │           │  │  └─📂 response
     │           │  ├─ 📂 exception
     │           │  ├─ 📂repository
     │           │  └─ 📂service
     │           ├─ 🗂️support
     │           │  ├─ AuditorAwareImpl.java
     │           │  ├─ 📂exception
     │           │  └─ 📂token
     │           │     ├─ 📂exception
     │           │     └─ 📂kakao
     │           └─ 🗂️userAccount
     │              ├─ UserAccount.java
     │              ├─ 📂dto
     │              └─ 📂repository
     └─ resources
```
