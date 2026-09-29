# 3장. IoC와 DI

스프링 프레임워크의 가장 핵심이 되는 개념은 **IoC**(Inversion of Control, 제어의 역전)와 **DI**(Dependency Injection, 의존성 주입)이다.
이번 장에서는 객체와 객체 사이의 의존 관계를 개발자가 직접 만들 때 생기는 문제를 살펴보고, 스프링 컨테이너가 객체를 대신 만들고 연결해 주는 방식으로 이 문제를 해결하는 방법을 배운다.
그리고 `@Component`, `@Service`, `@Repository` 애노테이션으로 객체를 스프링에 등록하고, 생성자를 통해 의존 객체를 주입받는 방법을 익힌다.

## 객체와 의존 관계

### 의존 관계란?

객체 A가 일을 하기 위해 객체 B를 사용한다면, "**A가 B에 의존한다**"고 말한다.
이때 B를 A의 **의존 객체**(Dependency)라고 한다.

게시판 애플리케이션을 예로 들어 보자. 게시글 목록을 조회하는 기능은 보통 다음과 같이 역할을 나눠 만든다.

```
BoardController  ──사용──▶  BoardService  ──사용──▶  BoardRepository
(요청을 받는다)              (업무를 처리한다)            (데이터를 저장·조회한다)
```

- `BoardController`는 `BoardService`에 의존한다.
- `BoardService`는 `BoardRepository`에 의존한다.

이처럼 애플리케이션은 여러 객체가 서로 의존하며 협력하는 구조로 만들어진다.
그렇다면 `BoardService`는 자신이 사용할 `BoardRepository` 객체를 어떻게 얻을까?

### 직접 객체를 만드는 방식

가장 쉬운 방법은 필요한 객체를 `new`로 **직접 만드는** 것이다.

```java
public class BoardService {

  // 필요한 객체를 직접 생성한다.
  private MemoryBoardRepository boardRepository = new MemoryBoardRepository();

  public List<Board> list() {
    return boardRepository.findAll();
  }
}
```

간단해 보이지만, 애플리케이션이 커지면 다음과 같은 문제가 생긴다.

**1) 의존 객체를 바꾸려면 사용하는 쪽의 코드를 고쳐야 한다**

데이터를 메모리가 아닌 데이터베이스에 저장하도록 `MemoryBoardRepository`를 `JdbcBoardRepository`로 바꾼다고 하자.
`BoardService`의 코드를 직접 고쳐야 한다. `BoardRepository`를 사용하는 클래스가 10개라면 10개를 모두 고쳐야 한다.

**2) 같은 객체가 여러 번 만들어진다**

`BoardService`와 `CommentService`가 각각 `new MemoryBoardRepository()`를 실행하면 저장소 객체가 두 개 만들어진다.
한쪽에서 저장한 게시글이 다른 쪽에서는 보이지 않는 문제가 생긴다.

**3) 테스트하기 어렵다**

`BoardService`를 테스트할 때 진짜 저장소 대신 테스트용 가짜 저장소를 사용하고 싶어도, `BoardService` 안에서 저장소를 직접 만들고 있으므로 바꿔 끼울 방법이 없다.

이 문제들의 원인은 하나이다. **객체가 자신이 사용할 객체를 스스로 만들고 있기 때문**이다.
사용하는 쪽이 의존 객체의 **구체적인 클래스**와 **생성 방법**까지 알고 있으니, 의존 객체가 바뀌면 사용하는 쪽도 함께 바뀌어야 한다.
이런 상태를 두 객체가 **강하게 결합**(Tight Coupling)되어 있다고 한다.

## 제어의 역전(IoC)

### IoC란?

**제어의 역전**(IoC, Inversion of Control)은 **객체를 만들고 연결하는 제어권을 객체 자신이 아닌 외부에 넘기는 것**이다.

- **기존 방식**: `BoardService`가 필요한 `MemoryBoardRepository`를 **스스로** 만든다.
- **IoC 방식**: `BoardService`는 필요한 객체를 만들지 않는다. **외부의 누군가**가 객체를 만들어서 `BoardService`에 넘겨준다.

식당에 비유해 보자.
요리사가 요리할 때마다 직접 시장에 가서 재료를 사 온다면 요리에 집중하기 어렵다. 재료가 바뀌면 요리사가 가는 가게도 바뀌어야 한다.
대신 식당 관리자가 재료를 준비해서 주방에 넣어 주면, 요리사는 **받은 재료로 요리하는 일에만 집중**할 수 있다.
재료를 어디서 사 올지는 관리자가 결정하므로, 거래처가 바뀌어도 요리사는 달라질 것이 없다.

### 이미 경험한 IoC

사실 우리는 1장에서 이미 IoC를 경험했다.

```java
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello, Spring Boot!";
  }
}
```

이 코드 어디에도 `new HelloController()`라는 코드는 없다. `hello()` 메서드를 호출하는 코드도 없다.
그런데도 `/hello` 요청이 들어오면 `hello()` 메서드가 실행되었다.
`HelloController` 객체를 만들고 `hello()` 메서드를 호출한 것은 개발자의 코드가 아니라 **스프링**이다.
개발자는 "무엇을 할지"만 작성하고, "언제 만들고 언제 호출할지"는 스프링이 결정했다. 이것이 제어의 역전이다.

> **라이브러리와 프레임워크의 차이**
> 라이브러리는 개발자의 코드가 필요할 때 **라이브러리를 호출**한다. 실행 흐름의 제어권이 개발자에게 있다.
> 프레임워크는 **프레임워크가 개발자의 코드를 호출**한다. 실행 흐름의 제어권이 프레임워크에 있다.
> 스프링이 "프레임워크"인 이유가 바로 IoC이다.

## 의존성 주입(DI)

### DI란?

IoC는 "제어권을 외부에 넘긴다"는 **원칙**이다. 그렇다면 외부에서 만든 객체를 어떻게 넘겨받을까?
그 구체적인 방법이 **의존성 주입**(DI, Dependency Injection)이다.

