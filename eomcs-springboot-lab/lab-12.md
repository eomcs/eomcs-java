# 12장. Spring Security 개요

11장까지 게시판의 REST API와 CRUD 화면을 완성했다. 그런데 지금은 **누구나** 게시글을 등록·수정·삭제할 수 있다.
이번 장에서는 **Spring Security**로 애플리케이션에 보안을 적용한다. 보안의 두 축인 **인증**(Authentication)과 **인가**(Authorization)의 개념을 배우고, Spring Security가 요청을 처리하는 구조인 **Security Filter Chain**을 살펴본다.
그리고 **기본 보안 설정**으로 화면과 REST API에 접근 규칙을 정하고, 로그인·로그아웃과 CSRF 보호가 동작하는 것을 확인한다.
사용자 정보를 데이터베이스에 저장하고 회원가입을 구현하는 것은 13장, 사용자와 관리자의 권한을 나누는 것은 14장에서 다룬다.

## 웹 애플리케이션과 보안

### 지금 게시판의 문제

11장까지 만든 게시판은 다음과 같은 요청을 **아무나** 보낼 수 있다.

| 요청 | 문제 |
| --- | --- |
| `GET /web/boards/new` → `POST /web/boards` | 누가 글을 썼는지 확인하지 않는다. 작성자 이름도 마음대로 입력할 수 있다. |
| `POST /web/boards/1/delete` | 다른 사람의 글을 삭제할 수 있다. |
| `DELETE /boards/1` (REST API) | 화면을 거치지 않고 API로 바로 삭제할 수 있다. |
| `GET /h2-console` | 데이터베이스 관리 화면에 누구나 접속할 수 있다. |

이 문제를 해결하려면 애플리케이션이 두 가지 질문에 답할 수 있어야 한다.

1. 요청을 보낸 사람이 **누구인가?**
2. 그 사람이 이 요청을 **해도 되는가?**

### 인증과 인가

첫 번째 질문에 답하는 것이 **인증**, 두 번째 질문에 답하는 것이 **인가**이다.

| 구분 | 인증 (Authentication) | 인가 (Authorization) |
| --- | --- | --- |
| 질문 | 당신은 **누구**인가? | 당신은 이것을 **해도 되는가**? |
| 확인 방법 | 아이디와 비밀번호, 인증서, 토큰 등 | 사용자의 권한(역할)과 요청한 자원을 비교 |
| 순서 | 먼저 | 인증 후 (또는 인증하지 않은 사용자로) |
| 실패하면 | `401 Unauthorized` (화면은 로그인 화면으로 이동) | `403 Forbidden` |
| 예 | 로그인 | 글 작성은 로그인한 사용자만, 회원 관리는 관리자만 |

회사 건물에 비유하면, 출입구에서 사원증으로 **누구인지 확인**하는 것이 인증이고, 사원증에 등록된 권한에 따라 **들어갈 수 있는 층을 정하는** 것이 인가이다.

> HTTP 상태 코드 `401`의 이름은 `Unauthorized`이지만, 실제 의미는 "**인증되지 않음**"이다. 권한이 없어 거부된 경우는 `403 Forbidden`이다. (4장 HTTP 상태 코드 참고)

### 로그인 상태를 기억하는 방법

4장에서 배운 것처럼 HTTP는 **상태를 기억하지 않는다**(stateless). 요청마다 서로 독립적이므로, 로그인한 후에도 서버는 다음 요청을 누가 보냈는지 알 수 없다.
그래서 인증 정보를 다음 요청에 전달하는 방법이 필요하다. 이 장에서는 두 가지 방법을 사용한다.

| 방법 | 동작 | 사용하는 곳 |
| --- | --- | --- |
| **세션 + 쿠키** | 로그인에 성공하면 서버가 **세션**(서버 메모리의 저장 공간)에 인증 정보를 저장하고, 세션 번호를 **쿠키**(`JSESSIONID`)로 웹 브라우저에 보낸다. 웹 브라우저는 이후 요청마다 쿠키를 자동으로 보낸다. | 화면 (폼 로그인) |
| **요청마다 인증 정보 전송** | 요청할 때마다 `Authorization` 헤더에 아이디와 비밀번호를 담아 보낸다. 서버는 세션을 만들지 않는다. | REST API (HTTP Basic 인증) |

**HTTP Basic 인증**은 `아이디:비밀번호`를 Base64로 바꿔 `Authorization` 헤더에 담는다.

```
Authorization: Basic dXNlcjoxMjM0          ← "user:1234"를 Base64로 바꾼 값
```

> Base64는 암호화가 아니라 **단순한 변환**이다. 누구나 원래 값으로 되돌릴 수 있으므로, 실제 서비스에서는 반드시 **HTTPS**로 통신해야 한다. 실무의 REST API는 보통 JWT 같은 **토큰**을 사용한다. 이 교재에서는 원리 이해가 쉽도록 HTTP Basic 인증을 사용한다.

## Spring Security 소개

### Spring Security란

**Spring Security**는 스프링 애플리케이션의 **인증, 인가, 공격 방어**를 담당하는 스프링 프로젝트이다.

| 기능 | 내용 |
| --- | --- |
| 인증 | 폼 로그인, HTTP Basic, OAuth 2.0(소셜 로그인), 토큰 등 다양한 인증 방식 |
| 인가 | 주소별 접근 제어, 메서드별 접근 제어(14장) |
| 공격 방어 | CSRF 방어, 세션 고정 공격 방어, 보안 관련 응답 헤더 추가(클릭재킹, MIME 스니핑 방어 등) |
| 비밀번호 보호 | 비밀번호를 안전하게 암호화하여 저장(13장 `PasswordEncoder`) |

보안 기능을 직접 만들면 실수하기 쉽고, 실수는 곧 보안 사고로 이어진다. Spring Security는 검증된 보안 기능을 **설정만으로** 적용할 수 있게 해 준다.

### 스타터를 추가하면

`spring-boot-starter-security`를 추가하기만 하면 스프링 부트의 **자동 구성**(2장)이 다음 보안 설정을 적용한다.

| 기본 동작 | 내용 |
| --- | --- |
| 모든 요청에 인증 요구 | 모든 주소(오류 화면 `/error` 포함)는 로그인해야 접근할 수 있다. |
| 기본 사용자 1명 | 아이디는 `user`, 비밀번호는 실행할 때마다 새로 만들어 **실행 로그에 출력**한다. |
| 폼 로그인·로그아웃 | 로그인 화면(`/login`)과 로그아웃 화면(`/logout`)을 자동으로 만들어 준다. |
| HTTP Basic 인증 | API 요청은 `Authorization` 헤더로 인증할 수 있다. |
| 요청 종류에 따른 응답 | 인증되지 않은 요청이 웹 브라우저의 화면 요청이면 **로그인 화면으로 리다이렉트**, 그 밖의 요청이면 `401`을 응답한다. |
| CSRF 방어 | `POST`, `PUT`, `DELETE` 요청에 CSRF 토큰을 요구한다. |
| 보안 응답 헤더 | `X-Frame-Options`, `X-Content-Type-Options`, `Cache-Control` 등의 헤더를 추가한다. |

실습-1에서 이 기본 동작을 하나씩 확인한다.

## Spring Security 처리 구조

### 필터: DispatcherServlet보다 먼저

9장에서 모든 요청은 `DispatcherServlet`이 받아 컨트롤러에 전달한다고 배웠다.
그런데 서블릿 컨테이너(톰캣)는 요청을 서블릿에 전달하기 **전에** **필터**(Filter)를 먼저 실행한다. 필터는 요청을 검사하거나 바꿀 수 있고, 필요하면 요청을 **서블릿까지 보내지 않고 바로 응답**할 수도 있다.

