# study-spring-core-basic
스프링 핵심 원리 기본편 스터디

# 의존관계 자동 주입

## 다양한 의존관계 주입 방법
- 생성자 주입
  - 생성자 호출시점에 딱 1번만 호출되는 것이 보장
  - 불변, 필수 의존관계에 사용 
  - 생성자가 한개라면 `@Autowired` 안써도 자동 주입 가능 
  - 가장 많이 사용 
- 수정자 주입(setter 주입) 
  - setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법 
  - 선택,변경 가능성이 있는 의존관계에 사용 
  - [자바빈](https://github.com/boboram/TIL/blob/main/JAVA/%EC%9D%B4%ED%8E%99%ED%8B%B0%EB%B8%8C-%EC%9E%90%EB%B0%94-%EC%99%84%EB%B2%BD-%EA%B3%B5%EB%9E%B5/%EC%95%84%EC%9D%B4%ED%85%9C-2-%EC%99%84%EB%B2%BD%EA%B3%B5%EB%9E%B5/6-%EC%9E%90%EB%B0%94%EB%B9%88.md) 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법 
  - 주입할 대상이 없어도 동작하게 하려면 `@Autowired(required = false)`로 지정 
- 필드 주입
  - 필드에 바로 주입 
  - 외부에서 변경이 불가능해서 테스트하기 번거롭다. 
  - 테스트 코드 정도에서만 사용하는 것이 좋다. 
- 일반 메서드 주입
  - 아무 메서드에나 `@Autowired` 사용 가능  
  - 스프링 빈이어야 동작 

## 옵션 처리 
- `@Autowired`만 사용하면 `required` 옵션의 기본값이 `true`로 되어 있어서 자동 주입 대상이 없으면 오류 발생
- 자동 주입 대상 옵션으로 처리하는 방법 
  - `@Autowired(required=false)`: 자동 주입 대상이 없으면 수정자 메서드 자체가 호출 X
  - `org.springframework.lang.@Nullable`: 자동 주입할 대상이 없으면 null 이 입력 
  - `Optional<>`: 자동 주입 대상이 없으면 `Optional.empty`가 입력 
- `AutowiredTest`
  - Member는 스프링 빈이 아니다.
  - `setNoBean1()`은 `@Autowired(required=false)`이므로 호출 자체가 안된다. 