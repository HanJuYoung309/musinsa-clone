# musinsa-clone


🛍️ Musinsa-Lite Backend (Spring Boot + JWT + MySQL)
무신사 클론 프로젝트의 백엔드 서버입니다.
Spring Boot, Spring Security, JWT 인증, JPA, MySQL을 기반으로 구현되었습니다.

🚀 기술 스택
구분기술LanguageJava 17FrameworkSpring Boot 3.xBuild ToolGradleDatabaseMySQL 8.xORMSpring Data JPASecuritySpring Security + JWTAPI DocsSwagger (Springdoc OpenAPI)TestJUnit5, MockitoToolsLombok, H2 (로컬테스트용)

⚙️ 프로젝트 구조
src/
├── main/
│   ├── java/com/musinsa
│   │   ├── MusinsaApplication.java
│   │   ├── auth/         # 인증/인가 (JWT)
│   │   ├── user/         # 회원가입, 로그인, 마이페이지
│   │   ├── product/      # 상품 조회/등록/관리
│   │   ├── order/        # 주문, 장바구니
│   │   ├── common/       # 공통 예외, 응답, 설정
│   │   └── config/       # Security, CORS, JPA 설정
│   └── resources/
│       ├── application.yml
│       └── data.sql
└── test/
    └── ...


⚡ 주요 기능
🔑 회원 기능


회원가입 / 로그인 / 로그아웃


JWT Access / Refresh Token 발급


회원 정보 조회 및 수정 (MyPage)


토큰 재발급


🛍 상품 기능


상품 목록 조회 (카테고리, 정렬, 검색)


상품 상세 조회


관리자 상품 등록 / 수정 / 삭제


🛒 주문 기능


장바구니 담기, 수정, 삭제


주문 생성, 조회, 취소


결제(Stub)



🧩 API 예시
회원가입
POST /api/auth/signup
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "1234",
  "name": "홍길동"
}

로그인
POST /api/auth/login
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "1234"
}

Response:
{
  "accessToken": "eyJhbGciOiJIUzI1NiIs...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
}


🧠 환경설정
application.yml
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
  secret: "change-this-to-a-256bit-secret-change-this-to-a-256bit-secret"
  access-token-validity-seconds: 900
  refresh-token-validity-seconds: 604800

logging:
  level:
    org.springframework.security: INFO


🧪 로컬 실행 방법
# 1️⃣ 빌드
./gradlew clean build

# 2️⃣ 실행
java -jar build/libs/musinsa-0.0.1-SNAPSHOT.jar

또는 IDE에서 MusinsaApplication 실행.

🧰 Swagger UI


API 문서 확인:
👉 http://localhost:8080/swagger-ui/index.html



🧾 DB 설계 (요약)
테이블설명users사용자 계정refresh_tokensJWT 리프레시 토큰 저장products상품 정보orders주문 정보order_items주문 상세categories카테고리brands브랜드cart_items장바구니 항목

🧑‍💻 개발자 가이드
브랜치 전략


main — 운영 배포용


develop — 개발 통합 브랜치


feature/* — 기능 단위 개발 브랜치


커밋 컨벤션
feat: 새로운 기능 추가
fix: 버그 수정
refactor: 코드 구조 개선
docs: 문서 수정
chore: 설정, 빌드 관련 수정


📦 연동 정보
연동 대상경로Frontend (React)http://localhost:5173API Base URLhttp://localhost:8080/apiJWT HeaderAuthorization: Bearer <token>