Spring Security는 이 필터를 이용한다. 요청이 컨트롤러에 도착하기 전에 인증과 인가를 검사하고, 허용되지 않은 요청은 그 자리에서 돌려보낸다.

```
웹 브라우저
 │  요청
 ▼
서블릿 컨테이너 (Tomcat)
 ├─ 필터 ...
 ├─ DelegatingFilterProxy            ← 서블릿 필터로 등록된 Spring Security의 입구
 │   └─ FilterChainProxy             ← 요청에 맞는 SecurityFilterChain을 고른다.
 │       └─ SecurityFilterChain      ← 보안 필터 목록
 │           ├─ SecurityContextHolderFilter
 │           ├─ CsrfFilter
 │           ├─ UsernamePasswordAuthenticationFilter
 │           ├─ ...
 │           └─ AuthorizationFilter
 ├─ 필터 ...
 ▼
DispatcherServlet → 컨트롤러
```

| 구성 요소 | 역할 |
| --- | --- |
| `DelegatingFilterProxy` | 서블릿 컨테이너에 등록되는 **일반 서블릿 필터**. 스스로 일하지 않고 스프링 컨테이너의 빈(`FilterChainProxy`)에 일을 넘긴다. 서블릿 컨테이너와 스프링 컨테이너를 **연결**한다. |
| `FilterChainProxy` | Spring Security의 **시작점**. 요청 주소를 보고 사용할 `SecurityFilterChain`을 **하나** 고른 후, 그 체인의 보안 필터를 차례로 실행한다. |
| `SecurityFilterChain` | **보안 필터의 목록**과 이 체인이 담당할 **요청 조건**으로 이루어진다. 개발자는 이 객체를 빈으로 등록하여 보안 설정을 한다. |
| 보안 필터 | 인증, CSRF 검사, 인가 등 **한 가지 일**을 맡은 필터. 정해진 순서대로 실행된다. |

> 스프링 부트가 `DelegatingFilterProxy`를 서블릿 컨테이너에 자동으로 등록한다. 개발자는 `SecurityFilterChain`만 설정하면 된다.

### Security Filter Chain

`SecurityFilterChain`에 들어 있는 주요 보안 필터는 다음과 같다. 위에서부터 **순서대로** 실행된다.

| 순서 | 필터 | 하는 일 |
| --- | --- | --- |
| 1 | `SecurityContextHolderFilter` | 세션에 저장된 인증 정보를 꺼내 현재 요청에서 사용할 수 있게 한다. |
| 2 | `HeaderWriterFilter` | 보안 관련 응답 헤더를 추가한다. |
| 3 | `CsrfFilter` | `POST` 등 데이터를 바꾸는 요청에 올바른 CSRF 토큰이 있는지 검사한다. |
| 4 | `LogoutFilter` | 로그아웃 요청(`POST /logout`)을 처리한다. |
| 5 | `UsernamePasswordAuthenticationFilter` | 로그인 폼 요청(`POST /login`)의 아이디와 비밀번호로 **인증**한다. |
| 6 | `DefaultLoginPageGeneratingFilter` | 기본 로그인 화면(`GET /login`)을 만들어 응답한다. |
| 7 | `BasicAuthenticationFilter` | `Authorization: Basic ...` 헤더로 **인증**한다. |
| 8 | `RequestCacheAwareFilter` | 로그인 전에 요청했던 주소가 있으면 그 요청을 이어서 처리한다. |
| 9 | `AnonymousAuthenticationFilter` | 여기까지 인증되지 않았으면 **익명 사용자**로 표시한다. |
| 10 | `ExceptionTranslationFilter` | 뒤쪽 필터에서 발생한 인증·인가 예외를 응답으로 바꾼다. (로그인 화면으로 리다이렉트, `401`, `403`) |
| 11 | `AuthorizationFilter` | 접근 규칙에 따라 요청을 **허용하거나 거부**한다. (**인가**) |

> 실제 필터는 이보다 더 많다. 설정에 따라 필터가 추가되거나 빠지므로, 실습-2에서 실행 로그로 직접 확인한다.

필터의 이름과 순서에서 다음 흐름을 읽을 수 있다.

- **인증 필터**(5, 7번)가 먼저 실행되어 요청을 보낸 사람이 누구인지 확인한다.
- 인증 정보가 없으면 **익명 사용자**(9번)가 된다.
- 마지막으로 **인가 필터**(11번)가 이 사용자가 요청한 주소에 접근해도 되는지 판단한다.

### 로그인하지 않은 사용자가 글쓰기를 누르면

로그인하지 않은 사용자가 `GET /web/boards/new`를 요청하면 다음과 같이 처리된다.

| 단계 | 요청 | 처리 |
| --- | --- | --- |
| 1 | `GET /web/boards/new` | 인증 정보가 없으므로 `AnonymousAuthenticationFilter`가 익명 사용자로 표시한다. |
| 2 | | `AuthorizationFilter`가 "로그인 필요" 규칙에 따라 요청을 **거부**한다. (예외 발생) |
| 3 | | `ExceptionTranslationFilter`가 예외를 받아 **원래 요청을 세션에 저장**하고, 로그인 화면으로 **리다이렉트**한다. |
| 4 | `GET /login` | `DefaultLoginPageGeneratingFilter`가 로그인 화면을 응답한다. |
| 5 | `POST /login` | `UsernamePasswordAuthenticationFilter`가 아이디와 비밀번호를 확인한다. 성공하면 인증 정보를 **세션에 저장**한다. |
| 6 | | 3단계에서 저장한 주소(`/web/boards/new`)로 리다이렉트한다. |
| 7 | `GET /web/boards/new` | `SecurityContextHolderFilter`가 세션에서 인증 정보를 꺼낸다. `AuthorizationFilter`가 요청을 **허용**하고, 컨트롤러가 등록 폼을 응답한다. |

컨트롤러는 1~6단계를 전혀 알지 못한다. **보안 처리는 필터가, 기능 처리는 컨트롤러가** 나누어 맡는다.

### 인증 정보 보관: SecurityContextHolder

인증에 성공하면 Spring Security는 인증 결과를 `Authentication` 객체로 만들어 `SecurityContextHolder`에 보관한다.

```
SecurityContextHolder              ← 현재 요청의 보안 정보를 보관하는 곳
└── SecurityContext
    └── Authentication             ← 인증 결과
        ├── principal              ← 사용자 (누구인가)
        ├── credentials            ← 비밀번호 (인증 후에는 지운다)
        └── authorities            ← 권한 목록 (예: ROLE_USER)
```

애플리케이션 코드 어디에서든 현재 사용자를 확인할 수 있다.

```java
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String username = authentication.getName();          // 사용자 아이디
```

컨트롤러에서는 메서드 파라미터로 `Authentication`을 받을 수도 있다. 실습-3에서 확인한다.

> 아이디와 비밀번호를 실제로 확인하는 일은 `AuthenticationManager`가 맡는다. 이 객체는 `UserDetailsService`로 사용자 정보를 찾고, `PasswordEncoder`로 비밀번호를 비교한다. 이 부분은 13장에서 직접 구현한다.

## 기본 보안 설정

### SecurityFilterChain 빈 등록하기

스프링 부트의 기본 설정은 모든 주소에 로그인을 요구한다. 게시글 목록처럼 **누구나 볼 수 있어야 하는** 화면도 막혀 버린다.
원하는 규칙을 적용하려면 `SecurityFilterChain`을 **빈으로 등록**한다. 이 빈이 있으면 스프링 부트의 기본 보안 설정은 적용되지 않는다. (2장 자동 구성의 "개발자가 등록한 빈이 우선" 원칙)

