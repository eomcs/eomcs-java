# 11장. Thymeleaf + WebMVC

9장에서는 스프링 MVC로 화면을 응답하는 구조를, 10장에서는 Thymeleaf 템플릿의 문법을 배웠다. 지금까지 만든 화면은 게시글 **목록** 하나뿐이다.
이번 장에서는 게시글의 **상세 화면**을 만들고, HTML **폼**(Form)으로 게시글을 **등록·수정·삭제**하는 화면을 구현하여 JPA와 연동한 **CRUD 화면**을 완성한다.
이 과정에서 컨트롤러와 뷰가 데이터를 주고받는 방법, 폼 처리 후 리다이렉트하는 **PRG 패턴**, 입력 값을 검사하는 **Bean Validation**을 함께 배운다.

## 게시판 화면 설계

### 화면과 주소

게시판 화면은 다음과 같이 구성한다. 모든 화면의 주소는 9장에서 정한 대로 `/web`으로 시작한다.

| 화면 / 처리 | 메서드 | 주소 | 처리 후 |
| --- | --- | --- | --- |
| 목록 화면 | `GET` | `/web/boards` | 목록 화면 출력 (10장) |
| 상세 화면 | `GET` | `/web/boards/{id}` | 상세 화면 출력 |
| 등록 화면 (빈 폼) | `GET` | `/web/boards/new` | 등록 폼 출력 |
| 등록 처리 | `POST` | `/web/boards` | 상세 화면으로 **리다이렉트** |
| 수정 화면 (값이 채워진 폼) | `GET` | `/web/boards/{id}/edit` | 수정 폼 출력 |
| 수정 처리 | `POST` | `/web/boards/{id}/edit` | 상세 화면으로 **리다이렉트** |
| 삭제 처리 | `POST` | `/web/boards/{id}/delete` | 목록 화면으로 **리다이렉트** |

| 화면 | 사용자 동작 | 요청 | 다음 화면 |
| --- | --- | --- | --- |
| 목록 | 게시글 제목 클릭 | `GET /web/boards/{id}` | 상세 |
| 목록 | 글쓰기 클릭 | `GET /web/boards/new` | 등록 폼 |
| 등록 폼 | 등록 버튼 | `POST /web/boards` | 상세 (리다이렉트) |
| 상세 | 수정 클릭 | `GET /web/boards/{id}/edit` | 수정 폼 |
| 수정 폼 | 수정 버튼 | `POST /web/boards/{id}/edit` | 상세 (리다이렉트) |
| 상세 | 삭제 버튼 | `POST /web/boards/{id}/delete` | 목록 (리다이렉트) |

REST API(8장)와 비교하면 다음과 같다.

| 기능 | REST API (8장) | 화면 (11장) |
| --- | --- | --- |
| 등록 | `POST /boards` (JSON) | `GET /web/boards/new` (폼 화면) → `POST /web/boards` (폼 데이터) |
| 수정 | `PUT /boards/{id}` (JSON) | `GET /web/boards/{id}/edit` (폼 화면) → `POST /web/boards/{id}/edit` (폼 데이터) |
| 삭제 | `DELETE /boards/{id}` | `POST /web/boards/{id}/delete` |

화면에는 **입력 폼을 보여 주는 요청**(`GET`)과 **입력 값을 처리하는 요청**(`POST`)이 짝을 이룬다.
또 HTML 폼은 `GET`과 `POST`만 보낼 수 있으므로, 수정과 삭제도 `POST`로 요청한다. (뒤에서 자세히 설명한다.)

> 화면 컨트롤러(`BoardWebController`)와 REST API 컨트롤러(`BoardController`)는 모두 8장의 `BoardService`를 사용한다. 서비스는 고치지 않는다.

## 상세 화면

### 게시글 조회와 404 처리

상세 화면은 주소의 게시글 번호로 게시글을 조회하여 모델에 담는다. 5장에서 배운 `@PathVariable`을 그대로 사용한다.

```java
@GetMapping("/{id}")
public String detail(@PathVariable Long id, Model model) {
  BoardResponse board = boardService.get(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));   // 없으면 404
  model.addAttribute("board", board);
  return "boards/detail";
}
```

REST API에서는 게시글이 없으면 `ResponseEntity.notFound()`로 응답했다. 화면 컨트롤러는 뷰 이름을 리턴하므로, 대신 `ResponseStatusException`을 발생시킨다.
스프링 MVC는 이 예외를 받으면 지정한 상태 코드(`404`)로 **오류 화면**을 응답한다.

### 오류 화면 직접 만들기

스프링 부트는 오류가 발생하면 `templates/error` 폴더에서 **상태 코드와 같은 이름의 템플릿**을 찾아 오류 화면으로 사용한다. 템플릿이 없으면 Whitelabel Error Page를 보여 준다.

| 템플릿 | 사용되는 경우 |
| --- | --- |
| `templates/error/404.html` | 상태 코드가 `404`일 때 |
| `templates/error/500.html` | 상태 코드가 `500`일 때 |
| `templates/error/4xx.html` | `404.html`이 없고, 상태 코드가 `4xx`일 때 |
| `templates/error/error.html` | 해당하는 템플릿이 없을 때 |

오류 화면 템플릿에서는 스프링 부트가 모델에 담아 주는 다음 값을 사용할 수 있다.

| 이름 | 값 |
| --- | --- |
| `status` | 상태 코드 (`404`) |
| `error` | 상태 코드의 설명 (`Not Found`) |
| `path` | 요청한 주소 (`/web/boards/999`) |
| `timestamp` | 오류가 발생한 시각 |

## HTML 폼

### 폼으로 데이터 보내기

**HTML 폼**(Form)은 사용자가 입력한 값을 서버로 보내는 HTML 요소이다.

```html
<form action="/web/boards" method="post">
  <input type="text" name="title">
  <textarea name="content"></textarea>
  <input type="text" name="writer">
  <button type="submit">등록</button>
</form>
```

| 속성 | 의미 |
| --- | --- |
| `action` | 입력 값을 보낼 주소 |
| `method` | 요청 메서드. **`get`과 `post`만** 사용할 수 있다. |
| 입력 요소의 `name` | 서버로 보낼 때 사용하는 **값의 이름** |

`등록` 버튼을 누르면 웹 브라우저는 다음과 같은 요청을 보낸다.

