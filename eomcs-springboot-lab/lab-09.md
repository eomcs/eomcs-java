# 9장. Spring WebMVC

4~8장에서 만든 REST API는 화면 없이 **데이터**(JSON)만 응답했다. 화면은 웹 브라우저의 JavaScript나 스마트폰 앱 같은 클라이언트가 만든다.
이번 장에서는 반대로 **서버가 HTML 화면을 만들어 응답**하는 방법을 배운다.
이를 위해 화면을 만드는 웹 애플리케이션의 대표적인 설계 방식인 **MVC 패턴**을 이해하고, 스프링 MVC가 요청을 처리하는 구조와 `@Controller`, Model, View, View Resolver의 역할을 익힌다.
마지막으로 지금까지 사용한 `@RestController`와 비교하여 두 방식의 차이를 정리한다.

## MVC 패턴

### 화면을 만드는 코드의 문제

서버가 HTML 화면을 만든다고 해 보자. 가장 단순한 방법은 컨트롤러에서 HTML 문자열을 직접 만드는 것이다.

```java
@GetMapping("/web/boards")
@ResponseBody
public String list() {
  StringBuilder html = new StringBuilder();
  html.append("<html><body><h1>게시글 목록</h1><ul>");
  for (BoardSummary board : boardService.list(null, 1, 10)) {
    html.append("<li>").append(board.title()).append("</li>");
  }
  html.append("</ul></body></html>");
  return html.toString();
}
```

이 코드에는 다음과 같은 문제가 있다.

- **요청 처리 코드와 화면 코드가 섞여 있다.** 화면의 디자인을 조금만 바꿔도 자바 코드를 고치고 다시 컴파일해야 한다.
- **화면을 만드는 사람과 코드를 만드는 사람이 함께 일하기 어렵다.** 웹 디자이너는 자바 코드 안의 HTML 문자열을 다룰 수 없다.
- **코드가 금방 복잡해진다.** 화면이 커질수록 문자열을 이어 붙이는 코드가 길어지고 읽기 어려워진다.

### MVC 패턴이란?

**MVC 패턴**(Model-View-Controller Pattern)은 화면이 있는 애플리케이션을 **세 가지 역할로 나누는** 설계 방식이다.

| 역할 | 하는 일 | 스프링 MVC에서 |
| --- | --- | --- |
| **Model** (모델) | 화면에 보여 줄 **데이터** | `Model` 객체에 담는 값 (게시글 목록 등) |
| **View** (뷰) | 데이터를 받아 **화면(HTML)을 만든다.** | 템플릿 파일 (Thymeleaf의 `.html` 파일) |
| **Controller** (컨트롤러) | 요청을 받아 필요한 일을 처리하고, **모델을 준비하여 뷰를 선택**한다. | `@Controller` 클래스 |

식당에 비유하면 다음과 같다.

| 식당 | MVC |
| --- | --- |
| 손님의 주문을 받고, 주방에 요리를 요청하고, 어떤 그릇에 담을지 정하는 **웨이터** | Controller |
| 주방에서 만든 **요리** | Model |
| 요리를 보기 좋게 담아 내는 **그릇과 플레이팅** | View |

컨트롤러는 "무엇을 보여 줄지(모델)"와 "어떤 화면으로 보여 줄지(뷰 이름)"만 정하고, HTML을 만드는 일은 뷰에 맡긴다.
그래서 화면의 디자인을 바꿀 때는 뷰(템플릿 파일)만, 처리 로직을 바꿀 때는 컨트롤러만 고치면 된다.

> 8장의 계층 구조(Controller-Service-Repository)와 MVC 패턴은 함께 사용된다.
> MVC 패턴의 컨트롤러가 요청을 받으면, 8장에서 만든 **서비스**를 호출하여 데이터를 얻는다. 서비스와 리포지토리는 REST API와 화면이 **함께 사용**한다.

## 스프링 MVC의 요청 처리 구조

### DispatcherServlet: 모든 요청의 입구

스프링 MVC에서는 `DispatcherServlet`이 모든 HTTP 요청을 가장 먼저 받는다.
`DispatcherServlet`은 요청을 직접 처리하지 않고, 요청에 맞는 컨트롤러를 찾아 **일을 나눠 주는(dispatch)** 역할을 한다.
이처럼 모든 요청을 한곳에서 먼저 받아 처리하는 구조를 **프런트 컨트롤러**(Front Controller) 패턴이라고 한다.

> 2장의 자동 구성에서 "Spring MVC 라이브러리가 있으면 `DispatcherServlet`을 자동으로 설정한다"고 배웠다. 그 `DispatcherServlet`이 이것이다.

### 화면을 응답할 때의 처리 흐름

```
[ 웹 브라우저 ]
   │ ① GET /web/boards
   ▼
[ DispatcherServlet ]
   │ ② 이 요청을 처리할 컨트롤러 메서드는?
   ├──────────────▶ [ HandlerMapping ]
   │                   @GetMapping("/web/boards")가 붙은 메서드를 찾는다.
   │ ③ 컨트롤러 메서드 실행
   ├──────────────▶ [ @Controller ] ──▶ [ Service ] ──▶ [ Repository ]
   │                   모델에 데이터를 담고, 뷰 이름("boards/list")을 리턴한다.
   │ ④ 뷰 이름 "boards/list"에 해당하는 뷰는?
   ├──────────────▶ [ ViewResolver ]
   │                   templates/boards/list.html 템플릿을 찾는다.
   │ ⑤ 모델의 데이터로 HTML 만들기
   ├──────────────▶ [ View ]
   │                   템플릿 + 모델 → HTML
   ▼
[ 웹 브라우저 ]  ⑥ HTML 응답
```