```java
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth            // 접근 규칙 (인가)
            .requestMatchers("/web/boards").permitAll()
            .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults())          // 폼 로그인 사용 (기본 설정)
        .logout(Customizer.withDefaults());            // 로그아웃 사용 (기본 설정)
    return http.build();                               // 설정대로 SecurityFilterChain을 만든다.
  }
}
```

- `HttpSecurity`: `SecurityFilterChain`을 만드는 **빌더**. 스프링이 파라미터로 넣어 준다.
- 설정 메서드마다 **람다식**으로 세부 설정을 적는다. 기본 설정을 그대로 사용할 때는 `Customizer.withDefaults()`를 넘긴다.
- 설정한 기능에 따라 필요한 보안 필터가 체인에 추가된다. 예를 들어 `formLogin()`은 `UsernamePasswordAuthenticationFilter`를, `httpBasic()`은 `BasicAuthenticationFilter`를 추가한다.

> 스프링 부트에서는 설정 클래스에 `@EnableWebSecurity`를 붙이지 않아도 된다. 자동 구성이 대신 적용한다.

### 접근 규칙: authorizeHttpRequests

`authorizeHttpRequests()`에 **주소별 접근 규칙**을 적는다.

| 메서드 | 의미 |
| --- | --- |
| `requestMatchers("주소", ...)` | 규칙을 적용할 주소. `*`(한 단계), `**`(여러 단계), `{변수}`를 사용할 수 있다. |
| `requestMatchers(HttpMethod.GET, "주소", ...)` | HTTP 메서드까지 지정한다. |
| `anyRequest()` | 앞의 규칙에 해당하지 않는 나머지 모든 요청 |
| `permitAll()` | 누구나 접근할 수 있다. (로그인하지 않아도 된다.) |
| `authenticated()` | 로그인(인증)한 사용자만 접근할 수 있다. |
| `denyAll()` | 아무도 접근할 수 없다. |
| `hasRole("ADMIN")` | 특정 역할을 가진 사용자만 접근할 수 있다. (14장) |

규칙은 **위에서부터 차례로** 검사하고, **처음 일치하는 규칙 하나만** 적용한다. 그래서 **좁은 규칙을 먼저, 넓은 규칙을 나중에** 적고, 마지막에는 `anyRequest()`로 나머지를 처리한다.

예를 들어 다음 규칙에서 `/web/boards/{id}`는 `/web/boards/new`와도 일치한다. (`{id}` 자리에 `new`가 들어간다.)

```java
.requestMatchers(HttpMethod.GET, "/web/boards", "/web/boards/{id}").permitAll()   // /web/boards/new도 허용된다!
.requestMatchers(HttpMethod.GET, "/web/boards/new").authenticated()              // 이 규칙은 적용되지 않는다.
```

두 줄의 순서를 바꾸어야 등록 폼에 로그인이 필요해진다.

```java
.requestMatchers(HttpMethod.GET, "/web/boards/new").authenticated()              // 좁은 규칙을 먼저
.requestMatchers(HttpMethod.GET, "/web/boards", "/web/boards/{id}").permitAll()
```

> 컨트롤러의 `@GetMapping`은 고정된 주소(`/new`)를 경로 변수(`/{id}`)보다 **우선**하지만(11장), 접근 규칙은 **적은 순서**가 우선이다. 헷갈리기 쉬우므로 주의한다.

### 로그인과 로그아웃

| 설정 | 추가되는 기능 |
| --- | --- |
| `formLogin(Customizer.withDefaults())` | `GET /login`: 기본 로그인 화면, `POST /login`: 로그인 처리. 로그인하지 않은 사용자가 보호된 화면을 요청하면 로그인 화면으로 리다이렉트한다. |
| `httpBasic(Customizer.withDefaults())` | `Authorization: Basic ...` 헤더로 인증한다. 인증되지 않은 요청에는 `401`과 `WWW-Authenticate` 헤더를 응답한다. |
| `formLogin(form -> form.defaultSuccessUrl("/web/boards"))` | 로그인 화면으로 직접 들어와 로그인했을 때 이동할 주소를 정한다. (기본값은 `/`) 로그인 전에 요청했던 주소가 있으면 그 주소로 이동한다. |
| `logout(Customizer.withDefaults())` | `POST /logout`: 세션을 없애고 로그인 화면(`/login?logout`)으로 리다이렉트한다. |

> 기본 로그인 화면을 사용하는 동안에는 `GET /logout`으로 접속하면 "로그아웃할지 묻는 화면"이 나온다. 이 화면의 버튼이 `POST /logout`을 요청한다.
> 로그인 화면을 직접 만드는 방법은 13장에서 다룬다.

### CSRF 방어

**CSRF**(Cross-Site Request Forgery, 사이트 간 요청 위조)는 사용자가 **로그인한 상태를 악용**하는 공격이다.

1. 사용자가 게시판에 로그인한다. 웹 브라우저에 세션 쿠키가 저장된다.
2. 사용자가 공격자가 만든 다른 사이트를 방문한다.
3. 그 사이트에는 게시판으로 `POST /web/boards/1/delete`를 **몰래 보내는** 폼이 숨어 있다.
4. 웹 브라우저는 게시판으로 가는 요청에 **세션 쿠키를 자동으로 붙인다.** 서버는 로그인한 사용자의 정상 요청으로 알고 게시글을 삭제한다.

> 최신 웹 브라우저는 다른 사이트에서 보내는 요청에 쿠키를 붙이지 않는 정책(SameSite 쿠키)으로 이런 공격을 상당 부분 막아 준다. 그러나 웹 브라우저와 설정에 따라 막지 못하는 경우도 있으므로, 서버에서도 방어해야 한다.

이를 막기 위해 Spring Security는 서버가 만든 **CSRF 토큰**(추측할 수 없는 임의의 값)을 폼에 넣어 두고, `POST` 요청마다 이 토큰이 함께 오는지 검사한다.
공격자의 사이트는 게시판 화면에 들어 있는 토큰 값을 알 수 없으므로, 위조한 요청은 `403`으로 거부된다.

Thymeleaf 폼은 이 토큰을 **자동으로** 넣어 준다. 11장에서 폼 주소를 `data-th-action`으로 지정한 폼은 다음과 같이 숨은 입력 항목이 추가된다.

```html
<form method="post" action="/web/boards">
  <input type="hidden" name="_csrf" value="임의의 긴 문자열"/>   ← Spring Security와 Thymeleaf가 추가
  ...
</form>
```

| 요청 | CSRF 토큰 검사 |
| --- | --- |
| `GET`, `HEAD`, `OPTIONS`, `TRACE` | 검사하지 않는다. (데이터를 바꾸지 않는 요청이어야 한다. 4장 참고) |
| `POST`, `PUT`, `PATCH`, `DELETE` | 검사한다. 토큰이 없거나 틀리면 `403` |

> `GET` 요청은 검사하지 않으므로, 데이터를 바꾸는 기능을 `GET`으로 만들면 CSRF 방어가 동작하지 않는다. 11장에서 삭제를 링크가 아닌 **`POST` 폼**으로 만든 이유가 하나 더 생겼다.

**세션을 사용하지 않는 REST API**는 CSRF 방어를 끈다. CSRF는 웹 브라우저가 **쿠키를 자동으로 보내는** 점을 악용하는 공격인데, 요청마다 `Authorization` 헤더로 인증하는 API는 웹 브라우저가 이 헤더를 자동으로 붙이지 않으므로 공격이 성립하지 않는다.

