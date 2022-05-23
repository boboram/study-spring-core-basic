# study-spring-core-basic
스프링 핵심 원리 기본편 스터디


## 빈이름
- 빈 이름은 메서드 이름을 사용 
- 직접 부여 가능 `@Bean`
- 항상 다른 이름 사용 

## 스프링 빈 의존관계
- 스피링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)한다. 

스프링 컨테이너를 생성하고, 설정(구성) 정보를 참고해서 스프링 빈도 등록하고, 의존관계도 설정했다.
이제 스프링 컨테이너에서 데이터를 조회해보자.

## 컨테이너에 등록된 모든 빈 조회 
- ApplicationContextInfoTest@findAllBean 
- ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름 조회 
- ac.getBean() : 빈 이름으로 빈 객체(인스턴스) 조회 
- 애플리케이션 빈 출력
  - getRole로 구분 가능
  - ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
  - ROLE_APPLICATION : 내가 직접 등록한 빈인지 확인 
