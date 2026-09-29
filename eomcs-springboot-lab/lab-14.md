# 14장. 권한 기반 인가

13장에서는 사용자를 데이터베이스에 저장하고 로그인을 구현했다. 그런데 지금은 로그인만 하면 **누구의 게시글이든** 수정·삭제할 수 있다.
이번 장에서는 사용자의 **역할**(Role)과 **권한**(Authority)에 따라 할 수 있는 일을 나누는 **인가**를 구현한다.
**URL별 접근 제어**로 관리자 화면을 보호하고, **Method Security**로 서비스 메서드에 "작성자만 수정, 작성자와 관리자만 삭제" 규칙을 적용한다.
마지막으로 **Thymeleaf와 Spring Security를 연동**하여, 사용자의 로그인 상태와 권한에 따라 화면에 보이는 메뉴와 버튼을 바꾼다.

## 역할과 권한

### 인가 규칙 정하기

게시판의 사용자를 다음 세 종류로 나누고, 할 수 있는 일을 정한다.

| 기능 | 로그인하지 않은 사용자 | 일반 사용자 (`USER`) | 관리자 (`ADMIN`) |
| --- | --- | --- | --- |
| 게시글 목록·상세 조회 | O | O | O |
| 게시글 등록 | | O | O |
| 게시글 수정 | | **자신의 글만** | **자신의 글만** |
| 게시글 삭제 | | **자신의 글만** | **모든 글** |
| 회원 관리 (회원 목록, 역할 변경) | | | O |
| H2 콘솔 | | | O |

이 규칙에는 두 종류가 있다.

| 종류 | 예 | 판단에 필요한 정보 | 적용 방법 |
| --- | --- | --- | --- |
| **역할**만으로 판단 | 회원 관리는 관리자만 | 사용자의 역할 | URL별 접근 제어, Method Security |
| **데이터**까지 보고 판단 | 게시글 수정은 작성자만 | 사용자의 아이디 + 게시글의 작성자 | Method Security (게시글을 조회하여 비교) |

### GrantedAuthority: 권한

Spring Security는 사용자의 권한을 **문자열**로 다룬다. 권한 하나는 `GrantedAuthority` 객체이고, `getAuthority()`가 권한 문자열을 리턴한다.
12장 `MeController`의 `authorities`에 출력된 `ROLE_USER`, `FACTOR_PASSWORD`가 모두 `GrantedAuthority`이다.

### Role과 Authority

**역할**(Role)은 `ROLE_`로 시작하는 권한이다. Spring Security는 따로 역할을 저장하지 않고, 권한 문자열에 `ROLE_`을 붙여 역할을 표현한다.

| 구분 | 권한 문자열의 예 | 의미 |
| --- | --- | --- |
| Role (역할) | `ROLE_USER`, `ROLE_ADMIN` | 사용자가 **누구인지**(어떤 부류인지)를 나타낸다. 보통 몇 개 되지 않는다. |
| Authority (세부 권한) | `board:write`, `user:manage` | **무엇을 할 수 있는지**를 세밀하게 나타낸다. 이름은 개발자가 정한다. |

역할을 다루는 메서드는 `ROLE_`을 **자동으로 붙이거나 붙여서 비교**한다.

| 코드 | 실제 권한 문자열 |
| --- | --- |
| `User.withUsername(...).roles("ADMIN")` (13장) | `ROLE_ADMIN`을 부여한다. |
| `hasRole("ADMIN")` | `ROLE_ADMIN`이 있는지 검사한다. |
| `hasAnyRole("USER", "ADMIN")` | `ROLE_USER`나 `ROLE_ADMIN` 중 하나라도 있는지 검사한다. |
| `hasAuthority("ROLE_ADMIN")` | `ROLE_ADMIN`이 있는지 검사한다. (`ROLE_`을 직접 적는다.) |
| `hasAuthority("board:write")` | `board:write`가 있는지 검사한다. |

> `hasRole("ROLE_ADMIN")`처럼 `ROLE_`을 붙여 쓰지 않는다. 역할에는 `hasRole("ADMIN")`, 세부 권한에는 `hasAuthority(...)`를 사용한다.

이 교재에서는 `users` 테이블의 `role` 열(13장)에 `USER` 또는 `ADMIN`을 저장하고, 로그인할 때 `roles(user.getRole())`로 `ROLE_USER` 또는 `ROLE_ADMIN` 권한을 부여한다.

> 서비스의 규모가 커지면 "관리자는 일반 사용자의 권한도 모두 가진다" 같은 **역할 계층**(`RoleHierarchy`)이나, 역할마다 세부 권한을 묶어 두는 설계를 사용한다. 이 교재에서는 역할 두 개만 사용한다.

### 권한은 로그인할 때 정해진다

사용자의 권한은 **로그인할 때** `UserDetailsService`가 읽어 `Authentication` 객체에 담고, 이 객체가 세션에 저장된다(12장).
그래서 로그인한 후에 데이터베이스에서 역할을 바꿔도, 그 사용자가 **다시 로그인하기 전까지는** 이전 권한이 그대로 적용된다. 실습-1에서 확인한다.

## URL별 접근 제어

### 역할로 주소 보호하기

12장에서 배운 `authorizeHttpRequests()`에 역할 조건을 사용한다.

```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/admin/**").hasRole("ADMIN")          // 관리자 화면: ROLE_ADMIN만
    .requestMatchers("/h2-console/**").hasRole("ADMIN")     // H2 콘솔: ROLE_ADMIN만
    ...
    .anyRequest().authenticated())
```