| 단계 | 구성 요소 | 하는 일 |
| --- | --- | --- |
| ① | `DispatcherServlet` | 모든 요청을 가장 먼저 받는다. |
| ② | `HandlerMapping` | 요청 주소와 메서드를 보고, 처리할 **컨트롤러 메서드**를 찾는다. (`@GetMapping` 등의 매핑 정보 사용) |
| ③ | 컨트롤러 | 서비스를 호출하여 데이터를 얻고, **모델**에 담은 후 **뷰 이름**을 리턴한다. |
| ④ | `ViewResolver` | 뷰 이름으로 실제 **뷰**(템플릿)를 찾는다. |
| ⑤ | `View` | 템플릿에 모델의 데이터를 채워 **HTML을 만든다.** |
| ⑥ | `DispatcherServlet` | 만들어진 HTML을 응답한다. |

개발자가 작성하는 것은 **컨트롤러**(③)와 **템플릿**(⑤)뿐이다. 나머지는 스프링 MVC와 스프링 부트의 자동 구성이 처리한다.

> 실제로는 `HandlerMapping`이 찾은 컨트롤러 메서드를 `HandlerAdapter`가 호출한다.
> `HandlerAdapter`는 `@PathVariable`, `@RequestParam`, `@RequestBody`, `Model` 같은 파라미터를 준비하여 메서드를 호출하는 역할을 한다. 5장에서 파라미터에 값이 자동으로 들어갔던 것은 `HandlerAdapter` 덕분이다.

### 데이터를 응답할 때의 처리 흐름

4~8장의 `@RestController`는 뷰를 사용하지 않는다. ①~③은 같지만, ④⑤ 대신 **메시지 컨버터**(HttpMessageConverter)가 리턴 값을 JSON으로 바꿔 응답 본문에 쓴다.

```
[ 클라이언트 ]
   │ ① GET /boards
   ▼
[ DispatcherServlet ] ② HandlerMapping ③ @RestController 메서드 실행 → List<BoardSummary> 리턴
   │ ④ HttpMessageConverter: 리턴 값 → JSON (Jackson)
   ▼
[ 클라이언트 ]  ⑤ JSON 응답
```

| 구분 | 화면 응답 (`@Controller`) | 데이터 응답 (`@RestController`) |
| --- | --- | --- |
| 메서드의 리턴 값 | **뷰 이름** (`"boards/list"`) | **데이터** (객체, `List`, `ResponseEntity` 등) |
| 리턴 값을 처리하는 것 | `ViewResolver` → `View` | `HttpMessageConverter` |
| 응답 | HTML | JSON |

## @Controller

### @Controller로 화면 컨트롤러 만들기

화면을 응답하는 컨트롤러에는 `@Controller`를 붙인다.
메서드는 **뷰 이름을 문자열로 리턴**한다.

```java
@Controller
public class BoardWebController {

  private final BoardService boardService;

  public BoardWebController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/web/boards")
  public String list(Model model) {
    model.addAttribute("boards", boardService.list(null, 1, 10));   // 모델에 데이터를 담는다.
    return "boards/list";                                            // 뷰 이름을 리턴한다.
  }
}
```

- `@Controller`도 3장에서 배운 스테레오타입 애노테이션이므로, 컴포넌트 스캔으로 빈에 등록된다.
- 요청 매핑(`@GetMapping`, `@RequestMapping` 등)과 요청 데이터 꺼내기(`@PathVariable`, `@RequestParam`)는 4~5장에서 배운 방법을 **그대로** 사용한다.
- 서비스는 8장에서 만든 `BoardService`를 그대로 사용한다. 서비스는 REST API 컨트롤러와 화면 컨트롤러가 **함께** 사용한다.

> 이 교재에서는 REST API(`/boards`)와 구분하기 위해 화면의 주소는 `/web`으로 시작하도록 한다.

## Model

### 모델에 데이터 담기

`Model`은 컨트롤러가 뷰에 전달할 데이터를 담는 객체이다.
컨트롤러 메서드의 파라미터로 `Model`을 선언하면 스프링 MVC가 넣어 준다. `addAttribute(이름, 값)`으로 데이터를 담는다.

```java
@GetMapping("/web/boards")
public String list(Model model) {
  model.addAttribute("boards", boardService.list(null, 1, 10));   // 이름 "boards"로 목록을 담는다.
  model.addAttribute("count", boardService.count());              // 이름 "count"로 개수를 담는다.
  return "boards/list";
}
```

뷰(템플릿)에서는 담을 때 사용한 **이름**으로 데이터를 꺼낸다.

```html
<p>전체 게시글 수: <span th:text="${count}">0</span></p>
```

