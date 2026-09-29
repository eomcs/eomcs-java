# 5장. REST API 구현

4장에서는 게시글 목록 조회와 등록 API를 만들었지만, 등록할 때 클라이언트가 보낸 데이터를 받지 못해 서버가 정한 제목으로만 게시글을 만들 수 있었다.
이번 장에서는 클라이언트가 요청에 담아 보낸 데이터를 받는 세 가지 방법인 `@PathVariable`, `@RequestParam`, `@RequestBody`를 배운다.
그리고 요청과 응답에 사용할 데이터를 담는 **DTO**를 만들고, JSON으로 데이터를 주고받으며 게시글의 등록·조회·변경·삭제(CRUD) REST API를 완성한다.

## 요청 데이터를 받는 방법

클라이언트는 HTTP 요청의 세 곳에 데이터를 담아 보낼 수 있다.

```
PUT /boards/3?notify=true HTTP/1.1          ← ① 경로(Path): /boards/3 의 "3"
Host: localhost:8080                        ← ② 쿼리 스트링(Query String): ?notify=true
Content-Type: application/json

{"title":"수정한 제목","content":"수정한 내용"}   ← ③ 본문(Body): JSON 데이터
```

스프링은 각 위치의 데이터를 꺼내는 애노테이션을 제공한다.

| 데이터 위치 | 예 | 꺼내는 애노테이션 | 주로 사용하는 곳 |
| --- | --- | --- | --- |
| ① 경로 | `/boards/3` | `@PathVariable` | **어떤 자원인지** 식별할 때 (게시글 번호) |
| ② 쿼리 스트링 | `/boards?keyword=스프링&page=2` | `@RequestParam` | 검색어, 정렬, 페이지처럼 **조회 조건**을 전달할 때 |
| ③ 본문 | `{"title":"제목", ...}` | `@RequestBody` | 등록·변경할 **데이터**를 전달할 때 |

## @PathVariable

### 경로에서 값 꺼내기

`@PathVariable`은 요청 **경로의 일부**를 메서드의 파라미터로 받는다.
매핑 주소에 `{변수이름}` 형식으로 값이 들어갈 자리를 표시하고, 같은 이름의 파라미터에 `@PathVariable`을 붙인다.

```java
@GetMapping("/{id}")                            // /boards/{id}
public Board get(@PathVariable Long id) {       // /boards/3 → id = 3
  ...
}
```

| 요청 | `id` 값 |
| --- | --- |
| `GET /boards/1` | `1` |
| `GET /boards/25` | `25` |

경로의 값은 원래 문자열이지만, 스프링이 파라미터 타입(`Long`)에 맞게 **자동으로 변환**해 준다.
변환할 수 없는 값이 오면(예: `GET /boards/abc`) 메서드를 실행하지 않고 `400 Bad Request`를 응답한다.

### 변수 이름과 파라미터 이름이 다를 때

`{}` 안의 이름과 파라미터 이름이 다르면, `@PathVariable`에 경로 변수의 이름을 지정한다.

```java
@GetMapping("/{boardId}/comments/{commentId}")
public Comment getComment(@PathVariable("boardId") Long id,
                          @PathVariable Long commentId) { ... }
```

경로 변수는 이처럼 여러 개를 사용할 수 있다.

> 스프링 부트 프로젝트는 컴파일할 때 파라미터 이름 정보를 함께 저장하도록 설정되어 있어서, 이름이 같으면 `@PathVariable`만 붙여도 된다.

### 고정 주소와 경로 변수

4장에서 만든 `GET /boards/count`와 `GET /boards/{id}`는 둘 다 `/boards/무엇` 형태이다.
`/boards/count`로 요청하면 어느 메서드가 실행될까?

스프링은 **고정된 주소**(`/boards/count`)를 **경로 변수가 있는 주소**(`/boards/{id}`)보다 우선한다.
그래서 `/boards/count`는 `count()` 메서드가, `/boards/3`은 `get()` 메서드가 처리한다.

## @RequestParam

### 쿼리 스트링에서 값 꺼내기

**쿼리 스트링**(Query String)은 주소 뒤에 `?`를 붙이고 `이름=값` 형식으로 데이터를 전달하는 방법이다. 여러 개는 `&`로 연결한다.

```
GET /boards?keyword=스프링&page=2&size=5
```

`@RequestParam`은 쿼리 스트링의 값을 메서드의 파라미터로 받는다.

```java
@GetMapping
public List<BoardSummary> list(@RequestParam String keyword,
                               @RequestParam int page,
                               @RequestParam int size) {
  ...
}
```

`@PathVariable`과 마찬가지로 파라미터 타입(`int` 등)에 맞게 자동으로 변환된다.

### 필수 여부와 기본값

`@RequestParam`으로 받는 값은 기본적으로 **필수**이다. 값을 보내지 않으면 `400 Bad Request`가 응답된다.
검색어나 페이지 번호처럼 **없어도 되는 값**은 다음 속성으로 처리한다.

| 속성 | 의미 | 예 |
| --- | --- | --- |
| `required = false` | 값이 없어도 된다. 값이 없으면 `null`이 들어간다. | `@RequestParam(required = false) String keyword` |
| `defaultValue = "값"` | 값이 없으면 지정한 기본값을 사용한다. (자동으로 필수가 아니게 된다.) | `@RequestParam(defaultValue = "1") int page` |
| `name = "이름"` | 쿼리 스트링의 이름과 파라미터 이름이 다를 때 지정한다. | `@RequestParam(name = "q") String keyword` |

```java
@GetMapping
public List<BoardSummary> list(
    @RequestParam(required = false) String keyword,     // 없으면 null
    @RequestParam(defaultValue = "1") int page,         // 없으면 1
    @RequestParam(defaultValue = "10") int size) {      // 없으면 10
  ...
}
```