| 결과 | 로그인하지 않은 사용자 | 로그인했지만 역할이 맞지 않는 사용자 |
| --- | --- | --- |
| 화면 요청 | 로그인 화면으로 리다이렉트 | `403` (12장에서 만든 `error/403.html`) |
| API 요청 | `401` | `403` |

**인증**이 안 된 것(누구인지 모름)과 **인가**가 안 된 것(누구인지 알지만 권한이 없음)이 다르게 처리되는 것을 확인할 수 있다.

### 관리자 화면

관리자 화면은 `/admin`으로 시작하는 주소에 모은다. 주소 하나의 규칙(`/admin/**`)으로 관리자 기능 전체를 보호할 수 있다.

| 주소 | 메서드 | 기능 |
| --- | --- | --- |
| `/admin/users` | `GET` | 회원 목록 |
| `/admin/users/{id}/role` | `POST` | 회원의 역할 변경 (`USER` ↔ `ADMIN`) |

12장에서 "개발용"으로 누구에게나 열어 두었던 H2 콘솔도 관리자만 사용하게 바꾼다.

## Method Security

### URL 규칙만으로는 부족하다

URL별 접근 제어는 **주소**만 보고 판단한다. 다음과 같은 경우에는 URL 규칙만으로 충분하지 않다.

- "게시글 수정은 **작성자만**"처럼 **데이터**를 봐야 판단할 수 있는 규칙
- 같은 기능(게시글 수정)을 **여러 주소**(`POST /web/boards/{id}/edit`, `PUT /boards/{id}`)에서 사용하는 경우. 주소마다 규칙을 적다가 하나를 빠뜨리기 쉽다.
- URL 규칙을 실수로 빠뜨리거나 잘못 적은 경우. 한 겹의 방어만 있으면 바로 보안 사고가 된다.

**Method Security**는 **메서드**에 인가 규칙을 붙인다. 메서드를 호출하기 **전에** 규칙을 검사하고, 통과하지 못하면 메서드를 실행하지 않고 예외(`AccessDeniedException`)를 던진다.

```java
@Service
public class UserService {

  @PreAuthorize("hasRole('ADMIN')")        // 이 메서드는 관리자만 호출할 수 있다.
  public List<UserSummary> findAll() {
    ...
  }
}
```

### @EnableMethodSecurity와 @PreAuthorize

Method Security는 설정 클래스에 `@EnableMethodSecurity`를 붙여야 동작한다.

```java
@Configuration
@EnableMethodSecurity                      // @PreAuthorize 등을 사용한다.
public class SecurityConfig {
  ...
}
```

| 애노테이션 | 검사 시점 | 사용 예 |
| --- | --- | --- |
| `@PreAuthorize("식")` | 메서드 실행 **전** | `@PreAuthorize("hasRole('ADMIN')")` |
| `@PostAuthorize("식")` | 메서드 실행 **후** (리턴 값 `returnObject`를 검사할 수 있다.) | `@PostAuthorize("returnObject.writer == authentication.name")` |
| `@Secured("ROLE_ADMIN")` | 실행 전 (역할만 검사, 식 사용 불가) | `@EnableMethodSecurity(securedEnabled = true)`가 필요하다. |

이 교재에서는 가장 많이 쓰는 `@PreAuthorize`를 사용한다.

### @PreAuthorize의 식

`@PreAuthorize`의 문자열은 **SpEL**(Spring Expression Language) 식이다. URL 규칙의 `hasRole()` 등을 그대로 쓸 수 있고, 메서드의 파라미터와 스프링 빈도 사용할 수 있다.

| 식 | 의미 |
| --- | --- |
| `hasRole('ADMIN')`, `hasAuthority('...')` | 역할·권한 검사 |
| `isAuthenticated()`, `isAnonymous()` | 로그인했는지, 로그인하지 않았는지 |
| `authentication` | 현재 사용자의 `Authentication` 객체 (`authentication.name`은 아이디) |
| `#파라미터이름` | 메서드의 파라미터 값. 예: `#id` |
| `@빈이름.메서드(...)` | 스프링 빈의 메서드를 호출한 결과 |
| `and`, `or`, `not` | 조건 조합 |

```java
@PreAuthorize("hasRole('ADMIN') or #username == authentication.name")   // 관리자이거나 자기 자신
public void changePassword(String username, String newPassword) { ... }
```

> `#id`처럼 파라미터 이름을 사용하려면 컴파일할 때 파라미터 이름이 클래스 파일에 저장되어야 한다(`-parameters` 옵션). 스프링 부트의 Gradle 플러그인이 자동으로 설정하므로 따로 할 일은 없다.

### 게시글 권한 검사를 빈으로 만들기

"작성자만 수정"은 게시글을 **조회**해야 판단할 수 있다. 식 안에 복잡한 내용을 쓰면 읽기 어려우므로, 판단하는 코드를 **빈**으로 만들고 식에서 호출한다.

```java
@Component("boardSecurity")                        // 식에서 @boardSecurity로 사용한다.
public class BoardSecurity {

  // 수정할 수 있는가? → 작성자만
  public boolean canEdit(Long boardId, Authentication authentication) { ... }

  // 삭제할 수 있는가? → 작성자 또는 관리자
  public boolean canDelete(Long boardId, Authentication authentication) { ... }
}
```

