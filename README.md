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
### 스프링 빈 조회 - 동일한 타입이 둘 이상
- 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자.
- `ac.getBeanOfType()`을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.
### 스프링 빈 조회 - 상속관계 
- 부모 타입으로 조회하면, 자식 타입도 함께 조회된다.
  - 그래서 모든 자바 객체의 최고 부모인 `Object` 타입으로 조회하면, 모든 스프링 빈을 조회한다.
### BeanFactory 와 ApplicationContext
- BeanFactory
  - 스프링 컨테이너의 최상위 인터페이스다.
  - 스프링 빈을 관리하고 조회하는 역할을 담당한다.
  - `getBean()` 을 제공한다.
  - 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory 가 제공하는 기능이다. 
- ApplicationContext
  - BeanFactory 기능을 모두 상속받아서 제공한다.
  - 빈을 관리하고 검색하는 기능을 BeanFactory 가 제공해주는데, 그러면 둘의 차이가 뭘까?
  - 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능은 물론이고 수많은 부가기능이 필요하다.
#### ApplicationContext 부가기능 
- 메시지 소스를 활용한 국제화 기능 
- 환경 변수
- 애플리케이션 이벤트
- 편리한 리소스 조회 
#### ApplicationContext 정리
- ApplicationContext 는 BeanFactory 의 기능을 상속받는다.
- ApplicationContext 는 빈 관리 기능 + `편리한 부가 기능`을 제공
- BeanFactory 를 직접 사용할 일은 거의 없다. 부가기능이 포함된 ApplicationContext 를 사용한다.
- BeanFactory 나 ApplicationContext 를 `스프링 컨테이너`라 한다.