> 반대로 세션(쿠키)으로 인증하는 곳에서 CSRF 방어를 끄면 안 된다. 그래서 이 장에서는 **화면**(세션 사용, CSRF 방어 켬)과 **REST API**(세션 사용 안 함, CSRF 방어 끔)의 설정을 **별도의 필터 체인**으로 나눈다.

### 여러 개의 SecurityFilterChain

`SecurityFilterChain` 빈은 여러 개 등록할 수 있다. `FilterChainProxy`는 요청마다 체인을 **순서대로** 검사하여 **처음 일치하는 체인 하나만** 실행한다.

| 순서 | 체인 | 담당 주소 (`securityMatcher`) | 인증 방식 | 세션 | CSRF 방어 |
| --- | --- | --- | --- | --- | --- |
| 1 | API 체인 | `/boards/**` | HTTP Basic | 사용 안 함 | 끔 |
| 2 | 화면 체인 | 나머지 모든 주소 | 폼 로그인 | 사용 | 켬 |

- `securityMatcher("/boards/**")`: 이 체인이 담당할 주소를 정한다. 지정하지 않으면 **모든 요청**을 담당한다.
- `@Order(숫자)`: 체인을 검사할 순서. 숫자가 작을수록 먼저 검사한다.
- 모든 요청을 담당하는 체인은 **맨 마지막**에 두어야 한다. 앞에 두면 뒤의 체인은 절대 실행되지 않는다. (실습-4에서 확인)

> `securityMatcher()`는 "**이 체인을** 어떤 요청에 사용할지", `requestMatchers()`는 "체인 안에서 **어떤 접근 규칙을** 적용할지"를 정한다. 이름이 비슷하므로 구분한다.

### 게시판의 보안 규칙

이번 장에서는 다음과 같이 규칙을 정한다. 아직 사용자 구분(일반 사용자·관리자)이 없으므로, **로그인했는지**만 확인한다.

**API 체인** (`/boards/**`)

| 요청 | 규칙 |
| --- | --- |
| `GET /boards/**` (목록, 개수, 조회) | 누구나 |
| 그 밖의 요청 (`POST`, `PUT`, `DELETE`) | 인증 필요 (HTTP Basic) |

**화면 체인** (나머지 모든 주소)

| 요청 | 규칙 | 이유 |
| --- | --- | --- |
| `/css/**` | 누구나 | 정적 자원. 막으면 로그인 전 화면의 스타일이 적용되지 않는다. |
| `/error` | 누구나 | 스프링 부트의 오류 처리 주소. 막으면 로그인하지 않은 사용자에게 404 화면 대신 로그인 화면이 나온다. |
| `/swagger-ui.html`, `/swagger-ui/**`, `/v3/api-docs/**` | 누구나 | API 문서(8장). |
| `/h2-console/**` | 누구나 | H2 콘솔(6장). 콘솔 자체의 로그인을 사용한다. 개발할 때만 사용한다. |
| `/hello/**`, `/web/hello`, `/web/greet`, `/web/demo/**` | 누구나 | 1~10장의 연습용 주소 |
| `GET /web/boards/new` | 인증 필요 | 등록 폼 (다음 규칙보다 먼저 적는다.) |
| `GET /web/boards`, `GET /web/boards/{id}` | 누구나 | 목록, 상세 화면 |
| 나머지 (등록·수정·삭제 처리, 수정 폼 등) | 인증 필요 | |

> H2 콘솔은 화면을 `<frame>`으로 구성하고 자체 폼을 사용한다. 그래서 H2 콘솔 주소는 CSRF 검사에서 제외하고, 같은 사이트 안에서는 `<frame>`을 허용하도록(`X-Frame-Options: SAMEORIGIN`) 설정해야 한다.
> 이 설정은 **개발용**이다. 운영 환경에서는 H2 콘솔과 Swagger UI를 끄거나(8장 "운영 환경에서는 끄기") 관리자만 접근하게 한다(14장).

## 정리

| 주제 | 핵심 내용 |
| --- | --- |
| 인증과 인가 | 인증은 "누구인가"(실패하면 `401`), 인가는 "해도 되는가"(실패하면 `403`). 인증이 먼저이다. |
| 로그인 상태 유지 | 화면은 세션과 쿠키(`JSESSIONID`), REST API는 요청마다 `Authorization` 헤더(HTTP Basic)로 인증한다. |
| 처리 구조 | `DelegatingFilterProxy` → `FilterChainProxy` → `SecurityFilterChain`의 보안 필터들. 요청이 컨트롤러에 도착하기 전에 필터가 인증·인가를 검사한다. |
| Security Filter Chain | 인증 필터 → 익명 사용자 → `ExceptionTranslationFilter` → `AuthorizationFilter`(인가) 순서로 실행된다. 여러 체인 중 처음 일치하는 체인 하나만 실행된다. |
| 인증 정보 | 인증 결과(`Authentication`)는 `SecurityContextHolder`에 보관된다. |
| 기본 보안 설정 | `SecurityFilterChain` 빈으로 설정한다. `authorizeHttpRequests`의 규칙은 위에서부터 처음 일치하는 규칙이 적용된다. |
| CSRF | 세션(쿠키)을 사용하는 화면은 CSRF 토큰으로 방어한다. `data-th-action` 폼에는 토큰이 자동으로 추가된다. 세션을 사용하지 않는 API는 CSRF 방어를 끈다. |

## 실습

이번 장의 실습은 11장까지 사용한 `hello` 프로젝트에서 이어서 진행한다.
실습-1~3에서는 스프링 부트의 **기본 보안 설정**을 확인하고, 실습-4~6에서는 **보안 설정 클래스**를 만들어 게시판에 맞는 규칙을 적용한다.

실습을 마치면 다음 파일이 추가되거나 바뀐다.

```
hello
├── build.gradle                          ← 스타터 추가                  (실습-1)
├── http
│   ├── board.http                        ← 인증 헤더 추가 (수정)         (실습-6)
│   └── security.http                     ← 보안 확인용 요청              (실습-1, 3, 6)
└── src/main
    ├── java/com/example/hello
    │   ├── MeController.java             ← 인증 정보 확인               (실습-3)
    │   └── SecurityConfig.java           ← 보안 설정                   (실습-4)
    └── resources
        ├── application.properties        ← 로그 설정, 사용자 계정         (실습-2, 3)
        └── templates
            ├── fragments
            │   └── layout.html           ← 로그인·로그아웃 링크 (수정)    (실습-5)
            └── error
                └── 403.html              ← 403 오류 화면               (실습-5)
```

### 실습-1: Spring Security 적용하고 기본 동작 확인하기

**1) 스타터 추가하기**

`build.gradle`의 `dependencies` 블록에 다음 한 줄을 추가한다.

```groovy
  implementation 'org.springframework.boot:spring-boot-starter-security'
```

> 코드는 한 줄도 고치지 않는다. 스타터만 추가했을 때 어떤 일이 일어나는지 확인한다.

**2) 실행하고 비밀번호 확인하기**

애플리케이션을 다시 실행한다. 실행 로그에서 다음과 같은 경고(`WARN`) 메시지를 찾는다.

```
... WARN ... : 

Using generated security password: 3f2b8c1e-6a4d-4f0e-9b7a-1c2d3e4f5a6b

This generated password is for development use only. Your security configuration must be updated before running your application in production.
```

- `Using generated security password`: 기본 사용자 `user`의 비밀번호이다. **실행할 때마다 바뀐다.**
- 두 번째 문장은 "이 비밀번호는 개발용이므로, 운영 환경에 배포하기 전에 보안 설정을 바꾸라"는 뜻이다.

비밀번호를 복사해 둔다.

**3) 웹 브라우저로 확인하기**

