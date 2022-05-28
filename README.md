# study-spring-core-basic
스프링 핵심 원리 기본편 스터디

# 웹 애플리케이션과 싱글톤

- 스프링은 태생이 기업용 온라인 서비스 기술을 지원하기 위해 탄생
- 대부분의 스프링 애플리케이션은 웹 애플리케이션이다. 물론 웹이 아닌 애플리케이션 개발도 얼마든지 개발 가능
- 웹 애플리케이션은 보통 여러 고객이 동시에 요청을 한다.

## 스프링 없는 순수한 DI 컨테이너 
- 우리가 만들었던 스프링 없는 순수한 DI 컨테이너인 AppConfig 는 요청을 할 때 마다 객체를 새로 생성(`SingleTonTest@pureContainer`)
- 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸된다! -> 메모리 낭비 심함
- 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> 싱글톤 패턴

# 싱글톤 패턴
- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
  - private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.
- 예제 : `SingletonService` & `SingletonTest@SingleTonTest` 

> 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법 선택 

- 싱글톤 패턴을 사용하면 이미 만들어진 객체를 공유해서 효율적으로 사용가능하나 여러가지 문제점이 존재한다.

## 싱글톤 패턴 문제점
- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
- 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP 위반
- 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
- 테스트 어려움
- 내부 속성을 변경하거나 초기화 하기 어려움
- private 생성자로 자식 클래스를 만들기 어렵다.
- 결론적으로 유연성 떨어짐
- 안티패턴으로 불리기도 함 

# 싱글톤 컨테이너
- 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 ㅇ니스턴스를 싱글톤으로 관리
- 지금까지 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.

## 싱글톤 컨테이너
- 스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리
  - 컨테이너는 객체를 하나만 생성 후 관리 
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
- 스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하며넛 객체를 싱글톤으로 유지 가능 
  - 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
  - DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤 사용 가능 
- `SingleTonTest@springContainer`