```
POST /web/boards HTTP/1.1
Content-Type: application/x-www-form-urlencoded

title=%EC%8A%A4%ED%94%84%EB%A7%81&content=...&writer=...
```

- 본문의 형식은 JSON이 아니라 `이름=값&이름=값` 형식(**폼 데이터**)이다. `Content-Type`은 `application/x-www-form-urlencoded`이다.
- 입력 요소의 `name`이 이름이 된다. 한글은 URL 인코딩된다.
- `method="get"`이면 같은 `이름=값` 형식이 주소 뒤의 쿼리 스트링으로 붙는다. (10장의 검색 폼)

### 폼 데이터 받기: @ModelAttribute

폼 데이터는 `@RequestParam`으로 하나씩 받을 수도 있지만, 값이 많으면 **객체 하나로** 받는 것이 편리하다.
`@ModelAttribute`를 붙인 파라미터는 스프링 MVC가 객체를 만들고, **요청 파라미터의 이름과 같은 필드**에 값을 채워 준다. 이를 **데이터 바인딩**(Data Binding)이라고 한다.

```java
@PostMapping
public String create(@ModelAttribute("boardForm") BoardForm form) {
  // form.getTitle(), form.getContent(), form.getWriter()에 폼 입력 값이 들어 있다.
  ...
}
```

| 요청 파라미터 | 호출되는 `BoardForm`의 메서드 |
| --- | --- |
| `title=스프링` | `setTitle("스프링")` |
| `content=...` | `setContent("...")` |
| `writer=홍길동` | `setWriter("홍길동")` |

| 요청 데이터를 받는 애노테이션 | 요청 데이터의 형식 | 사용하는 곳 |
| --- | --- | --- |
| `@RequestBody` (5장) | 본문의 **JSON** | REST API |
| `@ModelAttribute` | **폼 데이터** 또는 쿼리 스트링 (`이름=값`) | 화면(HTML 폼) |

`@ModelAttribute("boardForm")`으로 받은 객체는 **모델에도 자동으로 담긴다.** 그래서 입력 값에 오류가 있어 폼 화면을 다시 보여 줄 때, 사용자가 입력했던 값이 그대로 남아 있게 할 수 있다.

### 폼 객체

폼의 입력 값을 담는 객체를 **폼 객체**(Form Object)라고 한다. 폼 객체도 화면과 컨트롤러 사이에서 데이터를 전달하는 DTO의 일종이다.

```java
public class BoardForm {

  private String title;
  private String content;
  private String writer;

  public BoardForm() {                       // 빈 폼을 만들 때 사용하는 기본 생성자
  }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  ...
}
```

지금까지 DTO는 `record`로 만들었지만, 폼 객체는 **기본 생성자와 getter·setter가 있는 일반 클래스**로 만든다.

- 등록 화면에서는 값이 비어 있는 폼 객체를 만들어 화면에 보낸다. (기본 생성자)
- 사용자가 입력한 값은 이름에 맞는 **setter**로 채워진다.
- Thymeleaf의 폼 입력 요소는 **getter**로 폼 객체의 값을 읽어 입력 창에 채운다.

> 폼 객체는 화면(Controller ↔ View) 사이에서만 사용한다. 컨트롤러는 폼 객체의 값으로 서비스용 DTO(`BoardCreateRequest`, `BoardUpdateRequest`)를 만들어 서비스를 호출한다. 서비스는 8장 그대로이다.

### Thymeleaf 폼: data-th-object와 data-th-field

Thymeleaf는 폼 객체와 HTML 폼을 연결하는 속성을 제공한다.

```html
<form action="#" method="post" data-th-action="@{/web/boards}" data-th-object="${boardForm}">
  <input type="text" id="title" name="title" data-th-field="*{title}">
  <textarea id="content" name="content" data-th-field="*{content}"></textarea>
  ...
</form>
```

| 속성 | 의미 |
| --- | --- |
| `data-th-object="${boardForm}"` | 이 폼에서 사용할 **폼 객체**를 선택한다. |
| `*{title}` | **선택 변수 표현식**. `data-th-object`로 선택한 객체의 `title` 값이다. (`${boardForm.title}`과 같다.) |
| `data-th-field="*{title}"` | 입력 요소의 `id`, `name`을 `title`로 정하고, `value`에 폼 객체의 `title` 값을 채운다. `<textarea>`는 태그 안의 내용을 채운다. |
| `data-th-action="@{...}"` | 폼을 보낼 주소. 링크 URL 표현식을 사용한다. |

`data-th-field`는 서버에서 처리되면 다음과 같이 바뀐다.

```html
<input type="text" id="title" name="title" value="스프링 공부">
```

> 폼 객체의 필드 이름, 입력 요소의 `name`, 요청 파라미터의 이름이 **모두 같아지므로** 데이터 바인딩이 정확하게 이루어진다.

> `data-th-field`가 `id`와 `name`을 만들어 주지만, 템플릿에도 `id`와 `name`을 적어 둔다. `<label for="title">`이 연결할 `id`가 있어야 HTML5 표준 검사를 통과하기 때문이다.

> 다음 장에서 Spring Security를 적용하면, `data-th-action`을 사용한 폼에는 보안 토큰(CSRF 토큰)이 **자동으로** 추가된다. 폼의 주소는 항상 `data-th-action`으로 지정한다.

## PRG 패턴

### POST 처리 후 화면을 바로 응답하면

등록 처리(`POST`)가 끝난 후 상세 화면의 뷰 이름을 바로 리턴하면 어떻게 될까?

```java
@PostMapping
public String create(...) {
  BoardResponse created = boardService.create(...);
  model.addAttribute("board", created);
  return "boards/detail";                 // POST 요청에 대해 화면을 바로 응답
}
```

화면은 정상적으로 보인다. 그런데 사용자가 이 화면에서 **새로 고침**(F5)을 누르면, 웹 브라우저는 마지막 요청인 **`POST /web/boards`를 다시 보낸다.** 같은 게시글이 **한 번 더 등록된다.**

### Post/Redirect/Get

이 문제를 막기 위해 `POST` 요청을 처리한 후에는 화면을 바로 응답하지 않고 **리다이렉트**한다. 이 방식을 **PRG 패턴**(Post/Redirect/Get)이라고 한다.