웹 브라우저의 **시크릿 창**(Chrome: `Ctrl+Shift+N`, macOS `⌘+Shift+N`)을 열고 [http://localhost:8080/web/boards](http://localhost:8080/web/boards) 에 접속한다.

> 시크릿 창은 창을 닫으면 쿠키(세션)가 모두 지워진다. 로그인하지 않은 상태를 확인할 때 편리하다.

1. 게시글 목록 대신 **Please sign in** 로그인 화면이 나온다. 주소가 `/login`으로 바뀌었다. (Spring Security가 자동으로 만든 화면이다.)
2. Username에 `user`, Password에 틀린 비밀번호를 입력하고 `Sign in`을 누른다. **Bad credentials** 메시지가 나온다.
3. 복사한 비밀번호를 입력하고 로그인한다. 처음 요청한 **게시글 목록 화면**으로 이동한다.
4. 11장에서 만든 게시글 등록·수정·삭제가 모두 **그대로 동작**한다.

> 로그인 후 이동한 주소 끝에 `?continue`가 붙을 수 있다. Spring Security가 "로그인 전에 저장해 둔 요청을 이어서 처리한다"는 표시로 붙이는 값이며, 무시해도 된다.

등록·수정·삭제가 그대로 동작하는 이유는 11장에서 모든 폼의 주소를 `data-th-action`으로 지정했기 때문이다. Thymeleaf가 CSRF 토큰을 폼에 자동으로 넣어 준다. (실습-5에서 확인한다.)

**4) 세션 쿠키 확인하기**

개발자 도구(`F12`)를 열고 `Application` 탭 → `Cookies` → `http://localhost:8080`을 선택한다. `JSESSIONID` 쿠키가 있다. 이 쿠키가 로그인 상태를 유지한다.

`JSESSIONID` 쿠키를 선택하고 삭제(`Delete` 키)한 후 화면을 새로 고침한다. 다시 로그인 화면이 나온다. 서버는 쿠키로 사용자를 알아보기 때문이다.

**5) 로그아웃하기**

다시 로그인한 후 [http://localhost:8080/logout](http://localhost:8080/logout) 에 접속한다.

1. **Are you sure you want to log out?** 화면이 나온다. `Log Out` 버튼을 누른다.
2. 로그인 화면(`/login?logout`)으로 이동하고 **You have been signed out** 메시지가 나온다.
3. [http://localhost:8080/web/boards](http://localhost:8080/web/boards) 에 접속하면 다시 로그인 화면이 나온다.

**6) REST API 확인하기**

`http` 폴더에 `security.http` 파일을 만들고 다음과 같이 작성한다. `@password`에는 실행 로그의 비밀번호를 적는다.

```http
@baseUrl = http://localhost:8080
@username = user
@password = 3f2b8c1e-6a4d-4f0e-9b7a-1c2d3e4f5a6b

### 1. 인증 정보 없이 목록 조회
GET {{baseUrl}}/boards

### 2. HTTP Basic 인증으로 목록 조회
GET {{baseUrl}}/boards
Authorization: Basic {{username}}:{{password}}

### 3. HTTP Basic 인증으로 게시글 등록
POST {{baseUrl}}/boards
Authorization: Basic {{username}}:{{password}}
Content-Type: application/json

{
  "title": "보안 적용",
  "content": "HTTP Basic 인증으로 등록한다.",
  "writer": "홍길동"
}
```

> REST Client는 `Authorization: Basic 아이디:비밀번호`를 적으면 Base64로 바꾸어 보낸다.
> `@baseUrl` 같은 변수는 파일마다 따로 정의해야 하므로, 새 파일에도 `@baseUrl`을 적는다.

세 요청을 차례로 보내고 응답을 확인한다.

| 요청 | 응답 | 이유 |
| --- | --- | --- |
| 1. 인증 정보 없이 조회 | `401 Unauthorized` | 인증되지 않았다. 응답 헤더 `WWW-Authenticate: Basic ...`는 "Basic 인증 정보를 보내라"는 뜻이다. |
| 2. 인증하고 조회 | `200 OK` | 인증에 성공했다. |
| 3. 인증하고 등록 | `403 Forbidden` | 인증 정보는 올바르지만 **CSRF 토큰이 없다.** |

REST API 요청에 대해서는 로그인 화면으로 리다이렉트하지 않고 `401`을 응답한다. 요청의 종류(`Accept` 헤더 등)를 보고 응답 방식을 고르기 때문이다.

3번 요청은 사용자 인증 정보가 올바른데도 거부되었다. CSRF 검사(`CsrfFilter`)가 인증 필터보다 **먼저** 실행되기 때문이다. REST Client는 CSRF 토큰을 받을 화면이 없으므로 API로는 게시글을 등록할 수 없다. 실습-4에서 API의 설정을 따로 만들어 해결한다.

**7) 막힌 주소 확인하기**

기본 설정은 **모든 주소**에 로그인을 요구한다. 로그아웃한 상태에서 다음 주소에 접속해 본다. 모두 로그인 화면이 나온다.

- [http://localhost:8080/web/boards/1](http://localhost:8080/web/boards/1) (게시글 상세)
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) (API 문서)
- [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (H2 콘솔)

H2 콘솔은 로그인한 후에도 화면이 제대로 나오지 않는다. H2 콘솔은 `<frame>`을 사용하는데, Spring Security가 보안 헤더(`X-Frame-Options: DENY`)로 `<frame>`을 막기 때문이다. 실습-4에서 해결한다.

### 실습-2: 보안 필터 체인 확인하기

**1) 보안 로그 켜기**

`application.properties`에 다음 설정을 잠시 추가한다.

```properties
# 12장: Spring Security의 처리 과정을 로그로 출력한다. (확인 후 삭제)
logging.level.org.springframework.security=DEBUG
```

**2) 필터 목록 확인하기**

애플리케이션을 다시 실행하고, 실행 로그에서 `DefaultSecurityFilterChain`이 출력한 다음과 같은 줄을 찾는다.

```
... DEBUG ... o.s.s.web.DefaultSecurityFilterChain : Will secure any request with filters: DisableEncodeUrlFilter, WebAsyncManagerIntegrationFilter, SecurityContextHolderFilter, HeaderWriterFilter, CsrfFilter, LogoutFilter, UsernamePasswordAuthenticationFilter, DefaultResourcesFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter, BasicAuthenticationFilter, RequestCacheAwareFilter, SecurityContextHolderAwareRequestFilter, AnonymousAuthenticationFilter, ExceptionTranslationFilter, AuthorizationFilter
```

> 버전에 따라 로그의 형식과 필터 목록이 조금 다를 수 있다. 자신의 로그에 출력된 필터 목록을 확인한다.

- `Will secure any request`: 이 체인이 **모든 요청**을 담당한다는 뜻이다. (스프링 부트의 기본 체인)
- 개념 설명의 "Security Filter Chain" 표에 있는 필터를 로그에서 찾아본다. `CsrfFilter`가 `BasicAuthenticationFilter`보다 앞에 있는 것도 확인한다. (실습-1의 3번 요청이 `403`이었던 이유)

**3) 요청 처리 과정 확인하기**

