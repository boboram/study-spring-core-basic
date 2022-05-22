# study-spring-core-basic
스프링 핵심 원리 기본편 스터디

## 새로운 할인 정책 개발

### 애자일 소프트웨어 개발 선언

- 공정과 도구보다 개인과 상호작용을
- 포괄적인 문서보다 작동하는 소프트웨어를
- 계약 협상보다 고객과의 협력을
- 계획을 따르기보다 변화에 대응하기를

더 가치있게 여긴다.

### new test-code 
- command + shift + t 하면 새로운 테스트 파일 생성 가능

## 관심사 분리
- AppConfig 를 통해서 관심사를 확실하게 분리한다.
- 배역, 배우를 생각해보자.
- AppConfig 는 공연 기획자다.
- AppConfig 는 구체 클래스를 선택한다. 배역에 맞는 담당 배우를 선택한다. 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
- 이제 각 배우들은 담당 기능을 실행하는 책임만 지면 된다.
- OrderServiceImpl 은 기능을 실행하는 책임만 지면 된다. 

## AppConfig 리팩터링
- 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
- 역할과 구현 클래스가 한눈에 들어온다.