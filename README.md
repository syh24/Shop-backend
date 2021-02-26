# :octocat: 스프링 부트로 간단한 쇼핑몰 프로젝트

### 개발 스펙
* Java 11
* Gradle 6.8.2
* Spring Boot 2.4.3

### 사용 기술
* REST API
* Spring MVC
* Spring Data JPA
* Querydsl
* Spring boot security
* h2 Database

### 개발 방식
* TDD 방식을 따를 것 (TDD가 어려울 경우 단위 테스트라도 작성하기)

### 기능
* 회원
  * 회원 인증
    * 로그인, 로그아웃 jwt 토큰을 통한 인증방식
      * jwt 토큰의 만료시간은 10시간
  * 회원 조회
    * 프로필 조회 (상세 조회)
  * 회원 수정
    * 주소 변경 가능

* 상품
  * 상품 조회 (인증 필요x)
    * 한 페이지당 5개 단위의 페이징 처리
  * 상품 상세 (인증 필요x)
  * 상품 추가 (admin 권한에서만 허용)
  * 상품 삭제 (admin 권한에서만 허용)
  * 상품 할인
    * 상품(one) to 상품할인(many)
    * 유효기간이 유효한 할인 리스트들 중 가장 할인률이 높은 리스트를 선택하는 로직
  * 상품 리뷰
    * 상품(one) to 리뷰(many)
    * 상품명, 상품평점, 리뷰 본문내용 등의 정보 포함
    * 리뷰는 해당 상품을 구매한 유저만 작성 가능
      * Buyer 도메인 추가
  * 상품 평점
    * 상품을 구매한 유저들의 리뷰에 적용된 평점을 평균내어 저장
      * 상품 리뷰 추가 시 평균내어 상품 테이블에 저장