시크릿 창을 새로 열고 [http://localhost:8080/web/boards/new](http://localhost:8080/web/boards/new) 에 접속한다. 실행 로그에 다음과 비슷한 내용이 출력된다.

```
... Securing GET /web/boards/new
... Set SecurityContextHolder to anonymous SecurityContext
... Saved request http://localhost:8080/web/boards/new?continue to session
... Redirecting to /login
... Securing GET /login
```

| 로그 | 의미 (개념 설명의 단계) |
| --- | --- |
| `Securing GET /web/boards/new` | `FilterChainProxy`가 보안 필터 체인을 실행하기 시작한다. |
| `Set SecurityContextHolder to anonymous SecurityContext` | 인증 정보가 없어 **익명 사용자**가 되었다. (1단계) |
| `Saved request ... to session` | 인가에 실패하여 원래 요청을 세션에 저장한다. (2~3단계) |
| `Redirecting to /login` | 로그인 화면으로 리다이렉트한다. (3단계) |
| `Securing GET /login` | 웹 브라우저가 로그인 화면을 요청한다. (4단계) |

로그인한 후의 로그도 확인한다. 로그인 요청(`POST /login`)을 처리하고, 저장해 둔 주소(`/web/boards/new`)로 리다이렉트하는 내용이 출력된다.

**4) 필터가 실행되는 순서 확인하기**

설정을 `TRACE`로 바꾸고 애플리케이션을 다시 실행한다.

```properties
logging.level.org.springframework.security=TRACE
```

[http://localhost:8080/web/boards](http://localhost:8080/web/boards) 에 접속하면, 필터가 하나씩 실행되는 로그가 출력된다.

```
... Invoking DisableEncodeUrlFilter (1/16)
... Invoking WebAsyncManagerIntegrationFilter (2/16)
... Invoking SecurityContextHolderFilter (3/16)
... Invoking HeaderWriterFilter (4/16)
... Invoking CsrfFilter (5/16)
...
... Invoking AuthorizationFilter (16/16)
```

`(순서/전체 개수)`로 몇 번째 필터가 실행되는지 알 수 있다. (전체 개수는 2)에서 확인한 필터 목록의 개수와 같다.) 요청 **한 번**에 이 필터들이 모두 실행된다.

**5) 로그 설정 지우기**

확인을 마치면 `application.properties`에서 `logging.level.org.springframework.security` 설정을 **삭제**한다. 로그가 너무 많아 다른 로그를 보기 어렵기 때문이다.

> 보안 설정이 예상대로 동작하지 않을 때 이 로그 설정을 다시 켜고 원인을 찾는다.

### 실습-3: 사용자 계정 설정하고 인증 정보 확인하기

**1) 사용자 계정 설정하기**

실행할 때마다 비밀번호가 바뀌면 불편하다. `application.properties`에 다음 설정을 추가한다.

```properties
# 12장: 개발용 사용자 계정 (13장에서 데이터베이스의 사용자로 바꾼다.)
spring.security.user.name=user
spring.security.user.password=1234
spring.security.user.roles=USER
```

| 설정 | 의미 |
| --- | --- |
| `spring.security.user.name` | 사용자 아이디 (기본값 `user`) |
| `spring.security.user.password` | 비밀번호. 설정하면 비밀번호를 만들어 로그에 출력하지 않는다. |
| `spring.security.user.roles` | 사용자의 역할. `USER`는 권한 `ROLE_USER`가 된다. (역할은 14장에서 자세히 다룬다.) |

> 이 설정은 사용자 **한 명**만 만들 수 있고, 비밀번호가 설정 파일에 그대로 적혀 있다. 실습과 개발용이다. 13장에서 사용자를 데이터베이스에 저장하고 비밀번호를 암호화(`PasswordEncoder`)한다.

`http/security.http`의 비밀번호 변수도 바꾼다.

```http
@password = 1234
```

애플리케이션을 다시 실행한다. 실행 로그에 `Using generated security password`가 더 이상 출력되지 않는다. 아이디 `user`, 비밀번호 `1234`로 로그인할 수 있다.

**2) 현재 사용자 확인 API 만들기**

`com.example.hello` 패키지에 `MeController.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

  // 현재 로그인한 사용자의 인증 정보: GET /me
  @GetMapping("/me")
  public Map<String, Object> me(Authentication authentication) {   // 스프링이 현재 사용자의 인증 정보를 넣어 준다.
    Map<String, Object> result = new LinkedHashMap<>();             // 넣은 순서대로 JSON에 출력한다.
    result.put("name", authentication.getName());                   // 사용자 아이디
    result.put("authorities", authentication.getAuthorities().toString());   // 권한 목록
    result.put("credentials", authentication.getCredentials());     // 비밀번호
    result.put("type", authentication.getClass().getSimpleName());  // Authentication 구현 클래스

    // SecurityContextHolder에 보관된 인증 정보와 같은 객체인지 확인한다.
    Authentication fromHolder = SecurityContextHolder.getContext().getAuthentication();
    result.put("sameAsHolder", authentication == fromHolder);
    return result;
  }
}
```

**3) 실행하고 확인하기**

애플리케이션을 다시 실행하고 [http://localhost:8080/me](http://localhost:8080/me) 에 접속한다. 로그인 화면이 나오면 `user` / `1234`로 로그인한다. 다음과 비슷한 JSON이 출력된다.

```json
{
  "name": "user",
  "authorities": "[ROLE_USER, FACTOR_PASSWORD]",
  "credentials": null,
  "type": "UsernamePasswordAuthenticationToken",
  "sameAsHolder": true
}
```

| 항목 | 확인할 내용 |
| --- | --- |
| `name` | 로그인한 사용자의 아이디 |
| `authorities` | `spring.security.user.roles=USER`로 지정한 `ROLE_USER`가 들어 있다. |
| `credentials` | `null`이다. 인증에 성공하면 Spring Security가 **비밀번호를 지운다.** 인증 정보가 세션에 보관되므로, 비밀번호가 메모리에 남지 않게 하기 위해서이다. |
| `type` | 아이디와 비밀번호로 인증한 결과는 `UsernamePasswordAuthenticationToken` 객체이다. |
| `sameAsHolder` | `true`이다. 컨트롤러가 받은 인증 정보는 `SecurityContextHolder`에 보관된 **같은 객체**이다. |

> `FACTOR_PASSWORD`는 Spring Security 7부터 추가되는 권한으로, "**비밀번호로 인증했다**"는 표시이다. 여러 단계로 인증하는 기능(다중 인증, MFA)에서 사용한다. 이 교재에서는 사용하지 않는다. 권한의 순서는 다를 수 있다.

### 실습-4: 보안 설정 클래스 만들기

**1) SecurityConfig 만들기**

`com.example.hello` 패키지에 `SecurityConfig.java` 파일을 만들고 다음과 같이 작성한다. 개념 설명의 "게시판의 보안 규칙"을 코드로 옮긴 것이다.

```java
package com.example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  // 1번 체인: REST API (/boards/**)
  @Bean
  @Order(1)
  public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/boards/**")                                    // 이 체인이 담당할 주소
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/boards/**").permitAll()    // 조회는 누구나
            .anyRequest().authenticated())                                // 등록·변경·삭제는 인증 필요
        .httpBasic(Customizer.withDefaults())                             // HTTP Basic 인증
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))      // 세션을 사용하지 않는다.
        .csrf(csrf -> csrf.disable());                                    // 세션을 사용하지 않으므로 CSRF 방어를 끈다.
    return http.build();
  }

  // 2번 체인: 화면과 나머지 모든 요청
  @Bean
  @Order(2)
  public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/css/**", "/error").permitAll()                              // 정적 자원, 오류 화면
            .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()   // API 문서 (개발용)
            .requestMatchers("/h2-console/**").permitAll()                                 // H2 콘솔 (개발용)
            .requestMatchers("/hello/**", "/web/hello", "/web/greet", "/web/demo/**").permitAll()   // 연습용 주소
            .requestMatchers(HttpMethod.GET, "/web/boards/new").authenticated()            // 등록 폼 (다음 줄보다 먼저)
            .requestMatchers(HttpMethod.GET, "/web/boards", "/web/boards/{id}").permitAll() // 목록, 상세
            .anyRequest().authenticated())                                                 // 나머지는 로그인 필요
        .formLogin(form -> form
            .defaultSuccessUrl("/web/boards"))                            // 로그인 후 이동할 기본 주소
        .logout(Customizer.withDefaults())
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**"))                   // H2 콘솔은 CSRF 검사에서 제외
        .headers(headers -> headers
            .frameOptions(frame -> frame.sameOrigin()));                  // 같은 사이트의 <frame> 허용 (H2 콘솔)
    return http.build();
  }
}
```