```
① POST /web/boards (등록)        → 서버: 게시글 등록 → 302 Location: /web/boards/9
② GET  /web/boards/9 (자동 요청)  → 서버: 상세 화면 응답
③ 새로 고침                       → 마지막 요청인 GET /web/boards/9를 다시 보낸다. (조회만 반복, 등록되지 않음)
```

```java
@PostMapping
public String create(...) {
  BoardResponse created = boardService.create(...);
  return "redirect:/web/boards/" + created.id();      // 상세 화면으로 리다이렉트
}
```

> 9장에서 배운 `redirect:`가 여기서 사용된다. 데이터를 바꾸는 `POST` 처리 후에는 **항상 리다이렉트**한다.

### 리다이렉트 후 메시지 전달: 플래시 속성

"게시글을 등록했습니다." 같은 메시지를 다음 화면에 보여 주고 싶다. 그런데 리다이렉트는 **새 요청**이므로, 등록 처리에서 모델에 담은 값은 리다이렉트된 화면에 전달되지 않는다.
이때 `RedirectAttributes`의 **플래시 속성**(Flash Attribute)을 사용한다.

```java
@PostMapping
public String create(..., RedirectAttributes redirectAttributes) {
  BoardResponse created = boardService.create(...);
  redirectAttributes.addFlashAttribute("message", "게시글을 등록했습니다.");   // 플래시 속성
  return "redirect:/web/boards/" + created.id();
}
```

```html
<!-- 상세 화면: message가 있을 때만 출력 -->
<p class="message" data-th-if="${message}" data-th-text="${message}">메시지</p>
```

플래시 속성은 서버에 잠시 보관되었다가 **리다이렉트된 다음 요청의 모델에 한 번만** 담기고 사라진다. 그래서 상세 화면을 새로 고침하면 메시지가 다시 나오지 않는다.

| `RedirectAttributes` 메서드 | 전달 방법 | 예 |
| --- | --- | --- |
| `addFlashAttribute(이름, 값)` | 다음 요청의 **모델**에 한 번만 담긴다. 주소에 나타나지 않는다. | 처리 결과 메시지 |
| `addAttribute(이름, 값)` | 리다이렉트 **주소의 쿼리 스트링**으로 붙는다. | `redirect:/web/boards` + `page=2` → `/web/boards?page=2` |

## 입력 값 검증

### 서버에서 검증해야 하는 이유

제목을 비워 두거나, 100자가 넘는 제목을 입력하면 어떻게 될까?
6장에서 DDL로 `title VARCHAR(100) NOT NULL`을 정의했으므로 데이터베이스가 저장을 거부하고 `500` 오류가 발생한다. 사용자는 무엇이 잘못되었는지 알 수 없다.
입력 값은 저장하기 전에 **서버에서 먼저 검사**하고, 잘못된 부분을 사용자에게 알려 줘야 한다.

> HTML의 `required`, `maxlength` 속성으로 웹 브라우저에서 먼저 검사할 수도 있다. 하지만 웹 브라우저의 검사는 사용자가 쉽게 우회할 수 있으므로(REST Client나 curl로 직접 요청하는 등), **서버의 검증은 반드시 필요하다.**

### Bean Validation

**Bean Validation**은 객체의 필드에 **애노테이션**으로 검증 규칙을 적는 자바 표준 기술이다. 스프링 부트에서는 **`spring-boot-starter-validation`** 스타터로 사용한다.

```java
public class BoardForm {

  @NotBlank(message = "제목을 입력하세요.")
  @Size(max = 100, message = "제목은 100자 이하로 입력하세요.")
  private String title;
  ...
}
```

자주 사용하는 검증 애노테이션은 다음과 같다. (`jakarta.validation.constraints` 패키지)

| 애노테이션 | 검사 내용 |
| --- | --- |
| `@NotNull` | `null`이 아니어야 한다. |
| `@NotEmpty` | `null`이 아니고, 빈 문자열(`""`)이나 빈 목록이 아니어야 한다. |
| `@NotBlank` | `null`이 아니고, 공백만 있는 문자열도 아니어야 한다. (문자열에 주로 사용) |
| `@Size(min, max)` | 문자열의 길이나 목록의 크기가 범위 안에 있어야 한다. |
| `@Min(값)`, `@Max(값)` | 숫자가 최솟값 이상, 최댓값 이하여야 한다. |
| `@Email` | 이메일 형식이어야 한다. |
| `@Pattern(regexp)` | 정규 표현식과 일치해야 한다. |

`message` 속성에는 검증에 실패했을 때 보여 줄 오류 메시지를 적는다.

> `@Size(max = 100)`처럼 검증 규칙을 DDL의 제약 조건(`VARCHAR(100)`, `NOT NULL`)과 맞춰 두면, 데이터베이스 오류가 발생하기 전에 사용자에게 알맞은 메시지를 보여 줄 수 있다.

### @Valid와 BindingResult

컨트롤러에서 폼 객체 파라미터 앞에 `@Valid`를 붙이면, 데이터 바인딩 후 검증 규칙을 검사한다.
검사 결과는 바로 다음 파라미터인 `BindingResult`에 담긴다.

```java
@PostMapping
public String create(@Valid @ModelAttribute("boardForm") BoardForm form,
                     BindingResult bindingResult,                  // 반드시 폼 객체 바로 다음에 선언한다.
                     RedirectAttributes redirectAttributes) {
  if (bindingResult.hasErrors()) {
    return "boards/form";                     // 오류가 있으면 폼 화면을 다시 보여 준다.
  }
  ...                                         // 오류가 없을 때만 등록한다.
}
```

- 오류가 있으면 리다이렉트하지 않고 **폼 화면을 다시** 보여 준다. 폼 객체(`boardForm`)는 모델에 담겨 있으므로 사용자가 입력했던 값이 그대로 채워진다.
- `BindingResult`를 선언하지 않으면, 검증에 실패했을 때 컨트롤러 메서드가 실행되지 않고 `400` 오류가 응답된다.

### 오류 메시지 출력하기

템플릿에서는 다음 기능으로 필드별 오류 메시지를 출력한다.

```html
<input type="text" id="title" name="title" data-th-field="*{title}" data-th-errorclass="field-error">
<p class="error" data-th-if="${#fields.hasErrors('title')}" data-th-errors="*{title}">제목 오류 메시지</p>
```

