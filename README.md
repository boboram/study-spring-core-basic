# study-spring-core-basic
스프링 핵심 원리 기본편 스터디

# 빈 생명주기 콜백

## 빈 생명주기 콜백 시작 
- DB 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 객체의 초기화와 종료 작업이 필요하다.
- `connect()` | `disconnect()`

```
생성자 호출, url = null
connect: null
call: null message = 초기화 연결 메시지
```
- 생성자 부분을 보면 url 정보 없이 connect가 호출되는 것을 확인할 수 있다. 
- 객체 생성 단계에는 url이 없고 객체를 생성 다음에 외부에서 수정자 주입을 통해서 `setUrl()`이 호출돼야 url이 존재하게 된다. 
- 스프링 빈 라이프사이클 : `객체생성 -> 의존관계 주입` 
  - 생성자 주입은 제외, 그 외의 주입 방식은 위같은 라이프사이클을 가진다. 
- 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공, 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.
  - 안전한 종료 작업 가능 
- 스프링 빈의 이벤트 라이프사이클 (싱글톤)
  - 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료 
    - 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
    - 소멸전 콜백 : 빈이 소멸되기 직전에 지원 
- 객체의 생성과 초기화를 분리하자 
  - 초기화 작업이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서 한번에 다 처리하는게 더 나을 수 있다.
- 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다. 
  - 인터페이스(InitializingBean, DisposableBean)
  - 설정 정보에 초기화 메서드, 종료 메서드 지정 
  - @PostConstruct, @PreDestroy 애노테이션 지원

## 인터페이스(InitializingBean, DisposableBean)
- `implements InitializingBean, DisposableBean`
- 초기화, 소멸 인터페이스 단점
  - 스프링 전용 인터페이스임. 해당 코드가 스프링 전용 인터페이스에 의존한다.
  - 초기화, 소멸 메서드의 이름을 변경 불가능 
  - 내가 코드를 고칠 수 없는 외부 라이브러리에 적용 불가능 
  - 인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법들이고, 지금은 다음의 더 나은 방법들이 있어서 거의 사용하지 않는다. 

## 빈 등록 초기화, 소멸 메서드
- `@Bean(initMethod = "init", destroyMethod = "close")`처럼 초기화, 소멸 메서드를 지정할 수 있다. 
  - 설정 정보 사용 특징
    - 메서드 이름을 자유롭게 줄 수 있다.
    - 스프링 빈이 스프링 코드에 의존하지 않는다.
    - 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다. 
  - 종료 메서드 추론
    - `@Bean의 destroyMethod` 속성에는 아주 특별한 기능이 있다. 
    - 라이브러리는 대부분 close, shutdown 이라는 이름의 종료 메서드를 사용한다. 
    - @Bean 의 `destroyMethod`는 기본값이 `(inferred)`(추론)으로 등록되어 있다.
    - destroyMethod 옵션값 미지정시 `@Bean(initMethod = "init", destroyMethod = "(inferred)")` 처럼 돼있는것 
      - 이 추론 기능은 close, shutdown 이라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다. 
    - 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다. 
    - **추론 기능을 사용하기 싫으면** `destroyMethod=""`처럼 빈 공백을 지정하면 된다. 

## 애노테이션 @PostConstruct, @PreDestroy
- 최신 스프링에서 권장하는 방법
- 매우 편리 
- javax -> 자바 표준임. 다른 컨테이너에서도 동작 
- 컴포넌트 스캔과 잘 어울린다. 
- 외부 라이브러리에는 적용 못함. 
  - 필요시 @Bean 의 기능을 사용하자 

## 정리 
- **@PostConstruct, @PreDestroy 애노테이션을 사용하자**
- 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 `@Bean`의 `initMethod`, `destroyMethod` 를 사용하자. 