의존성 주입은 **객체가 사용할 의존 객체를 외부에서 넣어 주는 것**이다.
의존 객체를 넣어 주는 방법에는 생성자, 세터(setter) 메서드, 필드가 있으며, 가장 권장되는 방법은 **생성자**를 이용하는 것이다.

```java
public class BoardService {

  private final MemoryBoardRepository boardRepository;

  // 필요한 객체를 생성자로 받는다.
  public BoardService(MemoryBoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public List<Board> list() {
    return boardRepository.findAll();
  }
}
```

이제 `BoardService`는 `new`로 저장소를 만들지 않는다. 누군가 생성자를 통해 넣어 주는 저장소를 받아서 사용할 뿐이다.

### 인터페이스와 함께 사용하기

하지만 위 코드는 아직 `MemoryBoardRepository`라는 **구체적인 클래스**에 의존하고 있다.
저장소를 다른 클래스로 바꾸려면 여전히 `BoardService`의 코드를 고쳐야 한다.

그래서 DI는 보통 **인터페이스**와 함께 사용한다.
저장소가 해야 할 일을 인터페이스로 정의하고, `BoardService`는 인터페이스에만 의존하게 만든다.

```java
// 저장소가 해야 할 일을 정의한 인터페이스
public interface BoardRepository {
  List<Board> findAll();
  void save(Board board);
}
```

```java
// 인터페이스를 구현한 클래스 (메모리에 저장)
public class MemoryBoardRepository implements BoardRepository { ... }

// 인터페이스를 구현한 다른 클래스 (데이터베이스에 저장)
public class JdbcBoardRepository implements BoardRepository { ... }
```

```java
public class BoardService {

  private final BoardRepository boardRepository;   // 인터페이스에 의존한다.

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }
  ...
}
```

이제 `BoardService`는 어떤 저장소가 들어오는지 모른다. `BoardRepository` 인터페이스를 구현한 객체라면 무엇이든 받아서 사용한다.
이런 상태를 **느슨하게 결합**(Loose Coupling)되어 있다고 한다.

### 객체를 조립하는 쪽

그렇다면 누가 객체를 만들고 넣어 줄까? 스프링을 사용하지 않는다면 다음과 같이 **객체를 조립하는 코드**를 따로 작성해야 한다.

```java
public class AppAssembler {

  public static void main(String[] args) {
    // 1. 의존 객체를 만든다.
    BoardRepository boardRepository = new MemoryBoardRepository();

    // 2. 의존 객체를 생성자로 넣어 주면서 객체를 만든다.
    BoardService boardService = new BoardService(boardRepository);

    // 3. 사용한다.
    System.out.println(boardService.list());
  }
}
```

저장소를 `JdbcBoardRepository`로 바꾸고 싶다면 **조립하는 코드 한 곳만** 고치면 된다. `BoardService`는 고칠 필요가 없다.

| 항목 | 직접 생성 방식 | DI 방식 |
| --- | --- | --- |
| 의존 객체를 만드는 곳 | 사용하는 객체 안 (`new`) | 외부 (조립하는 쪽) |
| 의존하는 대상 | 구체적인 클래스 | 인터페이스 |
| 의존 객체를 바꿀 때 | 사용하는 모든 클래스를 고친다. | 조립하는 코드 한 곳만 고친다. |
| 테스트할 때 | 가짜 객체로 바꿀 수 없다. | 가짜 객체를 생성자로 넣으면 된다. |

그런데 애플리케이션에 클래스가 수백 개라면, 이 조립 코드도 수백 줄이 된다.
**이 조립 작업을 대신 해 주는 것이 바로 스프링 컨테이너이다.**

## 스프링 컨테이너와 빈

### 스프링 컨테이너

**스프링 컨테이너**(Spring Container)는 애플리케이션에 필요한 객체를 **만들고**, 객체 사이의 의존 관계를 **연결하고**, 만든 객체를 **보관하고 관리하는** 역할을 한다.
앞에서 직접 작성한 `AppAssembler`의 역할을 스프링이 자동으로 해 주는 것이다.

```
[ 개발자 ]
   클래스를 작성하고 애노테이션(@Service 등)으로 표시한다.
        │
        ▼
[ 스프링 컨테이너 ]
   ① 객체를 만든다.
   ② 의존 관계를 연결한다. (DI)
   ③ 만든 객체를 보관하고 관리한다.
```

개발자는 "이 클래스의 객체를 스프링이 관리하게 해 달라"고 **애노테이션으로 표시**만 하면 된다.

### 빈(Bean)

스프링 컨테이너가 만들고 관리하는 객체를 **빈**(Bean)이라고 한다.
`new`로 직접 만든 객체는 빈이 아니다. 스프링 컨테이너에 등록되어 스프링이 관리하는 객체만 빈이다.

빈에는 이름이 붙는다. 따로 지정하지 않으면 **클래스 이름의 첫 글자를 소문자로 바꾼 것**이 빈의 이름이 된다.

| 클래스 | 빈 이름 |
| --- | --- |
| `BoardService` | `boardService` |
| `MemoryBoardRepository` | `memoryBoardRepository` |
| `HelloController` | `helloController` |

### ApplicationContext

스프링 컨테이너를 코드로 표현한 것이 **`ApplicationContext`** 인터페이스이다.
1장에서 본 `SpringApplication.run()` 메서드는 애플리케이션을 실행한 후 스프링 컨테이너, 즉 `ApplicationContext` 객체를 리턴한다.

```java
ApplicationContext context = SpringApplication.run(HelloApplication.class, args);
```

`ApplicationContext`의 주요 메서드는 다음과 같다.

| 메서드 | 기능 |
| --- | --- |
| `getBean(Class<T> type)` | 지정한 타입의 빈을 꺼낸다. |
| `getBean(String name)` | 지정한 이름의 빈을 꺼낸다. |
| `containsBean(String name)` | 지정한 이름의 빈이 있는지 확인한다. |
| `getBeanDefinitionNames()` | 등록된 모든 빈의 이름을 배열로 리턴한다. |
| `getBeanDefinitionCount()` | 등록된 빈의 개수를 리턴한다. |

> 실제 애플리케이션 코드에서는 `getBean()`으로 빈을 직접 꺼내 쓰는 일이 거의 없다. 필요한 빈은 생성자로 **주입받는다.**
> `getBean()`은 스프링 컨테이너의 동작을 확인하거나 학습할 때 주로 사용한다.