| 기능 | 의미 |
| --- | --- |
| `${#fields.hasErrors('title')}` | `title` 필드에 오류가 있으면 `true` |
| `data-th-errors="*{title}"` | `title` 필드의 오류 메시지를 출력한다. |
| `data-th-errorclass="field-error"` | 필드에 오류가 있으면 `class`에 `field-error`를 추가한다. (입력 창을 빨간 테두리로 표시하는 등) |

## 수정과 삭제

### 등록 폼과 수정 폼 함께 사용하기

등록 폼과 수정 폼은 입력 항목이 같다. 템플릿 하나(`boards/form.html`)를 함께 사용하고, **수정할 게시글의 번호**(`boardId`)가 모델에 있는지로 두 경우를 구분한다.

| 구분 | 모델의 `boardForm` | 모델의 `boardId` | 폼을 보낼 주소 |
| --- | --- | --- | --- |
| 등록 폼 | 빈 폼 객체 | 없음 (`null`) | `/web/boards` |
| 수정 폼 | 게시글 값이 채워진 폼 객체 | 게시글 번호 | `/web/boards/{id}/edit` |

```html
<form method="post" action="#"
      data-th-action="${boardId == null} ? @{/web/boards} : @{/web/boards/{id}/edit(id=${boardId})}"
      data-th-object="${boardForm}">
```

작성자는 수정할 수 없으므로 수정 폼에서는 `readonly`(읽기 전용)로 표시한다.

```html
<input type="text" id="writer" name="writer" data-th-field="*{writer}" data-th-readonly="${boardId != null}">
```

> `readonly` 입력 창의 값도 서버로 전송되지만, 서비스의 수정 기능(`BoardUpdateRequest`)은 제목과 내용만 사용하므로 작성자는 바뀌지 않는다.
> 웹 브라우저의 `readonly`는 사용자가 개발자 도구로 쉽게 바꿀 수 있으므로, "바꾸면 안 되는 값"은 반드시 **서버에서** 무시하거나 검사해야 한다.

### HTML 폼으로 삭제하기

HTML 폼은 `GET`과 `POST`만 보낼 수 있다. REST API처럼 `DELETE` 메서드를 사용할 수 없으므로, 삭제는 **`POST`** 요청으로 처리한다.

```html
<form method="post" action="#" data-th-action="@{/web/boards/{id}/delete(id=${board.id})}"
      onsubmit="return confirm('이 게시글을 삭제할까요?');">
  <button type="submit">삭제</button>
</form>
```

- `onsubmit="return confirm(...)"`은 폼을 보내기 전에 확인 창을 띄운다. `취소`를 누르면 폼을 보내지 않는다.

> 삭제를 **링크**(`<a href="/web/boards/3/delete">`)로 만들면 안 된다. 링크는 `GET` 요청이므로, 4장에서 배운 것처럼 검색 엔진이나 웹 브라우저의 미리 불러오기 기능이 링크를 방문하기만 해도 데이터가 삭제될 수 있다.

> 스프링은 폼에 `_method`라는 숨은 값을 넣어 `PUT`, `DELETE` 요청처럼 처리하는 기능(`spring.mvc.hiddenmethod.filter.enabled=true`)도 제공한다. 이 교재에서는 단순하게 `POST`를 사용한다.

## 컨트롤러와 뷰 사이의 데이터 전달

이번 장에서 사용한 데이터 전달 방법을 정리하면 다음과 같다.

**컨트롤러 → 뷰**

| 방법 | 사용 예 |
| --- | --- |
| `model.addAttribute(이름, 값)` | 목록, 게시글, 수정할 게시글 번호(`boardId`)를 화면에 전달 |
| `@ModelAttribute("이름")` 파라미터 | 폼 객체가 모델에 자동으로 담긴다. 검증 오류 시 입력 값을 다시 보여 줄 때 사용 |
| `redirectAttributes.addFlashAttribute(이름, 값)` | 리다이렉트된 다음 화면에 메시지 전달 |
| `BindingResult` | 검증 오류를 화면에 전달 (`#fields.hasErrors`, `data-th-errors`) |

**뷰 → 컨트롤러**

| 방법 | 받는 방법 | 사용 예 |
| --- | --- | --- |
| 주소의 경로 | `@PathVariable` | `/web/boards/{id}` |
| 쿼리 스트링 (링크, GET 폼) | `@RequestParam` | `/web/boards?page=2&keyword=...` |
| 폼 데이터 (POST 폼) | `@ModelAttribute` | 등록·수정 폼 |

## 정리

| 주제 | 핵심 내용 |
| --- | --- |
| 화면 설계 | 폼 화면(`GET`)과 처리(`POST`)가 짝을 이룬다. HTML 폼은 `GET`/`POST`만 사용하므로 수정·삭제도 `POST`로 처리한다. |
| 상세 화면 | 없는 게시글은 `ResponseStatusException(HttpStatus.NOT_FOUND)`로 404를 응답한다. `templates/error/404.html`로 오류 화면을 만든다. |
| 폼 처리 | `@ModelAttribute`로 폼 데이터를 폼 객체에 바인딩한다. 템플릿은 `data-th-object`, `data-th-field`, `*{...}`로 폼 객체와 연결한다. |
| PRG 패턴 | `POST` 처리 후에는 리다이렉트한다. 메시지는 `addFlashAttribute`로 전달한다. |
| 입력 값 검증 | `@NotBlank`, `@Size` 등으로 규칙을 적고, `@Valid` + `BindingResult`로 검사한다. 오류가 있으면 폼 화면을 다시 보여 준다. |

## 실습

이번 장의 실습은 10장까지 사용한 `hello` 프로젝트에서 이어서 진행한다.
10장에서 만든 게시글 목록 화면(`boards/list.html`), 공통 레이아웃(`fragments/layout.html`), 공통 스타일(`static/css/app.css`)을 바탕으로 상세·등록·수정·삭제 화면을 추가한다.
**모든 템플릿은 `data-th-` 형식으로 작성한다.**

실습을 마치면 다음 파일이 추가되거나 바뀐다.