```java
@PreAuthorize("@boardSecurity.canEdit(#id, authentication)")
@Transactional
public Optional<BoardResponse> update(Long id, BoardUpdateRequest request) { ... }

@PreAuthorize("@boardSecurity.canDelete(#id, authentication)")
@Transactional
public boolean delete(Long id) { ... }
```

규칙을 **서비스** 메서드에 붙이면, 서비스를 사용하는 화면(`BoardWebController`)과 REST API(`BoardController`) 모두에 같은 규칙이 적용된다.

### URL별 접근 제어와 Method Security 비교

| 구분 | URL별 접근 제어 | Method Security |
| --- | --- | --- |
| 설정 위치 | `SecurityConfig` 한 곳 | 각 메서드의 애노테이션 |
| 판단 기준 | 요청 주소, HTTP 메서드 | 메서드의 파라미터, 리턴 값, 빈을 이용한 데이터 비교 |
| 검사 시점 | 필터에서 (컨트롤러 호출 전) | 메서드 호출 전·후 |
| 잘 맞는 규칙 | 주소 영역 단위의 큰 규칙 (`/admin/**`는 관리자만) | 데이터에 따른 세밀한 규칙 (작성자만 수정) |

두 방법은 서로 대체하는 것이 아니라 **함께** 사용한다. URL 규칙으로 큰 영역을 막고, Method Security로 중요한 메서드를 한 번 더 막는다. 한쪽 설정에 실수가 있어도 다른 쪽이 막아 준다(**다층 방어**).

## Thymeleaf와 Spring Security 연동

### 화면에서 권한 확인하기

지금까지 만든 화면은 로그인 여부나 권한과 관계없이 모든 메뉴와 버튼을 보여 준다. 로그인한 사용자에게 `로그인` 링크가 보이고, 다른 사람의 게시글에도 `수정`·`삭제` 버튼이 보인다.
**Thymeleaf Extras Spring Security**를 사용하면 템플릿에서 로그인 상태와 권한을 확인할 수 있다.

```groovy
implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
```

> 이름에 `6`이 붙어 있지만 스프링 부트 4(Spring Security 7)에서 사용하는 라이브러리이다. 버전은 스프링 부트가 관리하므로 적지 않는다. 라이브러리를 추가하면 스프링 부트가 `sec` 속성을 처리하도록 자동으로 설정한다.

### sec 속성

`data-th-` 속성처럼 `data-sec-` 형식으로 쓰면 HTML5 표준 검사를 통과한다. (10장)

| 속성 | 기능 | 예 |
| --- | --- | --- |
| `data-sec-authorize="식"` | 식이 참일 때만 태그를 출력한다. | `data-sec-authorize="hasRole('ADMIN')"` |
| `data-sec-authentication="속성"` | 인증 정보의 값을 출력한다. | `data-sec-authentication="name"` (아이디) |

`data-sec-authorize`에는 `@PreAuthorize`와 같은 식을 사용한다.

| 식 | 화면에 출력되는 경우 |
| --- | --- |
| `isAnonymous()` | 로그인하지 않았을 때 (예: `로그인`, `회원가입` 링크) |
| `isAuthenticated()` | 로그인했을 때 (예: 사용자 아이디, `로그아웃` 버튼) |
| `hasRole('ADMIN')` | 관리자일 때 (예: `회원 관리` 메뉴) |

`data-th-` 속성의 식(`${...}`) 안에서는 다음 객체를 사용할 수 있다.

| 식 객체 | 내용 |
| --- | --- |
| `#authentication` | 현재 사용자의 `Authentication` 객체. `${#authentication.name}`은 아이디 |
| `#authorization` | 권한 검사. `${#authorization.expression('hasRole(''ADMIN'')')}` |

### 게시글 버튼 보이기·숨기기

게시글의 `수정`·`삭제` 버튼은 게시글마다 보일지 말지가 다르다. 템플릿에서도 `@빈이름`으로 스프링 빈을 사용할 수 있으므로, 서비스에 붙인 규칙과 **같은 빈**을 호출한다.

```html
<a href="#" data-th-href="@{/web/boards/{id}/edit(id=${board.id})}"
   data-th-if="${@boardSecurity.canEdit(board.id, #authentication)}">수정</a>
```

규칙이 `BoardSecurity` **한 곳**에 있으므로, 서버의 검사와 화면의 버튼이 서로 다르게 동작할 일이 없다.

> 화면에서 버튼을 숨기는 것은 **사용자의 편의**를 위한 것이지 **보안**이 아니다. 버튼이 없어도 주소를 직접 입력하거나 REST Client로 요청할 수 있다. 반드시 서버(URL 규칙, Method Security)에서 막아야 한다.

## 정리

| 주제 | 핵심 내용 |
| --- | --- |
| Role과 Authority | 권한은 문자열(`GrantedAuthority`)이다. 역할은 `ROLE_`로 시작하는 권한이다. `hasRole("ADMIN")`은 `ROLE_ADMIN`을 검사한다. 권한은 로그인할 때 정해진다. |
| URL별 접근 제어 | `requestMatchers("/admin/**").hasRole("ADMIN")`. 인증이 안 되면 로그인 화면(또는 `401`), 권한이 없으면 `403` |
| Method Security | `@EnableMethodSecurity` + `@PreAuthorize("식")`. 식에서 `#파라미터`, `authentication`, `@빈`을 사용한다. 서비스에 붙이면 화면과 API에 같은 규칙이 적용된다. |
| 사용자/관리자 권한 분리 | 회원 관리와 H2 콘솔은 관리자만. 게시글 수정은 작성자만, 삭제는 작성자와 관리자. 데이터가 필요한 규칙은 빈(`BoardSecurity`)으로 만든다. |
| Thymeleaf 연동 | `thymeleaf-extras-springsecurity6`. `data-sec-authorize`, `data-sec-authentication`, `#authentication`. 화면에서 숨기는 것은 편의일 뿐, 막는 것은 서버가 한다. |