### 빈의 범위: 싱글톤

스프링 컨테이너는 기본적으로 **빈을 클래스마다 하나만** 만든다. 이를 **싱글톤**(Singleton) 범위라고 한다.
`BoardService` 빈을 여러 곳에서 주입받더라도, 모두 **같은 객체** 하나를 공유한다.
앞에서 본 "같은 객체가 여러 번 만들어지는" 문제가 자연스럽게 해결된다.

> **주의**: 싱글톤 빈은 여러 요청이 **동시에 함께 사용**한다.
> 따라서 서비스나 컨트롤러 클래스의 필드에 "현재 로그인한 사용자"처럼 요청마다 달라지는 값을 저장하면 안 된다.
> 필드에는 주입받은 의존 객체처럼 **변하지 않는 값**만 두고, 요청마다 달라지는 값은 메서드의 파라미터나 지역 변수로 다룬다.

> 필요하면 요청할 때마다 새 객체를 만드는 **프로토타입**(Prototype) 등 다른 범위를 지정할 수도 있지만, 대부분의 빈은 싱글톤으로 사용한다.

## 빈 등록하기

### 컴포넌트 스캔

스프링 컨테이너에 빈을 등록하는 가장 일반적인 방법은 클래스에 **`@Component`** 애노테이션을 붙이는 것이다.

```java
@Component
public class MemoryBoardRepository implements BoardRepository { ... }
```

애플리케이션이 시작되면 스프링은 `@Component`가 붙은 클래스를 찾아서 객체를 만들고 빈으로 등록한다.
이렇게 빈으로 등록할 클래스를 찾는 과정을 **컴포넌트 스캔**(Component Scan)이라고 한다.

1장에서 배운 것처럼 `@SpringBootApplication`에는 `@ComponentScan`이 포함되어 있다.
그래서 `@SpringBootApplication`이 붙은 클래스가 있는 패키지와 그 **하위 패키지**가 컴포넌트 스캔의 대상이 된다.

```
com.example.hello                    ← HelloApplication (@SpringBootApplication) : 여기서부터 스캔
├── HelloController                  ← 스캔 대상 ✔
└── board                            ← 하위 패키지도 스캔 대상 ✔
    ├── BoardController
    ├── BoardService
    └── MemoryBoardRepository

com.example.other                    ← 다른 패키지 : 스캔 대상 아님 ✘
```

### 스테레오타입 애노테이션

`@Component` 외에도, 클래스의 **역할**을 나타내는 애노테이션이 있다.
이 애노테이션들은 모두 내부에 `@Component`를 포함하고 있으므로, 붙이면 똑같이 컴포넌트 스캔의 대상이 되어 빈으로 등록된다.
이처럼 역할을 표시하는 애노테이션을 **스테레오타입 애노테이션**(Stereotype Annotation)이라고 한다.

| 애노테이션 | 역할 | 예 |
| --- | --- | --- |
| `@Component` | 일반적인 컴포넌트. 특정 역할로 분류하기 어려울 때 사용한다. | 유틸리티, 공통 기능 |
| `@Controller` / `@RestController` | 웹 요청을 받아 처리하는 **컨트롤러** | `HelloController`, `BoardController` |
| `@Service` | 업무 로직을 처리하는 **서비스** | `BoardService` |
| `@Repository` | 데이터를 저장하고 조회하는 **저장소** | `MemoryBoardRepository` |
| `@Configuration` | 빈을 직접 등록하는 **설정 클래스** | `AppConfig` |

```
[ @RestController ]        [ @Service ]           [ @Repository ]
  BoardController   ──▶    BoardService    ──▶   MemoryBoardRepository
  (요청 처리)                (업무 처리)              (데이터 저장·조회)
```

모두 `@Component`로 표시해도 동작은 같지만, 역할에 맞는 애노테이션을 사용하면 **코드만 보고도 클래스의 역할을 알 수 있다.**
또한 `@Repository`가 붙은 클래스에서 데이터베이스 관련 예외가 발생하면, 스프링이 이를 스프링의 공통 예외로 바꿔 주는 추가 기능도 적용된다.

> 컨트롤러-서비스-저장소로 역할을 나누는 **계층 구조**는 다른 장에서 자세히 다룬다.

### @Configuration과 @Bean

`@Component`는 **개발자가 작성한 클래스**에만 붙일 수 있다.
JDK나 외부 라이브러리의 클래스처럼 소스 코드를 고칠 수 없는 클래스를 빈으로 등록하려면, **설정 클래스**에 **`@Bean` 메서드**를 작성한다.

```java
@Configuration
public class AppConfig {

  @Bean
  public Clock clock() {                 // 메서드 이름(clock)이 빈 이름이 된다.
    return Clock.systemDefaultZone();    // 리턴한 객체가 빈으로 등록된다.
  }
}
```

- `@Configuration`: 이 클래스가 빈을 등록하는 설정 클래스임을 표시한다. `@Configuration`도 내부에 `@Component`를 포함하므로 컴포넌트 스캔으로 발견된다.
- `@Bean`: 이 메서드가 리턴하는 객체를 빈으로 등록한다.

| 빈 등록 방법 | 사용하는 경우 |
| --- | --- |
| `@Component`(및 `@Service`, `@Repository` 등) | 직접 작성한 클래스를 빈으로 등록할 때 |
| `@Configuration` + `@Bean` | 직접 고칠 수 없는 클래스(JDK, 외부 라이브러리)를 빈으로 등록할 때, 객체를 만드는 과정을 직접 코드로 작성해야 할 때 |

### 2장의 자동 구성 다시 보기

2장에서 자동 구성 클래스를 "조건이 붙은 평범한 스프링 설정 클래스"라고 설명했다. 이제 그 의미를 이해할 수 있다.

```java
@AutoConfiguration                             // 자동 구성용 설정 클래스 (@Configuration의 일종)
@ConditionalOnClass(Tomcat.class)
public class TomcatAutoConfiguration {

  @Bean                                        // 리턴한 객체를 빈으로 등록한다.
  @ConditionalOnMissingBean                    // 같은 타입의 빈이 없을 때만
  public TomcatWebServerFactory tomcatWebServerFactory() { ... }
}
```