| 메서드 | 기능 |
| --- | --- |
| `addAttribute(이름, 값)` | 데이터를 이름과 함께 담는다. |
| `containsAttribute(이름)` | 그 이름의 데이터가 있는지 확인한다. |

> 모델의 데이터는 **이번 요청을 처리하는 동안에만** 유지된다. 다음 요청에서는 새 모델이 만들어진다.

> 예전 코드에서는 모델과 뷰 이름을 함께 담는 `ModelAndView` 객체를 리턴하는 방식도 볼 수 있다. 이 교재에서는 `Model` 파라미터와 뷰 이름(`String`)을 리턴하는 방식을 사용한다.

## View와 ViewResolver

### 뷰와 템플릿 엔진

**뷰**(View)는 모델의 데이터를 받아 HTML을 만드는 역할이다.
스프링 부트에서는 보통 **템플릿 엔진**(Template Engine)으로 뷰를 만든다. 템플릿 엔진은 HTML 파일(템플릿) 안의 표시된 자리에 데이터를 채워 완성된 HTML을 만든다.

| 템플릿 (`boards/list.html`) | 모델 | 완성된 HTML |
| --- | --- | --- |
| `<td th:text="${board.title}">제목</td>` | `board.title` = `"JPA 입문"` | `<td>JPA 입문</td>` |

이 교재에서는 스프링 부트가 권장하는 템플릿 엔진인 **Thymeleaf**(타임리프)를 사용한다.
Thymeleaf의 템플릿은 웹 브라우저가 그대로 열 수 있는 **HTML 파일**이어서, 서버 없이 웹 브라우저로 열어도 화면의 모양을 확인할 수 있다. 이런 템플릿을 **내추럴 템플릿**(Natural Template)이라고 한다.

```groovy
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
```

> 이번 장에서는 데이터를 출력하는 `th:text`와 목록을 반복하는 `th:each`만 사용한다. Thymeleaf의 문법은 10장에서 자세히 배운다.
> 10장에서는 템플릿을 HTML5 표준에 맞게 작성하는 방법(`data-th-` 형식)도 배운다.

### ViewResolver: 뷰 이름으로 템플릿 찾기

컨트롤러는 템플릿 파일의 경로가 아니라 **뷰 이름**만 리턴한다.
`ViewResolver`는 뷰 이름을 실제 템플릿 파일로 바꿔 준다.
Thymeleaf를 사용하면 스프링 부트의 **자동 구성**이 Thymeleaf용 `ViewResolver`를 등록하며, 다음 규칙으로 템플릿을 찾는다.

```
접두어(prefix)                  + 뷰 이름      + 접미어(suffix)
classpath:/templates/         + boards/list  + .html
→ src/main/resources/templates/boards/list.html
```

| 컨트롤러가 리턴한 뷰 이름 | 찾는 템플릿 파일 |
| --- | --- |
| `"hello"` | `src/main/resources/templates/hello.html` |
| `"boards/list"` | `src/main/resources/templates/boards/list.html` |

접두어와 접미어는 `application.properties`로 바꿀 수 있지만, 보통 기본값을 그대로 사용한다.

| 설정 | 기본값 | 의미 |
| --- | --- | --- |
| `spring.thymeleaf.prefix` | `classpath:/templates/` | 템플릿 파일의 위치 |
| `spring.thymeleaf.suffix` | `.html` | 템플릿 파일의 확장자 |
| `spring.thymeleaf.cache` | `true` | 템플릿을 한 번 읽으면 기억해 두고 다시 읽지 않는다. |

> 1장에서 만든 `src/main/resources/templates` 폴더가 바로 이 템플릿을 두는 곳이다.

### 템플릿을 고친 후 바로 반영하기

`spring.thymeleaf.cache`가 `true`(기본값)이면 Thymeleaf는 템플릿을 한 번 읽은 후 기억해 두고 다시 읽지 않는다.
개발하는 동안에는 `false`로 설정하여 **요청할 때마다 템플릿을 다시 읽게** 한다. (운영 환경에서는 성능을 위해 `true`로 둔다.)

그런데 캐시를 꺼도, `./gradlew bootRun`으로 실행한 상태에서 `src/main/resources/templates`의 템플릿을 고치면 **반영되지 않는다.**
Thymeleaf가 템플릿을 찾는 곳은 소스 폴더가 아니라 **클래스패스**(`classpath:/templates/`)이기 때문이다.

`bootRun`은 실행을 시작할 때 `src/main/resources`의 파일을 `build/resources/main`으로 **복사**하고, 이 복사본 폴더를 클래스패스로 사용한다.

```
src/main/resources/templates/hello.html      ← 개발자가 고치는 파일
        │  bootRun을 시작할 때 한 번 복사
        ▼
build/resources/main/templates/hello.html    ← Thymeleaf가 읽는 파일 (클래스패스)
```

그래서 캐시를 꺼서 템플릿을 매번 다시 읽더라도, 다시 읽는 파일은 **예전에 복사된 build 폴더의 파일**이다.
이 문제는 `bootRun`이 `src/main/resources`를 클래스패스로 **직접** 사용하도록 `build.gradle`에 설정하여 해결한다. 단, 이 설정은 개발 환경에서만 유용하며 운영 환경에는 필요 없다.

