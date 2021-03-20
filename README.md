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
* Swagger UI
* h2 Database

### 개발 방식
* TDD 방식을 따를 것 (TDD가 어려울 경우 단위 테스트라도 작성하기)

### 기능
* 회원
  * 회원 가입 (권한 X)
  * 회원 조회 (admin 권한)
    * 프로필 조회 (상세 조회)
    * 모든 회원 조회
  * 회원 수정 (user 권한)
  * 회원 삭제 (user 권한)
  * 이메일 중복 확인 (권한 X)
    
    
* 상품
  * 모든 상품 조회 (인증 필요x)
  * 상품 상세 (인증 필요x)
  * 상품 등록 (admin 권한)
  * 상품 수정 (admin 권한)
  * 상품 삭제 (admin 권한)

* 찜 목록
  * 찜 목록 추가 (user 권한)
  * 찜 목록 조회 (user 권한)
  * 찜 목록 삭제 (user 권한)

* 상품 주문
  * 주문 상세 (user 권한)
  * 전체 주문 조회 (user 권한)
  
  
### ERD
![image](https://user-images.githubusercontent.com/64251594/111862415-943d1f00-8998-11eb-961d-cee77315a3ad.png)


### Swagger Document

![image](https://user-images.githubusercontent.com/64251594/111862587-90f66300-8999-11eb-8e6a-e8ae64642658.png)
![image](https://user-images.githubusercontent.com/64251594/111862594-9653ad80-8999-11eb-90ce-612dc9bed553.png)
![image](https://user-images.githubusercontent.com/64251594/111862605-9e135200-8999-11eb-8f2d-0bc8299ac205.png)
![image](https://user-images.githubusercontent.com/64251594/111862612-a3709c80-8999-11eb-8d5b-34bc1216a0ea.png)