자동 구성은 스프링 부트가 미리 만들어 둔 `@Bean` 메서드들이다.
`@ConditionalOnMissingBean`은 "개발자가 같은 타입의 빈을 **이미 등록했으면** 이 빈은 등록하지 않는다"는 뜻이다.
그래서 개발자가 `@Bean`으로 직접 빈을 등록하면, 자동 구성이 물러나고 개발자의 빈이 사용된다.

## 생성자 기반 의존성 주입

### 생성자로 주입받기

빈으로 등록된 클래스에 **생성자가 하나만** 있으면, 스프링은 그 생성자를 사용해 객체를 만든다.
이때 생성자의 파라미터 타입에 맞는 빈을 컨테이너에서 찾아 **자동으로 넣어 준다.**

```java
@Service
public class BoardService {

  private final BoardRepository boardRepository;

  @Autowired
  public BoardService(BoardRepository boardRepository) {   // ① 파라미터 타입: BoardRepository
    this.boardRepository = boardRepository;                // ② 스프링이 BoardRepository 타입의 빈을 찾아 넣어 준다.
  }
}
```

스프링 컨테이너는 다음 순서로 빈을 만든다.

1. `BoardService`를 만들려면 `BoardRepository` 타입의 빈이 필요하다는 것을 생성자를 보고 알아낸다.
2. `BoardRepository` 인터페이스를 구현한 빈(`MemoryBoardRepository`)을 먼저 만든다.
3. 만든 빈을 생성자에 넣어 `BoardService` 빈을 만든다.

> 예전 코드에서는 생성자 위에 `@Autowired` 애노테이션을 붙인 것을 볼 수 있다.
> 생성자가 **하나뿐이면** `@Autowired`를 생략할 수 있으며, 요즘은 생략하는 것이 일반적이다. 생성자가 여러 개라면 주입에 사용할 생성자에 `@Autowired`를 붙여야 한다.

### 의존성 주입의 세 가지 방법

스프링은 생성자 외에도 세터 메서드나 필드로 의존 객체를 주입할 수 있다.

```java
// ① 생성자 주입 (권장)
@Service
public class BoardService {
  private final BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }
}
```

```java
// ② 세터 주입
@Service
public class BoardService {
  private BoardRepository boardRepository;

  @Autowired
  public void setBoardRepository(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }
}
```

```java
// ③ 필드 주입
@Service
public class BoardService {
  @Autowired
  private BoardRepository boardRepository;
}
```

필드 주입이 가장 짧아 보이지만, 스프링은 **생성자 주입**을 권장한다. 그 이유는 다음과 같다.

| 비교 항목 | 생성자 주입 | 세터 주입 / 필드 주입 |
| --- | --- | --- |
| 필드를 `final`로 선언 | 가능하다. 한 번 주입된 의존 객체가 바뀌지 않는다. | 불가능하다. 나중에 다른 객체로 바뀔 수 있다. |
| 의존 객체 누락 | 생성자 파라미터로 받으므로 빠뜨릴 수 없다. | 주입이 빠져도 객체가 만들어져서, 실행 중에 `NullPointerException`이 발생할 수 있다. |
| 스프링 없이 테스트 | `new BoardService(가짜저장소)`처럼 쉽게 만들 수 있다. | 필드 주입은 스프링 없이 의존 객체를 넣을 방법이 없다. |
| 의존 관계 파악 | 생성자만 보면 필요한 의존 객체를 알 수 있다. | 클래스 전체를 살펴봐야 한다. |

> 이 과정에서는 **생성자 주입만** 사용한다.

### 같은 타입의 빈이 여러 개일 때

`BoardRepository` 인터페이스를 구현한 빈이 두 개 이상 있다면, 스프링은 어느 빈을 넣어야 할지 결정할 수 없어서 애플리케이션 실행에 실패한다.
이때는 다음 방법 중 하나로 사용할 빈을 지정한다.

**방법 1: `@Primary`로 기본 빈 지정하기**

여러 빈 중 기본으로 사용할 빈에 `@Primary`를 붙인다.

```java
@Repository
@Primary                     // 같은 타입의 빈이 여러 개면 이 빈을 우선 사용한다.
public class MemoryBoardRepository implements BoardRepository { ... }
```

**방법 2: `@Qualifier`로 빈 이름 지정하기**

주입받는 쪽에서 사용할 빈의 이름을 지정한다.

```java
public BoardService(@Qualifier("memoryBoardRepository") BoardRepository boardRepository) {
  this.boardRepository = boardRepository;
}
```

> `@Primary`는 **빈을 제공하는 쪽**에서, `@Qualifier`는 **빈을 사용하는 쪽**에서 지정한다.
> 대부분의 경우 기본으로 쓸 빈을 `@Primary`로 지정하고, 특별히 다른 빈이 필요한 곳에서만 `@Qualifier`를 사용한다.

## 정리

| 용어 | 의미 |
| --- | --- |
| 의존 관계 | 한 객체가 일을 하기 위해 다른 객체를 사용하는 관계 |
| IoC (제어의 역전) | 객체를 만들고 연결하는 제어권을 객체 자신이 아닌 외부(스프링)에 넘기는 원칙 |
| DI (의존성 주입) | 객체가 사용할 의존 객체를 외부에서 넣어 주는 방법. IoC를 실현하는 구체적인 방법 |
| 스프링 컨테이너 | 빈을 만들고, 의존 관계를 연결하고, 관리하는 스프링의 핵심. 코드로는 `ApplicationContext` |
| 빈 (Bean) | 스프링 컨테이너가 만들고 관리하는 객체. 기본적으로 싱글톤 |
| 컴포넌트 스캔 | `@Component`가 붙은 클래스를 찾아 빈으로 등록하는 과정 |
| 스테레오타입 애노테이션 | `@Controller`, `@Service`, `@Repository`처럼 클래스의 역할을 표시하면서 빈으로 등록하는 애노테이션 |
| `@Configuration` + `@Bean` | 직접 고칠 수 없는 클래스를 빈으로 등록하는 방법 |
| 생성자 주입 | 생성자 파라미터로 의존 객체를 받는 방법. 스프링이 권장하는 방법 |