## 실습

이번 장의 실습은 13장까지 사용한 `hello` 프로젝트에서 이어서 진행한다.
13장에서 가입한 사용자를 그대로 사용한다. 이번 장의 실습에는 다음 세 사용자가 필요하다. 없는 사용자는 회원가입 화면([/signup](http://localhost:8080/signup))에서 가입한다.

| 아이디 | 비밀번호 | 역할 | 용도 |
| --- | --- | --- | --- |
| `user` | `1234` | `ADMIN` (실습-1에서 변경) | 관리자 |
| `hong` | `1234` | `USER` | 일반 사용자 1 |
| `kim` | `1234` | `USER` | 일반 사용자 2 |

**모든 템플릿은 `data-th-`, `data-sec-` 형식으로 작성한다.**

실습을 마치면 다음 파일이 추가되거나 바뀐다.

```
hello
├── build.gradle                          ← Thymeleaf Extras 추가             (실습-5)
├── http
│   └── security.http                     ← 권한 확인 요청 추가               (실습-6)
└── src/main
    ├── java/com/example/hello
    │   ├── SecurityConfig.java           ← 역할 규칙, @EnableMethodSecurity  (실습-2, 3)
    │   ├── board
    │   │   ├── BoardSecurity.java        ← 게시글 권한 검사 빈               (실습-4)
    │   │   ├── BoardService.java         ← @PreAuthorize (수정)              (실습-4)
    │   │   └── BoardWebController.java   ← @PreAuthorize (수정)              (실습-4)
    │   └── user
    │       ├── UserEntity.java           ← 역할 변경 메서드 (수정)           (실습-2)
    │       ├── UserSummary.java          ← 회원 목록 DTO                     (실습-2)
    │       ├── UserService.java          ← 회원 목록, 역할 변경 (수정)       (실습-2, 3)
    │       └── UserAdminController.java  ← 관리자 화면 컨트롤러              (실습-2)
    └── resources/templates
        ├── admin
        │   └── users.html                ← 회원 관리 화면                    (실습-2)
        ├── boards
        │   └── detail.html               ← 수정·삭제 버튼 (수정)             (실습-5)
        └── fragments
            └── layout.html               ← 로그인 상태별 메뉴 (수정)         (실습-5)
```

### 실습-1: 관리자 계정 만들고 권한 확인하기

**1) 역할 확인하기**

`user` / `1234`로 로그인하고 [http://localhost:8080/me](http://localhost:8080/me) (12장 `MeController`)에 접속한다. `authorities`에 `ROLE_USER`가 있다.

**2) 관리자로 바꾸기**

아직 관리자 화면이 없으므로, H2 콘솔에서 `user`의 역할을 직접 바꾼다. [http://localhost:8080/h2-console](http://localhost:8080/h2-console) 에서 다음 SQL을 실행한다.

```sql
UPDATE users SET role = 'ADMIN' WHERE username = 'user';

SELECT id, username, role FROM users;
```

**3) 권한이 바뀌었는지 확인하기**

로그인한 상태 그대로 [/me](http://localhost:8080/me) 를 새로 고침한다. `authorities`는 여전히 `ROLE_USER`이다.
권한은 로그인할 때 읽어 세션에 저장해 두기 때문이다. 데이터베이스를 바꿔도 이미 로그인한 사용자의 권한은 바뀌지 않는다.

로그아웃한 후 다시 `user` / `1234`로 로그인하고 [/me](http://localhost:8080/me) 에 접속한다. 이제 `authorities`에 `ROLE_ADMIN`이 있다.

```json
{
  "name": "user",
  "authorities": "[ROLE_ADMIN, FACTOR_PASSWORD]",
  ...
}
```

> 권한의 순서는 다를 수 있다.

### 실습-2: 관리자 화면 만들고 URL로 보호하기

**1) 엔티티에 역할 변경 메서드 추가하기**

`user/UserEntity.java`에 다음 메서드를 추가한다.

```java
  // 역할 변경 (변경 감지로 UPDATE된다.)
  public void changeRole(String role) {
    this.role = role;
  }
```

**2) 회원 목록 DTO 만들기**

`user` 폴더에 `UserSummary.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.user;

// 회원 목록 화면용 DTO: 비밀번호는 담지 않는다.
public record UserSummary(Long id, String username, String role) {

  public static UserSummary from(UserEntity user) {
    return new UserSummary(user.getId(), user.getUsername(), user.getRole());
  }
}
```

**3) 서비스에 회원 목록과 역할 변경 추가하기**

`user/UserService.java`에 다음 두 메서드를 추가한다.

```java
import java.util.List;

import org.springframework.data.domain.Sort;

  // 회원 목록 (관리자)
  public List<UserSummary> findAll() {
    return userRepository.findAll(Sort.by("id")).stream()
        .map(UserSummary::from)                    // 엔티티 → DTO
        .toList();
  }

  // 역할 변경 (관리자): 사용자가 없으면 false
  @Transactional
  public boolean changeRole(Long id, String role) {
    return userRepository.findById(id)
        .map(user -> {
          user.changeRole(role);                   // 변경 감지
          return true;
        })
        .orElse(false);
  }
```