```
src/main
├── java/com/example/hello/board
│   ├── BoardForm.java                 ← 게시글 폼 객체          (실습-2, 3)
│   └── BoardWebController.java        ← 화면 컨트롤러 (수정)     (실습-1 ~ 5)
└── resources
    ├── static/css
    │   └── app.css                    ← 스타일 추가            (실습-1, 3)
    └── templates
        ├── boards
        │   ├── list.html              ← 링크와 메시지 (수정)     (실습-1, 5)
        │   ├── detail.html            ← 상세 화면              (실습-1)
        │   └── form.html              ← 등록·수정 폼           (실습-2 ~ 4)
        └── error
            └── 404.html               ← 404 오류 화면          (실습-1)
```

### 실습-1: 상세 화면 만들기

**1) 상세 화면 요청 처리하기**

`board/BoardWebController.java`에 다음 메서드를 추가한다.

```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

  // 상세 화면: GET /web/boards/{id}
  @GetMapping("/{id}")
  public String detail(@PathVariable Long id, Model model) {
    BoardResponse board = boardService.get(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    model.addAttribute("board", board);
    return "boards/detail";
  }
```

**2) 상세 화면 템플릿 만들기**

`templates/boards` 폴더에 `detail.html` 파일을 만들고 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head data-th-replace="~{fragments/layout :: head('게시글 상세')}">
  <meta charset="UTF-8">
  <title>게시글 상세</title>
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>

  <!-- 처리 결과 메시지 (등록·수정 후 리다이렉트되었을 때) -->
  <p class="message" data-th-if="${message}" data-th-text="${message}">메시지</p>

  <h2 data-th-text="${board.title}">게시글 제목</h2>
  <p class="meta">
    번호: <span data-th-text="${board.id}">1</span> |
    작성자: <span data-th-text="${board.writer}">작성자</span>
  </p>
  <div class="content" data-th-text="${board.content}">게시글 내용</div>

  <p>
    <a href="list.html" data-th-href="@{/web/boards}">목록</a>
  </p>

  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
</html>
```

> 게시글 내용은 사용자가 입력한 값이므로 10장에서 배운 대로 `data-th-text`로 출력한다.

**3) 스타일 추가하기**

`static/css/app.css` 파일 아래에 다음 내용을 추가한다.

```css
.message { padding: 8px; background-color: #e7f5e7; border: 1px solid #7c7; }
.meta { color: #555; }
.content { white-space: pre-wrap; border: 1px solid #ddd; padding: 12px; min-height: 80px; }
```

> `white-space: pre-wrap`은 게시글 내용의 **줄바꿈을 그대로** 보여 준다. 줄바꿈을 `<br>` 태그로 바꿔 `data-th-utext`로 출력하는 방법은 XSS 위험이 있으므로 사용하지 않는다.

**4) 목록 화면의 제목 링크 바꾸기**

10장에서는 게시글 제목을 게시글 조회 API(JSON)로 연결했다. 이제 상세 화면으로 연결한다.
`templates/boards/list.html`에서 제목 링크를 다음과 같이 바꾼다.

```html
          <a href="detail.html" data-th-href="@{/web/boards/{id}(id=${board.id})}"
             data-th-text="${board.title}">제목</a>
```

**5) 오류 화면 만들기**

`templates` 폴더 아래에 `error` 폴더를 만들고, 그 안에 `404.html` 파일을 만든다. 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head data-th-replace="~{fragments/layout :: head('페이지를 찾을 수 없습니다')}">
  <meta charset="UTF-8">
  <title>페이지를 찾을 수 없습니다</title>
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>

  <h2>요청한 페이지를 찾을 수 없습니다.</h2>
  <p>
    <span data-th-text="${status}">404</span>
    <span data-th-text="${error}">Not Found</span>:
    <span data-th-text="${path}">/web/boards/999</span>
  </p>
  <p><a href="#" data-th-href="@{/web/boards}">게시글 목록으로 이동</a></p>

  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
</html>
```

**6) 실행하고 확인하기**

애플리케이션을 다시 실행한다.

1. [http://localhost:8080/web/boards](http://localhost:8080/web/boards) 에서 게시글 제목을 누르면 상세 화면(`/web/boards/{번호}`)으로 이동한다.
2. 상세 화면에 제목, 번호, 작성자, 내용이 출력된다.
3. [http://localhost:8080/web/boards/999](http://localhost:8080/web/boards/999) 처럼 없는 번호로 접속하면, Whitelabel Error Page 대신 직접 만든 **404 화면**이 출력된다.
4. [http://localhost:8080/web/nothing](http://localhost:8080/web/nothing) 처럼 없는 주소로 접속해도 같은 404 화면이 출력된다.

> REST API는 오류 화면의 영향을 받지 않는다. REST Client로 `GET {{baseUrl}}/boards/999`를 요청하면 5장과 같이 `404`만 응답된다.

### 실습-2: 등록 화면 만들기

**1) 폼 객체 만들기**

`board` 폴더에 `BoardForm.java` 파일을 만들고 다음과 같이 작성한다. (검증 애노테이션은 실습-3에서 추가한다.)

```java
package com.example.hello.board;

// 게시글 등록·수정 폼 객체
public class BoardForm {

  private String title;
  private String content;
  private String writer;

  public BoardForm() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }
}
```

> getter·setter는 VS Code에서 필드를 선택하고 마우스 오른쪽 버튼(또는 명령 팔레트) → `Source Action...` → `Generate Getters and Setters`로 자동 생성할 수 있다.

**2) 등록 폼 요청과 등록 처리 추가하기**

`BoardWebController.java`에 다음 두 메서드를 추가한다.

```java
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

  // 등록 폼: GET /web/boards/new
  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("boardForm", new BoardForm());      // 빈 폼 객체
    return "boards/form";
  }

  // 등록 처리: POST /web/boards
  @PostMapping
  public String create(@ModelAttribute("boardForm") BoardForm form,
                       RedirectAttributes redirectAttributes) {
    BoardResponse created = boardService.create(
        new BoardCreateRequest(form.getTitle(), form.getContent(), form.getWriter()));   // 폼 객체 → 서비스용 DTO
    redirectAttributes.addFlashAttribute("message", "게시글을 등록했습니다.");
    return "redirect:/web/boards/" + created.id();         // PRG: 상세 화면으로 리다이렉트
  }
```

> `GET /web/boards/new`와 `GET /web/boards/{id}`는 모두 `/web/boards/무엇` 형태이다. 5장에서 배운 것처럼 고정된 주소(`/new`)가 경로 변수(`/{id}`)보다 우선하므로, `/web/boards/new`는 `createForm()`이 처리한다.

**3) 폼 템플릿 만들기**

`templates/boards` 폴더에 `form.html` 파일을 만들고 다음과 같이 작성한다. (실습-3, 4에서 검증 오류 출력과 수정 기능을 추가한다.)

```html
<!DOCTYPE html>
<html lang="ko">
<head data-th-replace="~{fragments/layout :: head('게시글 등록')}">
  <meta charset="UTF-8">
  <title>게시글 등록</title>
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>

  <h2>게시글 등록</h2>

  <form method="post" action="#" data-th-action="@{/web/boards}" data-th-object="${boardForm}">
    <div class="field">
      <label for="title">제목</label>
      <input type="text" id="title" name="title" data-th-field="*{title}">
    </div>
    <div class="field">
      <label for="content">내용</label>
      <textarea id="content" name="content" rows="8" data-th-field="*{content}"></textarea>
    </div>
    <div class="field">
      <label for="writer">작성자</label>
      <input type="text" id="writer" name="writer" data-th-field="*{writer}">
    </div>
    <p>
      <button type="submit">등록</button>
      <a href="list.html" data-th-href="@{/web/boards}">취소</a>
    </p>
  </form>

  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
</html>
```

**4) 목록 화면에 글쓰기 링크와 메시지 추가하기**

`templates/boards/list.html`에서 `<h2>게시글 목록</h2>` 아래에 다음 내용을 추가한다.

```html
  <!-- 처리 결과 메시지 (삭제 후 리다이렉트되었을 때) -->
  <p class="message" data-th-if="${message}" data-th-text="${message}">메시지</p>

  <p><a href="form.html" data-th-href="@{/web/boards/new}">글쓰기</a></p>
```

**5) 폼 스타일 추가하기**

