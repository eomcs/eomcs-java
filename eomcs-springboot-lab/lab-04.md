# 4장. REST API 기초

지금까지 만든 `/hello`, `/boards`는 웹 브라우저로 접속하면 HTML 화면이 아니라 문자열이나 JSON 데이터를 응답했다.
이처럼 화면 대신 **데이터를 주고받기 위한 창구**를 **API**라고 하며, 오늘날 웹 API를 설계하는 가장 일반적인 방식이 **REST**이다.
이번 장에서는 웹 통신의 기반이 되는 HTTP의 구조, 메서드, 상태 코드를 이해하고, REST 방식으로 API를 설계하는 규칙을 배운다.
그리고 `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`을 사용하여 스프링으로 REST API를 만드는 방법을 익힌다.

## 웹 애플리케이션과 API

### 화면을 주는 서버, 데이터를 주는 서버

웹 서버가 응답하는 방식은 크게 두 가지로 나눌 수 있다.

| 구분 | 응답 내용 | 예 |
| --- | --- | --- |
| **화면을 응답하는 서버** | 완성된 HTML 화면 | 게시글 목록이 표로 그려진 HTML 페이지 |
| **데이터를 응답하는 서버** | 화면 없이 데이터만 (주로 JSON) | `[{"id":1,"title":"첫 번째 게시글"}, ...]` |

예전 웹 애플리케이션은 대부분 서버가 HTML 화면을 만들어 응답했다.
하지만 지금은 같은 서비스를 웹 브라우저뿐만 아니라 스마트폰 앱, 다른 회사의 서버 등 **다양한 클라이언트**가 사용한다.
스마트폰 앱은 HTML이 필요 없고 데이터만 있으면 자기 방식대로 화면을 그린다.
그래서 서버는 **데이터만 응답**하고, 화면은 각 클라이언트가 만드는 방식이 널리 사용된다.

```
                    ┌─▶ 웹 브라우저 (JavaScript로 화면 생성)
[ 서버 ]  ── JSON ──┼─▶ 스마트폰 앱 (앱 화면 생성)
                    └─▶ 다른 서버 (데이터 활용)
```

> 서버가 HTML 화면을 만들어 응답하는 방식은 이후의 다른 장(Spring MVC, Thymeleaf)에서 다룬다.

### API란?

**API**(Application Programming Interface)는 프로그램끼리 서로 기능이나 데이터를 주고받기 위해 정해 둔 **약속된 창구**이다.
식당의 메뉴판에 비유할 수 있다. 손님은 주방에서 어떻게 요리하는지 몰라도, 메뉴판에 적힌 방식대로 주문하면 음식을 받을 수 있다.
마찬가지로 클라이언트는 서버 내부가 어떻게 동작하는지 몰라도, 정해진 주소와 방식으로 요청하면 원하는 데이터를 받을 수 있다.

웹에서 HTTP를 이용해 주고받는 API를 **웹 API**라고 한다. 이번 장부터 만드는 게시판 API도 웹 API이다.

## HTTP 기초

### HTTP 요청과 응답

**HTTP**(HyperText Transfer Protocol)는 웹에서 클라이언트와 서버가 데이터를 주고받는 규칙이다.
HTTP 통신은 항상 클라이언트가 **요청**(Request)을 보내고, 서버가 **응답**(Response)을 돌려주는 방식으로 이루어진다.

HTTP 요청과 응답은 사람이 읽을 수 있는 **텍스트 메시지**이다. 1장의 `/hello`를 요청할 때 실제로 오가는 메시지는 다음과 같다.

**HTTP 요청 메시지**

```
GET /hello HTTP/1.1                ← ① 요청 줄: 메서드, 경로, HTTP 버전
Host: localhost:8080               ← ② 헤더: 요청에 대한 부가 정보
Accept: */*
                                   ← ③ 빈 줄: 헤더의 끝
                                   ← ④ 본문: 서버에 보낼 데이터 (GET 요청은 보통 없다)
```

**HTTP 응답 메시지**

```
HTTP/1.1 200                       ← ① 상태 줄: HTTP 버전, 상태 코드
Content-Type: text/plain;charset=UTF-8   ← ② 헤더: 응답에 대한 부가 정보
Content-Length: 19
                                   ← ③ 빈 줄: 헤더의 끝
Hello, Spring Boot!                ← ④ 본문: 실제 응답 데이터
```

| 구성 요소 | 요청 메시지 | 응답 메시지 |
| --- | --- | --- |
| 첫 줄 | **메서드**, 경로, HTTP 버전 | HTTP 버전, **상태 코드** |
| 헤더 | 요청 정보 (받고 싶은 데이터 형식 등) | 응답 정보 (본문의 데이터 형식 등) |
| 본문 | 서버에 보낼 데이터 | 클라이언트에 보낼 데이터 |

자주 보게 될 헤더는 다음과 같다.