**4) 관리자 화면 컨트롤러 만들기**

`user` 폴더에 `UserAdminController.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

  private final UserService userService;

  public UserAdminController(UserService userService) {
    this.userService = userService;
  }

  // 회원 목록: GET /admin/users
  @GetMapping
  public String list(Model model) {
    model.addAttribute("users", userService.findAll());
    return "admin/users";
  }

  // 역할 변경: POST /admin/users/{id}/role
  @PostMapping("/{id}/role")
  public String changeRole(@PathVariable Long id,
                           @RequestParam String role,
                           RedirectAttributes redirectAttributes) {
    if (!role.equals("USER") && !role.equals("ADMIN")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);   // 정해진 역할만 허용한다.
    }
    if (!userService.changeRole(id, role)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    redirectAttributes.addFlashAttribute("message", "역할을 변경했습니다. 해당 사용자가 다시 로그인하면 적용됩니다.");
    return "redirect:/admin/users";
  }
}
```

> 역할 값은 화면의 숨은 입력 항목으로 전달되지만, 사용자가 개발자 도구로 얼마든지 바꿀 수 있다. 그래서 서버에서 허용된 값(`USER`, `ADMIN`)인지 다시 확인한다.

**5) 회원 관리 화면 만들기**

`templates` 폴더 아래에 `admin` 폴더를 만들고, 그 안에 `users.html` 파일을 만든다. 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head data-th-replace="~{fragments/layout :: head('회원 관리')}">
  <meta charset="UTF-8">
  <title>회원 관리</title>
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>

  <h2>회원 관리</h2>

  <p class="message" data-th-if="${message}" data-th-text="${message}">메시지</p>

  <table border="1">
    <thead>
      <tr>
        <th>번호</th>
        <th>아이디</th>
        <th>역할</th>
        <th>역할 변경</th>
      </tr>
    </thead>
    <tbody>
      <tr data-th-each="user : ${users}">
        <td data-th-text="${user.id}">1</td>
        <td data-th-text="${user.username}">user</td>
        <td data-th-text="${user.role}">USER</td>
        <td>
          <form method="post" action="#" data-th-action="@{/admin/users/{id}/role(id=${user.id})}">
            <input type="hidden" name="role" value="ADMIN"
                   data-th-value="${user.role == 'ADMIN'} ? 'USER' : 'ADMIN'">
            <button type="submit"
                    data-th-text="${user.role == 'ADMIN'} ? '일반 사용자로 변경' : '관리자로 지정'">관리자로 지정</button>
          </form>
        </td>
      </tr>
    </tbody>
  </table>

  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
</html>
```

- 역할이 `ADMIN`인 사용자에게는 `일반 사용자로 변경` 버튼을, `USER`인 사용자에게는 `관리자로 지정` 버튼을 보여 준다.
- 숨은 입력 항목(`role`)에 바꿀 역할을 담아 보낸다.

**6) URL 규칙 추가하기**

`SecurityConfig.java`의 `webFilterChain()`에서 H2 콘솔 규칙을 바꾸고, 관리자 화면 규칙을 추가한다.

```java
            .requestMatchers("/h2-console/**").hasRole("ADMIN")                            // H2 콘솔: 관리자만 (변경)
            .requestMatchers("/admin/**").hasRole("ADMIN")                                 // 관리자 화면 (추가)
```

> H2 콘솔을 관리자만 사용하게 바꾸었으므로, 관리자로 로그인해야 H2 콘솔을 사용할 수 있다. 관리자 계정이 없는 상태에서 이 규칙을 적용하면 H2 콘솔로 관리자를 만들 수 없게 된다. 그래서 실습-1에서 관리자를 먼저 만들었다.

**7) 실행하고 확인하기**

애플리케이션을 다시 실행한다. 시크릿 창에서 다음과 같이 확인한다.

| 사용자 | [/admin/users](http://localhost:8080/admin/users) | [/h2-console](http://localhost:8080/h2-console) |
| --- | --- | --- |
| 로그인하지 않음 | 로그인 화면으로 이동 | 로그인 화면으로 이동 |
| `hong` (`USER`) | **403 화면** (12장 `error/403.html`) | **403 화면** |
| `user` (`ADMIN`) | 회원 목록이 나온다. | H2 콘솔이 나온다. |

`user`로 로그인한 상태에서 회원 목록의 `kim` 줄에 있는 `관리자로 지정`을 누른다.

1. "역할을 변경했습니다..." 메시지가 나오고, `kim`의 역할이 `ADMIN`으로 바뀐다.
2. **다른 웹 브라우저**(예: `user`는 Chrome, `kim`은 Edge나 Safari)에서 `kim`으로 로그인하여 [/admin/users](http://localhost:8080/admin/users) 에 접속한다. 회원 목록이 나온다.
3. `user`의 화면에서 `kim`을 다시 `일반 사용자로 변경`한다. `kim`의 화면에서 새로 고침하면 **여전히 회원 목록이 나온다.** (실습-1과 같은 이유)
4. `kim`으로 다시 로그인하면 `403`이 된다.

> 실제 서비스에서는 역할을 바꿀 때 그 사용자의 세션을 끊어 바로 적용되게 하기도 한다. 이 교재에서는 다루지 않는다.

### 실습-3: Method Security 적용하기

**1) Method Security 켜기**

`SecurityConfig.java` 클래스에 `@EnableMethodSecurity`를 붙인다.

```java
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity                        // 추가: @PreAuthorize 사용
public class SecurityConfig {
  ...
}
```

**2) 서비스 메서드에 규칙 붙이기**

`user/UserService.java`의 `findAll()`과 `changeRole()`에 `@PreAuthorize`를 붙인다.

```java
import org.springframework.security.access.prepost.PreAuthorize;

  // 회원 목록 (관리자)
  @PreAuthorize("hasRole('ADMIN')")          // 추가
  public List<UserSummary> findAll() {
    ...
  }

  // 역할 변경 (관리자): 사용자가 없으면 false
  @PreAuthorize("hasRole('ADMIN')")          // 추가
  @Transactional
  public boolean changeRole(Long id, String role) {
    ...
  }