| 요청 | `keyword` | `page` | `size` |
| --- | --- | --- | --- |
| `GET /boards` | `null` | `1` | `10` |
| `GET /boards?keyword=스프링` | `"스프링"` | `1` | `10` |
| `GET /boards?page=2&size=5` | `null` | `2` | `5` |

> `int` 같은 기본 타입은 `null`을 담을 수 없다. 기본 타입 파라미터에 `required = false`만 지정하고 값을 보내지 않으면 오류가 발생하므로, 기본 타입에는 `defaultValue`를 사용한다.

### @PathVariable과 @RequestParam 중 무엇을 사용할까?

| 구분 | `@PathVariable` | `@RequestParam` |
| --- | --- | --- |
| 용도 | **어떤 자원**인지 식별한다. | 자원을 **어떻게** 조회할지 조건을 준다. |
| 예 | `/boards/3` (3번 게시글) | `/boards?keyword=스프링&page=2` (게시글 중 검색·페이지) |
| 값이 없으면 | 다른 자원이 된다. (`/boards`는 목록) | 조건 없이 조회한다. |

> 한글이나 공백 같은 문자는 주소에 그대로 쓸 수 없어서 `%EC%8A%A4...`처럼 인코딩되어 전송된다(**URL 인코딩**).
> 웹 브라우저와 REST Client가 자동으로 인코딩하고, 스프링이 자동으로 원래 문자로 되돌려 주므로 개발자는 신경 쓰지 않아도 된다.

## JSON과 @RequestBody

### JSON 형식

**JSON**(JavaScript Object Notation)은 데이터를 주고받기 위한 텍스트 형식이다. REST API에서는 요청과 응답 본문에 주로 JSON을 사용한다.

```json
{
  "id": 1,
  "title": "첫 번째 게시글",
  "tags": ["공지", "스프링"],
  "pinned": false,
  "writer": { "name": "홍길동", "email": "hong@example.com" },
  "deletedAt": null
}
```

| 형식 | 표기 | 예 | Java 대응 |
| --- | --- | --- | --- |
| 객체 | `{ "이름": 값, ... }` | `{"id": 1}` | 객체, `record`, `Map` |
| 배열 | `[ 값, 값, ... ]` | `["공지", "스프링"]` | `List`, 배열 |
| 문자열 | 큰따옴표 `"` | `"첫 번째 게시글"` | `String` |
| 숫자 | 따옴표 없음 | `1`, `3.14` | `int`, `long`, `Long`, `double` 등 |
| 논리값 | `true`, `false` | `false` | `boolean` |
| 없음 | `null` | `null` | `null` |

> JSON의 이름은 반드시 큰따옴표로 감싸야 한다. 작은따옴표(`'`)를 쓰거나, 마지막 항목 뒤에 쉼표(`,`)를 붙이면 잘못된 JSON이 된다.

### 요청 본문을 객체로 받기

`@RequestBody`는 요청 **본문의 JSON**을 Java 객체로 변환하여 파라미터로 받는다.

```java
@PostMapping
public ResponseEntity<Board> create(@RequestBody BoardCreateRequest request) {
  ...
}
```

```http
POST /boards
Content-Type: application/json

{"title":"스프링 공부","content":"오늘은 REST API를 배웠다.","writer":"홍길동"}
```

JSON의 **이름**과 Java 객체의 **필드(record의 구성 요소) 이름**이 같은 것끼리 값이 채워진다.

| JSON의 이름 | `BoardCreateRequest`의 구성 요소 | 채워지는 값 |
| --- | --- | --- |
| `"title"` | `title` | `"스프링 공부"` |
| `"content"` | `content` | `"오늘은 REST API를 배웠다."` |
| `"writer"` | `writer` | `"홍길동"` |

- JSON에 **없는 항목**은 `null`(숫자 기본 타입은 `0`)이 된다.
- JSON 변환은 2장에서 본 Jackson 자동 구성이 처리한다. 응답할 때 객체를 JSON으로 바꾸는 것도 같은 Jackson이 한다.

### 요청 본문을 받을 때 발생하는 오류

| 상황 | 상태 코드 | 원인 |
| --- | --- | --- |
| `Content-Type` 헤더가 없거나 `application/json`이 아니다. | `415 Unsupported Media Type` | 서버가 본문을 어떤 형식으로 해석해야 할지 모른다. |
| 본문이 올바른 JSON 형식이 아니다. | `400 Bad Request` | 따옴표 누락, 쉼표 오류 등 |
| 본문이 없다. | `400 Bad Request` | `@RequestBody`는 기본적으로 본문이 필수이다. |
| 값의 타입이 맞지 않다. (예: 숫자 자리에 `"abc"`) | `400 Bad Request` | 변환할 수 없다. |

> JSON을 본문에 담아 보낼 때는 반드시 **`Content-Type: application/json`** 헤더를 함께 보내야 한다.

## DTO

### DTO란?

**DTO**(Data Transfer Object)는 계층이나 시스템 사이에서 **데이터를 전달하기 위해서만** 사용하는 객체이다.
REST API에서는 요청 본문을 받거나 응답 본문을 만들 때 DTO를 사용한다.

지금까지는 게시글 데이터를 `Board` 하나로 다뤘다. 그런데 기능마다 주고받는 데이터가 조금씩 다르다.

| 기능 | 필요한 데이터 | `Board`를 그대로 쓰면 |
| --- | --- | --- |
| 게시글 등록 요청 | 제목, 내용, 작성자 | 클라이언트가 게시글 번호(`id`)까지 보낼 수 있다. 번호는 서버가 정해야 한다. |
| 게시글 변경 요청 | 제목, 내용 | 작성자까지 바꿀 수 있게 된다. |
| 게시글 목록 응답 | 번호, 제목, 작성자 | 긴 내용(`content`)까지 목록에 모두 담겨 응답이 커진다. |