| 헤더 | 의미 | 예 |
| --- | --- | --- |
| `Content-Type` | 본문 데이터의 형식 | `application/json`, `text/plain;charset=UTF-8` |
| `Accept` | 클라이언트가 받고 싶은 데이터 형식 | `application/json` |
| `Location` | 새로 만들어진 데이터의 주소 (등록 응답에서 사용) | `/boards/3` |

이번 장에서는 이 중 **메서드**와 **상태 코드**를 중심으로 살펴본다.

### HTTP 메서드

**HTTP 메서드**(Method)는 요청의 **목적**, 즉 서버에 무엇을 하려는지를 나타낸다.

| 메서드 | 의미 | 게시판 예 | 본문 |
| --- | --- | --- | --- |
| `GET` | 데이터를 **조회**한다. | 게시글 목록 조회, 게시글 한 개 조회 | 없음 |
| `POST` | 새 데이터를 **등록**한다. | 게시글 등록 | 등록할 데이터 |
| `PUT` | 데이터를 **전체 변경**한다. | 게시글 수정 (모든 항목) | 변경할 데이터 |
| `PATCH` | 데이터를 **일부 변경**한다. | 게시글 제목만 수정 | 변경할 항목 |
| `DELETE` | 데이터를 **삭제**한다. | 게시글 삭제 | 없음 |

데이터를 다루는 기본 작업인 등록(Create), 조회(Read), 변경(Update), 삭제(Delete)를 줄여서 **CRUD**라고 한다.
HTTP 메서드는 CRUD와 다음과 같이 대응한다.

```
Create → POST      Read → GET      Update → PUT / PATCH      Delete → DELETE
```

> **GET 요청은 서버의 데이터를 바꾸면 안 된다.**
> 웹 브라우저, 검색 엔진, 캐시 서버 등은 GET 요청을 "조회만 하는 안전한 요청"으로 여기고 자유롭게 반복하거나 미리 보낸다.
> 만약 `GET /boards/delete`처럼 GET 요청으로 데이터를 삭제하게 만들면, 검색 엔진이 링크를 방문하기만 해도 데이터가 지워질 수 있다.

> 웹 브라우저의 주소창에 주소를 입력하면 항상 **GET** 요청이 보내진다. 그래서 POST, PUT, DELETE 요청을 보내려면 별도의 도구가 필요하다. (실습-1에서 준비한다.)

### HTTP 상태 코드

**상태 코드**(Status Code)는 서버가 요청을 **어떻게 처리했는지** 알려 주는 세 자리 숫자이다.
첫 번째 자리 숫자로 결과의 종류를 구분한다.

| 범위 | 분류 | 의미 |
| --- | --- | --- |
| `1xx` | 정보 | 요청을 받았고 처리를 계속하고 있다. (거의 볼 일이 없다.) |
| `2xx` | **성공** | 요청을 정상적으로 처리했다. |
| `3xx` | 리다이렉션 | 요청을 완료하려면 다른 주소로 다시 요청해야 한다. |
| `4xx` | **클라이언트 오류** | 요청이 잘못되었다. 클라이언트가 요청을 고쳐야 한다. |
| `5xx` | **서버 오류** | 요청은 올바르지만 서버가 처리하다 실패했다. |

REST API에서 자주 사용하는 상태 코드는 다음과 같다.

| 코드 | 이름 | 사용하는 경우 |
| --- | --- | --- |
| `200` | OK | 요청 성공. 조회, 변경 등 대부분의 성공 응답 |
| `201` | Created | 새 데이터가 **등록**되었다. (POST 성공) |
| `204` | No Content | 요청은 성공했지만 응답 본문이 없다. (삭제 성공 등) |
| `400` | Bad Request | 요청 데이터의 형식이나 값이 잘못되었다. |
| `401` | Unauthorized | 로그인(인증)이 필요하다. |
| `403` | Forbidden | 로그인했지만 권한이 없다. |
| `404` | Not Found | 요청한 주소나 데이터가 없다. |
| `405` | Method Not Allowed | 주소는 있지만 그 메서드는 지원하지 않는다. |
| `415` | Unsupported Media Type | 서버가 처리할 수 없는 형식의 데이터를 보냈다. |
| `500` | Internal Server Error | 서버에서 예상하지 못한 오류가 발생했다. |

> 상태 코드만 보고도 클라이언트는 요청이 성공했는지, 실패했다면 **누구의 잘못인지**(4xx는 클라이언트, 5xx는 서버) 알 수 있다.
> 그래서 API를 만들 때는 상황에 맞는 상태 코드를 정확하게 응답해야 한다.

## REST

### REST란?

**REST**(REpresentational State Transfer)는 웹 API를 설계하는 방식(아키텍처 스타일)이다.
2000년에 HTTP 설계에 참여했던 로이 필딩(Roy Fielding)이 박사 학위 논문에서 제안했다.
REST 방식을 따르는 API를 **REST API** 또는 **RESTful API**라고 한다.

REST의 핵심 아이디어는 "**모든 것을 자원(Resource)으로 보고, HTTP를 원래 설계된 대로 사용하자**"이다. REST API는 다음 세 가지로 구성된다.

