# 🛍️ Musinsa Lite Backend

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Security-red?logo=jsonwebtokens)
![License](https://img.shields.io/badge/license-MIT-lightgrey)
![Status](https://img.shields.io/badge/status-Developing-yellow)

> **Musinsa Lite Backend**는 패션 커머스 플랫폼 [무신사(MUSINSA)]를 모티브로 한  
> **Spring Boot 기반 RESTful 백엔드 서버 프로젝트**입니다.  
> JWT 인증을 통한 로그인/회원가입, 상품/주문 API를 포함한 실제 서비스 구조를 구현했습니다.

---

## 🧩 Tech Stack

| 구분 | 기술 |
|------|------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.x |
| **Security** | Spring Security, JWT |
| **Database** | MySQL 8.x |
| **ORM** | Spring Data JPA, Hibernate |
| **Build Tool** | Gradle |
| **API Docs** | Springdoc OpenAPI (Swagger UI) |
| **Testing** | JUnit5, Mockito |
| **ETC** | Lombok, H2 (for local test) |

---

## 📁 Project Structure


---

## 🔐 Main Features

### 👤 Auth
- 회원가입 / 로그인 / 로그아웃
- JWT 기반 Access/Refresh Token 발급
- Refresh Token을 이용한 Access Token 자동 재발급
- 비밀번호 암호화 (BCrypt)
- Spring Security 기반 인증 및 인가 처리

### 🧑‍💼 User
- 회원 정보 조회 / 수정
- 마이페이지 API
- 탈퇴 (Soft Delete 예정)

### 🛍️ Product
- 상품 등록 / 수정 / 삭제 (관리자)
- 카테고리, 브랜드, 가격, 정렬 기능
- 검색 기능 (상품명, 브랜드명 등)

### 🛒 Order / Cart
- 장바구니 CRUD
- 주문 생성 / 취소
- 주문 상태 변경 (관리자)
- 주문 내역 조회

---

## ⚙️ Configuration (application.yml)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/musinsa?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true

jwt:
  issuer: musinsa-lite
  secret: change-this-to-a-256bit-secret-change-this-to-a-256bit-secret
  access-token-validity-seconds: 900
  refresh-token-validity-seconds: 604800

logging:
  level:
    org.springframework.security: INFO






🧠 Future Improvements

| 기능                                 | 설명                                               |
| ---------------------------------- | ------------------------------------------------ |
| 🔁 **Redis 기반 리프레시 토큰 관리**         | JWT 리프레시 토큰을 Redis에 저장하여 빠른 검증 및 로그아웃 처리 지원      |
| ☁️ **AWS S3 이미지 업로드 기능**           | 상품 및 사용자 이미지를 Amazon S3에 업로드하고 공개/비공개 버킷 관리      |
| ⚡ **ElasticSearch 상품 검색 고도화**      | 상품명, 브랜드, 색상, 태그 기반의 고급 검색 및 유사어·오타 대응 기능 추가     |
| 📦 **Kafka 기반 비동기 주문 처리**          | 주문, 결제, 알림 이벤트를 Kafka 토픽으로 분리하여 비동기 처리 구현        |
| 🧱 **Docker Compose 기반 CI/CD 자동화** | 애플리케이션을 Docker로 컨테이너화하고 배포 자동화 파이프라인 구축          |
| 🔒 **OAuth2 소셜 로그인 (카카오, 네이버)**    | Spring Security OAuth2를 활용한 카카오/네이버 간편 로그인 기능 추가 |