```

**3) URL 규칙을 빠뜨렸을 때 확인하기**

Method Security가 **두 번째 방어선**으로 동작하는지 확인한다. `SecurityConfig.java`에서 관리자 화면 규칙을 잠시 주석으로 처리한다.

```java
            // .requestMatchers("/admin/**").hasRole("ADMIN")                              // 관리자 화면 (잠시 주석)
```

애플리케이션을 다시 실행하고 `hong`으로 로그인하여 [/admin/users](http://localhost:8080/admin/users) 에 접속한다.

- URL 규칙이 없으므로 요청은 `anyRequest().authenticated()` 규칙을 통과하여 컨트롤러까지 간다.
- 컨트롤러가 `userService.findAll()`을 호출하는 순간 `@PreAuthorize`가 막는다. **403 화면**이 나온다.

`@PreAuthorize`를 잠시 지우고 같은 실험을 하면, `hong`에게 회원 목록이 그대로 보인다. 한 겹의 방어만 있을 때 실수가 어떤 결과를 낳는지 확인한 것이다.

확인한 후에는 URL 규칙의 주석과 `@PreAuthorize`를 모두 **원래대로** 되돌린다.

> `@PreAuthorize`가 막으면 `AccessDeniedException`(정확히는 그 하위 클래스인 `AuthorizationDeniedException`)이 발생한다. 이 예외는 12장에서 배운 `ExceptionTranslationFilter`가 받아 `403`으로 응답한다.

### 실습-4: 게시글 수정·삭제 권한 적용하기

**1) 게시글 권한 검사 빈 만들기**

`board` 폴더에 `BoardSecurity.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

// 게시글 권한 검사: @PreAuthorize와 템플릿에서 @boardSecurity로 사용한다.
@Component("boardSecurity")
public class BoardSecurity {

  private final BoardJpaRepository boardJpaRepository;

  public BoardSecurity(BoardJpaRepository boardJpaRepository) {
    this.boardJpaRepository = boardJpaRepository;
  }

  // 수정할 수 있는가? → 작성자만
  public boolean canEdit(Long boardId, Authentication authentication) {
    if (!isLoggedIn(authentication)) {
      return false;
    }
    return boardJpaRepository.findById(boardId)
        .map(board -> board.getWriter().equals(authentication.getName()))   // 작성자 = 로그인 아이디 (13장)
        .orElse(true);             // 게시글이 없으면 통과시킨다. → 컨트롤러가 404로 응답한다.
  }

  // 삭제할 수 있는가? → 관리자 또는 작성자
  public boolean canDelete(Long boardId, Authentication authentication) {
    return isAdmin(authentication) || canEdit(boardId, authentication);
  }

  // 로그인했는가? (로그인하지 않은 사용자는 AnonymousAuthenticationToken이다. 12장)
  private boolean isLoggedIn(Authentication authentication) {
    return authentication != null
        && authentication.isAuthenticated()
        && !(authentication instanceof AnonymousAuthenticationToken);
  }

  // 관리자인가?
  private boolean isAdmin(Authentication authentication) {
    return isLoggedIn(authentication)
        && authentication.getAuthorities().stream()
            .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));
  }
}
```

- `@Component("boardSecurity")`: 빈의 이름을 `boardSecurity`로 정한다. 식에서는 `@boardSecurity`로 부른다.
- 게시글이 없을 때 `true`를 리턴하는 이유: 권한 검사를 통과시켜야 서비스가 "게시글 없음"을 알려 주고 컨트롤러가 `404`로 응답한다. `false`를 리턴하면 없는 게시글에 `403`이 응답된다.

> 13장 이전에 등록한 게시글은 작성자가 `홍길동` 같은 이름이므로, 아무도 수정할 수 없고 관리자만 삭제할 수 있다.

**2) 서비스 메서드에 규칙 붙이기**

`board/BoardService.java`의 `update()`와 `delete()`에 `@PreAuthorize`를 붙인다.

```java
import org.springframework.security.access.prepost.PreAuthorize;

  // 변경: 작성자만
  @PreAuthorize("@boardSecurity.canEdit(#id, authentication)")        // 추가
  @Transactional
  public Optional<BoardResponse> update(Long id, BoardUpdateRequest request) {
    ...
  }

  // 삭제: 작성자 또는 관리자
  @PreAuthorize("@boardSecurity.canDelete(#id, authentication)")      // 추가
  @Transactional
  public boolean delete(Long id) {
    ...
  }