## 실습

이번 장의 실습은 1장에서 만든 `hello` 프로젝트에서 진행한다.
`src/main/java/com/example/hello` 폴더 아래에 `board` 폴더(패키지)를 만들고, 게시판의 목록 조회 기능을 단계적으로 바꿔 가며 IoC와 DI를 체험한다.

> VS Code 탐색기에서 `src/main/java/com/example/hello` 폴더를 마우스 오른쪽 버튼으로 클릭하고 `New Folder...`를 선택하여 `board` 폴더를 만든다.
> `board` 폴더에 만드는 클래스의 패키지는 `com.example.hello.board`가 된다.

실습을 모두 마치면 `board` 패키지는 다음과 같이 구성된다.

```
com.example.hello
├── HelloApplication.java
├── HelloController.java
├── AppConfig.java                    ← 실습-6
└── board
    ├── Board.java                    ← 실습-1
    ├── BoardRepository.java          ← 실습-2
    ├── MemoryBoardRepository.java    ← 실습-1
    ├── SampleBoardRepository.java    ← 실습-5
    ├── BoardService.java             ← 실습-1
    ├── BoardController.java          ← 실습-3
    └── ManualMain.java               ← 실습-1
```

### 실습-1: 객체를 직접 생성하는 방식의 문제 확인하기

스프링을 사용하지 않고, 객체가 필요한 객체를 `new`로 직접 만드는 방식으로 코드를 작성한다.

**1) 게시글 데이터 클래스 만들기**

`board` 폴더에 `Board.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

public record Board(Long id, String title, String writer) {
}
```

> `record`는 데이터를 담는 클래스를 간단히 정의하는 Java 문법이다(Java 16 이상).
> 필드, 생성자, 값을 꺼내는 메서드(`id()`, `title()`, `writer()`), `toString()` 등을 자동으로 만들어 준다.

**2) 저장소 클래스 만들기**

`MemoryBoardRepository.java` 파일을 만들고 다음과 같이 작성한다. 게시글을 메모리(`List`)에 저장하는 저장소이다.

```java
package com.example.hello.board;

import java.util.ArrayList;
import java.util.List;

public class MemoryBoardRepository {

  private final List<Board> boards = new ArrayList<>();

  public MemoryBoardRepository() {
    System.out.println("MemoryBoardRepository 객체 생성");
    boards.add(new Board(1L, "첫 번째 게시글", "홍길동"));
    boards.add(new Board(2L, "두 번째 게시글", "임꺽정"));
  }

  public List<Board> findAll() {
    return List.copyOf(boards);
  }

  public void save(Board board) {
    boards.add(board);
  }
}
```

> 객체가 언제 만들어지는지 확인하기 위해 생성자에서 메시지를 출력한다.

**3) 서비스 클래스 만들기**

`BoardService.java` 파일을 만들고 다음과 같이 작성한다. 저장소 객체를 **직접 생성**한다.

```java
package com.example.hello.board;

import java.util.List;

public class BoardService {

  // 필요한 객체를 직접 생성한다.
  private final MemoryBoardRepository boardRepository = new MemoryBoardRepository();

  public List<Board> list() {
    return boardRepository.findAll();
  }

  public void register(Board board) {
    boardRepository.save(board);
  }
}
```

**4) 실행 클래스 만들기**

`ManualMain.java` 파일을 만들고 다음과 같이 작성한다.
`BoardService` 객체를 두 개 만들고, 한쪽에만 게시글을 등록한 후 양쪽의 목록을 출력한다.

```java
package com.example.hello.board;

public class ManualMain {

  public static void main(String[] args) {
    BoardService service1 = new BoardService();
    BoardService service2 = new BoardService();

    service1.register(new Board(3L, "세 번째 게시글", "유관순"));

    System.out.println("service1 목록: " + service1.list());
    System.out.println("service2 목록: " + service2.list());
  }
}
```

**5) 실행하기**

`ManualMain.java` 파일의 `main()` 메서드 위에 표시되는 `Run` 링크를 클릭한다.
스프링 부트 애플리케이션이 아니라 **일반 Java 프로그램**으로 실행된다. 터미널에 다음과 같이 출력된다.

```
MemoryBoardRepository 객체 생성
MemoryBoardRepository 객체 생성
service1 목록: [Board[id=1, title=첫 번째 게시글, writer=홍길동], Board[id=2, title=두 번째 게시글, writer=임꺽정], Board[id=3, title=세 번째 게시글, writer=유관순]]
service2 목록: [Board[id=1, title=첫 번째 게시글, writer=홍길동], Board[id=2, title=두 번째 게시글, writer=임꺽정]]
```

**6) 결과 분석**

- `MemoryBoardRepository 객체 생성`이 **두 번** 출력되었다. `BoardService`가 만들어질 때마다 저장소도 새로 만들어졌다.
- `service1`에 등록한 세 번째 게시글이 `service2`의 목록에는 **없다**. 두 서비스가 서로 다른 저장소를 사용하기 때문이다.
- 저장소를 다른 클래스로 바꾸려면 `BoardService`의 코드를 직접 고쳐야 한다.

객체가 자신이 사용할 객체를 **스스로 만들기 때문에** 생기는 문제이다.

### 실습-2: 인터페이스와 생성자로 의존 객체 주입하기

`BoardService`가 저장소를 직접 만들지 않고, **외부에서 생성자로 받도록** 바꾼다.

**1) 저장소 인터페이스 만들기**

`BoardRepository.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

import java.util.List;

public interface BoardRepository {

  List<Board> findAll();

  void save(Board board);
}
```

**2) 저장소 클래스가 인터페이스를 구현하도록 바꾸기**

`MemoryBoardRepository.java`의 클래스 선언부에 `implements BoardRepository`를 추가하고, 두 메서드에 `@Override`를 붙인다.