그래서 기능별로 필요한 데이터만 담은 DTO를 따로 만든다.

```java
// 등록 요청: 번호가 없다.
public record BoardCreateRequest(String title, String content, String writer) {}

// 변경 요청: 제목과 내용만 있다.
public record BoardUpdateRequest(String title, String content) {}

// 목록 응답: 내용이 없다.
public record BoardSummary(Long id, String title, String writer) {}
```

### DTO를 사용하는 이유

- **API가 받고 보내는 데이터를 정확하게 정의한다.** DTO만 보면 이 API에 무엇을 보내야 하고 무엇을 받는지 알 수 있다.
- **보내면 안 되는 데이터를 막는다.** 클라이언트가 게시글 번호나 작성자를 마음대로 바꾸지 못한다.
- **보여 주면 안 되는 데이터를 숨긴다.** 예를 들어 회원 정보를 응답할 때 비밀번호가 포함되지 않도록 한다.
- **내부 구조가 바뀌어도 API는 그대로 유지할 수 있다.** `Board`에 항목이 추가되어도 DTO를 바꾸지 않으면 API 형식은 바뀌지 않는다.

DTO의 이름은 보통 용도를 나타내도록 짓는다.

| 용도 | 이름 예 |
| --- | --- |
| 요청 | `BoardCreateRequest`, `BoardUpdateRequest` |
| 응답 | `BoardResponse`, `BoardSummary` |

> DTO는 값을 담아 전달하기만 하면 되므로, 이 교재에서는 Java의 `record`로 만든다.
> 8장에서 데이터베이스와 연결되는 엔티티(Entity) 클래스가 등장하면, 엔티티와 DTO를 분리하는 이유를 더 자세히 다룬다.

### 응답 DTO로 변환하기

`Board`를 `BoardSummary`로 바꾸는 코드는 여러 곳에서 사용하므로, DTO 안에 **변환 메서드**를 만들어 두면 편리하다.

```java
public record BoardSummary(Long id, String title, String writer) {

  public static BoardSummary from(Board board) {
    return new BoardSummary(board.id(), board.title(), board.writer());
  }
}
```

```java
List<BoardSummary> summaries = boards.stream()
    .map(BoardSummary::from)          // Board → BoardSummary
    .toList();
```

## CRUD 응답 다루기

### 상황에 맞는 상태 코드

CRUD API는 처리 결과에 따라 다음과 같이 응답한다.

| 기능 | 성공 | 대상이 없을 때 | 요청 데이터가 잘못되었을 때 |
| --- | --- | --- | --- |
| 목록 조회 `GET /boards` | `200` + 목록 | - | - |
| 한 개 조회 `GET /boards/{id}` | `200` + 게시글 | `404` | - |
| 등록 `POST /boards` | `201` + `Location` + 게시글 | - | `400` |
| 변경 `PUT /boards/{id}` | `200` + 변경된 게시글 | `404` | `400` |
| 삭제 `DELETE /boards/{id}` | `204` (본문 없음) | `404` | - |

상황에 따라 상태 코드가 달라지므로, 4장에서 배운 `ResponseEntity`를 사용한다.

```java
return ResponseEntity.ok(board);                  // 200 + 본문
return ResponseEntity.notFound().build();          // 404 (본문 없음)
return ResponseEntity.noContent().build();         // 204 (본문 없음)
return ResponseEntity.badRequest().build();        // 400 (본문 없음)
```

### Optional로 "있을 수도 없을 수도 있는" 결과 다루기

번호로 게시글을 찾으면 게시글이 **있을 수도 있고 없을 수도 있다.**
이런 결과는 `null` 대신 JDK의 `Optional`로 리턴하면, 사용하는 쪽에서 "없는 경우"를 빠뜨리지 않고 처리할 수 있다.

```java
// 저장소: 찾은 게시글을 Optional에 담아 리턴한다.
public Optional<Board> findById(Long id) {
  return Optional.ofNullable(boards.get(id));     // 있으면 값을 담고, 없으면 빈 Optional
}
```

컨트롤러에서는 `Optional`의 결과에 따라 `200` 또는 `404`로 응답한다.

```java
@GetMapping("/{id}")
public ResponseEntity<Board> get(@PathVariable Long id) {
  return boardService.get(id)
      .map(ResponseEntity::ok)                    // 값이 있으면: 200 + 게시글
      .orElse(ResponseEntity.notFound().build()); // 값이 없으면: 404
}
```

| `Optional` 메서드 | 의미 |
| --- | --- |
| `map(함수)` | 값이 있으면 함수를 적용한 결과를 담은 `Optional`을 리턴한다. 값이 없으면 빈 `Optional`을 그대로 리턴한다. |
| `orElse(값)` | 값이 있으면 그 값을, 없으면 지정한 값을 리턴한다. |
| `isPresent()` / `isEmpty()` | 값이 있는지 / 없는지 확인한다. |

> `ResponseEntity::ok`는 `board -> ResponseEntity.ok(board)`를 줄여 쓴 **메서드 참조**이다.

## 게시판 CRUD REST API

이번 장에서 완성할 게시판 API는 다음과 같다.