`static/css/app.css` 파일 아래에 다음 내용을 추가한다.

```css
.field { margin-bottom: 12px; }
.field label { display: block; font-weight: bold; }
.field input, .field textarea { width: 100%; max-width: 600px; padding: 4px; }
```

**6) 실행하고 확인하기**

애플리케이션을 다시 실행한다.

1. [http://localhost:8080/web/boards](http://localhost:8080/web/boards) 에서 `글쓰기`를 누르면 빈 등록 폼(`/web/boards/new`)이 나온다.
2. 제목, 내용, 작성자를 입력하고 `등록`을 누른다.
3. 주소가 `/web/boards/{새 번호}`로 바뀌면서 상세 화면이 나오고, 위쪽에 **게시글을 등록했습니다.** 메시지가 보인다.
4. 상세 화면에서 **새로 고침**(F5)을 누른다. 메시지는 사라지고, 게시글은 **다시 등록되지 않는다.** (목록에서 확인)

`페이지 소스 보기`로 등록 폼의 HTML을 확인하면, `data-th-action`은 `action="/web/boards"`로, `data-th-field`는 `id`, `name`, `value` 속성으로 바뀌어 있다.

**7) (확인) 리다이렉트하지 않으면**

PRG 패턴을 사용하지 않으면 어떤 문제가 생기는지 확인한다. `create()` 메서드를 잠시 다음과 같이 바꾸고 애플리케이션을 다시 실행한다.

```java
  @PostMapping
  public String create(@ModelAttribute("boardForm") BoardForm form, Model model) {
    BoardResponse created = boardService.create(
        new BoardCreateRequest(form.getTitle(), form.getContent(), form.getWriter()));
    model.addAttribute("board", created);
    return "boards/detail";                                // 리다이렉트하지 않고 화면을 바로 응답
  }
```

글을 등록하면 상세 화면이 나오지만, 주소는 `/web/boards` 그대로이다.
이 화면에서 새로 고침을 누르면 웹 브라우저가 "양식을 다시 제출할지" 묻는다. `계속`을 누르면 **같은 게시글이 한 번 더 등록된다.** (목록에서 확인)

확인한 후에는 `create()` 메서드를 원래대로(`RedirectAttributes`를 사용하는 코드) 되돌린다.

### 실습-3: 입력 값 검증하기

**1) 검증 스타터 추가하기**

`build.gradle`의 `dependencies` 블록에 다음 한 줄을 추가한다.

```groovy
  implementation 'org.springframework.boot:spring-boot-starter-validation'
```

**2) 폼 객체에 검증 규칙 추가하기**

`BoardForm.java`의 필드에 검증 애노테이션을 추가한다. 6장의 DDL(`VARCHAR(100)`, `VARCHAR(2000)`, `VARCHAR(50)`, `NOT NULL`)과 규칙을 맞춘다.

```java
package com.example.hello.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 게시글 등록·수정 폼 객체
public class BoardForm {

  @NotBlank(message = "제목을 입력하세요.")
  @Size(max = 100, message = "제목은 100자 이하로 입력하세요.")
  private String title;

  @NotBlank(message = "내용을 입력하세요.")
  @Size(max = 2000, message = "내용은 2000자 이하로 입력하세요.")
  private String content;

  @NotBlank(message = "작성자를 입력하세요.")
  @Size(max = 50, message = "작성자는 50자 이하로 입력하세요.")
  private String writer;

  ... (생성자와 getter·setter는 그대로 둔다.)
}
```

**3) 컨트롤러에서 검증하기**

`BoardWebController.java`의 `create()` 메서드를 다음과 같이 바꾼다.

```java
import jakarta.validation.Valid;

import org.springframework.validation.BindingResult;

  // 등록 처리: POST /web/boards
  @PostMapping
  public String create(@Valid @ModelAttribute("boardForm") BoardForm form,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "boards/form";                                // 오류가 있으면 폼 화면을 다시 보여 준다.
    }
    BoardResponse created = boardService.create(
        new BoardCreateRequest(form.getTitle(), form.getContent(), form.getWriter()));
    redirectAttributes.addFlashAttribute("message", "게시글을 등록했습니다.");
    return "redirect:/web/boards/" + created.id();
  }
```

**4) 폼에 오류 메시지 출력하기**

`templates/boards/form.html`의 세 입력 항목(`<div class="field">`)을 다음과 같이 바꾼다.