```java
public class MemoryBoardRepository implements BoardRepository {   // 변경

  private final List<Board> boards = new ArrayList<>();

  public MemoryBoardRepository() {
    System.out.println("MemoryBoardRepository 객체 생성");
    boards.add(new Board(1L, "첫 번째 게시글", "홍길동"));
    boards.add(new Board(2L, "두 번째 게시글", "임꺽정"));
  }

  @Override                                                       // 추가
  public List<Board> findAll() {
    return List.copyOf(boards);
  }

  @Override                                                       // 추가
  public void save(Board board) {
    boards.add(board);
  }
}
```

**3) 서비스가 생성자로 저장소를 받도록 바꾸기**

`BoardService.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import java.util.List;

public class BoardService {

  private final BoardRepository boardRepository;     // 인터페이스에 의존한다.

  public BoardService(BoardRepository boardRepository) {
    System.out.println("BoardService 객체 생성");
    this.boardRepository = boardRepository;           // 외부에서 받은 객체를 사용한다.
  }

  public List<Board> list() {
    return boardRepository.findAll();
  }

  public void register(Board board) {
    boardRepository.save(board);
  }
}
```

이제 `BoardService`에는 `new`가 없고, `MemoryBoardRepository`라는 클래스 이름도 없다.

**4) 조립 코드 작성하기**

`BoardService`를 만들려면 이제 저장소를 넣어 줘야 한다. `ManualMain.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

public class ManualMain {

  public static void main(String[] args) {
    // 1. 의존 객체를 한 번만 만든다.
    BoardRepository boardRepository = new MemoryBoardRepository();

    // 2. 같은 의존 객체를 생성자로 넣어 준다.
    BoardService service1 = new BoardService(boardRepository);
    BoardService service2 = new BoardService(boardRepository);

    service1.register(new Board(3L, "세 번째 게시글", "유관순"));

    System.out.println("service1 목록: " + service1.list());
    System.out.println("service2 목록: " + service2.list());
  }
}
```

**5) 실행하기**

`ManualMain`을 다시 실행한다.

```
MemoryBoardRepository 객체 생성
BoardService 객체 생성
BoardService 객체 생성
service1 목록: [Board[id=1, ...], Board[id=2, ...], Board[id=3, title=세 번째 게시글, writer=유관순]]
service2 목록: [Board[id=1, ...], Board[id=2, ...], Board[id=3, title=세 번째 게시글, writer=유관순]]
```

**6) 결과 분석**

- 저장소가 **한 번만** 만들어졌고, 두 서비스가 같은 저장소를 공유한다. 그래서 `service2`에서도 세 번째 게시글이 보인다.
- 객체를 만들고 연결하는 일을 `BoardService`가 아니라 `ManualMain`이 한다. **제어권이 외부로 넘어갔다**(IoC).
- 저장소를 다른 클래스로 바꾸려면 `ManualMain`의 `new MemoryBoardRepository()` 한 곳만 고치면 된다.

하지만 클래스가 많아지면 `ManualMain` 같은 조립 코드도 계속 늘어난다. 다음 실습에서는 이 조립을 스프링에 맡긴다.

### 실습-3: 스프링 컨테이너에 객체 생성과 주입 맡기기

애노테이션을 붙여 클래스를 빈으로 등록하고, 스프링이 객체를 만들고 연결하도록 한다.

**1) 저장소를 빈으로 등록하기**

`MemoryBoardRepository.java`의 클래스 선언부 위에 `@Repository`를 붙인다.

```java
package com.example.hello.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository                                                        // 추가
public class MemoryBoardRepository implements BoardRepository {
  ...
}
```

**2) 서비스를 빈으로 등록하기**

`BoardService.java`의 클래스 선언부 위에 `@Service`를 붙인다. 나머지 코드는 그대로 둔다.

```java
package com.example.hello.board;

import java.util.List;

import org.springframework.stereotype.Service;

@Service                                                           // 추가
public class BoardService {

  private final BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    System.out.println("BoardService 객체 생성");
    this.boardRepository = boardRepository;
  }
  ...
}
```

**3) 컨트롤러 만들기**

`BoardController.java` 파일을 만들고 다음과 같이 작성한다.
컨트롤러도 `BoardService`를 **생성자로 주입받는다.**

```java
package com.example.hello.board;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    System.out.println("BoardController 객체 생성");
    this.boardService = boardService;
  }

  @GetMapping("/boards")
  public List<Board> list() {
    return boardService.list();
  }
}
```

**4) 실행하기**

`hello` 프로젝트 폴더에서 애플리케이션을 실행한다.

```bash
# macOS
./gradlew bootRun
```

```powershell
# Windows (PowerShell)
.\gradlew bootRun
```

실행 로그 중간에 다음 메시지가 순서대로 출력되는 것을 확인한다.

```
MemoryBoardRepository 객체 생성
BoardService 객체 생성
BoardController 객체 생성
...
... Tomcat started on port 8080 (http) with context path '/'
```

웹 브라우저에서 [http://localhost:8080/boards](http://localhost:8080/boards) 에 접속한다.

```json
[{"id":1,"title":"첫 번째 게시글","writer":"홍길동"},{"id":2,"title":"두 번째 게시글","writer":"임꺽정"}]
```

**5) 결과 분석**

- 코드 어디에도 `new MemoryBoardRepository()`, `new BoardService(...)`, `new BoardController(...)`가 없다. **스프링이 객체를 만들었다.**
- 객체는 **의존 관계 순서대로** 만들어졌다. `BoardController`를 만들려면 `BoardService`가 필요하고, `BoardService`를 만들려면 `BoardRepository`가 필요하므로, 스프링은 저장소 → 서비스 → 컨트롤러 순서로 만들었다.
- `BoardService`의 생성자는 `BoardRepository` **인터페이스** 타입을 받는다. 스프링이 이 인터페이스를 구현한 빈(`MemoryBoardRepository`)을 찾아서 넣어 주었다.
- 각 객체는 **한 번씩만** 만들어졌다. 빈은 기본적으로 싱글톤이기 때문이다.
- 실습-2의 `ManualMain`이 하던 조립 작업을 **스프링 컨테이너가 대신** 했다.

> `ManualMain`은 여전히 일반 Java 프로그램으로 실행할 수 있다. `@Service`, `@Repository`를 붙였어도 클래스 자체는 평범한 Java 클래스이므로, 스프링 없이 `new`로 만들어 사용할 수도 있다.