| 기능 | 메서드 | URI | 요청 데이터 | 성공 응답 |
| --- | --- | --- | --- | --- |
| 목록 조회 | `GET` | `/boards` | 쿼리 스트링: `keyword`, `page`, `size` (모두 선택) | `200` + `BoardSummary` 목록 |
| 개수 조회 | `GET` | `/boards/count` | - | `200` + `{"count": 개수}` |
| 한 개 조회 | `GET` | `/boards/{id}` | 경로: 게시글 번호 | `200` + `Board` |
| 등록 | `POST` | `/boards` | 본문: `BoardCreateRequest` | `201` + `Location` + `Board` |
| 변경 | `PUT` | `/boards/{id}` | 경로: 게시글 번호, 본문: `BoardUpdateRequest` | `200` + `Board` |
| 삭제 | `DELETE` | `/boards/{id}` | 경로: 게시글 번호 | `204` |

> 이처럼 API가 늘어나면 API의 주소, 파라미터, 응답을 정리한 **API 문서**가 필요하다.
> 컨트롤러 코드를 분석하여 API 문서를 자동으로 만드는 방법(springdoc-openapi, Swagger UI)은 8장에서 다룬다.

## 정리

- 요청 데이터는 위치에 따라 **경로**(`@PathVariable`), **쿼리 스트링**(`@RequestParam`), **본문**(`@RequestBody`)에서 꺼낸다.
- `@PathVariable`은 자원을 식별할 때, `@RequestParam`은 조회 조건을 전달할 때, `@RequestBody`는 등록·변경할 데이터를 전달할 때 사용한다.
- `@RequestParam`은 기본적으로 필수이며, `required = false`나 `defaultValue`로 선택 값을 만든다.
- JSON 본문은 Jackson이 Java 객체로 변환하며, 요청할 때 `Content-Type: application/json` 헤더가 필요하다.
- **DTO**는 기능별로 주고받는 데이터만 정확하게 담은 객체이다. 요청 DTO는 보내면 안 되는 데이터를 막고, 응답 DTO는 필요한 데이터만 보여 준다.
- 처리 결과에 따라 `ResponseEntity`로 `200`, `201`, `204`, `400`, `404` 등 알맞은 상태 코드를 응답한다.

## 실습

이번 장의 실습은 4장에서 사용한 `hello` 프로젝트의 `board` 패키지와 `http/board.http` 파일에서 이어서 진행한다.
실습을 마치면 `board` 패키지는 다음과 같이 구성된다.

```
com.example.hello.board
├── Board.java                  ← 게시글 (content 추가)
├── BoardRepository.java        ← 저장소 인터페이스 (메서드 추가)
├── MemoryBoardRepository.java  ← 메모리 저장소 (다시 작성)
├── BoardService.java           ← 서비스 (CRUD 메서드)
├── BoardController.java        ← 컨트롤러 (CRUD API)
├── BoardSummary.java           ← 응답 DTO: 목록용        (실습-3)
├── BoardCreateRequest.java     ← 요청 DTO: 등록용        (실습-4)
└── BoardUpdateRequest.java     ← 요청 DTO: 변경용        (실습-5)
```

### 실습-1: 게시판 코드 정리하기

CRUD API를 만들기 전에, 3장과 4장의 실습 코드를 정리하고 게시글에 **내용**(`content`) 항목을 추가한다.

**1) 3장 실습용 파일 삭제하기**

다음 파일은 이번 장에서 사용하지 않으므로 삭제한다. VS Code 탐색기에서 파일을 마우스 오른쪽 버튼으로 클릭하고 `Delete`를 선택한다.

- `board/ManualMain.java` (3장 실습-1, 2)
- `board/SampleBoardRepository.java` (3장 실습-5)

`HelloApplication.java`에 3장 실습-4의 출력 코드가 남아 있다면 1장의 원래 코드로 되돌린다.

```java
@SpringBootApplication
public class HelloApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloApplication.class, args);
  }

}
```

> `HelloApplication.java`에서 사용하지 않게 된 `import` 문(`ApplicationContext`, `BoardRepository`, `BoardService`)도 삭제한다.

**2) 게시글에 내용 추가하기**

`Board.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

public record Board(Long id, String title, String content, String writer) {
}
```

**3) 저장소 인터페이스에 메서드 추가하기**

CRUD에 필요한 메서드를 정의한다. `BoardRepository.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

  List<Board> findAll();                 // 전체 조회

  Optional<Board> findById(Long id);     // 번호로 한 개 조회 (없을 수도 있다)

  void save(Board board);                // 저장 (같은 번호가 있으면 덮어쓴다)

  boolean deleteById(Long id);           // 번호로 삭제 (삭제했으면 true)
}
```

**4) 메모리 저장소 다시 작성하기**

게시글을 번호로 찾고 바꾸고 지우기 쉽도록 `List` 대신 `Map`에 저장한다. `MemoryBoardRepository.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryBoardRepository implements BoardRepository {

  // 게시글 번호를 키로 하여 저장한다.
  // ConcurrentSkipListMap: 키(번호) 순서로 정렬해 보관하며, 여러 요청이 동시에 사용해도 안전하다.
  private final Map<Long, Board> boards = new ConcurrentSkipListMap<>();

  public MemoryBoardRepository() {
    boards.put(1L, new Board(1L, "첫 번째 게시글", "첫 번째 게시글의 내용입니다.", "홍길동"));
    boards.put(2L, new Board(2L, "두 번째 게시글", "두 번째 게시글의 내용입니다.", "임꺽정"));
  }

  @Override
  public List<Board> findAll() {
    return List.copyOf(boards.values());
  }

  @Override
  public Optional<Board> findById(Long id) {
    return Optional.ofNullable(boards.get(id));
  }

  @Override
  public void save(Board board) {
    boards.put(board.id(), board);
  }

  @Override
  public boolean deleteById(Long id) {
    return boards.remove(id) != null;
  }
}
```