```html
    <div class="field">
      <label for="title">제목</label>
      <input type="text" id="title" name="title" data-th-field="*{title}" data-th-errorclass="field-error">
      <p class="error" data-th-if="${#fields.hasErrors('title')}" data-th-errors="*{title}">제목 오류</p>
    </div>
    <div class="field">
      <label for="content">내용</label>
      <textarea id="content" name="content" rows="8" data-th-field="*{content}" data-th-errorclass="field-error"></textarea>
      <p class="error" data-th-if="${#fields.hasErrors('content')}" data-th-errors="*{content}">내용 오류</p>
    </div>
    <div class="field">
      <label for="writer">작성자</label>
      <input type="text" id="writer" name="writer" data-th-field="*{writer}" data-th-errorclass="field-error">
      <p class="error" data-th-if="${#fields.hasErrors('writer')}" data-th-errors="*{writer}">작성자 오류</p>
    </div>
```

`static/css/app.css` 파일 아래에 다음 내용을 추가한다.

```css
.error { color: #c00; margin: 4px 0 0; }
.field-error { border: 2px solid #c00; }
```

**5) 실행하고 확인하기**

애플리케이션을 다시 실행하고 [http://localhost:8080/web/boards/new](http://localhost:8080/web/boards/new) 에 접속한다.

| 입력 | 결과 |
| --- | --- |
| 모든 항목을 비워 두고 등록 | 세 항목 아래에 "제목을 입력하세요." 등 오류 메시지가 출력되고, 입력 창이 빨간 테두리로 바뀐다. |
| 제목에 공백만 입력 | "제목을 입력하세요." (`@NotBlank`는 공백만 있는 문자열도 거부한다.) |
| 제목만 입력하고 등록 | 제목 입력 창에 **입력했던 값이 남아 있고**, 내용과 작성자에만 오류가 표시된다. |
| 제목에 101자 이상 입력 | "제목은 100자 이하로 입력하세요." |
| 모든 항목을 올바르게 입력 | 등록되고 상세 화면으로 이동한다. |

> 101자 이상의 제목은 다음 문장을 여러 번 붙여 넣어 만들 수 있다. `0123456789` (10자)

오류가 있을 때 주소가 `/web/boards`인 것을 확인한다. 오류가 있으면 리다이렉트하지 않고 **폼 화면을 바로 다시** 보여 주기 때문이다.

> 검증에 실패하면 서비스가 호출되지 않으므로, 데이터베이스에는 아무것도 저장되지 않는다.

### 실습-4: 수정 화면 만들기

**1) 폼 객체에 변환 메서드 추가하기**

수정 폼에는 기존 게시글의 값이 채워져 있어야 한다. `BoardForm.java`에 게시글(`BoardResponse`)로 폼 객체를 만드는 메서드를 추가한다.

```java
  // 게시글의 값으로 폼 객체를 만든다. (수정 폼에서 사용)
  public static BoardForm from(BoardResponse board) {
    BoardForm form = new BoardForm();
    form.setTitle(board.title());
    form.setContent(board.content());
    form.setWriter(board.writer());
    return form;
  }
```

**2) 수정 폼 요청과 수정 처리 추가하기**

`BoardWebController.java`에 다음 두 메서드를 추가한다.

```java
  // 수정 폼: GET /web/boards/{id}/edit
  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable Long id, Model model) {
    BoardResponse board = boardService.get(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    model.addAttribute("boardForm", BoardForm.from(board));   // 게시글 값이 채워진 폼 객체
    model.addAttribute("boardId", id);                        // 수정할 게시글 번호
    return "boards/form";
  }

  // 수정 처리: POST /web/boards/{id}/edit
  @PostMapping("/{id}/edit")
  public String edit(@PathVariable Long id,
                     @Valid @ModelAttribute("boardForm") BoardForm form,
                     BindingResult bindingResult,
                     Model model,
                     RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("boardId", id);                      // 폼을 다시 보여 줄 때도 게시글 번호가 필요하다.
      return "boards/form";
    }
    boardService.update(id, new BoardUpdateRequest(form.getTitle(), form.getContent()))   // 작성자는 사용하지 않는다.
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    redirectAttributes.addFlashAttribute("message", "게시글을 수정했습니다.");
    return "redirect:/web/boards/" + id;
  }
```

**3) 폼 템플릿이 수정 폼도 처리하도록 바꾸기**

`templates/boards/form.html`을 다음과 같이 바꾼다. 모델에 `boardId`가 있는지로 등록 폼과 수정 폼을 구분한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head data-th-replace="~{fragments/layout :: head(${boardId == null} ? '게시글 등록' : '게시글 수정')}">
  <meta charset="UTF-8">
  <title>게시글 등록·수정</title>
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>

  <h2 data-th-text="${boardId == null} ? '게시글 등록' : '게시글 수정'">게시글 등록</h2>

  <form method="post" action="#"
        data-th-action="${boardId == null} ? @{/web/boards} : @{/web/boards/{id}/edit(id=${boardId})}"
        data-th-object="${boardForm}">
    <div class="field">
      <label for="title">제목</label>
      <input type="text" id="title" name="title" data-th-field="*{title}" data-th-errorclass="field-error">
      <p class="error" data-th-if="${#fields.hasErrors('title')}" data-th-errors="*{title}">제목 오류</p>
    </div>
    <div class="field">
      <label for="content">내용</label>
      <textarea id="content" name="content" rows="8" data-th-field="*{content}" data-th-errorclass="field-error"></textarea>
      <p class="error" data-th-if="${#fields.hasErrors('content')}" data-th-errors="*{content}">내용 오류</p>
    </div>
    <div class="field">
      <label for="writer">작성자</label>
      <input type="text" id="writer" name="writer" data-th-field="*{writer}" data-th-errorclass="field-error"
             data-th-readonly="${boardId != null}">
      <p class="error" data-th-if="${#fields.hasErrors('writer')}" data-th-errors="*{writer}">작성자 오류</p>
    </div>
    <p>
      <button type="submit" data-th-text="${boardId == null} ? '등록' : '수정'">저장</button>
      <a href="list.html"
         data-th-href="${boardId == null} ? @{/web/boards} : @{/web/boards/{id}(id=${boardId})}">취소</a>
    </p>
  </form>

  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
