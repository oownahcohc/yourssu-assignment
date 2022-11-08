# yourssu-assignment

# 유어슈 1주차 과제

***

### 1. application.yml 설정
```
server:
  port: 8080
  servlet:
    encoding:
      charset: UTF-8
      force: true
    context-path: /api

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/assignment?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
    username: root

  jpa:
    database: mysql
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        show_sql: true
        format_sql: true

logging:
  level:
    org.hibernate.SQL: debug
    org.hibernate.type: trace
    com.amazonaws.util.EC2MetadataUtils: error

jwt:
  secret: {$SECRET}
```

***

## 2. 예외처리
![image](./src/main/resources/image/exception.png)
* **@ExceptionHandler**
<br> - 에러를 매우 유연하게 처리할 수 있는 방법을 제공하는 기능으로 Exception 클래스들을 속성으로 받아 처리할 예외를 지정할 수 있다.
<br> - @ResponseStatus와 달리 에러 응답(payload)을 자유롭게 다룰 수 있다는 점에서 유연하다.

* **@RestControllerAdvice**
<br> - 여러 컨트롤러에 대해 전역적으로 ExceptionHandler를 적용해주는 어노테이션으로 @ResponseBody가 붙어 있어 응답을 Json으로 내려준다

#### 위의 @ExceptionHandler 와 @RestControllerAdvice 를 이용하면 
* 하나의 클래스로 모든 컨트롤러에 대해 전역적으로 예외 처리가 가능함
* 직접 정의한 에러 응답을 일관성있게 클라이언트에게 내려줄 수 있음
* 별도의 try-catch문이 없어 코드의 가독성이 높아짐
#### 과 같은 이점을 누릴 수 있다

***

## 3. 패키지 분할

### domain
**DB와 매핑되는 entity 클래스와 각 엔티티에 대한 repository 를 관리하는 도메인 패키지**
![image](./src/main/resources/image/domain.png)

* **article** : article 엔티티와 repository 
* **comment** : comment 엔티티와 repository 
* **user** : user 엔티티와 repository 
* **common** : 도메인 패키지 내에서 공통적으로 사용되는 프로그램 관리

### config
**애플리케이션 구동에 있어 필요한 설정을 관리하는 패키지**
![image](./src/main/resources/image/config.png)

* **querydsl** : querydsl 설정
* **security** : 클라이언트 통신 간 security 설정

### common
**애플리케이션 개발에 있어서 전역, 공통적으로 사용되는 코드를 관리하는 패키지**
![image](./src/main/resources/image/common.png)

* **advice** : 전역 예외처리 코드 관리
* **constants** : 불변 상수 코드 관리
* **dto** : 고정된 예외 response, 성공 response 등의 dto 관리
* **exception** : 프로젝트 내에서 사용할 커스텀 exception, status code 등 관리
* **log** : 프로젝트 내에서 사용할 logger 파일 설정
* **utils** : 프로젝트 내에서 사용할 utils 파일 관리

### app
**애플리케이션 api 개발을 관리하는 패키지**
![image](./src/main/resources/image/app.png)

**구성**<br>
* **dto** : request 와 response 로 구분해 요청 관련 dto, 응답 관련 dto 분리
* **controller** : 각 api 별로 controller 관리
* **service** : 각 api 별로 service 관리

***

## 유어슈 1주차 과제