```groovy
tasks.named("bootRun") {
  sourceResources sourceSets.main
}
```

| 설정 | 템플릿을 고친 후 반영하려면 |
| --- | --- |
| 아무 설정도 하지 않음 | `bootRun`을 **다시 실행**한다. (다시 실행할 때 build 폴더로 새로 복사된다.) |
| `spring.thymeleaf.cache=false`만 설정 | `bootRun`을 **다시 실행**한다. (build 폴더의 파일은 그대로이다.) |
| `cache=false` + `sourceResources` 설정 | 웹 브라우저에서 **새로 고침**만 한다. |

> `sourceResources` 설정은 `bootRun`으로 실행할 때만 적용된다. `./gradlew build`로 만드는 jar 파일에는 영향이 없다.

> 이 설정은 템플릿, `static` 폴더의 CSS·JavaScript·이미지 같은 **리소스 파일**에만 적용된다.
> 컨트롤러 같은 **Java 코드**를 고쳤을 때는 컴파일이 필요하므로, 여전히 `bootRun`을 다시 실행해야 한다.

### 리다이렉트

뷰 이름 앞에 `redirect:`를 붙이면 템플릿을 찾지 않고, 웹 브라우저에게 **다른 주소로 다시 요청하라**고 응답한다.

```java
@GetMapping("/web")
public String home() {
  return "redirect:/web/boards";     // 웹 브라우저가 /web/boards로 다시 요청한다.
}
```

```
웹 브라우저 ──GET /web──▶ 서버
웹 브라우저 ◀──302 Found, Location: /web/boards── 서버
웹 브라우저 ──GET /web/boards──▶ 서버     (웹 브라우저가 자동으로 다시 요청)
```

리다이렉트는 11장에서 등록·수정 폼을 처리한 후 목록이나 상세 화면으로 이동할 때 사용한다.

## @Controller와 @RestController 비교

### 두 애노테이션의 관계

4장에서 배운 것처럼 `@RestController`는 `@Controller`와 `@ResponseBody`를 합친 것이다.

| 애노테이션 | 메서드의 리턴 값 |
| --- | --- |
| `@Controller` | **뷰 이름**으로 해석한다. → `ViewResolver`가 템플릿을 찾아 HTML을 만든다. |
| `@Controller` + 메서드에 `@ResponseBody` | 그 메서드만 리턴 값을 **응답 본문에 그대로** 쓴다. |
| `@RestController` | 모든 메서드의 리턴 값을 응답 본문에 쓴다. (`@Controller` + 모든 메서드에 `@ResponseBody`) |

```java
@Controller
public class SampleController {

  @GetMapping("/a")
  public String a() {
    return "hello";               // 뷰 이름 → templates/hello.html로 만든 HTML 응답
  }

  @GetMapping("/b")
  @ResponseBody
  public String b() {
    return "hello";               // 문자열 "hello"가 그대로 응답 본문
  }
}
```

### 언제 무엇을 사용하는가

| 구분 | `@Controller` | `@RestController` |
| --- | --- | --- |
| 응답 | HTML 화면 | 데이터 (JSON) |
| 화면을 만드는 곳 | **서버** (템플릿 엔진) | **클라이언트** (JavaScript, 앱) |
| 이런 방식을 부르는 이름 | 서버 사이드 렌더링(SSR) | REST API |
| 주로 사용하는 곳 | 관리자 화면, 화면이 단순한 웹 사이트, 검색 엔진 노출이 중요한 페이지 | 스마트폰 앱, React·Vue 같은 프런트엔드 프레임워크와 함께 개발하는 웹 서비스 |
| 이 교재 | 9~11장 (`/web/...`) | 4~8장 (`/boards`) |

두 방식은 함께 사용할 수 있다. 이 교재의 `hello` 프로젝트처럼 한 애플리케이션 안에 REST API 컨트롤러와 화면 컨트롤러를 모두 두고, **같은 서비스**를 사용하게 할 수 있다.

```
[ BoardController (@RestController) ]     /boards       → JSON 응답
            │
            ├──▶ [ BoardService ] ──▶ [ BoardJpaRepository ]
            │
[ BoardWebController (@Controller) ]      /web/boards   → HTML 응답
```

## 정리

| 용어 | 의미 |
| --- | --- |
| MVC 패턴 | 화면이 있는 애플리케이션을 Model(데이터), View(화면), Controller(요청 처리)로 나누는 설계 방식 |
| `DispatcherServlet` | 모든 요청을 가장 먼저 받아, 알맞은 컨트롤러에 나눠 주는 스프링 MVC의 프런트 컨트롤러 |
| `HandlerMapping` | 요청 주소와 메서드로 처리할 컨트롤러 메서드를 찾는다. |
| `@Controller` | 뷰 이름을 리턴하는 화면 컨트롤러 |
| `Model` | 컨트롤러가 뷰에 전달할 데이터를 담는 객체. `addAttribute(이름, 값)` |
| `ViewResolver` | 뷰 이름을 실제 템플릿 파일로 바꾼다. (`templates/` + 뷰 이름 + `.html`) |
| View | 템플릿과 모델로 HTML을 만든다. 이 교재에서는 Thymeleaf를 사용한다. |
| `redirect:` | 웹 브라우저에게 다른 주소로 다시 요청하라고 응답한다. |
| `@RestController` | `@Controller` + `@ResponseBody`. 리턴 값을 뷰가 아닌 응답 본문(JSON)으로 쓴다. |