```

**3) 수정 폼에도 규칙 붙이기**

수정 **폼**을 보여 주는 `editForm()`은 서비스의 `get()`을 사용하므로 위의 규칙이 적용되지 않는다. 다른 사람의 게시글 수정 폼을 볼 수 없도록 `board/BoardWebController.java`의 `editForm()`에도 규칙을 붙인다.

```java
import org.springframework.security.access.prepost.PreAuthorize;

  // 수정 폼: GET /web/boards/{id}/edit
  @PreAuthorize("@boardSecurity.canEdit(#id, authentication)")        // 추가
  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable Long id, Model model) {
    ...
  }
```

> `@PreAuthorize`는 컨트롤러 메서드에도 붙일 수 있다. 컨트롤러도 스프링 빈이기 때문이다.

**4) 실행하고 확인하기**

애플리케이션을 다시 실행한다.

1. `hong`으로 로그인하여 게시글을 하나 등록한다. (상세 화면의 번호를 기억한다.)
2. 로그아웃하고 `kim`으로 로그인한 후, `hong`의 게시글 상세 화면에서 `수정`을 누른다. **403 화면**이 나온다.
3. 상세 화면에서 `삭제`를 누른다. **403 화면**이 나오고 게시글은 삭제되지 않는다.
4. `kim`으로 게시글을 등록하고, 그 게시글은 수정·삭제할 수 있는 것을 확인한다.
5. 로그아웃하고 `user`(관리자)로 로그인한다. `hong`의 게시글에서 `수정`을 누르면 **403**(관리자도 다른 사람의 글은 수정할 수 없다), `삭제`를 누르면 **삭제된다.**
6. 없는 게시글의 수정 폼([/web/boards/999/edit](http://localhost:8080/web/boards/999/edit))에 접속하면 **404 화면**이 나온다.

`수정`·`삭제` 버튼은 아직 모든 사용자에게 보인다. 버튼을 눌러도 서버가 막기 때문에 안전하지만 사용하기에는 불편하다. 실습-5에서 권한에 따라 버튼을 보이거나 숨긴다.

### 실습-5: Thymeleaf와 Spring Security 연동하기

**1) 라이브러리 추가하기**

`build.gradle`의 `dependencies` 블록에 다음 한 줄을 추가한다.

```groovy
  implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
```

> 버전을 적지 않는다. 스프링 부트가 버전을 관리하는 라이브러리이다.

**2) 헤더 메뉴를 로그인 상태에 따라 바꾸기**

`templates/fragments/layout.html`의 `header` 프래그먼트를 다음과 같이 바꾼다.

```html
  <!-- header 프래그먼트 -->
  <header data-th-fragment="header">
    <h1><a href="#" data-th-href="@{/web/boards}">스프링 게시판</a></h1>
    <nav>
      <a href="#" data-th-href="@{/web/boards}">게시글 목록</a>
      <a href="#" data-th-href="@{/web/demo/basic}">Thymeleaf 연습</a>
      <a href="#" data-th-href="@{/swagger-ui.html}">API 문서</a>

      <!-- 관리자에게만 보인다. -->
      <a href="#" data-th-href="@{/admin/users}" data-sec-authorize="hasRole('ADMIN')">회원 관리</a>

      <!-- 로그인하지 않은 사용자에게만 보인다. -->
      <a href="#" data-th-href="@{/login}" data-sec-authorize="isAnonymous()">로그인</a>
      <a href="#" data-th-href="@{/signup}" data-sec-authorize="isAnonymous()">회원가입</a>

      <!-- 로그인한 사용자에게만 보인다. -->
      <span data-sec-authorize="isAuthenticated()"><strong data-sec-authentication="name">아이디</strong>님</span>
      <form class="inline" method="post" action="#" data-th-action="@{/logout}"
            data-sec-authorize="isAuthenticated()">
        <button type="submit">로그아웃</button>
      </form>
    </nav>
    <hr>
  </header>
```

| 사용자 | 헤더에 보이는 메뉴 (게시글 목록, Thymeleaf 연습, API 문서 외) |
| --- | --- |
| 로그인하지 않음 | 로그인, 회원가입 |
| `hong` (`USER`) | **hong**님, 로그아웃 |
| `user` (`ADMIN`) | 회원 관리, **user**님, 로그아웃 |

**3) 상세 화면의 버튼을 권한에 따라 보이기**

`templates/boards/detail.html`에서 `수정` 링크와 삭제 폼에 `data-th-if`를 추가한다.

```html
  <p>
    <a href="list.html" data-th-href="@{/web/boards}">목록</a>
    <a href="form.html" data-th-href="@{/web/boards/{id}/edit(id=${board.id})}"
       data-th-if="${@boardSecurity.canEdit(board.id, #authentication)}">수정</a>
  </p>
  <form method="post" action="#" data-th-action="@{/web/boards/{id}/delete(id=${board.id})}"
        data-th-if="${@boardSecurity.canDelete(board.id, #authentication)}"
        onsubmit="return confirm('이 게시글을 삭제할까요?');">
    <button type="submit">삭제</button>
  </form>
```

- `@boardSecurity`: 실습-4에서 만든 빈. 서비스의 `@PreAuthorize`와 **같은 규칙**으로 버튼을 보여 준다.
- `#authentication`: 현재 사용자의 인증 정보. 로그인하지 않은 사용자는 익명 사용자(`AnonymousAuthenticationToken`)이므로 `canEdit()`이 `false`를 리턴한다.

**4) 실행하고 확인하기**