</html>
```

| 부분 | 등록 폼 (`boardId`가 `null`) | 수정 폼 (`boardId`가 있음) |
| --- | --- | --- |
| 화면 제목 | 게시글 등록 | 게시글 수정 |
| 폼을 보낼 주소 | `/web/boards` | `/web/boards/{id}/edit` |
| 작성자 입력 창 | 입력 가능 | 읽기 전용 (`readonly`) |
| 버튼 | 등록 | 수정 |
| 취소 | 목록 화면 | 상세 화면 |

**4) 상세 화면에 수정 링크 추가하기**

`templates/boards/detail.html`의 `목록` 링크 옆에 수정 링크를 추가한다.

```html
  <p>
    <a href="list.html" data-th-href="@{/web/boards}">목록</a>
    <a href="form.html" data-th-href="@{/web/boards/{id}/edit(id=${board.id})}">수정</a>
  </p>
```

**5) 실행하고 확인하기**

애플리케이션을 다시 실행한다.

1. 게시글 상세 화면에서 `수정`을 누르면 기존 값이 채워진 수정 폼이 나온다. 화면 제목과 버튼이 **게시글 수정**, **수정**으로 바뀌고, 작성자는 바꿀 수 없다.
2. 제목과 내용을 바꾸고 `수정`을 누르면 상세 화면으로 이동하고 **게시글을 수정했습니다.** 메시지가 보인다.
3. 수정 폼에서 제목을 지우고 `수정`을 누르면 오류 메시지가 나오고, 폼을 보낼 주소는 그대로 `/web/boards/{id}/edit`이다. (`boardId`를 다시 모델에 담았기 때문이다.)
4. 수정 처리 후 실행 로그에서 `update board set ...` SQL을 확인한다. 8장의 서비스가 **변경 감지**로 수정했다.

### 실습-5: 삭제 기능 만들기

**1) 삭제 처리 추가하기**

`BoardWebController.java`에 다음 메서드를 추가한다.

```java
  // 삭제 처리: POST /web/boards/{id}/delete
  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    if (!boardService.delete(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    redirectAttributes.addFlashAttribute("message", "게시글을 삭제했습니다.");
    return "redirect:/web/boards";                           // 목록 화면으로 리다이렉트
  }
```

**2) 상세 화면에 삭제 버튼 추가하기**

`templates/boards/detail.html`의 링크(`<p>...</p>`) 아래에 다음 폼을 추가한다.

```html
  <form method="post" action="#" data-th-action="@{/web/boards/{id}/delete(id=${board.id})}"
        onsubmit="return confirm('이 게시글을 삭제할까요?');">
    <button type="submit">삭제</button>
  </form>
```

**3) 실행하고 확인하기**

애플리케이션을 다시 실행한다.

1. 게시글 상세 화면에서 `삭제`를 누르면 확인 창이 뜬다. `취소`를 누르면 아무 일도 일어나지 않는다.
2. 다시 `삭제`를 누르고 `확인`을 누르면 목록 화면으로 이동하고 **게시글을 삭제했습니다.** 메시지가 보인다. 삭제한 게시글은 목록에 없다.
3. 웹 브라우저의 `뒤로` 버튼으로 삭제한 게시글의 상세 화면 주소로 돌아간 후 새로 고침하면 404 화면이 나온다.

> 7장에서 댓글을 단 게시글(`sample-data.sql` 기준 1번)을 삭제하면, DDL의 `ON DELETE CASCADE`에 따라 댓글도 함께 삭제된다.

### 실습-6: 화면과 REST API 함께 확인하기

화면과 REST API가 **같은 서비스와 데이터베이스**를 사용한다는 것을 확인하고, 이번 장의 템플릿이 HTML5 표준에 맞는지 검사한다.

**1) API로 등록하고 화면에서 확인하기**

`http/board.http`에서 게시글 등록 요청(`POST {{baseUrl}}/boards`)을 보낸다.
[http://localhost:8080/web/boards](http://localhost:8080/web/boards) 를 새로 고침하면, API로 등록한 게시글이 목록 맨 위에 보인다.

**2) 화면에서 수정하고 API로 확인하기**

방금 등록한 게시글을 화면에서 수정한 후, REST Client로 `GET {{baseUrl}}/boards/{번호}`를 요청한다. 화면에서 수정한 제목과 내용이 JSON으로 응답된다.

```
[ BoardController (@RestController) ]    → JSON
            │
            ├──▶ [ BoardService ] ──▶ [ BoardJpaRepository ] ──▶ [ H2 ]
            │
[ BoardWebController (@Controller) ]     → HTML
```

**3) HTML5 표준 검사하기**

10장에서 사용한 W3C 검사기([https://validator.w3.org/nu/#textarea](https://validator.w3.org/nu/#textarea))에 이번 장에서 만든 템플릿(`detail.html`, `form.html`, `error/404.html`)의 내용을 붙여 넣어 검사한다. 모두 오류 없이 통과한다.

**4) 정리**

| 실습 | 확인한 내용 |
| --- | --- |
| 실습-1 | `@PathVariable`로 게시글을 조회하여 상세 화면을 만든다. 없는 게시글은 `ResponseStatusException`으로 404를 응답하고, `error/404.html`이 오류 화면으로 사용된다. |
| 실습-2 | `@ModelAttribute`로 폼 데이터를 폼 객체에 받고, `data-th-object`·`data-th-field`로 폼을 만든다. 등록 후 리다이렉트(PRG)하여 새로 고침해도 중복 등록되지 않는다. |
| 실습-3 | `@Valid`와 `BindingResult`로 입력 값을 검증하고, 오류가 있으면 입력 값과 오류 메시지를 담아 폼을 다시 보여 준다. |
| 실습-4 | 폼 템플릿 하나로 등록과 수정을 함께 처리한다. 수정 처리는 8장의 서비스가 변경 감지로 반영한다. |
| 실습-5 | HTML 폼은 `GET`/`POST`만 사용하므로 삭제도 `POST`로 처리한다. 처리 결과는 플래시 속성으로 전달한다. |
| 실습-6 | 화면과 REST API는 같은 서비스와 데이터베이스를 사용한다. |

이것으로 게시판의 **CRUD 화면**이 완성되었다. 12장부터는 Spring Security로 로그인한 사용자만 글을 쓰고, 작성자만 수정·삭제할 수 있도록 만든다.