| 구성 요소 | 의미 | 게시판 예 |
| --- | --- | --- |
| **자원**(Resource) | 다루는 대상. **URI**(주소)로 식별한다. | `/boards` (게시글 전체), `/boards/1` (1번 게시글) |
| **행위**(Verb) | 자원에 대해 할 일. **HTTP 메서드**로 표현한다. | `GET`(조회), `POST`(등록), `DELETE`(삭제) |
| **표현**(Representation) | 자원의 상태를 담아 주고받는 데이터. 주로 **JSON**을 사용한다. | `{"id":1,"title":"첫 번째 게시글","writer":"홍길동"}` |

즉, REST API는 **"무엇을"은 URI로, "어떻게"는 HTTP 메서드로** 표현한다.

```
GET     /boards      →  게시글 목록을 조회한다.
POST    /boards      →  게시글을 등록한다.
GET     /boards/1    →  1번 게시글을 조회한다.
DELETE  /boards/1    →  1번 게시글을 삭제한다.
```

같은 `/boards`라는 주소라도 메서드가 `GET`이면 조회, `POST`면 등록이 된다.

### REST API 설계 규칙

REST API의 주소를 설계할 때는 다음 규칙을 따른다.

**1) URI는 자원을 나타내는 명사로 작성한다. 행위는 HTTP 메서드로 표현한다.**

| 잘못된 예 | 올바른 예 |
| --- | --- |
| `GET /getBoards` | `GET /boards` |
| `POST /boards/create` | `POST /boards` |
| `GET /boards/delete?id=1` | `DELETE /boards/1` |

URI에 `get`, `create`, `delete` 같은 동사가 들어가면 안 된다. 동사의 역할은 HTTP 메서드가 한다.

**2) 자원의 모음(컬렉션)은 복수형 명사를 사용한다.**

`/board`보다 `/boards`처럼 복수형을 사용한다. 특정 자원 하나는 컬렉션 뒤에 식별자(id)를 붙여 `/boards/1`처럼 나타낸다.

**3) 자원 사이의 관계는 `/`로 계층을 표현한다.**

`GET /boards/1/comments` → 1번 게시글의 댓글 목록

**4) 소문자를 사용하고, 단어를 이어야 하면 하이픈(`-`)을 사용한다.**

| 잘못된 예 | 올바른 예 |
| --- | --- |
| `/Boards`, `/boardComments`, `/board_comments` | `/boards`, `/board-comments` |

**5) 주소 끝에 `/`나 파일 확장자를 붙이지 않는다.**

| 잘못된 예 | 올바른 예 |
| --- | --- |
| `/boards/`, `/boards.json` | `/boards` |

데이터 형식은 확장자가 아니라 `Content-Type`, `Accept` 헤더로 주고받는다.

### 게시판 REST API 설계

위 규칙에 따라 게시판의 CRUD API를 설계하면 다음과 같다.

| 기능 | 메서드 | URI | 성공 시 상태 코드 |
| --- | --- | --- | --- |
| 게시글 목록 조회 | `GET` | `/boards` | `200 OK` |
| 게시글 등록 | `POST` | `/boards` | `201 Created` |
| 게시글 한 개 조회 | `GET` | `/boards/{id}` | `200 OK` |
| 게시글 변경 | `PUT` | `/boards/{id}` | `200 OK` |
| 게시글 삭제 | `DELETE` | `/boards/{id}` | `204 No Content` |

`{id}`는 게시글 번호가 들어갈 자리이다. 예를 들어 3번 게시글을 조회하려면 `GET /boards/3`으로 요청한다.

이번 장에서는 게시글 **목록 조회**와 **등록**을 만든다.
주소에서 `{id}` 값을 꺼내거나, 요청 본문으로 보낸 JSON 데이터를 받는 방법은 다음 장에서 배운다.

## 스프링으로 REST API 만들기

### @RestController

REST API를 처리하는 클래스에는 `@RestController`를 붙인다.

```java
@RestController
public class BoardController { ... }
```

`@RestController`는 다음 두 애노테이션을 합친 것이다.

| 애노테이션 | 역할 |
| --- | --- |
| `@Controller` | 이 클래스가 웹 요청을 처리하는 컨트롤러임을 표시하고, 빈으로 등록한다. (3장의 스테레오타입 애노테이션) |
| `@ResponseBody` | 메서드의 리턴 값을 화면(HTML)으로 바꾸지 않고, **응답 본문에 그대로** 넣는다. |

메서드의 리턴 값은 타입에 따라 다음과 같이 응답 본문으로 바뀐다.

| 리턴 타입 | 응답 본문 | `Content-Type` |
| --- | --- | --- |
| `String` | 문자열 그대로 | `text/plain;charset=UTF-8` |
| 객체, `List`, `Map`, `record` 등 | **JSON**으로 변환 | `application/json` |

객체를 JSON으로 바꾸는 일은 2장에서 본 Jackson 자동 구성이 처리한다.

> `@Controller`만 붙이면 메서드의 리턴 값을 **화면(뷰)의 이름**으로 해석한다. 이 방식은 이후의 다른 장에서 다룬다.