> 3장 실습-5에서 붙인 `@Primary`는 이제 `BoardRepository`의 구현 클래스가 하나뿐이므로 필요 없다. 위 코드에서는 삭제했다.

**5) 서비스 정리하기**

4장에서 만든 `create()` 메서드는 실습-4에서 다시 만들기 위해 잠시 삭제하고, 게시글 한 개를 조회하는 `get()`과 개수를 세는 `count()`를 추가한다.
`BoardService.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class BoardService {

  private final BoardRepository boardRepository;

  // 게시글 번호 생성기 (샘플 게시글이 2번까지 있으므로 3번부터 발급한다.)
  private final AtomicLong sequence = new AtomicLong(2);

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public List<Board> list() {
    return boardRepository.findAll();
  }

  public int count() {
    return boardRepository.findAll().size();
  }

  public Optional<Board> get(Long id) {
    return boardRepository.findById(id);
  }
}
```

**6) 컨트롤러 정리하기**

4장에서 만든 `create()` 메서드를 잠시 삭제하고, `count()`가 서비스의 `count()`를 사용하도록 바꾼다.
`BoardController.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping
  public List<Board> list() {
    return boardService.list();
  }

  @GetMapping("/count")
  public Map<String, Integer> count() {
    return Map.of("count", boardService.count());
  }
}
```

**7) 요청 파일 정리하기**

`http/board.http`의 내용을 모두 지우고 다음과 같이 작성한다. 이번 장의 요청은 이 파일에 계속 추가한다.

```http
@baseUrl = http://localhost:8080

### 게시글 목록 조회
GET {{baseUrl}}/boards

### 게시글 개수 조회
GET {{baseUrl}}/boards/count
```

**8) 실행하고 확인하기**

애플리케이션을 실행하고 `GET {{baseUrl}}/boards`를 요청한다. 게시글에 `content`가 추가된 것을 확인한다.

```json
[
  {
    "id": 1,
    "title": "첫 번째 게시글",
    "content": "첫 번째 게시글의 내용입니다.",
    "writer": "홍길동"
  },
  {
    "id": 2,
    "title": "두 번째 게시글",
    "content": "두 번째 게시글의 내용입니다.",
    "writer": "임꺽정"
  }
]
```

> 실행할 때 컴파일 오류가 발생하면, 삭제해야 할 파일(`ManualMain.java`, `SampleBoardRepository.java`)이 남아 있지 않은지 확인한다.

### 실습-2: @PathVariable로 게시글 한 개 조회하기

`GET /boards/{id}` 요청으로 게시글 한 개를 조회하는 API를 만든다.

**1) 컨트롤러에 메서드 추가하기**

`BoardController.java`에 다음 메서드를 추가한다.

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

  @GetMapping("/{id}")                                     // GET /boards/{id}
  public ResponseEntity<Board> get(@PathVariable Long id) {
    return boardService.get(id)
        .map(ResponseEntity::ok)                           // 있으면 200 + 게시글
        .orElse(ResponseEntity.notFound().build());        // 없으면 404
  }
```

**2) 요청 추가하기**

`board.http`에 다음 요청을 추가한다.

```http
### 게시글 조회 - 있는 번호
GET {{baseUrl}}/boards/1

### 게시글 조회 - 없는 번호
GET {{baseUrl}}/boards/999

### 게시글 조회 - 숫자가 아닌 값
GET {{baseUrl}}/boards/abc
```

**3) 실행하고 확인하기**

애플리케이션을 다시 실행하고 세 요청을 차례로 보낸다.

| 요청 | 결과 | 설명 |
| --- | --- | --- |
| `GET /boards/1` | `200` + 1번 게시글 | 경로의 `1`이 `id` 파라미터에 들어갔다. |
| `GET /boards/999` | `404` (본문 없음) | 게시글이 없어서 `Optional`이 비어 있으므로 `notFound()`로 응답했다. |
| `GET /boards/abc` | `400` | `abc`를 `Long`으로 변환할 수 없어서 메서드가 실행되지 않았다. |

```
HTTP/1.1 200
Content-Type: application/json
...

{
  "id": 1,
  "title": "첫 번째 게시글",
  "content": "첫 번째 게시글의 내용입니다.",
  "writer": "홍길동"
}
```

`GET {{baseUrl}}/boards/count`도 다시 요청하여, `/boards/{id}`가 아니라 `count()` 메서드가 처리하는 것을 확인한다. (고정된 주소가 우선한다.)

### 실습-3: @RequestParam으로 검색하고 페이지 나누기

목록 조회 API에 **검색어**와 **페이지** 조건을 추가하고, 목록에는 내용(`content`)을 빼고 응답하도록 **응답 DTO**를 적용한다.

**1) 목록용 응답 DTO 만들기**

`BoardSummary.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

// 게시글 목록 응답용 DTO: 내용(content)은 담지 않는다.
public record BoardSummary(Long id, String title, String writer) {

  public static BoardSummary from(Board board) {
    return new BoardSummary(board.id(), board.title(), board.writer());
  }
}
```

**2) 서비스의 목록 조회 메서드 바꾸기**

`BoardService.java`의 `list()` 메서드를 다음과 같이 바꾼다.

```java
  // 검색어(keyword)가 제목에 포함된 게시글 중에서 page번째 페이지의 게시글을 size개 리턴한다.
  public List<Board> list(String keyword, int page, int size) {
    return boardRepository.findAll().stream()
        .filter(board -> keyword == null || board.title().contains(keyword))   // 검색
        .skip((long) (page - 1) * size)                                        // 앞 페이지 건너뛰기
        .limit(size)                                                           // size개만
        .toList();
  }