## 실습

이번 장의 실습은 8장까지 사용한 `hello` 프로젝트에서 이어서 진행한다.
8장에서 완성한 `BoardService`는 그대로 사용하고, 화면을 응답하는 컨트롤러와 템플릿을 추가한다.

실습을 마치면 다음 파일이 추가된다.

```
src/main
├── java/com/example/hello
│   ├── WebHelloController.java           ← 첫 번째 화면 컨트롤러   (실습-1, 3)
│   └── board
│       └── BoardWebController.java       ← 게시글 화면 컨트롤러   (실습-4, 5)
└── resources/templates
    ├── hello.html                        ← 첫 번째 템플릿         (실습-1)
    ├── greet.html                        ←                      (실습-3)
    └── boards
        └── list.html                     ← 게시글 목록 템플릿     (실습-4)
```

### 실습-1: 첫 번째 화면 만들기

**1) Thymeleaf 스타터 추가하기**

`build.gradle`의 `dependencies` 블록에 다음 한 줄을 추가한다.

```groovy
  implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
```

> VS Code에서 `build.gradle`을 저장한 후 빌드 설정을 동기화할지 묻는 알림이 뜨면 `Yes`를 누른다.

**2) 템플릿을 고치면 바로 반영되도록 설정하기**

템플릿을 고친 후 애플리케이션을 다시 실행하지 않고 새로 고침만 해도 반영되도록 두 가지를 설정한다.

먼저 `application.properties`에 다음 설정을 추가하여 템플릿 캐시를 끈다.

```properties
# 개발 중에는 템플릿 캐시를 끈다. (요청할 때마다 템플릿을 다시 읽는다.)
spring.thymeleaf.cache=false
```

다음으로 `build.gradle` 파일의 맨 아래에 다음 설정을 추가한다. `bootRun`으로 실행할 때 build 폴더의 복사본 대신 `src/main/resources`의 파일을 직접 읽게 한다.

```groovy
// bootRun으로 실행할 때 src/main/resources의 파일을 직접 사용한다.
tasks.named("bootRun") {
  sourceResources sourceSets.main
}
```

> 두 설정이 모두 필요한 이유는 앞의 "템플릿을 고친 후 바로 반영하기"에서 설명했다.
> 캐시만 끄면 Thymeleaf가 템플릿을 다시 읽기는 하지만, 읽는 파일이 `bootRun`을 시작할 때 복사해 둔 `build/resources/main`의 파일이어서 고친 내용이 반영되지 않는다.

> `build.gradle`의 설정은 Gradle이 시작될 때 읽으므로, 이미 `bootRun`을 실행 중이었다면 종료하고 **한 번** 다시 실행해야 적용된다.

**3) 화면 컨트롤러 만들기**

`src/main/java/com/example/hello` 폴더에 `WebHelloController.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller                                          // 화면 컨트롤러
public class WebHelloController {

  @GetMapping("/web/hello")
  public String hello(Model model) {
    model.addAttribute("message", "Hello, Spring MVC!");      // 모델에 데이터를 담는다.
    model.addAttribute("now", LocalDateTime.now());
    return "hello";                                            // 뷰 이름 → templates/hello.html
  }
}
```

> `Model`은 `org.springframework.ui.Model`을 `import`한다.

**4) 템플릿 만들기**

`src/main/resources/templates` 폴더에 `hello.html` 파일을 만들고 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Hello</title>
</head>
<body>
  <h1 th:text="${message}">여기에 메시지가 출력된다</h1>
  <p>현재 시각: <span th:text="${now}">2026-01-01T00:00</span></p>
</body>
</html>
```

| 코드 | 의미 |
| --- | --- |
| `xmlns:th="http://www.thymeleaf.org"` | 이 HTML에서 `th:`로 시작하는 Thymeleaf 속성을 사용한다고 선언한다. |
| `th:text="${message}"` | 모델에서 `message`라는 이름의 값을 꺼내, 태그 안의 내용을 그 값으로 **바꾼다.** |
| 태그 안의 원래 내용 (`여기에 메시지가 출력된다`) | 서버를 거치지 않고 HTML 파일을 직접 열었을 때 보이는 **임시 내용**이다. 서버에서는 모델의 값으로 바뀐다. |

**5) 실행하고 확인하기**

애플리케이션을 실행하고 웹 브라우저에서 [http://localhost:8080/web/hello](http://localhost:8080/web/hello) 에 접속한다.

```
Hello, Spring MVC!
현재 시각: 2026-xx-xxTxx:xx:xx.xxxxxx
```

웹 브라우저에서 마우스 오른쪽 버튼을 누르고 `페이지 소스 보기`를 선택하여, 서버가 응답한 HTML을 확인한다.

```html
<h1>Hello, Spring MVC!</h1>
<p>현재 시각: <span>2026-xx-xxTxx:xx:xx.xxxxxx</span></p>
```

- `th:text` 속성은 사라지고, 태그 안의 내용이 **모델의 값으로 바뀌어** 있다.
- 웹 브라우저가 받은 것은 **완성된 HTML**이다. 화면을 만든 곳은 서버이다.

**6) 템플릿을 직접 열어 보기**

VS Code 탐색기에서 `hello.html`을 마우스 오른쪽 버튼으로 클릭하고 `Reveal in Finder`(macOS) 또는 `Reveal in File Explorer`(Windows)를 선택한 후, 파일을 웹 브라우저로 직접 열어 본다.
서버를 거치지 않았으므로 `여기에 메시지가 출력된다` 같은 **임시 내용**이 보인다. Thymeleaf의 템플릿은 그 자체로 올바른 HTML이어서, 웹 디자이너가 서버 없이도 화면을 확인하고 작업할 수 있다.

**7) 템플릿 수정이 바로 반영되는지 확인하기**

`bootRun`으로 애플리케이션을 실행한 상태에서 `hello.html`의 `<h1>` 아래에 다음 줄을 추가하고 저장한다.

```html
  <p>템플릿을 고쳤다.</p>