### @RequestMapping

`@RequestMapping`은 요청 주소(와 메서드)를 처리할 클래스나 메서드에 연결한다. 이를 **요청 매핑**(Request Mapping)이라고 한다.

`@RequestMapping`을 **클래스**에 붙이면, 그 클래스의 모든 메서드에 공통으로 적용되는 주소가 된다.

```java
@RestController
@RequestMapping("/boards")                          // 이 클래스의 공통 주소
public class BoardController {

  @RequestMapping(method = RequestMethod.GET)       // GET /boards
  public List<Board> list() { ... }

  @RequestMapping(path = "/count", method = RequestMethod.GET)   // GET /boards/count
  public Map<String, Integer> count() { ... }
}
```

클래스의 주소(`/boards`)와 메서드의 주소(`/count`)가 합쳐져 최종 주소(`/boards/count`)가 된다.
메서드에 주소를 지정하지 않으면 클래스의 주소가 그대로 사용된다.

| 속성 | 의미 |
| --- | --- |
| `path` (또는 `value`) | 요청 주소. 속성 이름 없이 `@RequestMapping("/boards")`처럼 쓰면 `path`로 인식한다. |
| `method` | 처리할 HTTP 메서드. 지정하지 않으면 **모든 메서드**를 처리한다. |

> 메서드에 붙인 `@RequestMapping`에 `method`를 빠뜨리면 GET, POST, DELETE 등 모든 요청이 그 메서드로 들어온다.
> 이런 실수를 막기 위해, 메서드에는 다음 절의 `@GetMapping`, `@PostMapping`을 사용하는 것이 일반적이다.

### @GetMapping, @PostMapping

`@GetMapping`은 `@RequestMapping(method = RequestMethod.GET)`을 **줄인 것**이다. 다른 메서드도 같은 방식의 애노테이션이 있다.

| 애노테이션 | 같은 의미 | 용도 |
| --- | --- | --- |
| `@GetMapping` | `@RequestMapping(method = RequestMethod.GET)` | 조회 |
| `@PostMapping` | `@RequestMapping(method = RequestMethod.POST)` | 등록 |
| `@PutMapping` | `@RequestMapping(method = RequestMethod.PUT)` | 전체 변경 |
| `@PatchMapping` | `@RequestMapping(method = RequestMethod.PATCH)` | 일부 변경 |
| `@DeleteMapping` | `@RequestMapping(method = RequestMethod.DELETE)` | 삭제 |

앞의 예를 바꾸면 다음과 같이 짧고 읽기 쉬워진다.

```java
@RestController
@RequestMapping("/boards")          // 클래스: 공통 주소
public class BoardController {

  @GetMapping                       // GET /boards
  public List<Board> list() { ... }

  @GetMapping("/count")             // GET /boards/count
  public Map<String, Integer> count() { ... }

  @PostMapping                      // POST /boards
  public ResponseEntity<Board> create() { ... }
}
```

일반적으로 **클래스에는 `@RequestMapping`으로 공통 주소**를, **메서드에는 `@GetMapping`, `@PostMapping` 등으로 메서드와 세부 주소**를 지정한다.

### 응답 상태 코드 지정하기

컨트롤러 메서드가 정상적으로 값을 리턴하면 상태 코드는 기본으로 `200 OK`가 된다.
등록 성공(`201 Created`)처럼 다른 상태 코드를 응답하려면 다음 두 방법 중 하나를 사용한다.

**방법 1: `@ResponseStatus`**

메서드에 `@ResponseStatus`를 붙여 성공했을 때의 상태 코드를 지정한다.

```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED)          // 성공하면 201
public Board create() {
  ...
  return created;
}
```

**방법 2: `ResponseEntity`**

`ResponseEntity`는 응답의 **상태 코드, 헤더, 본문**을 모두 담는 객체이다. 이 객체를 리턴하면 응답 메시지 전체를 코드로 정할 수 있다.

```java
@PostMapping
public ResponseEntity<Board> create() {
  ...
  return ResponseEntity
      .created(URI.create("/boards/" + created.id()))   // 상태 코드 201 + Location 헤더
      .body(created);                                   // 응답 본문
}
```

`ResponseEntity`는 상황에 맞는 응답을 만드는 메서드를 제공한다.

| 메서드 | 만들어지는 응답 |
| --- | --- |
| `ResponseEntity.ok(본문)` | `200 OK` + 본문 |
| `ResponseEntity.created(주소).body(본문)` | `201 Created` + `Location` 헤더 + 본문 |
| `ResponseEntity.noContent().build()` | `204 No Content` (본문 없음) |
| `ResponseEntity.notFound().build()` | `404 Not Found` (본문 없음) |
| `ResponseEntity.status(상태 코드).body(본문)` | 지정한 상태 코드 + 본문 |

REST에서는 새 데이터를 등록하면 `201 Created`와 함께 **새 데이터의 주소를 `Location` 헤더**에 담아 응답하는 것이 관례이다.
헤더까지 지정해야 하므로, 등록 API에는 보통 `ResponseEntity`를 사용한다.