| 부분 | 설명 |
| --- | --- |
| `@Order(1)`, `@Order(2)` | API 체인을 먼저 검사한다. 모든 요청을 담당하는 화면 체인은 마지막에 둔다. |
| `securityMatcher("/boards/**")` | `/boards`와 `/boards/`로 시작하는 요청만 API 체인이 담당한다. `/web/boards`는 해당하지 않는다. |
| `SessionCreationPolicy.STATELESS` | API 요청으로는 세션을 만들지 않고, 세션에 저장된 인증 정보도 사용하지 않는다. 요청마다 `Authorization` 헤더로 인증해야 한다. |
| `formLogin(form -> form.defaultSuccessUrl(...))` | 기본 로그인 화면을 사용하되, 로그인 화면으로 직접 들어와 로그인하면 게시글 목록으로 이동한다. |

> 이 클래스를 만들면 스프링 부트의 기본 보안 설정(실습-1의 동작)은 적용되지 않는다. 사용자 계정은 실습-3의 `spring.security.user.*` 설정을 그대로 사용한다.

**2) 화면 확인하기**

애플리케이션을 다시 실행한다. 시크릿 창을 새로 열고(로그인하지 않은 상태) 다음 주소에 접속한다.

| 주소 | 결과 |
| --- | --- |
| [/web/boards](http://localhost:8080/web/boards) | 로그인하지 않아도 게시글 목록이 나온다. |
| [/web/boards/1](http://localhost:8080/web/boards/1) | 게시글 상세 화면이 나온다. |
| [/web/boards/999](http://localhost:8080/web/boards/999) | 11장에서 만든 **404 화면**이 나온다. (`/error`를 허용했기 때문) |
| [/web/boards/new](http://localhost:8080/web/boards/new) | 로그인 화면이 나온다. 로그인하면 **등록 폼**으로 이동한다. |
| [/web/nothing](http://localhost:8080/web/nothing) | 로그인 화면이 나온다. 로그인한 후에 접속하면 404 화면이 나온다. |
| [/swagger-ui.html](http://localhost:8080/swagger-ui.html) | 로그인하지 않아도 API 문서가 나온다. |
| [/h2-console](http://localhost:8080/h2-console) | H2 콘솔이 나온다. `Connect`를 누르면 콘솔 화면이 정상적으로 나온다. |
| [/me](http://localhost:8080/me) | 로그인 화면이 나온다. |

`/web/nothing`은 없는 주소인데도 로그인 화면이 나온다. 인가 검사(필터)가 컨트롤러를 찾는 일(`DispatcherServlet`)보다 **먼저** 실행되기 때문이다. 로그인하지 않은 사용자는 어떤 주소가 있는지조차 알 수 없다.

시크릿 창에서 [http://localhost:8080/login](http://localhost:8080/login) 에 직접 접속하여 로그인하면 게시글 목록(`/web/boards`)으로 이동한다. (`defaultSuccessUrl`)

**3) 규칙의 순서 확인하기**

`webFilterChain()`에서 다음 두 줄의 순서를 잠시 바꾼다.

```java
            .requestMatchers(HttpMethod.GET, "/web/boards", "/web/boards/{id}").permitAll() // 목록, 상세
            .requestMatchers(HttpMethod.GET, "/web/boards/new").authenticated()            // 등록 폼
```

애플리케이션을 다시 실행하고, 로그인하지 않은 상태에서 [/web/boards/new](http://localhost:8080/web/boards/new) 에 접속한다. 로그인 화면 대신 **등록 폼이 나온다.** `/web/boards/{id}` 규칙이 먼저 일치하여 `permitAll()`이 적용되었기 때문이다.

> 등록 폼은 보이지만 `등록` 버튼을 누르면(`POST /web/boards`) `anyRequest().authenticated()` 규칙에 따라 로그인 화면으로 이동한다. 폼을 보여 주는 요청과 처리하는 요청을 모두 보호해야 한다.

확인한 후에는 두 줄의 순서를 **원래대로** 되돌린다.

**4) 체인의 순서 확인하기**

`apiFilterChain()`의 `@Order(1)`을 `@Order(3)`으로 잠시 바꾸고 애플리케이션을 다시 실행한다. 모든 요청을 담당하는 화면 체인(`@Order(2)`)이 API 체인보다 앞에 오게 된다.

애플리케이션이 시작되지 않고 다음과 비슷한 오류 메시지가 출력된다.

```
... A filter chain that matches any request [...] has already been configured, which means that this filter chain [...] will never get invoked. Please use `HttpSecurity#securityMatcher` to ensure that there is only one filter chain configured for 'any request' and that the 'any request' filter chain is published last.
```

"모든 요청을 담당하는 체인이 이미 있으므로, 그 뒤의 체인은 절대 실행되지 않는다. 모든 요청을 담당하는 체인은 마지막에 두라"는 뜻이다. Spring Security가 잘못된 순서를 **시작할 때 알려 준다.**

확인한 후에는 `@Order(1)`로 **되돌린다.**

### 실습-5: 화면에 로그인·로그아웃 연결하고 CSRF 토큰 확인하기

**1) 헤더에 로그인·로그아웃 링크 추가하기**

`templates/fragments/layout.html`의 `header` 프래그먼트에 로그인·로그아웃 링크를 추가한다.

```html
  <!-- header 프래그먼트 -->
  <header data-th-fragment="header">
    <h1><a href="#" data-th-href="@{/web/boards}">스프링 게시판</a></h1>
    <nav>
      <a href="#" data-th-href="@{/web/boards}">게시글 목록</a>
      <a href="#" data-th-href="@{/web/demo/basic}">Thymeleaf 연습</a>
      <a href="#" data-th-href="@{/swagger-ui.html}">API 문서</a>
      <a href="#" data-th-href="@{/login}">로그인</a>       <!-- 추가 -->
      <a href="#" data-th-href="@{/logout}">로그아웃</a>    <!-- 추가 -->
    </nav>
    <hr>
  </header>
```

> 지금은 로그인 여부와 관계없이 두 링크가 모두 보인다. 로그인한 사용자에게는 `로그아웃`만, 로그인하지 않은 사용자에게는 `로그인`만 보이게 하는 방법은 14장(Thymeleaf와 Security 연동)에서 다룬다.

**2) 로그인·로그아웃 확인하기**

시크릿 창에서 [http://localhost:8080/web/boards](http://localhost:8080/web/boards) 에 접속한다.

1. `로그인`을 누르고 `user` / `1234`로 로그인한다. 게시글 목록으로 돌아온다.
2. `글쓰기`를 눌러 게시글을 등록한다. 로그인한 상태이므로 로그인 화면 없이 등록 폼이 나온다.
3. `로그아웃`을 누르고 확인 화면에서 `Log Out`을 누른다.
4. 다시 `글쓰기`를 누르면 로그인 화면이 나온다.

**3) CSRF 토큰 확인하기**

로그인한 후 등록 폼([/web/boards/new](http://localhost:8080/web/boards/new))에서 마우스 오른쪽 버튼 → `페이지 소스 보기`를 선택한다. `<form>` 태그 안에 다음과 같은 숨은 입력 항목이 있다.

```html
<form method="post" action="/web/boards"><input type="hidden" name="_csrf" value="Xk3v...(긴 문자열)"/>
```

- `data-th-action`을 사용한 `POST` 폼에 Spring Security와 Thymeleaf가 **자동으로** 추가한 CSRF 토큰이다.
- 페이지를 새로 고침하면 `value` 값이 바뀐다. 토큰이 노출되어도 안전하도록 매번 다른 값으로 변환하여 넣기 때문이다. 서버는 변환된 값에서 원래 토큰을 꺼내 비교한다.

게시글 상세 화면의 소스도 확인한다. **삭제 폼**에도 CSRF 토큰이 들어 있다.
게시글 목록 화면의 **검색 폼**에는 CSRF 토큰이 없다. `GET` 폼이기 때문이다.

**4) 403 오류 화면 만들기**

`templates/error` 폴더에 `403.html` 파일을 만들고 다음과 같이 작성한다. 11장의 `404.html`과 같은 구조이다.

```html
<!DOCTYPE html>
<html lang="ko">
<head data-th-replace="~{fragments/layout :: head('접근이 거부되었습니다')}">
  <meta charset="UTF-8">
  <title>접근이 거부되었습니다</title>
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>

  <h2>요청이 거부되었습니다.</h2>
  <p>
    <span data-th-text="${status}">403</span>
    <span data-th-text="${error}">Forbidden</span>:
    <span data-th-text="${path}">/web/boards</span>
  </p>
  <p>권한이 없거나, 화면을 연 지 오래되어 보안 토큰이 만료되었을 수 있습니다. 화면을 다시 열어 시도하세요.</p>
  <p><a href="#" data-th-href="@{/web/boards}">게시글 목록으로 이동</a></p>

  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
</html>
```

**5) CSRF 토큰이 없는 요청 보내 보기**

로그인한 후 등록 폼을 연다. 개발자 도구(`F12`)의 `Elements` 탭에서 `<input type="hidden" name="_csrf" ...>` 줄을 선택하고 `Delete` 키로 삭제한다.
제목, 내용, 작성자를 입력하고 `등록`을 누른다.

- 방금 만든 **403 화면**이 나온다. 로그인한 사용자라도 CSRF 토큰이 없는 `POST` 요청은 거부된다.
- 목록에서 확인하면 게시글은 등록되지 않았다.

공격자의 사이트에서 몰래 보낸 요청이 바로 이런 요청이다. 공격자는 사용자의 화면에 들어 있는 토큰 값을 알 수 없다.

### 실습-6: REST API 인증 확인하기

**1) 요청 추가하기**

`http/security.http` 파일 아래에 다음 요청을 추가한다.

```http
### 4. 인증 정보 없이 게시글 등록
POST {{baseUrl}}/boards
Content-Type: application/json

{
  "title": "인증 없이 등록",
  "content": "등록되지 않아야 한다.",
  "writer": "홍길동"
}

### 5. 틀린 비밀번호로 게시글 등록
POST {{baseUrl}}/boards
Authorization: Basic {{username}}:wrong
Content-Type: application/json

{
  "title": "틀린 비밀번호",
  "content": "등록되지 않아야 한다.",
  "writer": "홍길동"
}

### 6. 인증 정보 없이 게시글 삭제
DELETE {{baseUrl}}/boards/1

### 7. 인증하고 게시글 변경 (번호는 3번 요청의 응답을 보고 바꾼다.)
PUT {{baseUrl}}/boards/1
Authorization: Basic {{username}}:{{password}}
Content-Type: application/json

{
  "title": "보안 적용 (변경)",
  "content": "HTTP Basic 인증으로 변경한다."
}
```

**2) 실행하고 확인하기**

1번부터 7번까지 차례로 요청한다. 3번 요청의 응답에서 새 게시글 번호를 확인하고, 7번 요청의 번호를 그 번호로 바꾸어 보낸다.

| 요청 | 실습-1 (기본 설정) | 실습-4 (`SecurityConfig`) |
| --- | --- | --- |
| 1. 인증 없이 조회 | `401` | `200` (조회는 누구나) |
| 2. 인증하고 조회 | `200` | `200` |
| 3. 인증하고 등록 | `403` (CSRF 토큰 없음) | `201` (API 체인은 CSRF 방어를 끔) |
| 4. 인증 없이 등록 | | `401` |
| 5. 틀린 비밀번호로 등록 | | `401` |
| 6. 인증 없이 삭제 | | `401` |
| 7. 인증하고 변경 | | `200` |

**3) 세션을 사용하지 않는 것 확인하기**

3번 요청의 **응답 헤더**를 확인한다. `Set-Cookie: JSESSIONID=...` 헤더가 **없다.** API 체인은 세션을 만들지 않는다(`STATELESS`).
웹 브라우저로 로그인했을 때는 `JSESSIONID` 쿠키가 만들어진 것과 비교한다. (실습-1)

그래서 REST API는 **요청할 때마다** `Authorization` 헤더를 보내야 한다. 2번 요청을 보낸 후 `Authorization` 헤더 없이 등록 요청(4번)을 보내도 `401`이 응답된다.

**4) 기존 요청 파일 고치기**

5장부터 사용한 `http/board.http`의 등록·변경·삭제 요청은 이제 `401`이 응답된다. 각 요청의 첫 줄 아래에 `Authorization` 헤더를 추가한다.

```http
@username = user
@password = 1234

### 게시글 등록
POST {{baseUrl}}/boards
Authorization: Basic {{username}}:{{password}}
Content-Type: application/json

...
```

> 변수(`@username`, `@password`)는 `board.http` 파일 맨 위의 `@baseUrl` 아래에 추가한다.

**5) Swagger UI 확인하기**

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 에서 목록 조회 API를 `Try it out` → `Execute`로 실행한다. 조회는 누구나 할 수 있으므로 정상적으로 응답된다.
등록 API를 실행하면 `401`이 응답된다. 이때 웹 브라우저가 아이디와 비밀번호를 묻는 창을 띄울 수 있다. `user` / `1234`를 입력하면 등록된다.

> Swagger UI 화면에 인증 정보 입력 기능(`Authorize` 버튼)을 추가하려면 OpenAPI 보안 설정이 필요하다. 이 교재에서는 다루지 않는다.

**6) 정리**

| 실습 | 확인한 내용 |
| --- | --- |
| 실습-1 | 스타터만 추가해도 모든 주소에 로그인이 필요해진다. 화면 요청은 로그인 화면으로 리다이렉트, API 요청은 `401`. 로그인 상태는 세션 쿠키(`JSESSIONID`)로 유지된다. |
| 실습-2 | 보안 로그로 필터 목록과 요청 처리 과정(익명 사용자 → 요청 저장 → 로그인 화면으로 리다이렉트)을 확인한다. |
| 실습-3 | `spring.security.user.*`로 개발용 계정을 정한다. 인증 결과는 `Authentication` 객체로 `SecurityContextHolder`에 보관되고, 비밀번호는 지워진다. |
| 실습-4 | `SecurityFilterChain` 빈 두 개로 API와 화면의 보안을 나눈다. 접근 규칙과 체인은 모두 **순서**가 중요하다. |
| 실습-5 | `data-th-action` 폼에는 CSRF 토큰이 자동으로 들어간다. 토큰이 없는 `POST` 요청은 로그인한 사용자라도 `403`으로 거부된다. |
| 실습-6 | API는 조회만 누구나 할 수 있고, 등록·변경·삭제는 요청마다 HTTP Basic 인증이 필요하다. 세션을 만들지 않는다. |