```

웹 브라우저를 새로 고침하면 애플리케이션을 다시 실행하지 않았는데도 추가한 문장이 보인다.
`spring.thymeleaf.cache=false`로 템플릿을 매번 다시 읽고, `sourceResources` 설정으로 `src/main/resources`의 파일을 직접 읽기 때문이다.

> (확인) `build.gradle`에서 `tasks.named("bootRun") { ... }` 설정을 잠시 주석 처리하고 `bootRun`을 다시 실행한 후, 같은 방법으로 템플릿을 고쳐 보자.
> 이번에는 새로 고침해도 반영되지 않는다. VS Code 탐색기에서 `build/resources/main/templates/hello.html`을 열어 보면 고치기 전의 내용이 그대로 있다.
> 확인한 후에는 주석을 해제하고 `bootRun`을 다시 실행한다.

> 템플릿은 새로 고침만 하면 반영되지만, `WebHelloController.java` 같은 **Java 코드**를 고쳤을 때는 `bootRun`을 다시 실행해야 한다.

### 실습-2: 요청 처리 흐름 확인하기

`DispatcherServlet`이 요청을 받아 처리하는 과정을 로그로 확인한다.

**1) 로그 수준 바꾸기**

`application.properties`에 다음 설정을 추가한다. 스프링 MVC가 요청을 처리하면서 남기는 자세한 로그(DEBUG)를 출력한다.

```properties
# 스프링 MVC의 요청 처리 로그 출력
logging.level.org.springframework.web=debug
```

**2) 화면 요청의 로그 확인하기**

애플리케이션을 다시 실행하고 [http://localhost:8080/web/hello](http://localhost:8080/web/hello) 에 접속한다. 로그에서 다음과 비슷한 내용을 찾는다. (형식은 버전에 따라 다를 수 있다.)

```
DEBUG ... o.s.web.servlet.DispatcherServlet        : GET "/web/hello", parameters={}
DEBUG ... s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.example.hello.WebHelloController#hello(Model)
DEBUG ... o.s.w.s.v.ContentNegotiatingViewResolver : Selected 'text/html' given [text/html, ...]
DEBUG ... o.s.web.servlet.DispatcherServlet        : Completed 200 OK
```

| 로그 | 처리 단계 |
| --- | --- |
| `DispatcherServlet : GET "/web/hello"` | ① `DispatcherServlet`이 요청을 받았다. |
| `RequestMappingHandlerMapping : Mapped to ...WebHelloController#hello(Model)` | ② `HandlerMapping`이 처리할 컨트롤러 메서드를 찾았다. |
| `ContentNegotiatingViewResolver : Selected 'text/html'` | ④ `ViewResolver`가 뷰를 찾았다. |
| `DispatcherServlet : Completed 200 OK` | ⑥ 응답을 마쳤다. |

**3) REST API 요청의 로그와 비교하기**

`http/board.http`에서 `GET {{baseUrl}}/boards`를 요청하고 로그를 확인한다.

```
DEBUG ... o.s.web.servlet.DispatcherServlet        : GET "/boards", parameters={}
DEBUG ... s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.example.hello.board.BoardController#list(String, int, int)
DEBUG ... m.m.a.HttpEntityMethodProcessor          : Using 'application/json', given [*/*] and supported [...]
DEBUG ... m.m.a.HttpEntityMethodProcessor          : Writing [[BoardSummary[id=5, title=JPA 쿼리 메서드, writer=임꺽정], ...]]
DEBUG ... o.s.web.servlet.DispatcherServlet        : Completed 200 OK
```

- ①②는 화면 요청과 **같다.** 두 요청 모두 `DispatcherServlet`이 받아 `HandlerMapping`으로 컨트롤러 메서드를 찾았다.
- 그다음부터 다르다. 화면 요청은 `ViewResolver`가 뷰를 찾았지만, REST API 요청은 리턴 값을 **JSON**(`application/json`)으로 바꿔 응답 본문에 썼다(`Writing ...`).

**4) 로그 수준 되돌리기**

로그가 너무 많이 출력되므로, 확인한 후에는 추가한 `logging.level.org.springframework.web=debug` 설정을 삭제한다.