| 비교 | `@ResponseStatus` | `ResponseEntity` |
| --- | --- | --- |
| 상태 코드 | 항상 같은 코드 | 상황에 따라 다른 코드를 선택할 수 있다. |
| 헤더 지정 | 불가능 | 가능 (`Location` 등) |
| 코드 길이 | 짧다. | 조금 길다. |

### 스프링이 자동으로 응답하는 상태 코드

다음과 같은 경우에는 컨트롤러 코드가 실행되기 전에 스프링이 알아서 오류 상태 코드를 응답한다.

| 상황 | 상태 코드 | 예 |
| --- | --- | --- |
| 요청 주소를 처리하는 메서드가 없다. | `404 Not Found` | `GET /nothing` |
| 주소는 있지만 요청한 HTTP 메서드를 처리하는 메서드가 없다. | `405 Method Not Allowed` | `DELETE /boards` (아직 만들지 않음) |
| 컨트롤러 메서드에서 처리하지 않은 예외가 발생했다. | `500 Internal Server Error` | `NullPointerException` 발생 |

이때 스프링 부트는 다음과 같은 JSON 형식의 오류 정보를 응답 본문에 담는다. (웹 브라우저로 요청하면 2장에서 본 Whitelabel Error Page가 출력된다.)

```json
{
  "timestamp": "2026-xx-xxTxx:xx:xx.xxx+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/nothing"
}
```

## 정리

- **API**는 프로그램끼리 데이터를 주고받는 약속된 창구이며, 웹 API는 주로 **JSON** 데이터를 주고받는다.
- **HTTP** 요청은 메서드·경로·헤더·본문으로, 응답은 상태 코드·헤더·본문으로 구성된다.
- **HTTP 메서드**는 요청의 목적(GET 조회, POST 등록, PUT/PATCH 변경, DELETE 삭제)을, **상태 코드**는 처리 결과(2xx 성공, 4xx 클라이언트 오류, 5xx 서버 오류)를 나타낸다.
- **REST**는 자원을 **URI**로, 행위를 **HTTP 메서드**로, 데이터를 **JSON**으로 표현하는 API 설계 방식이다.
- 스프링에서는 `@RestController`로 컨트롤러를 만들고, 클래스에는 `@RequestMapping`으로 공통 주소를, 메서드에는 `@GetMapping`, `@PostMapping`으로 메서드와 세부 주소를 지정한다.
- 기본 상태 코드는 `200`이며, 다른 상태 코드는 `@ResponseStatus`나 `ResponseEntity`로 지정한다.

## 실습

이번 장의 실습은 3장에서 사용한 `hello` 프로젝트의 `board` 패키지에서 이어서 진행한다.

> 3장의 실습-4에서 `HelloApplication.java`에 추가한 빈 정보 출력 코드는 이번 장에서 필요 없다.
> 실행 로그를 간단하게 보려면 `main()` 메서드를 1장의 원래 코드(`SpringApplication.run(HelloApplication.class, args);` 한 줄)로 되돌려도 된다.

### 실습-1: HTTP 요청 도구 준비하기

웹 브라우저의 주소창으로는 GET 요청만 보낼 수 있고, 응답의 상태 코드와 헤더도 보기 어렵다.
VS Code의 **REST Client** 확장 프로그램을 설치하여 원하는 메서드로 요청을 보내고 응답 메시지 전체를 확인할 수 있도록 준비한다.

**1) REST Client 확장 프로그램 설치하기**

VS Code의 확장(Extensions) 화면에서 `REST Client`를 검색하고, 게시자가 **Huachao Mao**인 항목을 설치한다.
또는 터미널에서 다음 명령을 실행한다.

```bash
code --install-extension humao.rest-client
```

**2) 요청 파일 만들기**

`hello` 프로젝트 폴더(`build.gradle`이 있는 폴더)에 `http` 폴더를 만들고, 그 안에 `board.http` 파일을 만든다.
REST Client는 확장자가 `.http`인 파일에 적은 요청을 보낼 수 있다. 다음과 같이 작성한다.

```http
@baseUrl = http://localhost:8080

### 인사말 요청
GET {{baseUrl}}/hello
```

| 문법 | 의미 |
| --- | --- |
| `@이름 = 값` | 변수를 정의한다. 이후 `{{이름}}`으로 사용한다. |
| `###` | 요청과 요청을 구분한다. `###` 뒤에 적은 글은 설명(주석)이다. |
| `메서드 주소` | 보낼 요청의 첫 줄이다. |

**3) 요청 보내기**

애플리케이션을 실행한다.

```bash
# macOS
./gradlew bootRun
```

```powershell
# Windows (PowerShell)
.\gradlew bootRun
```

`board.http` 파일에서 `GET {{baseUrl}}/hello` 줄 위에 표시되는 **`Send Request`** 링크를 클릭한다.
오른쪽에 새 창이 열리고 응답 메시지 전체가 표시된다.

```
HTTP/1.1 200
Content-Type: text/plain;charset=UTF-8
Content-Length: 19
Date: ...
Connection: close

Hello, Spring Boot!
```