```

| 코드 | 의미 | 예: `page=2`, `size=5` |
| --- | --- | --- |
| `filter(...)` | 검색어가 없으면 모두, 있으면 제목에 검색어가 포함된 게시글만 남긴다. | |
| `skip(...)` | 앞 페이지의 게시글 수만큼 건너뛴다. | `(2 - 1) × 5 = 5`개를 건너뛴다. |
| `limit(size)` | `size`개만 가져온다. | 6~10번째 게시글 |

**3) 컨트롤러의 목록 조회 메서드 바꾸기**

`BoardController.java`의 `list()` 메서드를 다음과 같이 바꾼다.

```java
import org.springframework.web.bind.annotation.RequestParam;

  @GetMapping
  public List<BoardSummary> list(
      @RequestParam(required = false) String keyword,      // 검색어 (없으면 null)
      @RequestParam(defaultValue = "1") int page,          // 페이지 번호 (없으면 1)
      @RequestParam(defaultValue = "10") int size) {       // 페이지 크기 (없으면 10)
    return boardService.list(keyword, page, size).stream()
        .map(BoardSummary::from)                            // Board → BoardSummary
        .toList();
  }
```

**4) 요청 추가하기**

`board.http`에 다음 요청을 추가한다.

```http
### 게시글 목록 조회 - 검색
GET {{baseUrl}}/boards?keyword=첫

### 게시글 목록 조회 - 페이지
GET {{baseUrl}}/boards?page=2&size=1

### 게시글 목록 조회 - 숫자가 아닌 페이지 번호
GET {{baseUrl}}/boards?page=abc
```

**5) 실행하고 확인하기**

애플리케이션을 다시 실행하고 요청을 차례로 보낸다.

`GET {{baseUrl}}/boards` — 조건 없이 조회하면 기본값(`page=1`, `size=10`)이 적용된다. 목록에 `content`가 없는 것을 확인한다.

```json
[
  { "id": 1, "title": "첫 번째 게시글", "writer": "홍길동" },
  { "id": 2, "title": "두 번째 게시글", "writer": "임꺽정" }
]
```

`GET {{baseUrl}}/boards?keyword=첫` — 제목에 "첫"이 들어간 게시글만 조회된다.

```json
[
  { "id": 1, "title": "첫 번째 게시글", "writer": "홍길동" }
]
```

`GET {{baseUrl}}/boards?page=2&size=1` — 한 페이지에 1개씩 나눴을 때 2페이지, 즉 두 번째 게시글이 조회된다.

```json
[
  { "id": 2, "title": "두 번째 게시글", "writer": "임꺽정" }
]
```

`GET {{baseUrl}}/boards?page=abc` — `abc`를 `int`로 변환할 수 없으므로 `400`이 응답된다.

> **DTO의 효과**: `Board`에는 `content`가 있지만, 목록 API는 `BoardSummary`를 응답하므로 `content`가 빠졌다.
> 게시글 한 개 조회(`GET /boards/1`)는 여전히 `Board`를 응답하므로 `content`가 포함된다. 같은 데이터라도 API의 용도에 맞게 필요한 항목만 응답할 수 있다.

### 실습-4: @RequestBody로 게시글 등록하기

클라이언트가 JSON으로 보낸 제목, 내용, 작성자로 게시글을 등록하는 API를 만든다.

**1) 등록 요청 DTO 만들기**

`BoardCreateRequest.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

// 게시글 등록 요청용 DTO: 번호(id)는 서버가 정하므로 받지 않는다.
public record BoardCreateRequest(String title, String content, String writer) {
}
```

**2) 서비스에 등록 메서드 추가하기**

`BoardService.java`에 다음 메서드를 추가한다.

```java
  public Board create(BoardCreateRequest request) {
    Board board = new Board(
        sequence.incrementAndGet(),     // 서버가 새 번호를 발급한다.
        request.title(),
        request.content(),
        request.writer());
    boardRepository.save(board);
    return board;
  }