### 실습-3: 요청 파라미터를 받아 화면에 출력하기

`@Controller`에서도 5장에서 배운 `@RequestParam`, `@PathVariable`을 그대로 사용할 수 있다.

**1) 컨트롤러에 메서드 추가하기**

`WebHelloController.java`에 다음 메서드를 추가한다.

```java
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

  @GetMapping("/web/greet")
  public String greet(@RequestParam(defaultValue = "손님") String name, Model model) {
    model.addAttribute("name", name);
    model.addAttribute("fruits", List.of("사과", "바나나", "포도"));
    return "greet";                                            // 뷰 이름 → templates/greet.html
  }
```

> 메서드의 파라미터 순서는 상관없다. `@RequestParam` 파라미터와 `Model` 파라미터를 함께 선언하면, 스프링 MVC가 각각 알맞은 값을 넣어 준다.

**2) 템플릿 만들기**

`templates` 폴더에 `greet.html` 파일을 만들고 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>인사</title>
</head>
<body>
  <h1>안녕하세요, <span th:text="${name}">이름</span>님!</h1>

  <h2>과일 목록</h2>
  <ul>
    <li th:each="fruit : ${fruits}" th:text="${fruit}">과일</li>
  </ul>
</body>
</html>
```

`th:each="fruit : ${fruits}"`는 모델의 `fruits` 목록에서 값을 하나씩 꺼내 `fruit`에 담으면서, 이 태그(`<li>`)를 **값의 개수만큼 반복**하여 만든다.

**3) 실행하고 확인하기**

웹 브라우저에서 다음 주소에 접속한다.

| 주소 | 결과 |
| --- | --- |
| [http://localhost:8080/web/greet](http://localhost:8080/web/greet) | 안녕하세요, **손님**님! (기본값) |
| [http://localhost:8080/web/greet?name=홍길동](http://localhost:8080/web/greet?name=홍길동) | 안녕하세요, **홍길동**님! |

과일 목록은 `<li>` 태그가 세 개 만들어져 출력된다. 페이지 소스를 열어 `<li>`가 세 개인 것을 확인한다.

### 실습-4: 게시글 목록 화면 만들기

8장의 `BoardService`를 사용하여 데이터베이스의 게시글 목록을 HTML 표로 보여 주는 화면을 만든다.

**1) 게시글 화면 컨트롤러 만들기**

`board` 폴더에 `BoardWebController.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/boards")
public class BoardWebController {

  private final BoardService boardService;                  // 8장의 서비스를 그대로 사용한다.

  public BoardWebController(BoardService boardService) {
    this.boardService = boardService;
  }

  // 게시글 목록 화면: GET /web/boards?keyword=&page=&size=
  @GetMapping
  public String list(
      @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      Model model) {
    model.addAttribute("boards", boardService.list(keyword, page, size));
    model.addAttribute("count", boardService.count());
    return "boards/list";                                   // 뷰 이름 → templates/boards/list.html
  }
}
```

REST API 컨트롤러(`BoardController`)의 `list()` 메서드와 비교해 보자.

| 구분 | `BoardController.list()` (8장) | `BoardWebController.list()` |
| --- | --- | --- |
| 애노테이션 | `@RestController` | `@Controller` |
| 주소 | `/boards` | `/web/boards` |
| 요청 파라미터 | `keyword`, `page`, `size` | 같다. |
| 호출하는 서비스 | `boardService.list(keyword, page, size)` | **같다.** |
| 리턴 값 | 데이터 (`List<BoardSummary>`) | 뷰 이름 (`"boards/list"`) |
| 응답 | JSON | HTML |

요청을 받고 서비스를 호출하는 부분은 같고, **결과를 응답하는 방식**만 다르다. 8장에서 업무 처리를 서비스 계층으로 분리해 두었기 때문에, 화면 컨트롤러를 쉽게 추가할 수 있다.

**2) 템플릿 만들기**

`templates` 폴더 아래에 `boards` 폴더를 만들고, 그 안에 `list.html` 파일을 만든다. 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>게시글 목록</title>
</head>
<body>
  <h1>게시글 목록</h1>
  <p>전체 게시글 수: <span th:text="${count}">0</span></p>

  <table border="1">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
      </tr>
    </thead>
    <tbody>
      <tr th:each="board : ${boards}">
        <td th:text="${board.id}">1</td>
        <td th:text="${board.title}">제목</td>
        <td th:text="${board.writer}">작성자</td>
      </tr>
    </tbody>
  </table>
</body>
</html>
```

- `th:each="board : ${boards}"`: 모델의 `boards`(`List<BoardSummary>`)에서 게시글을 하나씩 꺼내 `<tr>`을 반복하여 만든다.
- `${board.title}`: `BoardSummary`의 `title` 값을 꺼낸다. record의 구성 요소도 이처럼 이름으로 꺼낼 수 있다.

**3) 실행하고 확인하기**

애플리케이션을 다시 실행하고 다음 주소에 접속한다.