상태 줄(`200`), 헤더(`Content-Type` 등), 빈 줄, 본문(`Hello, Spring Boot!`)으로 이루어진 응답 메시지를 확인한다.

> 헤더의 종류와 순서는 환경에 따라 조금 다를 수 있다.
> 내장 Tomcat은 상태 줄에 `OK` 같은 설명 문구를 붙이지 않고 `HTTP/1.1 200`처럼 숫자만 보낸다.

**4) (참고) curl로 요청 보내기**

터미널에서 `curl` 명령으로도 요청을 보낼 수 있다. `-i` 옵션을 붙이면 응답 헤더까지 출력된다.

```bash
# macOS
curl -i http://localhost:8080/hello
```

```powershell
# Windows (PowerShell)
curl.exe -i http://localhost:8080/hello
```

`-X` 옵션으로 메서드를 지정할 수 있다. (예: `curl -i -X POST http://localhost:8080/boards`)

> 이 교재에서는 이후 실습에서 REST Client를 사용한다. curl이 익숙하다면 curl을 사용해도 된다.

### 실습-2: HTTP 응답과 상태 코드 살펴보기

여러 가지 요청을 보내 보고, 응답의 `Content-Type`과 상태 코드를 확인한다.

**1) 요청 추가하기**

`board.http` 파일 아래에 다음 요청을 추가한다.

```http
### 게시글 목록 조회
GET {{baseUrl}}/boards

### 없는 주소 요청
GET {{baseUrl}}/nothing

### 지원하지 않는 메서드 요청
DELETE {{baseUrl}}/boards
```

**2) 게시글 목록 조회**

`GET {{baseUrl}}/boards`의 `Send Request`를 클릭한다.

```
HTTP/1.1 200
Content-Type: application/json
...

[
  {
    "id": 1,
    "title": "첫 번째 게시글",
    "writer": "홍길동"
  },
  {
    "id": 2,
    "title": "두 번째 게시글",
    "writer": "임꺽정"
  }
]
```

- 상태 코드는 `200`이다.
- `/hello`는 `String`을 리턴하므로 `text/plain`이었지만, `/boards`는 `List<Board>`를 리턴하므로 `Content-Type`이 `application/json`이다.

> REST Client는 JSON 본문을 보기 좋게 정리하여 보여 준다. 실제로 전송된 본문은 한 줄로 이어진 JSON이다.

**3) 없는 주소 요청**

`GET {{baseUrl}}/nothing`의 `Send Request`를 클릭한다.

```
HTTP/1.1 404
Content-Type: application/json
...

{
  "timestamp": "2026-xx-xxTxx:xx:xx.xxx+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/nothing"
}
```

`/nothing`을 처리하는 메서드가 없으므로 스프링이 `404 Not Found`를 응답했다.
웹 브라우저로 요청했을 때는 Whitelabel Error Page(HTML)가 나왔지만, REST Client로 요청하면 **JSON**으로 오류 정보가 온다.
스프링 부트가 요청의 `Accept` 헤더를 보고 클라이언트에 맞는 형식으로 응답하기 때문이다.

**4) 지원하지 않는 메서드 요청**

`DELETE {{baseUrl}}/boards`의 `Send Request`를 클릭한다.

```
HTTP/1.1 405
Allow: GET
Content-Type: application/json
...

{
  "timestamp": "...",
  "status": 405,
  "error": "Method Not Allowed",
  "path": "/boards"
}
```

`/boards` 주소는 있지만 `DELETE` 메서드를 처리하는 메서드가 없으므로 `405 Method Not Allowed`가 응답되었다.
`Allow` 헤더에는 이 주소가 지원하는 메서드(`GET`)가 표시된다. (헤더의 형식은 버전에 따라 조금 다를 수 있다.)

**5) 정리**

| 요청 | 상태 코드 | 이유 |
| --- | --- | --- |
| `GET /boards` | `200` | 정상 처리 |
| `GET /nothing` | `404` | 주소를 처리하는 메서드가 없다. |
| `DELETE /boards` | `405` | 주소는 있지만 `DELETE`를 처리하는 메서드가 없다. |

`404`와 `405` 모두 **4xx**, 즉 **클라이언트가 요청을 잘못 보낸 경우**이다. 주소나 메서드를 고쳐서 다시 요청해야 한다.

확인한 후에는 `Ctrl + C`를 눌러 애플리케이션을 종료한다.

### 실습-3: @RequestMapping으로 공통 주소 정리하기

`BoardController`에 `@RequestMapping`으로 공통 주소를 지정하고, 게시글 개수를 조회하는 API를 추가한다.

**1) 컨트롤러 수정하기**

`BoardController.java`를 다음과 같이 바꾼다. 3장에서 추가한 생성자의 출력 코드는 삭제한다.

```java
package com.example.hello.board;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")                            // 추가: 공통 주소
public class BoardController {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping                                         // 변경: GET /boards
  public List<Board> list() {
    return boardService.list();
  }

  @GetMapping("/count")                               // 추가: GET /boards/count
  public Map<String, Integer> count() {
    return Map.of("count", boardService.list().size());
  }
}
```