```

**3) 컨트롤러에 등록 메서드 추가하기**

`BoardController.java`에 다음 메서드를 추가한다.

```java
import java.net.URI;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

  @PostMapping                                                          // POST /boards
  public ResponseEntity<Board> create(@RequestBody BoardCreateRequest request) {
    // 제목이 없으면 400 Bad Request
    if (request.title() == null || request.title().isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    Board created = boardService.create(request);
    return ResponseEntity
        .created(URI.create("/boards/" + created.id()))                 // 201 + Location
        .body(created);
  }
```

> 입력 값을 검사하는 코드를 직접 작성했다. 여러 항목을 편리하게 검사하는 방법(Bean Validation)은 이후 다른 장에서 다룬다.

**4) 요청 추가하기**

`board.http`에 다음 요청을 추가한다. JSON 본문을 보낼 때는 헤더 다음에 **빈 줄**을 하나 두고 본문을 적는다.

```http
### 게시글 등록
POST {{baseUrl}}/boards
Content-Type: application/json

{
  "title": "스프링 공부",
  "content": "오늘은 REST API를 배웠다.",
  "writer": "유관순"
}

### 게시글 등록 - Content-Type 없음
POST {{baseUrl}}/boards

{
  "title": "스프링 공부",
  "content": "오늘은 REST API를 배웠다.",
  "writer": "유관순"
}

### 게시글 등록 - 잘못된 JSON (마지막 쉼표)
POST {{baseUrl}}/boards
Content-Type: application/json

{
  "title": "스프링 공부",
}

### 게시글 등록 - 제목 없음
POST {{baseUrl}}/boards
Content-Type: application/json

{
  "content": "제목을 빠뜨렸다.",
  "writer": "유관순"
}

### 게시글 등록 - 번호를 보내 보기
POST {{baseUrl}}/boards
Content-Type: application/json

{
  "id": 1,
  "title": "1번 게시글을 덮어쓸 수 있을까?",
  "content": "번호를 직접 보내 본다.",
  "writer": "해커"
}
```

**5) 실행하고 확인하기**

애플리케이션을 다시 실행하고 요청을 차례로 보낸다.

**게시글 등록** — `201`과 함께 등록된 게시글이 응답된다.

```
HTTP/1.1 201
Location: /boards/3
Content-Type: application/json
...

{
  "id": 3,
  "title": "스프링 공부",
  "content": "오늘은 REST API를 배웠다.",
  "writer": "유관순"
}
```

`GET {{baseUrl}}/boards/3`을 요청하여 등록된 게시글을 조회해 본다.

나머지 요청의 결과는 다음과 같다.

| 요청 | 결과 | 설명 |
| --- | --- | --- |
| Content-Type 없음 | `415` | 본문의 형식을 알 수 없어서 JSON으로 변환하지 않았다. |
| 잘못된 JSON | `400` | 마지막 항목 뒤의 쉼표 때문에 JSON을 해석할 수 없다. |
| 제목 없음 | `400` | JSON에 `title`이 없어서 `null`이 들어갔고, 컨트롤러의 검사 코드가 `badRequest()`로 응답했다. |
| 번호를 보내 보기 | `201` | 새 번호(예: `4`)로 등록된다. `BoardCreateRequest`에는 `id`가 없으므로 보낸 `id`는 **무시되었다**. |

`GET {{baseUrl}}/boards/1`을 요청하여 1번 게시글이 바뀌지 않았는지 확인한다.

> **DTO의 효과**: 등록 요청에 `Board`를 그대로 받았다면 클라이언트가 보낸 `id: 1`이 그대로 들어가 1번 게시글을 덮어쓸 수 있었다.
> `BoardCreateRequest`에는 `id`가 없으므로, 번호는 항상 서버가 정한다.

> (참고) JSON에 DTO에 없는 항목(`id`)이 있어도 오류 없이 무시되는 것은, 스프링 부트 4가 사용하는 Jackson 3의 기본 설정이 "모르는 항목은 무시한다"이기 때문이다.
> 모르는 항목이 있으면 `400`으로 응답하게 하려면 `application.properties`에 `spring.jackson.deserialization.fail-on-unknown-properties=true`를 설정한다.

### 실습-5: 게시글 변경과 삭제 API 만들기

`PUT /boards/{id}`로 게시글을 변경하고, `DELETE /boards/{id}`로 삭제하는 API를 만들어 CRUD를 완성한다.

**1) 변경 요청 DTO 만들기**

`BoardUpdateRequest.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

// 게시글 변경 요청용 DTO: 제목과 내용만 바꿀 수 있다.
public record BoardUpdateRequest(String title, String content) {
}
```

**2) 서비스에 변경·삭제 메서드 추가하기**

`BoardService.java`에 다음 메서드를 추가한다.

```java
  // 게시글이 있으면 변경하고 변경된 게시글을 리턴한다. 없으면 빈 Optional을 리턴한다.
  public Optional<Board> update(Long id, BoardUpdateRequest request) {
    return boardRepository.findById(id)
        .map(old -> {
          Board updated = new Board(old.id(), request.title(), request.content(), old.writer());
          boardRepository.save(updated);    // 같은 번호로 저장하면 덮어쓴다.
          return updated;
        });
  }

  // 삭제했으면 true, 게시글이 없으면 false를 리턴한다.
  public boolean delete(Long id) {
    return boardRepository.deleteById(id);
  }
```

> `record`는 값을 바꿀 수 없는(불변) 객체이다. 그래서 기존 게시글의 값을 바꾸는 대신, 바뀐 값으로 **새 `Board` 객체를 만들어** 같은 번호로 저장한다.
> 작성자(`writer`)는 기존 값(`old.writer()`)을 그대로 사용하므로 바뀌지 않는다.

**3) 컨트롤러에 변경·삭제 메서드 추가하기**

`BoardController.java`에 다음 메서드를 추가한다.

```java
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

  @PutMapping("/{id}")                                                  // PUT /boards/{id}
  public ResponseEntity<Board> update(@PathVariable Long id,
                                      @RequestBody BoardUpdateRequest request) {
    if (request.title() == null || request.title().isBlank()) {
      return ResponseEntity.badRequest().build();                       // 400
    }
    return boardService.update(id, request)
        .map(ResponseEntity::ok)                                        // 200 + 변경된 게시글
        .orElse(ResponseEntity.notFound().build());                     // 404
  }

  @DeleteMapping("/{id}")                                               // DELETE /boards/{id}
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (boardService.delete(id)) {
      return ResponseEntity.noContent().build();                        // 204
    }
    return ResponseEntity.notFound().build();                           // 404
  }
```

- `update()`는 **경로**에서 게시글 번호를, **본문**에서 변경할 데이터를 받는다. 한 메서드에서 `@PathVariable`과 `@RequestBody`를 함께 사용할 수 있다.
- `delete()`는 응답 본문이 없으므로 `ResponseEntity<Void>`로 선언한다.

**4) 요청 추가하기**

`board.http`에 다음 요청을 추가한다.

```http
### 게시글 변경
PUT {{baseUrl}}/boards/1
Content-Type: application/json

{
  "title": "첫 번째 게시글 (수정)",
  "content": "내용을 수정했습니다."
}

### 게시글 변경 - 없는 번호
PUT {{baseUrl}}/boards/999
Content-Type: application/json

{
  "title": "없는 게시글",
  "content": "변경할 수 없다."
}

### 게시글 삭제
DELETE {{baseUrl}}/boards/2