| 주소 | 결과 |
| --- | --- |
| [http://localhost:8080/web/boards](http://localhost:8080/web/boards) | 데이터베이스의 게시글이 최신순으로 표에 출력된다. |
| [http://localhost:8080/web/boards?keyword=스프링](http://localhost:8080/web/boards?keyword=스프링) | 제목에 "스프링"이 들어간 게시글만 출력된다. |
| [http://localhost:8080/web/boards?page=2&size=2](http://localhost:8080/web/boards?page=2&size=2) | 2개씩 나눴을 때 두 번째 페이지의 게시글이 출력된다. |

REST Client로 `GET {{baseUrl}}/boards`를 요청하여, **같은 데이터**가 JSON으로 응답되는 것과 비교한다.

> 게시글 제목을 눌러 상세 화면으로 이동하거나, 게시글을 등록·수정하는 화면은 11장에서 만든다.
> 페이지 이동 링크를 만드는 방법(`th:href`)과 조건에 따라 화면을 다르게 보여 주는 방법(`th:if`)은 10장에서 배운다.

### 실습-5: @Controller와 @RestController의 차이 확인하기

**1) 뷰 이름에 해당하는 템플릿이 없으면**

`WebHelloController.java`에 다음 메서드를 추가한다.

```java
  @GetMapping("/web/no-template")
  public String noTemplate() {
    return "Hello, World!";          // 이 문자열은 뷰 이름으로 해석된다.
  }
```

애플리케이션을 다시 실행하고 [http://localhost:8080/web/no-template](http://localhost:8080/web/no-template) 에 접속한다.
`500` 오류(Whitelabel Error Page)가 발생하고, 로그에는 다음과 비슷한 오류가 출력된다.

```
... Error resolving template [Hello, World!], template might not exist or might not be accessible ...
```

`@Controller`의 메서드가 리턴한 문자열은 응답 내용이 아니라 **뷰 이름**이다. `ViewResolver`가 `templates/Hello, World!.html` 파일을 찾으려다 실패한 것이다.

**2) `@ResponseBody`를 붙이면**

`noTemplate()` 메서드에 `@ResponseBody`를 추가한다.

```java
import org.springframework.web.bind.annotation.ResponseBody;

  @GetMapping("/web/no-template")
  @ResponseBody                      // 추가: 리턴 값을 응답 본문에 그대로 쓴다.
  public String noTemplate() {
    return "Hello, World!";
  }
```

애플리케이션을 다시 실행하고 같은 주소에 접속하면, 이번에는 `Hello, World!` 문자열이 그대로 응답된다.
`@ResponseBody`가 붙은 메서드는 리턴 값을 뷰 이름으로 해석하지 않고 응답 본문에 쓴다. `@RestController`는 모든 메서드에 `@ResponseBody`를 붙인 것과 같다.

확인한 후에는 `noTemplate()` 메서드를 삭제한다.

**3) 리다이렉트 확인하기**

`BoardWebController.java`에 다음 메서드를 추가한다.

```java
  // /web/boards/home → 목록 화면으로 리다이렉트
  @GetMapping("/home")
  public String home() {
    return "redirect:/web/boards";
  }
```

애플리케이션을 다시 실행하고 웹 브라우저에서 [http://localhost:8080/web/boards/home](http://localhost:8080/web/boards/home) 에 접속한다.
주소창의 주소가 `/web/boards`로 **바뀌면서** 게시글 목록 화면이 출력된다.

터미널에서 `curl`로 요청하여 서버의 응답을 직접 확인한다. (`-i` 옵션은 응답 헤더까지 출력한다.)

```bash
# macOS
curl -i http://localhost:8080/web/boards/home
```

```powershell
# Windows (PowerShell)
curl.exe -i http://localhost:8080/web/boards/home
```

```
HTTP/1.1 302
Location: http://localhost:8080/web/boards
...
```

서버는 HTML 대신 **`302` 상태 코드**와 **`Location` 헤더**를 응답했다. 웹 브라우저는 이 응답을 받으면 `Location`의 주소로 **자동으로 다시 요청**한다.

> REST Client는 기본적으로 리다이렉트 응답을 받으면 자동으로 다시 요청하므로, `302` 응답을 직접 보려면 `curl`을 사용한다.

**4) 정리**

| 실습 | 확인한 내용 |
| --- | --- |
| 실습-1 | `@Controller`는 모델에 데이터를 담고 뷰 이름을 리턴한다. `ViewResolver`가 `templates/뷰이름.html`을 찾고, Thymeleaf가 모델의 데이터로 HTML을 만든다. |
| 실습-2 | 화면 요청과 REST API 요청 모두 `DispatcherServlet` → `HandlerMapping` → 컨트롤러 순서로 처리된다. 그다음 화면은 `ViewResolver`가, 데이터는 메시지 컨버터가 처리한다. |
| 실습-3 | `@Controller`에서도 `@RequestParam` 등으로 요청 데이터를 받는다. `th:each`로 목록을 반복하여 출력한다. |
| 실습-4 | 화면 컨트롤러와 REST API 컨트롤러는 같은 서비스를 사용하고, 응답 방식만 다르다. |
| 실습-5 | `@Controller`의 리턴 값은 뷰 이름이다. `@ResponseBody`를 붙이면 응답 본문이 된다. `redirect:`는 `302`와 `Location` 헤더로 응답한다. |