- 클래스의 `@RequestMapping("/boards")`가 모든 메서드의 공통 주소가 된다.
- `list()`의 `@GetMapping`에는 주소가 없으므로 공통 주소 `/boards`가 그대로 사용된다.
- `count()`의 `@GetMapping("/count")`는 공통 주소와 합쳐져 `/boards/count`가 된다.

**2) 요청 추가하기**

`board.http`에 다음 요청을 추가한다.

```http
### 게시글 개수 조회
GET {{baseUrl}}/boards/count
```

**3) 실행하고 확인하기**

애플리케이션을 실행하고 `GET {{baseUrl}}/boards`와 `GET {{baseUrl}}/boards/count`를 차례로 요청한다.

```
HTTP/1.1 200
Content-Type: application/json
...

{
  "count": 2
}
```

`GET /boards`는 이전과 똑같이 목록을 응답하고, `GET /boards/count`는 게시글 개수를 JSON으로 응답한다.

**4) (확인) `method`를 지정하지 않은 `@RequestMapping`**

`count()` 메서드의 `@GetMapping("/count")`를 잠시 `@RequestMapping("/count")`로 바꾸고(`import`도 추가), 애플리케이션을 다시 실행한다.
그리고 `board.http`에 다음 요청을 추가하여 보낸다.

```http
### count에 DELETE 요청 (확인용)
DELETE {{baseUrl}}/boards/count
```

`405`가 아니라 `200`과 함께 개수가 응답된다.
`method`를 지정하지 않은 `@RequestMapping`은 **모든 HTTP 메서드**를 처리하기 때문이다.
조회만 해야 하는 API가 DELETE 요청까지 받아 버렸다. 이런 실수를 막으려면 메서드에는 `@GetMapping`처럼 **HTTP 메서드가 정해진 애노테이션**을 사용한다.

확인한 후에는 `@GetMapping("/count")`로 되돌리고, 애플리케이션을 다시 실행하여 `DELETE {{baseUrl}}/boards/count`가 `405`를 응답하는지 확인한다.

### 실습-4: @PostMapping으로 게시글 등록 API 만들기

`POST /boards` 요청으로 게시글을 등록하고, REST 관례에 따라 `201 Created`와 `Location` 헤더로 응답하는 API를 만든다.

> 클라이언트가 보낸 제목과 작성자를 받는 방법(`@RequestBody`)은 5장에서 배운다.
> 이번 실습에서는 서버가 정한 제목(`새 게시글 번호`)과 작성자(`익명`)로 게시글을 등록한다.

**1) 서비스에 등록 메서드 추가하기**

게시글을 등록하려면 새 게시글 번호가 필요하다. `BoardService.java`를 다음과 같이 바꾼다.
3장에서 추가한 생성자의 출력 코드는 삭제한다.

```java
package com.example.hello.board;

import java.util.List;
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

  public void register(Board board) {
    boardRepository.save(board);
  }

  // 추가: 새 번호를 발급하여 게시글을 등록하고, 등록한 게시글을 리턴한다.
  public Board create(String title, String writer) {
    long id = sequence.incrementAndGet();          // 번호를 1 증가시키고 그 값을 가져온다.
    Board board = new Board(id, title + " " + id, writer);
    boardRepository.save(board);
    return board;
  }
}
```

> `AtomicLong`은 여러 요청이 동시에 번호를 받아도 같은 번호가 중복으로 발급되지 않도록 해 주는 JDK 클래스이다.
> 3장에서 배운 것처럼 서비스 빈은 싱글톤이라 여러 요청이 함께 사용하므로, 일반 `long` 필드 대신 `AtomicLong`을 사용한다.
> 6장에서 데이터베이스를 사용하면 번호는 데이터베이스가 자동으로 발급한다.

**2) 컨트롤러에 등록 메서드 추가하기**

`BoardController.java`에 다음 메서드를 추가한다.

```java
package com.example.hello.board;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {

  ... (기존 코드는 그대로 둔다.)

  @PostMapping                                                  // POST /boards
  public ResponseEntity<Board> create() {
    Board created = boardService.create("새 게시글", "익명");
    return ResponseEntity
        .created(URI.create("/boards/" + created.id()))        // 201 Created + Location 헤더
        .body(created);                                         // 응답 본문: 등록한 게시글
  }
}
```

**3) 요청 추가하기**

`board.http`에 다음 요청을 추가한다.

```http
### 게시글 등록
POST {{baseUrl}}/boards
```

**4) 실행하고 확인하기**

애플리케이션을 다시 실행하고 `POST {{baseUrl}}/boards`를 요청한다.

```
HTTP/1.1 201
Location: /boards/3
Content-Type: application/json
...

{
  "id": 3,
  "title": "새 게시글 3",
  "writer": "익명"
}
```