### 게시글 삭제 - 없는 번호
DELETE {{baseUrl}}/boards/999
```

**5) 실행하고 확인하기**

애플리케이션을 다시 실행하고 요청을 차례로 보낸다.

| 요청 | 결과 | 확인할 내용 |
| --- | --- | --- |
| 게시글 변경 | `200` + 변경된 게시글 | 제목과 내용이 바뀌고, 작성자(`홍길동`)는 그대로이다. |
| 게시글 변경 - 없는 번호 | `404` | |
| 게시글 삭제 | `204` (본문 없음) | |
| 게시글 삭제 - 없는 번호 | `404` | |

```
HTTP/1.1 200
Content-Type: application/json
...

{
  "id": 1,
  "title": "첫 번째 게시글 (수정)",
  "content": "내용을 수정했습니다.",
  "writer": "홍길동"
}
```

```
HTTP/1.1 204
Date: ...
```

마지막으로 `GET {{baseUrl}}/boards`를 요청하여 1번 게시글은 변경되고 2번 게시글은 삭제된 것을 확인한다.

> 같은 `DELETE /boards/2` 요청을 한 번 더 보내면 이미 삭제되었으므로 `404`가 응답된다.

### 실습-6: CRUD 시나리오 테스트하기

지금까지 만든 API를 실제 사용 순서대로 호출하여 CRUD 전체 흐름을 확인한다.
REST Client의 **요청 이름** 기능을 사용하면, 앞 요청의 응답 값을 다음 요청에서 사용할 수 있다.

**1) 시나리오 파일 만들기**

`http` 폴더에 `board-scenario.http` 파일을 만들고 다음과 같이 작성한다.

```http
@baseUrl = http://localhost:8080

### 1. 게시글 등록
# @name created
POST {{baseUrl}}/boards
Content-Type: application/json

{
  "title": "시나리오 테스트",
  "content": "CRUD 흐름을 확인한다.",
  "writer": "테스터"
}

### 2. 등록한 게시글 조회
GET {{baseUrl}}/boards/{{created.response.body.$.id}}

### 3. 등록한 게시글 변경
PUT {{baseUrl}}/boards/{{created.response.body.$.id}}
Content-Type: application/json

{
  "title": "시나리오 테스트 (수정)",
  "content": "변경을 확인한다."
}

### 4. 변경 결과 확인
GET {{baseUrl}}/boards/{{created.response.body.$.id}}

### 5. 등록한 게시글 삭제
DELETE {{baseUrl}}/boards/{{created.response.body.$.id}}

### 6. 삭제 결과 확인 (404가 응답되어야 한다)
GET {{baseUrl}}/boards/{{created.response.body.$.id}}
```

| 문법 | 의미 |
| --- | --- |
| `# @name created` | 바로 아래 요청에 `created`라는 이름을 붙인다. |
| `{{created.response.body.$.id}}` | `created` 요청의 응답 본문(JSON)에서 `id` 값을 꺼낸다. |

> `{{created.response.body.$.id}}`를 사용하는 요청은, `created` 요청(1번)을 먼저 보낸 후에 보내야 한다.

**2) 시나리오 실행하기**

애플리케이션을 실행하고 1번부터 6번까지 순서대로 요청을 보낸다. 각 요청의 상태 코드를 다음 표와 비교한다.

| 순서 | 요청 | 기대하는 상태 코드 |
| --- | --- | --- |
| 1 | `POST /boards` | `201` |
| 2 | `GET /boards/{id}` | `200` |
| 3 | `PUT /boards/{id}` | `200` |
| 4 | `GET /boards/{id}` | `200` (변경된 제목 확인) |
| 5 | `DELETE /boards/{id}` | `204` |
| 6 | `GET /boards/{id}` | `404` |

**3) 완성된 컨트롤러 코드**

이번 장에서 완성한 `BoardController.java`의 전체 코드는 다음과 같다.

```java
package com.example.hello.board;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  // 목록 조회: GET /boards?keyword=&page=&size=
  @GetMapping
  public List<BoardSummary> list(
      @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    return boardService.list(keyword, page, size).stream()
        .map(BoardSummary::from)
        .toList();
  }

  // 개수 조회: GET /boards/count
  @GetMapping("/count")
  public Map<String, Integer> count() {
    return Map.of("count", boardService.count());
  }

  // 한 개 조회: GET /boards/{id}
  @GetMapping("/{id}")
  public ResponseEntity<Board> get(@PathVariable Long id) {
    return boardService.get(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // 등록: POST /boards
  @PostMapping
  public ResponseEntity<Board> create(@RequestBody BoardCreateRequest request) {
    if (request.title() == null || request.title().isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    Board created = boardService.create(request);
    return ResponseEntity
        .created(URI.create("/boards/" + created.id()))
        .body(created);
  }

  // 변경: PUT /boards/{id}
  @PutMapping("/{id}")
  public ResponseEntity<Board> update(@PathVariable Long id,
                                      @RequestBody BoardUpdateRequest request) {
    if (request.title() == null || request.title().isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    return boardService.update(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // 삭제: DELETE /boards/{id}
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (boardService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
```

**4) 정리**

| 애노테이션 | 사용한 곳 | 받은 데이터 |
| --- | --- | --- |
| `@PathVariable` | `get()`, `update()`, `delete()` | 경로의 게시글 번호 |
| `@RequestParam` | `list()` | 쿼리 스트링의 검색어, 페이지 번호, 페이지 크기 |
| `@RequestBody` | `create()`, `update()` | 본문의 JSON (등록·변경할 데이터) |

지금은 게시글을 메모리에 저장하므로 애플리케이션을 다시 실행하면 데이터가 초기화된다.
6장부터는 JPA를 사용하여 게시글을 데이터베이스에 저장한다.