확인한 후에는 `Ctrl + C`를 눌러 종료한다.

### 실습-4: ApplicationContext로 스프링 컨테이너 살펴보기

`SpringApplication.run()`이 리턴하는 `ApplicationContext`를 사용하여 스프링 컨테이너에 등록된 빈을 직접 확인한다.

**1) 실행 클래스 수정하기**

`HelloApplication.java`를 다음과 같이 바꾼다.

```java
package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.hello.board.BoardRepository;
import com.example.hello.board.BoardService;

@SpringBootApplication
public class HelloApplication {

  public static void main(String[] args) {
    // 스프링 컨테이너를 리턴받는다.
    ApplicationContext context = SpringApplication.run(HelloApplication.class, args);

    System.out.println("==================================================");

    // ① 등록된 빈의 개수
    System.out.println("등록된 빈의 개수: " + context.getBeanDefinitionCount());

    // ② 이름에 board가 들어간 빈 목록
    System.out.println("[board 관련 빈]");
    for (String name : context.getBeanDefinitionNames()) {
      if (name.toLowerCase().contains("board")) {
        System.out.println("- " + name);
      }
    }

    // ③ 이름으로 빈이 있는지 확인
    System.out.println("boardService 빈이 있는가? " + context.containsBean("boardService"));

    // ④ 인터페이스 타입으로 빈 꺼내기
    BoardRepository repository = context.getBean(BoardRepository.class);
    System.out.println("BoardRepository 타입의 빈: " + repository.getClass().getSimpleName());

    // ⑤ 같은 빈을 두 번 꺼내서 비교
    BoardService service1 = context.getBean(BoardService.class);
    BoardService service2 = context.getBean(BoardService.class);
    System.out.println("service1 == service2 ? " + (service1 == service2));

    System.out.println("==================================================");
  }

}
```

**2) 실행하기**

애플리케이션을 실행한다. 스프링 부트의 시작 로그가 모두 출력된 후, 다음과 같은 내용이 출력된다. (빈의 개수는 환경에 따라 다를 수 있다.)

```
==================================================
등록된 빈의 개수: 1xx
[board 관련 빈]
- boardController
- boardService
- memoryBoardRepository
boardService 빈이 있는가? true
BoardRepository 타입의 빈: MemoryBoardRepository
service1 == service2 ? true
==================================================
```

**3) 결과 분석**

- 직접 만든 빈은 몇 개 되지 않지만, 스프링 컨테이너에는 **100개가 넘는 빈**이 등록되어 있다. 대부분은 2장에서 배운 **자동 구성**이 등록한 빈이다.
- 빈의 이름은 클래스 이름의 첫 글자를 소문자로 바꾼 것(`boardService`, `memoryBoardRepository`)이다.
- `BoardRepository` **인터페이스 타입**으로 빈을 꺼내면, 그 인터페이스를 구현한 `MemoryBoardRepository` 빈이 나온다. 생성자 주입도 이와 같은 방식으로 빈을 찾는다.
- `getBean()`으로 두 번 꺼낸 `BoardService`는 **같은 객체**(`true`)이다. 빈이 **싱글톤**으로 관리된다는 것을 확인할 수 있다.

> 자동 구성이 등록한 빈의 이름도 보고 싶다면, ②의 `if` 조건을 없애고 모든 빈의 이름을 출력해 보자.

확인한 후에는 `Ctrl + C`를 눌러 종료한다. `HelloApplication.java`는 다음 실습에서도 사용하므로 그대로 둔다.

### 실습-5: 코드 수정 없이 구현체 바꾸기

`BoardRepository` 인터페이스를 구현한 저장소를 하나 더 만들고, `BoardService`의 코드를 고치지 않고 저장소를 바꿔 본다.

**1) 새 저장소 만들기**

`SampleBoardRepository.java` 파일을 만들고 다음과 같이 작성한다. 미리 정해 둔 샘플 게시글을 제공하는 저장소이다.

```java
package com.example.hello.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SampleBoardRepository implements BoardRepository {

  private final List<Board> boards = new ArrayList<>();

  public SampleBoardRepository() {
    System.out.println("SampleBoardRepository 객체 생성");
    boards.add(new Board(100L, "[샘플] 공지사항", "관리자"));
    boards.add(new Board(101L, "[샘플] 자주 묻는 질문", "관리자"));
  }

  @Override
  public List<Board> findAll() {
    return List.copyOf(boards);
  }

  @Override
  public void save(Board board) {
    boards.add(board);
  }
}
```

**2) 실행하고 오류 확인하기**

애플리케이션을 실행하면 다음과 같은 오류가 발생하며 실행에 실패한다. (형식은 버전에 따라 조금 다를 수 있다.)

```
***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of constructor in com.example.hello.board.BoardService required a single bean, but 2 were found:
	- memoryBoardRepository: defined in file [.../MemoryBoardRepository.class]
	- sampleBoardRepository: defined in file [.../SampleBoardRepository.class]

Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
```

오류 메시지를 해석하면 다음과 같다.

- **Description**: `BoardService` 생성자의 첫 번째 파라미터(`BoardRepository`)에 넣을 빈이 **하나여야 하는데 두 개**(`memoryBoardRepository`, `sampleBoardRepository`)가 있다.
- **Action**: 둘 중 하나에 `@Primary`를 붙이거나, `@Qualifier`로 사용할 빈을 지정하라.

스프링 부트는 이처럼 실행에 실패한 **원인**과 **해결 방법**을 함께 알려 준다. 오류가 발생하면 `Description`과 `Action`부터 읽는 습관을 들이자.

**3) `@Primary`로 기본 빈 지정하기**

`SampleBoardRepository.java`에 `@Primary`를 추가한다.

```java
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary                                                           // 추가
public class SampleBoardRepository implements BoardRepository {
  ...
}
```