- 상태 코드가 `200`이 아니라 `201`이다. 새 데이터가 만들어졌다는 뜻이다.
- **`Location`** 헤더에 새로 만들어진 게시글의 주소(`/boards/3`)가 담겨 있다.
- 응답 본문에는 등록된 게시글의 정보가 담겨 있다.

`POST` 요청을 몇 번 더 보낸 후, `GET {{baseUrl}}/boards`와 `GET {{baseUrl}}/boards/count`를 요청하여 게시글이 추가된 것을 확인한다.

> `Location` 헤더의 주소(`/boards/3`)로 게시글 한 개를 조회하는 API는 이후의 다른 장에서 만든다. 지금 요청하면 `404`가 응답된다.

> 게시글은 메모리에 저장되므로, 애플리케이션을 다시 실행하면 등록한 게시글이 사라진다. 데이터를 영구히 저장하는 방법은 이후의 다른 장에서 배운다.

**5) 같은 주소, 다른 메서드**

`board.http`에 적은 요청을 다시 보면, 게시글 목록 조회와 등록이 **같은 주소**(`/boards`)를 사용한다.

```http
GET  {{baseUrl}}/boards      ← 목록 조회
POST {{baseUrl}}/boards      ← 등록
```

주소는 **자원**(게시글)을, 메서드는 **행위**(조회, 등록)를 나타낸다. 이것이 REST 방식의 API 설계이다.

**6) (참고) `@ResponseStatus`로 바꿔 보기**

`Location` 헤더가 필요 없다면 `@ResponseStatus`로 더 짧게 작성할 수 있다. `create()` 메서드를 다음과 같이 바꿔 보자.

```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)          // 성공하면 201
  public Board create() {
    return boardService.create("새 게시글", "익명");
  }
```

애플리케이션을 다시 실행하고 `POST` 요청을 보내면, 상태 코드는 `201`이지만 `Location` 헤더는 없다.
확인한 후에는 `ResponseEntity`를 사용하는 코드로 되돌린다.

### 실습-5: REST API 설계 연습

코드를 작성하기 전에 API를 설계하는 연습을 한다. 다음 기능에 대해 **HTTP 메서드**, **URI**, **성공 시 상태 코드**를 정해 보자.

**1) 문제: 회원 API**

| 기능 | 메서드 | URI | 성공 시 상태 코드 |
| --- | --- | --- | --- |
| 회원 목록 조회 | | | |
| 회원 가입(등록) | | | |
| 10번 회원 조회 | | | |
| 10번 회원 정보 전체 변경 | | | |
| 10번 회원 탈퇴(삭제) | | | |

**2) 문제: 댓글 API**

| 기능 | 메서드 | URI | 성공 시 상태 코드 |
| --- | --- | --- | --- |
| 5번 게시글의 댓글 목록 조회 | | | |
| 5번 게시글에 댓글 등록 | | | |

**3) 문제: 잘못 설계된 API 고치기**

다음 API를 REST 설계 규칙에 맞게 고쳐 보자.

| 잘못 설계된 API | 고친 API |
| --- | --- |
| `GET /getMemberList` | |
| `POST /members/insert` | |
| `GET /members/delete?id=10` | |
| `POST /Member_Update/10` | |

**4) 예시 답안**

<details>
<summary>답안 보기</summary>

**회원 API**

| 기능 | 메서드 | URI | 성공 시 상태 코드 |
| --- | --- | --- | --- |
| 회원 목록 조회 | `GET` | `/members` | `200 OK` |
| 회원 가입(등록) | `POST` | `/members` | `201 Created` |
| 10번 회원 조회 | `GET` | `/members/10` | `200 OK` |
| 10번 회원 정보 전체 변경 | `PUT` | `/members/10` | `200 OK` |
| 10번 회원 탈퇴(삭제) | `DELETE` | `/members/10` | `204 No Content` |

**댓글 API**

| 기능 | 메서드 | URI | 성공 시 상태 코드 |
| --- | --- | --- | --- |
| 5번 게시글의 댓글 목록 조회 | `GET` | `/boards/5/comments` | `200 OK` |
| 5번 게시글에 댓글 등록 | `POST` | `/boards/5/comments` | `201 Created` |

**잘못 설계된 API 고치기**

| 잘못 설계된 API | 고친 API | 고친 이유 |
| --- | --- | --- |
| `GET /getMemberList` | `GET /members` | URI에 동사(`get`)를 쓰지 않고, 복수형 명사를 사용한다. |
| `POST /members/insert` | `POST /members` | 등록이라는 행위는 `POST` 메서드로 표현한다. |
| `GET /members/delete?id=10` | `DELETE /members/10` | 삭제는 `DELETE` 메서드로 표현한다. GET으로 데이터를 바꾸면 안 된다. |
| `POST /Member_Update/10` | `PUT /members/10` | 변경은 `PUT`으로 표현하고, 소문자 복수형 명사를 사용한다. |

</details>

> 삭제 성공 시 본문 없이 `204 No Content`를 응답하는 대신, 삭제된 데이터를 본문에 담아 `200 OK`로 응답하는 API도 있다. 팀에서 정한 규칙을 일관되게 따르는 것이 중요하다.

