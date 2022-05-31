# study-spring-core-basic
스프링 핵심 원리 기본편 스터디

# 컴포넌트 스캔 

## 컴포넌트 스캔과 의존관계 자동 주입 시작하기
- 지금까지 스프링 빈을 등록할 때는 자바 코드의 @Bean이나 XML의 <bean> 등을 통해서 설정 정보에 직접 등록할 빈을 나열했다. 
- 예제에서는 몇개가 안되었지만, 이렇게 등록해야 할 스프링 빈이 수십, 수백개가 되면 일일이 등록하기도 귀찮고, 설정 정보도 커지고, 누락하는 문제도 발생한다. 
- 그래서 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다. 
- 또 의존관계도 자동으로 주입하는 `@Autowired` 라는 기능도 제공한다. 
- `AutoAppConfig` 

### @ComponentScan 
- `@ComponentScan` 은 `@Component` 가 붙은 모든 클래스를 스프링 빈으로 등록 
- 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용 
  - 빈 이름 기본 전략: MemberServiceImpl 클래스 -> memberServiceImpl
  - 빈 이름 직접 지정: 만약 스프링 빈의 이름을 직접 지정하고 싶으면 `@Component("memberService")` 라고 이름을 부여 

# 탐색 위치와 기본 스캔 대상 
- basePackages = "hello.core.member"  

## 탐색할 패키지의 시작 위치 지정 
- 모든 자바 클래스를 다 컴포넌트 스캔하면 시간이 오래 걸린다. 그래서 꼭 필요한 위치부터 탐색하도록 시작 위치를 지정할 수 있다.  
- `basePackages` : 탐색할 패키지의 시작 위치 지정, 이 패키지를 모함해서 하위 패키지 모두 탐색 
  - `basePackages = {"hello.core", "hello.service"}` 이렇게 여러 시작 위치 지정 가능 
- `basePackageClasses` : 지정한 클래스의 패키지를 탐색 시작 위치로 지정 
- 만약 지정하지 않으면 `@ComponentScan` 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다. 
  - AutoAppConfig 클래스의 패키지부터

### 권장하는 방법 
- 선생님은.. 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것. 최근 스프링 부트도 이 방법을 기본으로 제공 
- SpringBootApplication 에서 이미 @ComponentScan 이 걸려 있다. (굳이 명시를 하지 않아도 프로젝트 초기 설정시 생기는 클래스에서 @SpringBootApplication 가 붙어 있음)

## 컴포넌트 스캔 기본 대상 
- 컴포넌트 스캔은 `@Component` 뿐만 아니라 다음과 같은 내용도 추가로 대상에 포함 
  - `@Component` : 컴포넌트 스캔에서 사용 
  - `@Controller` : 스프링 MVC 컨트롤러에서 사용 
  - `@Service` : 스프링 비즈니스 로직에서 사용 
  - `@Repository` : 스프링 데이터 접근 계층에서 사용 
  - `@Configuration` : 스프링 설정 정보에서 사용 
- 사실 애노테이션은 상속 관계라는 것이 없다. 그래서 이렇게 애노테이션이 특정 애노테이션을 들고 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능 

## 필터 
- `ComponentFilterAppConfigTest`
  - `includeFilters` 에 `MyIncludeComponent` 애노테이션을 추가해서 BeanA 가 스프링 빈에 등록
  - `excludeFilters` 에 `MyExcludeComponent` 애노테이션을 추가해서 BeanB 가 스프링 빈에 등록되지 않는다. 

### Filter Type 옵션 
- ANNOTATION: 기본값, 애노테이션을 인식해서 동작 
  - ex) org.example.SomeAnnotation  
- ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작 
  - ex) org.example.SomeClass
- ASPECTJ: AspectJ 패턴 사용 
  - ex) org.example.*Service+  
- REGEX : 정규 표현식 
  - ex) org\.example\.Default.* 
- CUSTOM : TypeFilter 라는 인터페이스를 구현해서 처리 
  - ex) org.example.MyTypeFilter

- 옵션을 변경하면서 사용하기 보다는 스프링의 기본 설정에 최대한 맞추어 사용하는 것을 권장 