애플리케이션을 다시 실행한다. 이번에는 정상적으로 실행된다.
실습-4에서 추가한 출력 내용과 [http://localhost:8080/boards](http://localhost:8080/boards) 의 응답을 확인한다.

```
BoardRepository 타입의 빈: SampleBoardRepository
```

```json
[{"id":100,"title":"[샘플] 공지사항","writer":"관리자"},{"id":101,"title":"[샘플] 자주 묻는 질문","writer":"관리자"}]
```

`BoardService`와 `BoardController`의 코드는 **한 줄도 고치지 않았는데** 사용하는 저장소가 바뀌었다.
`BoardService`가 구체적인 클래스가 아닌 `BoardRepository` **인터페이스**에 의존하고, 어떤 저장소를 넣을지는 **스프링이 결정**하기 때문이다.

**4) `@Qualifier`로 사용할 빈 지정하기**

이번에는 `BoardService`에서 사용할 빈을 직접 지정해 보자. `BoardService.java`의 생성자를 다음과 같이 바꾼다.

```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

  private final BoardRepository boardRepository;

  public BoardService(@Qualifier("memoryBoardRepository") BoardRepository boardRepository) {   // 변경
    System.out.println("BoardService 객체 생성");
    this.boardRepository = boardRepository;
  }
  ...
}
```

애플리케이션을 다시 실행하고 [http://localhost:8080/boards](http://localhost:8080/boards) 에 접속하면, `SampleBoardRepository`에 `@Primary`가 있는데도 `MemoryBoardRepository`의 게시글이 출력된다.
사용하는 쪽에서 `@Qualifier`로 지정한 빈이 `@Primary`보다 **우선**한다.

> 실습-4의 `BoardRepository 타입의 빈:` 출력은 여전히 `SampleBoardRepository`이다. `getBean(BoardRepository.class)`에는 `@Qualifier`가 적용되지 않으므로 `@Primary` 빈이 나온다.

**5) 원래대로 되돌리기**

다음 실습을 위해 다음과 같이 정리한다.

- `BoardService.java`: 생성자 파라미터의 `@Qualifier("memoryBoardRepository")`와 `import` 문을 삭제한다.
- `SampleBoardRepository.java`: `@Primary`와 `import` 문을 삭제한다.
- `MemoryBoardRepository.java`: `@Repository` 아래에 `@Primary`를 추가한다.

```java
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MemoryBoardRepository implements BoardRepository {
  ...
}
```

애플리케이션을 실행하여 [http://localhost:8080/boards](http://localhost:8080/boards) 에 `MemoryBoardRepository`의 게시글이 출력되는지 확인하고, `Ctrl + C`를 눌러 종료한다.

### 실습-6: @Bean으로 직접 빈 등록하기

JDK의 `java.time.Clock` 클래스처럼 **직접 고칠 수 없는 클래스**를 `@Configuration`과 `@Bean`으로 빈에 등록하고, 컨트롤러에서 주입받아 사용한다.

> `Clock`은 현재 시각을 알려 주는 JDK의 클래스이다. `LocalDateTime.now(clock)`처럼 사용하면 `clock`이 알려 주는 시각을 얻는다.

**1) 설정 클래스 만들기**

`src/main/java/com/example/hello` 폴더에 `AppConfig.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello;

import java.time.Clock;
import java.time.ZoneId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Clock clock() {
    System.out.println("Clock 빈 생성");
    return Clock.system(ZoneId.of("Asia/Seoul"));
  }
}
```

**2) 컨트롤러에서 주입받기**

`HelloController.java`에 생성자와 `time()` 메서드를 추가한다. 기존 메서드는 그대로 둔다.

```java
package com.example.hello;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final Clock clock;                        // 추가

  public HelloController(Clock clock) {             // 추가: Clock 빈을 주입받는다.
    this.clock = clock;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello, Spring Boot!";
  }

  // (2장 실습에서 추가한 helloJson() 메서드가 있다면 그대로 둔다.)

  @GetMapping("/hello/time")                        // 추가
  public String time() {
    return "현재 시각: " + LocalDateTime.now(clock);
  }
}
```

**3) 실행하기**

애플리케이션을 실행하고 [http://localhost:8080/hello/time](http://localhost:8080/hello/time) 에 접속한다.

```
현재 시각: 2026-xx-xxTxx:xx:xx.xxxxxx
```

현재 시각이 출력된다. 새로 고침할 때마다 시각이 바뀐다.

**4) 빈만 바꿔서 동작 바꾸기**

애플리케이션을 종료하고, `AppConfig.java`의 `clock()` 메서드가 **항상 같은 시각을 알려 주는** `Clock`을 리턴하도록 바꾼다.

```java
package com.example.hello;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Clock clock() {
    System.out.println("Clock 빈 생성");
    // 항상 2026년 1월 1일 0시(한국 시각)를 알려 주는 Clock
    return Clock.fixed(Instant.parse("2025-12-31T15:00:00Z"), ZoneId.of("Asia/Seoul"));
  }
}
```

애플리케이션을 다시 실행하고 [http://localhost:8080/hello/time](http://localhost:8080/hello/time) 에 접속한다.

```
현재 시각: 2026-01-01T00:00
```

새로 고침해도 시각이 바뀌지 않는다.
`HelloController`는 고치지 않았는데, **주입되는 빈을 바꾼 것만으로** 동작이 바뀌었다.
이처럼 시각에 따라 결과가 달라지는 기능을 테스트할 때, 고정된 시각을 알려 주는 `Clock`을 주입하면 언제 실행해도 같은 결과를 얻을 수 있다.

**5) 원래대로 되돌리기**

`AppConfig.java`의 `clock()` 메서드를 다시 `Clock.system(ZoneId.of("Asia/Seoul"))`를 리턴하도록 바꾸고, 사용하지 않는 `Instant`의 `import` 문을 삭제한다.

**6) 정리**

- `@Component` 계열의 애노테이션을 붙일 수 없는 클래스는 `@Configuration` 클래스의 `@Bean` 메서드로 빈을 등록한다.
- `@Bean` 메서드가 리턴한 객체는 다른 빈과 똑같이 생성자로 주입받을 수 있다.
- 사용하는 쪽의 코드를 고치지 않고, 등록하는 빈만 바꿔서 애플리케이션의 동작을 바꿀 수 있다. 이것이 DI의 가장 큰 장점이다.