애플리케이션을 다시 실행하고, 사용자를 바꿔 가며 헤더 메뉴와 `hong`이 쓴 게시글의 상세 화면을 확인한다. (실습-4에서 `hong`의 게시글을 삭제했다면 `hong`으로 게시글을 하나 더 등록한다.)

| 사용자 | 헤더 | `hong`의 게시글 상세 화면 |
| --- | --- | --- |
| 로그인하지 않음 | 로그인, 회원가입 | 버튼 없음 |
| `hong` (작성자) | hong님, 로그아웃 | 수정, 삭제 |
| `kim` (다른 사용자) | kim님, 로그아웃 | 버튼 없음 |
| `user` (관리자) | 회원 관리, user님, 로그아웃 | 삭제 |

`kim`으로 로그인하여 버튼이 보이지 않는 `hong`의 게시글 수정 폼 주소(`/web/boards/{번호}/edit`)를 **직접 입력**한다. 실습-4의 규칙 덕분에 **403 화면**이 나온다. 버튼을 숨기는 것만으로는 막을 수 없다는 것을 확인한다.

**5) 회원 관리 화면에서 자신의 역할은 바꾸지 못하게 하기**

관리자가 실수로 자신을 일반 사용자로 바꾸면 관리자가 한 명도 남지 않을 수 있다. `templates/admin/users.html`의 역할 변경 칸(`<td>`)을 다음과 같이 바꾼다.

```html
        <td>
          <!-- 자기 자신의 역할은 바꿀 수 없다. -->
          <span data-th-if="${user.username == #authentication.name}">(나)</span>
          <form method="post" action="#" data-th-action="@{/admin/users/{id}/role(id=${user.id})}"
                data-th-unless="${user.username == #authentication.name}">
            <input type="hidden" name="role" value="ADMIN"
                   data-th-value="${user.role == 'ADMIN'} ? 'USER' : 'ADMIN'">
            <button type="submit"
                    data-th-text="${user.role == 'ADMIN'} ? '일반 사용자로 변경' : '관리자로 지정'">관리자로 지정</button>
          </form>
        </td>
```

`user`로 로그인하여 회원 관리 화면을 열면, 자신의 줄에는 버튼 대신 `(나)`가 보인다.

> 이것도 화면에서 숨긴 것일 뿐이다. 서버에서도 막으려면 컨트롤러의 `changeRole()`에서 로그인한 사용자(`@AuthenticationPrincipal`, 13장)와 역할을 바꿀 사용자를 비교해야 한다. 직접 추가해 보자.

### 실습-6: REST API 권한 확인하기

**1) 요청 추가하기**

`http/security.http` 파일 맨 위의 변수 아래에 일반 사용자 변수를 추가한다.

```http
@username2 = hong
@password2 = 1234
```

파일 아래에 다음 요청을 추가한다. `{번호}`는 실습-4에서 `kim`으로 등록한 게시글의 번호로 바꾼다.

```http
### 8. 다른 사람(hong)이 게시글 변경
PUT {{baseUrl}}/boards/{번호}
Authorization: Basic {{username2}}:{{password2}}
Content-Type: application/json

{
  "title": "다른 사람이 변경",
  "content": "변경되지 않아야 한다."
}

### 9. 다른 사람(hong)이 게시글 삭제
DELETE {{baseUrl}}/boards/{번호}
Authorization: Basic {{username2}}:{{password2}}

### 10. 관리자(user)가 게시글 삭제
DELETE {{baseUrl}}/boards/{번호}
Authorization: Basic {{username}}:{{password}}
```

**2) 실행하고 확인하기**

| 요청 | 응답 | 이유 |
| --- | --- | --- |
| 8. `hong`이 `kim`의 게시글 변경 | `403` | 서비스의 `@PreAuthorize("@boardSecurity.canEdit(...)")` |
| 9. `hong`이 `kim`의 게시글 삭제 | `403` | 서비스의 `@PreAuthorize("@boardSecurity.canDelete(...)")` |
| 10. 관리자가 `kim`의 게시글 삭제 | `204` | 관리자는 모든 게시글을 삭제할 수 있다. |

REST API 컨트롤러(`BoardController`)는 전혀 고치지 않았다. 규칙을 **서비스**에 붙였기 때문에 화면과 API에 같은 규칙이 적용되었다.

**3) 정리**

| 실습 | 확인한 내용 |
| --- | --- |
| 실습-1 | 역할은 `ROLE_` 권한으로 부여된다. 권한은 로그인할 때 정해지므로, 역할을 바꾸면 다시 로그인해야 적용된다. |
| 실습-2 | `hasRole("ADMIN")`으로 관리자 화면과 H2 콘솔을 보호한다. 로그인하지 않으면 로그인 화면, 권한이 없으면 `403` |
| 실습-3 | `@EnableMethodSecurity`와 `@PreAuthorize`로 서비스 메서드를 보호한다. URL 규칙을 빠뜨려도 메서드에서 한 번 더 막는다. |
| 실습-4 | 데이터가 필요한 규칙은 빈(`BoardSecurity`)으로 만들어 `@PreAuthorize`에서 호출한다. 작성자만 수정, 작성자와 관리자만 삭제 |
| 실습-5 | `data-sec-authorize`, `data-sec-authentication`, `#authentication`으로 로그인 상태와 권한에 따라 메뉴와 버튼을 바꾼다. 숨기는 것은 편의이고, 막는 것은 서버이다. |
| 실습-6 | 서비스에 붙인 규칙은 REST API에도 그대로 적용된다. |
