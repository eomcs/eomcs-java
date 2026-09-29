# 8장. REST API + JPA

5장에서는 게시판 REST API를 만들었지만 게시글을 메모리에 저장했고, 6~7장에서는 JPA로 게시글을 데이터베이스에 저장하는 방법을 배웠다.
이번 장에서는 둘을 합쳐 **데이터베이스에 저장하는 게시판 REST API**를 완성한다.
코드를 **Controller-Service-Repository** 계층으로 나누어 역할을 분명히 하고, 요청·응답에 사용하는 **DTO와 엔티티를 분리**하는 이유와 방법을 배운다.
마지막으로 완성한 API의 문서를 **springdoc-openapi**로 자동 생성하고 **Swagger UI**로 확인한다.

## 계층 구조

### 계층을 나누는 이유

웹 애플리케이션이 하는 일은 크게 세 가지로 나눌 수 있다.

1. HTTP 요청을 받고 응답한다.
2. 업무 규칙에 따라 일을 처리한다. (예: 게시글을 등록한다, 작성자는 수정할 수 없다)
3. 데이터를 저장하고 조회한다.

이 세 가지를 한 클래스에서 모두 처리하면 코드가 길어지고, 한 부분을 바꿀 때 다른 부분까지 영향을 받는다.
그래서 역할별로 클래스를 나누는데, 이것을 **계층 구조**(Layered Architecture)라고 한다.
스프링 애플리케이션에서는 보통 다음 세 계층으로 나눈다.

```
[ 클라이언트 ]           웹 브라우저, 앱, REST Client
      │ ▲
      │ │  HTTP 요청·응답 (JSON)
      ▼ │
[ Controller 계층 ]      BoardController (@RestController)
      │ ▲                요청 받기, 응답 만들기
      │ │  요청 DTO / 응답 DTO
      ▼ │
[ Service 계층 ]         BoardService (@Service, @Transactional)
      │ ▲                업무 처리, 트랜잭션, 엔티티 ↔ DTO 변환
      │ │  엔티티 / 조회 모델
      ▼ │
[ Repository 계층 ]      BoardJpaRepository (JpaRepository)
      │ ▲                데이터 저장·조회
      │ │  SQL
      ▼ │
[ 데이터베이스 (H2) ]
```

### 각 계층의 역할

| 계층 | 클래스 | 하는 일 | 하지 않는 일 |
| --- | --- | --- | --- |
| **Controller** | `BoardController` | 요청 주소와 메서드 매핑, 요청 데이터 꺼내기(`@PathVariable`, `@RequestParam`, `@RequestBody`), 간단한 입력 검사, 상태 코드와 응답 만들기 | 업무 규칙 처리, 데이터베이스 접근 |
| **Service** | `BoardService` | 업무 규칙 처리, **트랜잭션** 관리, 엔티티와 DTO 변환 | HTTP 관련 처리(상태 코드, 헤더 등) |
| **Repository** | `BoardJpaRepository` | 데이터베이스에 엔티티 저장·조회·삭제 | 업무 규칙 처리 |

계층 사이의 의존 방향은 항상 **위에서 아래로**이다.

- 컨트롤러는 서비스를 사용하고, 서비스는 리포지토리를 사용한다. (3장에서 배운 **생성자 주입**으로 연결한다.)
- 아래 계층은 위 계층을 모른다. 서비스는 HTTP를 모르고, 리포지토리는 서비스를 모른다.
- 컨트롤러가 서비스를 건너뛰고 리포지토리를 직접 사용하지 않는다.

| 스테레오타입 애노테이션 | 계층 |
| --- | --- |
| `@RestController` | Controller |
| `@Service` | Service |
| `@Repository` (Spring Data JPA 인터페이스는 생략) | Repository |

3장에서 역할별 애노테이션을 사용한 이유가 바로 이 계층 구조 때문이다.

### 트랜잭션은 서비스 계층에서

7장에서 배운 것처럼 JPA의 변경 감지와 지연 로딩은 **트랜잭션 안에서만** 동작한다.
하나의 업무(예: "게시글을 수정한다")는 여러 데이터베이스 작업(조회 → 변경)으로 이루어지므로, 업무 단위를 담당하는 **서비스 계층의 메서드에 `@Transactional`을 붙인다.**

```java
@Service
@Transactional(readOnly = true)             // 클래스: 모든 메서드에 읽기 전용 트랜잭션 적용
public class BoardService {

  public Optional<BoardResponse> get(Long id) { ... }        // 읽기 전용 트랜잭션

  @Transactional                             // 메서드: 데이터를 바꾸는 메서드는 쓰기 트랜잭션으로 덮어쓴다.
  public Optional<BoardResponse> update(Long id, BoardUpdateRequest request) { ... }
}
```

클래스에 `@Transactional(readOnly = true)`를 붙여 기본값을 읽기 전용으로 하고, 데이터를 바꾸는 메서드에만 `@Transactional`을 다시 붙이는 방식을 많이 사용한다.
메서드에 붙인 설정이 클래스에 붙인 설정보다 우선한다.

### 패키지 구성

이 교재에서는 게시판과 관련된 클래스를 모두 `board` 패키지에 둔다.

```
com.example.hello.board
├── BoardController.java        ← Controller 계층
├── BoardService.java           ← Service 계층
├── BoardJpaRepository.java     ← Repository 계층
├── BoardListView.java          ← 조회 모델 (Repository 계층)
├── CommentJpaRepository.java
├── BoardEntity.java            ← 엔티티
├── CommentEntity.java
├── BoardResponse.java          ← DTO (응답)
├── BoardSummary.java
├── BoardCreateRequest.java     ← DTO (요청)
└── BoardUpdateRequest.java
```

> 이처럼 **기능**(게시판)별로 패키지를 나누는 방식을 많이 사용한다. 클래스가 많아지면 `board.controller`, `board.service`처럼 기능 안에서 다시 계층별로 나누기도 한다.

## DTO와 엔티티 분리

### 엔티티를 그대로 요청·응답에 사용하면

5장에서는 `Board` record 하나로 저장도 하고 응답도 했다. 엔티티도 같은 방식으로 컨트롤러에서 그대로 리턴하면 안 될까?

```java
@GetMapping("/{id}")
public BoardEntity get(@PathVariable Long id) {      // 엔티티를 그대로 응답 (좋지 않은 예)
  return boardJpaRepository.findById(id).orElseThrow();
}
```

이렇게 하면 다음과 같은 문제가 생긴다.

| 문제 | 설명 |
| --- | --- |
| **순환 참조** | `BoardEntity`는 `comments`를, `CommentEntity`는 다시 `board`를 참조한다. JSON으로 바꿀 때 게시글 → 댓글 → 게시글 → 댓글 … 로 끝없이 이어져 오류가 발생한다. |
| **지연 로딩 문제** | JSON으로 바꾸는 시점에 지연 로딩된 연관 엔티티(프록시)를 조회하게 되어, 예상하지 못한 SQL이 실행되거나 `LazyInitializationException`이 발생한다. |
| **내부 구조 노출** | 테이블 구조가 그대로 API가 된다. 엔티티에 필드를 추가하면 API 응답도 바뀐다. 회원의 비밀번호처럼 보여 주면 안 되는 필드도 응답에 포함된다. |
| **요청으로 바꾸면 안 되는 값을 바꿀 수 있다** | 요청 본문을 엔티티로 받으면, 클라이언트가 `id`나 `writer`처럼 바꾸면 안 되는 값까지 보낼 수 있다. |

그래서 **엔티티는 서비스 계층 안에서만 사용**하고, 컨트롤러와 클라이언트 사이에서는 **DTO**만 주고받는다.

```
클라이언트 ◀── JSON ──▶ Controller ◀── DTO ──▶ Service ◀── 엔티티, 조회 모델 ──▶ Repository
```

엔티티(또는 조회 모델)와 DTO 사이의 변환은 **서비스**에서 한다.

### 게시판의 DTO

| DTO | 용도 | 담는 값 |
| --- | --- | --- |
| `BoardCreateRequest` | 등록 요청 (5장) | 제목, 내용, 작성자 |
| `BoardUpdateRequest` | 변경 요청 (5장) | 제목, 내용 |
| `BoardSummary` | 목록 응답 (5장) | 번호, 제목, 작성자 |
| `BoardResponse` | 한 개 조회·등록·변경 응답 | 번호, 제목, 내용, 작성자 |

`BoardResponse`는 5장의 `Board` record를 이름만 바꾼 것이다. 이제 `Board`는 저장용이 아니라 **응답용 DTO**라는 것이 이름으로 드러나도록 `BoardResponse`로 바꾼다.

### 엔티티와 DTO 변환

**엔티티 → 응답 DTO**: 응답 DTO에 변환 메서드를 둔다.

```java
public record BoardResponse(Long id, String title, String content, String writer) {

  public static BoardResponse from(BoardEntity board) {
    return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), board.getWriter());
  }
}
```

**요청 DTO → 엔티티**: 서비스에서 요청 DTO의 값으로 엔티티를 만든다.

```java
@Transactional
public BoardResponse create(BoardCreateRequest request) {
  BoardEntity board = new BoardEntity(request.title(), request.content(), request.writer());
  BoardEntity saved = boardJpaRepository.save(board);
  return BoardResponse.from(saved);                   // 엔티티 → DTO로 바꿔서 리턴한다.
}
```

> 서비스가 엔티티 대신 **DTO를 리턴**하면, 엔티티는 트랜잭션 안(서비스)에서만 사용된다.
> 컨트롤러는 엔티티를 전혀 모르므로, 지연 로딩이나 순환 참조 문제가 컨트롤러까지 번지지 않는다.

## 목록 조회 최적화

### 필요한 열만 조회하기: 프로젝션

목록 화면에는 게시글의 내용(`content`)이 필요 없다.
그런데 `findAll()`로 엔티티를 조회한 후 `BoardSummary`로 바꾸면, 데이터베이스에서는 쓰지 않는 `content`까지 모두 읽어 온다.

```sql
-- findAll(): 엔티티의 모든 열을 조회한다.
select b.id, b.content, b.title, b.writer from board b
```

내용이 길고 게시글이 많을수록 데이터베이스, 네트워크, 메모리의 부담이 커진다.
Spring Data JPA는 리포지토리 메서드의 **리턴 타입을 엔티티가 아닌 다른 타입으로 지정하면, 그 타입에 필요한 열만 조회**한다. 이것을 **프로젝션**(Projection)이라고 한다.

프로젝션의 리턴 타입으로는 **필요한 값의 getter 메서드만 선언한 인터페이스**를 사용할 수 있다.

```java
// 게시글 목록 조회 결과의 모양 (조회 모델)
public interface BoardListView {
  Long getId();
  String getTitle();
  String getWriter();
}
```

```java
public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {

  List<BoardListView> findAllBy(Pageable pageable);     // 리턴 타입: BoardListView
}
```

```sql
-- 프로젝션: BoardListView의 getter(getId, getTitle, getWriter)에 해당하는 열만 조회한다.
select b.id, b.title, b.writer from board b ...
```

- 인터페이스의 getter 이름에서 `get`을 뺀 이름(`id`, `title`, `writer`)이 엔티티의 **필드 이름**과 같아야 한다.
- 구현 클래스는 만들지 않는다. Spring Data JPA가 조회 결과를 담은 구현 객체를 자동으로 만든다.
- 조회 결과는 엔티티가 아니므로 변경 감지의 대상이 아니어서 더 가볍다.

### 리포지토리가 API 응답 DTO를 리턴하지 않는 이유

프로젝션의 리턴 타입으로 `record`도 사용할 수 있다. 그렇다면 `BoardSummary`를 바로 리턴하면 더 간단하지 않을까?

```java
List<BoardSummary> findAllBy(Pageable pageable);      // 동작은 하지만 계층 원칙에 어긋난다.
```

`BoardSummary`는 컨트롤러가 클라이언트에 보내는 **JSON의 모양을 정한 API 응답 DTO**이다.
리포지토리가 이것을 리턴하면, 가장 아래 계층(Repository)이 가장 위 계층(Controller)의 형식을 알게 된다. 앞에서 정한 "**아래 계층은 위 계층을 모른다**"는 원칙에 어긋난다.

- 목록 API에 항목을 추가하거나 형식을 바꾸면, API만 바꿨는데 **리포지토리의 쿼리까지 바뀐다.**
- API 문서용 애노테이션(`@Schema` 등)이 붙은 클래스에 데이터베이스 조회가 묶인다.

그래서 조회 결과의 모양은 **리포지토리 쪽에서 정의한 조회 모델**(`BoardListView`)로 받고, 서비스에서 API 응답 DTO(`BoardSummary`)로 바꾼다.

```
Repository ──▶ BoardListView (조회 모델) ──▶ Service에서 변환 ──▶ BoardSummary (API 응답 DTO) ──▶ Controller
```

```java
public record BoardSummary(Long id, String title, String writer) {

  public static BoardSummary from(BoardListView view) {           // 조회 모델 → API 응답 DTO
    return new BoardSummary(view.getId(), view.getTitle(), view.getWriter());
  }
}
```

| 타입 | 소속 계층 | 역할 |
| --- | --- | --- |
| `BoardEntity` | Repository | 테이블과 매핑되는 엔티티 (등록·변경·삭제, 한 개 조회) |
| `BoardListView` | Repository | 목록 조회 결과의 모양 (필요한 열만 조회) |
| `BoardSummary`, `BoardResponse` | Controller ↔ Service | API 응답 DTO |
| `BoardCreateRequest`, `BoardUpdateRequest` | Controller ↔ Service | API 요청 DTO |

> 위 계층이 아래 계층의 타입을 아는 것은 괜찮다. 그래서 `BoardResponse.from(BoardEntity)`처럼 `BoardSummary.from(BoardListView)`도 응답 DTO 쪽에 변환 메서드를 둔다.

> 실무에서도 리포지토리가 조회 전용 DTO를 리턴하는 경우가 많다. 이때도 그 DTO는 **조회 전용 모델**로 따로 두고, API 요청·응답 DTO와 섞지 않는 것이 좋다.

### 페이지 단위로 조회하기: 페이징

`findAll()`은 테이블의 **모든 행**을 가져온다. 게시글이 10만 건이면 10만 건을 모두 읽어 온다.
열을 줄이는 것보다 **행을 줄이는 것**의 효과가 훨씬 크다.
5장에서는 모든 게시글을 가져온 후 자바 코드(`skip`, `limit`)로 페이지를 나눴지만, 이제는 **데이터베이스가 필요한 행만** 가져오도록 한다.

Spring Data JPA는 메서드의 마지막 파라미터로 `Pageable`을 받으면 페이징 SQL을 만든다.

```java
// 페이지 번호(0부터 시작), 페이지 크기, 정렬 방식
Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));

List<BoardListView> boards = boardJpaRepository.findAllBy(pageable);
```

```sql
select b.id, b.title, b.writer from board b order by b.id desc offset ? rows fetch first ? rows only
```

| 코드 | 의미 |
| --- | --- |
| `PageRequest.of(페이지, 크기, 정렬)` | 페이지 정보를 만든다. **페이지 번호는 0부터** 시작한다. |
| `Sort.by(Sort.Direction.DESC, "id")` | `id` 필드의 역순(최신순)으로 정렬한다. 필드 이름은 엔티티의 필드 이름이다. |
| `offset ? rows fetch first ? rows only` | 앞의 행을 건너뛰고(offset) 정해진 개수만 가져온다. (데이터베이스에 따라 `limit ? offset ?` 형식이 된다.) |

페이지 번호가 0부터 시작하는 것은 Spring Data가 페이지 번호를 자바의 배열·`List`처럼 **인덱스**로 다루기 때문이다.
이렇게 하면 SQL의 `offset`(건너뛸 행의 수)을 `페이지 번호 × 페이지 크기`로 바로 계산할 수 있다.

| 페이지 번호 | offset (`페이지 × 10`) | 조회되는 행 |
| --- | --- | --- |
| 0 | 0 | 1~10번째 |
| 1 | 10 | 11~20번째 |
| 2 | 20 | 21~30번째 |

> 이 교재의 API는 페이지 번호를 **1부터** 받으므로, 서비스에서 `PageRequest.of(page - 1, size, ...)`로 바꿔서 사용한다.

> 리턴 타입을 `Page<BoardListView>`로 하면 전체 개수, 전체 페이지 수 같은 정보도 함께 얻을 수 있다. 이때는 전체 개수를 세는 SQL이 한 번 더 실행된다.
> 이 교재에서는 5장의 API 응답 형식(목록)을 유지하기 위해 `List`를 사용하고, 전체 개수는 `GET /boards/count`로 따로 제공한다.

## API 문서 자동 생성

### API 문서가 필요한 이유

REST API를 사용하는 클라이언트 개발자는 다음 정보를 알아야 한다.

- 어떤 주소와 메서드로 요청하는가?
- 어떤 파라미터와 요청 본문을 보내야 하는가?
- 어떤 응답이 어떤 상태 코드로 오는가?

이 정보를 문서로 따로 작성하면, 코드를 바꿀 때마다 문서도 고쳐야 하고, 둘이 어긋나기 쉽다.
**springdoc-openapi**는 **컨트롤러 코드를 분석하여 API 문서를 자동으로 만든다.**

### springdoc-openapi와 Swagger UI

| 용어 | 의미 |
| --- | --- |
| **OpenAPI** | REST API를 설명하는 **표준 문서 형식**(JSON 또는 YAML). |
| **springdoc-openapi** | 스프링 애플리케이션의 컨트롤러를 분석하여 OpenAPI 문서를 자동으로 만드는 라이브러리. |
| **Swagger UI** | OpenAPI 문서를 **웹 화면**으로 보여 주는 도구. 화면에서 API를 직접 호출해 볼 수도 있다. |

springdoc-openapi는 애플리케이션이 시작될 때 스프링 MVC에 등록된 매핑 정보를 읽어 문서를 만든다.

| 코드에서 읽는 정보 | 문서에 들어가는 내용 |
| --- | --- |
| `@RequestMapping("/boards")`, `@GetMapping("/{id}")` | API 주소와 HTTP 메서드 |
| `@PathVariable`, `@RequestParam(defaultValue = "1")` | 파라미터의 이름, 위치, 타입, 필수 여부, 기본값 |
| `@RequestBody BoardCreateRequest` | 요청 본문의 JSON 구조 |
| 리턴 타입 `List<BoardSummary>`, `ResponseEntity<BoardResponse>` | 응답 본문의 JSON 구조 |

의존성은 다음과 같이 추가한다.

```groovy
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.1'
```

> springdoc-openapi는 스프링 부트가 버전을 관리하는 라이브러리가 아니므로 **버전을 직접 적어야 한다.** (2장 Starter 참고)
> Spring Boot 4에는 springdoc-openapi **3.x**를 사용한다. (Spring Boot 3에는 2.x)

| 주소 | 내용 |
| --- | --- |
| `/swagger-ui.html` | Swagger UI 화면 |
| `/v3/api-docs` | OpenAPI 문서 (JSON) |

### 애노테이션으로 문서 보강하기

자동으로 만든 문서에는 API에 대한 **설명이 없다.**
또 `ResponseEntity`로 상황에 따라 `201`, `404`, `400` 등을 응답하는 것은 코드만 보고 알 수 없으므로, 문서에는 `200`만 표시된다.
이런 정보는 다음 애노테이션으로 보강한다. (`io.swagger.v3.oas.annotations` 패키지)

| 애노테이션 | 붙이는 곳 | 용도 |
| --- | --- | --- |
| `@Tag(name, description)` | 컨트롤러 클래스 | API 묶음의 이름과 설명 |
| `@Operation(summary, description)` | 컨트롤러 메서드 | API의 요약과 설명 |
| `@ApiResponse(responseCode, description)` | 컨트롤러 메서드 | 응답 상태 코드별 설명 (여러 개 붙일 수 있다) |
| `@Parameter(description, example)` | 메서드 파라미터 | 파라미터 설명과 예시 값 |
| `@Schema(description, example)` | DTO와 DTO의 구성 요소 | 요청·응답 JSON의 항목 설명과 예시 값 |

```java
@Operation(summary = "게시글 조회", description = "게시글 번호로 게시글 한 개를 조회한다.")
@ApiResponse(responseCode = "200", description = "조회 성공")
@ApiResponse(responseCode = "404", description = "게시글이 없음", content = @Content)
@GetMapping("/{id}")
public ResponseEntity<BoardResponse> get(
    @Parameter(description = "게시글 번호", example = "1") @PathVariable Long id) { ... }
```

> `content = @Content`는 "응답 본문이 없다"는 뜻이다.

### 운영 환경에서는 끄기

API 문서는 API를 사용하는 개발자에게 유용하지만, 공격자에게도 유용한 정보이다.
외부에 공개하는 운영 서버에서는 다음 설정으로 문서를 끈다.

```properties
springdoc.api-docs.enabled=false
springdoc.swagger-ui.enabled=false
```

> 12장에서 Spring Security를 적용하면 Swagger UI 주소도 로그인이 필요해진다. 개발 중에 문서를 계속 보려면 Swagger UI 관련 주소를 허용하는 설정이 필요하다.

## 정리

| 주제 | 핵심 내용 |
| --- | --- |
| 계층 구조 | Controller(요청·응답) → Service(업무·트랜잭션) → Repository(데이터 접근). 의존 방향은 위에서 아래로. |
| 트랜잭션 | 서비스 계층에 `@Transactional`을 붙인다. 클래스에 `readOnly = true`, 데이터를 바꾸는 메서드에 `@Transactional`. |
| DTO와 엔티티 분리 | 엔티티는 서비스 안에서만 사용하고, 컨트롤러와는 DTO로 주고받는다. 순환 참조, 지연 로딩, 내부 구조 노출 문제를 막는다. |
| 프로젝션 | 리포지토리 메서드의 리턴 타입을 조회 모델(인터페이스)로 하면 필요한 열만 조회한다. 리포지토리는 API 응답 DTO를 리턴하지 않는다. |
| 페이징 | `Pageable`로 필요한 행만 조회한다. `PageRequest.of(페이지, 크기, 정렬)`, 페이지 번호는 0부터. |
| API 문서 | springdoc-openapi가 컨트롤러를 분석하여 OpenAPI 문서를 만들고, Swagger UI로 보여 준다. |

## 실습

이번 장의 실습은 7장까지 사용한 `hello` 프로젝트의 `board` 패키지에서 이어서 진행한다.
5장의 메모리 저장 게시판 API를 **JPA를 사용하는 계층 구조**로 바꾸고, API 문서를 추가한다.

실습 전후의 `board` 패키지를 비교하면 다음과 같다.

| 파일 | 실습 전 | 실습 후 |
| --- | --- | --- |
| `BoardController.java` | 메모리 저장 서비스를 사용 | DTO만 주고받는 컨트롤러 (수정) |
| `BoardService.java` | `BoardRepository`(메모리) 사용 | `BoardJpaRepository` 사용, 트랜잭션 적용 (다시 작성) |
| `BoardJpaRepository.java` | 7장 실습용 쿼리 메서드 | 목록 조회용 메서드 추가 |
| `Board.java` | 게시글 record | `BoardResponse.java`로 이름 변경 (응답 DTO) |
| `BoardSummary.java`, `BoardCreateRequest.java`, `BoardUpdateRequest.java` | 5장 DTO | 그대로 사용 (`BoardSummary`는 변환 메서드 수정) |
| `BoardListView.java` | - | 목록 조회 모델 (**추가**) |
| `BoardEntity.java`, `CommentEntity.java`, `CommentJpaRepository.java` | 6~7장 엔티티, 리포지토리 | 그대로 사용 |
| `BoardRepository.java`, `MemoryBoardRepository.java` | 5장 메모리 저장소 | **삭제** |
| `BoardDataRunner.java`, `BoardJpaPractice.java` | 7장 실습용 코드 | **삭제** |

### 실습-1: 엔티티를 그대로 응답하면 생기는 문제 확인하기

계층 구조를 만들기 전에, 엔티티를 컨트롤러에서 그대로 응답하면 어떤 문제가 생기는지 확인한다.

**1) 확인용 컨트롤러 만들기**

`board` 폴더에 `EntityTestController.java` 파일을 만들고 다음과 같이 작성한다.
확인이 끝나면 삭제할 **임시 코드**이다. (컨트롤러가 리포지토리를 직접 사용하는 것도 좋지 않은 예이다.)

```java
package com.example.hello.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// 엔티티를 그대로 응답하는 좋지 않은 예 (확인 후 삭제한다)
@RestController
public class EntityTestController {

  private final BoardJpaRepository boardJpaRepository;

  public EntityTestController(BoardJpaRepository boardJpaRepository) {
    this.boardJpaRepository = boardJpaRepository;
  }

  @GetMapping("/test/entity/{id}")
  public BoardEntity get(@PathVariable Long id) {
    return boardJpaRepository.findById(id).orElseThrow();
  }
}
```

**2) 요청하기**

애플리케이션을 실행하고 `http/board.http`에 다음 요청을 추가하여 보낸다.

```http
### (확인용) 댓글이 없는 게시글 엔티티
GET {{baseUrl}}/test/entity/2

### (확인용) 댓글이 있는 게시글 엔티티
GET {{baseUrl}}/test/entity/1
```

> 7장 실습-1의 샘플 데이터 기준으로 2번 게시글에는 댓글이 없고, 1번 게시글에는 댓글이 두 개 있다.

**3) 결과 확인하기**

**2번 게시글**은 다음과 같이 응답된다. 엔티티의 모든 필드가 그대로 JSON이 되었다. 댓글 목록(`comments`)을 조회하는 SQL도 실행되었다.

```json
{
  "id": 2,
  "title": "JPA 엔티티 만들기",
  "content": "엔티티와 테이블을 매핑했다.",
  "writer": "홍길동",
  "comments": []
}
```

**1번 게시글**은 `500` 오류가 발생한다. 실행 로그에는 JSON의 중첩이 너무 깊다는 오류가 출력된다. (오류 메시지는 버전에 따라 다를 수 있다.)

```
게시글(1번) → comments → 댓글 → board → 게시글(1번) → comments → 댓글 → board → ...
```

게시글과 댓글이 서로를 참조하므로, JSON으로 바꾸는 과정이 끝없이 반복되다가 오류가 발생한 것이다(**순환 참조**).
또한 JSON으로 바꾸는 과정에서 지연 로딩된 댓글 목록을 조회하는 SQL이 실행되었다. 컨트롤러가 엔티티를 다루면, **언제 어떤 SQL이 실행될지 컨트롤러 코드만 보고는 알 수 없다.**

> 로그에 `spring.jpa.open-in-view is enabled by default` 경고가 보일 수 있다.
> 스프링 부트는 기본적으로 HTTP 요청이 끝날 때까지 JPA가 엔티티를 관리하도록 하는데(Open Session In View), 그래서 컨트롤러에서도 지연 로딩이 동작했다.
> 이번 장에서는 서비스가 DTO를 리턴하도록 만들므로, 컨트롤러에서 지연 로딩이 일어날 일이 없다.

**4) 확인용 컨트롤러 삭제하기**

확인한 후에는 `EntityTestController.java` 파일과 `board.http`에 추가한 두 요청을 삭제한다.

### 실습-2: 메모리 저장소와 실습 코드 정리하고 응답 DTO 만들기

**1) 사용하지 않는 파일 삭제하기**

다음 파일을 삭제한다.

- `BoardRepository.java`, `MemoryBoardRepository.java` (5장 메모리 저장소)
- `BoardDataRunner.java`, `BoardJpaPractice.java` (7장 실습용 코드)

> 파일을 삭제하면 `BoardService.java`에 컴파일 오류가 표시된다. 실습-3에서 `BoardService`를 다시 작성하므로 지금은 그대로 둔다.

**2) `Board`를 `BoardResponse`로 이름 바꾸기**

`Board.java` 파일을 열고, `record Board`의 `Board` 위에 커서를 둔 상태에서 **`F2`** 키를 누른다.
새 이름으로 `BoardResponse`를 입력하고 `Enter` 키를 누른다.

VS Code가 클래스 이름과 파일 이름을 바꾸고, 이 클래스를 사용하는 다른 파일의 코드도 함께 바꾼다.

> `F2`는 VS Code의 **이름 바꾸기**(Rename Symbol) 기능이다. 직접 파일 이름을 바꾸는 것보다 안전하다.

**3) 응답 DTO에 변환 메서드 추가하기**

`BoardResponse.java`를 다음과 같이 작성한다.

```java
package com.example.hello.board;

// 게시글 응답용 DTO: 한 개 조회, 등록, 변경의 응답으로 사용한다.
public record BoardResponse(Long id, String title, String content, String writer) {

  public static BoardResponse from(BoardEntity board) {
    return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), board.getWriter());
  }
}
```

`BoardSummary.java`는 실습-3에서 수정한다. `BoardCreateRequest.java`, `BoardUpdateRequest.java`는 5장의 코드를 그대로 사용한다.

### 실습-3: 리포지토리와 서비스 계층 만들기

**1) 목록 조회 모델 만들기**

`board` 폴더에 `BoardListView.java` 파일을 만들고 다음과 같이 작성한다. 게시글 목록을 조회할 때 필요한 값(번호, 제목, 작성자)의 getter만 선언한 인터페이스이다.

```java
package com.example.hello.board;

// 게시글 목록 조회 모델 (프로젝션)
// getter 이름에서 get을 뺀 이름(id, title, writer)이 BoardEntity의 필드 이름과 같아야 한다.
public interface BoardListView {

  Long getId();

  String getTitle();

  String getWriter();
}
```

**2) 목록 응답 DTO의 변환 메서드 바꾸기**

`BoardSummary.java`의 `from()` 메서드는 실습-2에서 이름을 바꾼 `BoardResponse`를 받고 있다. 조회 모델(`BoardListView`)을 받도록 다음과 같이 바꾼다.

```java
package com.example.hello.board;

// 게시글 목록 응답용 DTO: 내용(content)은 담지 않는다.
public record BoardSummary(Long id, String title, String writer) {

  public static BoardSummary from(BoardListView view) {
    return new BoardSummary(view.getId(), view.getTitle(), view.getWriter());
  }
}
```

**3) 목록 조회 메서드 추가하기**

`BoardJpaRepository.java`를 다음과 같이 바꾼다. 7장에서 만든 쿼리 메서드와 JPQL은 게시판 API에서 사용하지 않으므로 정리하고, 목록 조회용 메서드 두 개를 선언한다.

```java
package com.example.hello.board;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {

  // 목록 조회: 필요한 열(id, title, writer)만, 페이지 단위로
  List<BoardListView> findAllBy(Pageable pageable);

  // 제목 검색: 제목에 키워드가 포함된 게시글을 필요한 열만, 페이지 단위로
  List<BoardListView> findByTitleContaining(String keyword, Pageable pageable);
}
```

리포지토리는 API 응답 DTO(`BoardSummary`)가 아니라 **조회 모델**(`BoardListView`)을 리턴한다.

> 7장의 쿼리 메서드와 JPQL을 남겨 두어도 동작에는 문제가 없다. 이 교재에서는 게시판 API가 사용하는 메서드만 남긴다.

**4) 서비스 다시 작성하기**

`BoardService.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)                          // 기본: 읽기 전용 트랜잭션
public class BoardService {

  private final BoardJpaRepository boardJpaRepository;

  public BoardService(BoardJpaRepository boardJpaRepository) {
    this.boardJpaRepository = boardJpaRepository;
  }

  // 목록 조회 (page는 1부터 시작)
  public List<BoardSummary> list(String keyword, int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
    List<BoardListView> views = (keyword == null || keyword.isBlank())
        ? boardJpaRepository.findAllBy(pageable)
        : boardJpaRepository.findByTitleContaining(keyword, pageable);
    return views.stream()
        .map(BoardSummary::from)                         // 조회 모델 → 응답 DTO
        .toList();
  }

  // 개수 조회
  public long count() {
    return boardJpaRepository.count();
  }

  // 한 개 조회
  public Optional<BoardResponse> get(Long id) {
    return boardJpaRepository.findById(id)
        .map(BoardResponse::from);                       // 엔티티 → 응답 DTO
  }

  // 등록
  @Transactional
  public BoardResponse create(BoardCreateRequest request) {
    BoardEntity board = new BoardEntity(request.title(), request.content(), request.writer());   // 요청 DTO → 엔티티
    BoardEntity saved = boardJpaRepository.save(board);
    return BoardResponse.from(saved);
  }

  // 변경: 변경 감지
  @Transactional
  public Optional<BoardResponse> update(Long id, BoardUpdateRequest request) {
    return boardJpaRepository.findById(id)
        .map(board -> {
          board.update(request.title(), request.content());   // 엔티티의 값만 바꾼다. (save() 호출 없음)
          return BoardResponse.from(board);
        });
  }

  // 삭제: 삭제했으면 true, 게시글이 없으면 false
  @Transactional
  public boolean delete(Long id) {
    if (!boardJpaRepository.existsById(id)) {
      return false;
    }
    boardJpaRepository.deleteById(id);
    return true;
  }
}
```

5장의 서비스와 비교해 보자.

| 기능 | 5장 (메모리) | 8장 (JPA) |
| --- | --- | --- |
| 번호 발급 | `AtomicLong`으로 직접 발급 | 데이터베이스가 발급 (`@GeneratedValue`) |
| 검색·페이징 | 모두 가져온 후 자바 코드(`filter`, `skip`, `limit`)로 처리 | 데이터베이스가 처리 (쿼리 메서드, `Pageable`) |
| 변경 | 새 `Board` 객체를 만들어 덮어쓰기 | 엔티티의 값만 바꾸기 (변경 감지) |
| 트랜잭션 | 없음 | `@Transactional` |
| 리턴 타입 | `Board` (저장용 겸 응답용) | DTO (`BoardResponse`, `BoardSummary`). 엔티티와 조회 모델은 서비스 밖으로 나가지 않는다. |

> 삭제할 때 게시글에 댓글이 있으면, 7장에서 DDL에 정의한 `ON DELETE CASCADE`에 따라 데이터베이스가 댓글도 함께 삭제한다.

### 실습-4: 컨트롤러 수정하고 CRUD API 확인하기

**1) 컨트롤러 수정하기**

`BoardController.java`를 다음과 같이 바꾼다. 5장의 코드에서 **응답 타입이 `Board`에서 DTO로** 바뀐 것 외에는 거의 같다.

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
  public ResponseEntity<List<BoardSummary>> list(
      @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    if (page < 1 || size < 1) {
      return ResponseEntity.badRequest().build();          // 페이지 번호와 크기는 1 이상
    }
    return ResponseEntity.ok(boardService.list(keyword, page, size));
  }

  // 개수 조회: GET /boards/count
  @GetMapping("/count")
  public Map<String, Long> count() {
    return Map.of("count", boardService.count());
  }

  // 한 개 조회: GET /boards/{id}
  @GetMapping("/{id}")
  public ResponseEntity<BoardResponse> get(@PathVariable Long id) {
    return boardService.get(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // 등록: POST /boards
  @PostMapping
  public ResponseEntity<BoardResponse> create(@RequestBody BoardCreateRequest request) {
    if (request.title() == null || request.title().isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    BoardResponse created = boardService.create(request);
    return ResponseEntity
        .created(URI.create("/boards/" + created.id()))
        .body(created);
  }

  // 변경: PUT /boards/{id}
  @PutMapping("/{id}")
  public ResponseEntity<BoardResponse> update(@PathVariable Long id,
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

> `PageRequest.of()`에 0보다 작은 페이지 번호를 넘기면 예외가 발생하여 `500`이 응답된다. 그래서 컨트롤러에서 페이지 번호와 크기를 먼저 검사하여 `400`으로 응답한다.

컨트롤러에는 `BoardEntity`도, `BoardJpaRepository`도 등장하지 않는다. 컨트롤러는 **서비스와 DTO만** 알고 있다.

**2) 실행하기**

애플리케이션을 실행한다. 컴파일 오류가 있으면 실습-2에서 삭제할 파일이 남아 있지 않은지, `Board`를 `BoardResponse`로 모두 바꿨는지, `BoardSummary.from()`이 `BoardListView`를 받도록 바꿨는지 확인한다.

**3) 목록과 검색 확인하기**

5장에서 작성한 `http/board.http`의 목록 조회 요청을 보낸다.

`GET {{baseUrl}}/boards` — 데이터베이스의 게시글이 **최신순**으로 응답된다. (7장 샘플 데이터 기준)

```json
[
  { "id": 5, "title": "JPA 쿼리 메서드", "writer": "임꺽정" },
  { "id": 4, "title": "스프링 DI 정리", "writer": "유관순" },
  { "id": 3, "title": "REST API 설계", "writer": "임꺽정" },
  { "id": 2, "title": "JPA 엔티티 만들기", "writer": "홍길동" },
  { "id": 1, "title": "스프링 부트 시작하기", "writer": "홍길동" }
]
```

> 5장에서는 번호 순서대로 응답했지만, 이제 서비스에서 `id` 역순으로 정렬하므로 최신 게시글이 먼저 나온다.

| 요청 | 결과 |
| --- | --- |
| `GET /boards?keyword=스프링` | 제목에 "스프링"이 들어간 4번, 1번 게시글 |
| `GET /boards?page=2&size=2` | 3번째~4번째 게시글: 3번, 2번 |
| `GET /boards?page=0` | `400` |
| `GET /boards/count` | `{"count": 5}` |

실행 로그에서 목록 조회 SQL을 확인한다. (형식은 버전에 따라 다를 수 있다.)

```
Hibernate: 
    select
        be1_0.id,
        be1_0.title,
        be1_0.writer 
    from
        board be1_0 
    order by
        be1_0.id desc 
    offset
        ? rows 
    fetch
        first ? rows only
```

- `content` 열을 조회하지 않는다. **프로젝션**으로 `BoardListView`에 필요한 열만 조회했다.
- `order by`, `offset`, `fetch first`로 **데이터베이스가** 정렬하고 필요한 행만 가져왔다.

**4) CRUD 시나리오 확인하기**

5장에서 작성한 `http/board-scenario.http`의 요청을 1번부터 6번까지 순서대로 보낸다.

| 순서 | 요청 | 기대하는 상태 코드 |
| --- | --- | --- |
| 1 | `POST /boards` | `201` |
| 2 | `GET /boards/{id}` | `200` |
| 3 | `PUT /boards/{id}` | `200` |
| 4 | `GET /boards/{id}` | `200` (변경된 제목 확인) |
| 5 | `DELETE /boards/{id}` | `204` |
| 6 | `GET /boards/{id}` | `404` |

5장과 **API는 똑같이 동작**하지만, 이제 데이터는 데이터베이스에 저장된다.
3번(변경) 요청을 보냈을 때 실행 로그에 `update board set ...` SQL이 출력되는지 확인한다. 서비스에서 `save()`를 호출하지 않았지만 **변경 감지**로 반영되었다.

**5) 데이터가 유지되는지 확인하기**

`board.http`의 게시글 등록 요청(`POST {{baseUrl}}/boards`)을 보낸 후 애플리케이션을 종료하고 다시 실행한다.
`GET {{baseUrl}}/boards`를 요청하여 등록한 게시글이 그대로 있는 것을 확인한다.
5장에서는 다시 실행하면 사라졌던 게시글이 이제는 유지된다.

[http://localhost:8080/h2-console](http://localhost:8080/h2-console) 에서 `SELECT * FROM board;`를 실행하여 API로 등록한 게시글이 테이블에 저장된 것도 확인한다.

**6) 댓글이 있는 게시글 삭제하기**

`DELETE {{baseUrl}}/boards/3`을 요청한다. (7장 샘플 데이터 기준으로 3번 게시글에는 댓글이 하나 있다.)
`204`가 응답된 후, H2 콘솔에서 `SELECT * FROM board_comment;`를 실행하여 3번 게시글의 댓글도 함께 삭제된 것을 확인한다. DDL의 `ON DELETE CASCADE` 덕분이다.

> 실습 데이터를 처음 상태로 되돌리려면 7장의 `db/sample-data.sql`을 H2 콘솔에서 다시 실행한다.

### 실습-5: API 문서 자동 생성하기

**1) 의존성 추가하기**

`build.gradle`의 `dependencies` 블록에 다음 한 줄을 추가한다.

```groovy
  implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.1'
```

> springdoc-openapi는 스프링 부트가 관리하는 라이브러리가 아니므로 버전(`3.1.1`)을 적어야 한다.
> 최신 버전은 [https://springdoc.org](https://springdoc.org) 에서 확인할 수 있다.

**2) Swagger UI 확인하기**

애플리케이션을 다시 실행하고 웹 브라우저에서 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 에 접속한다.
컨트롤러 코드를 전혀 고치지 않았는데도, 다음과 같은 API 목록이 표시된다.

- `board-controller`: `/boards`의 GET, POST, `/boards/{id}`의 GET, PUT, DELETE, `/boards/count`의 GET
- `hello-controller`: `/hello` 등

`GET /boards/{id}` 항목을 클릭하여 펼치고 다음을 확인한다.

- **Parameters**: `id` 파라미터가 경로(`path`)에 있고, 필수(`required`)이며, 타입이 정수(`integer`)이다.
- **Responses**: `200` 응답의 본문이 `BoardResponse`의 구조(`id`, `title`, `content`, `writer`)로 표시된다.

**3) Swagger UI에서 API 호출하기**

`POST /boards` 항목을 펼치고 `Try it out` 버튼을 누른다.
**Request body**에 다음 JSON을 입력하고 `Execute` 버튼을 누른다.

```json
{
  "title": "Swagger UI에서 등록",
  "content": "API 문서 화면에서 직접 요청을 보냈다.",
  "writer": "유관순"
}
```

아래 **Server response**에 `201` 상태 코드와 응답 본문, `location` 헤더가 표시되는 것을 확인한다.
Swagger UI는 API 문서이면서 동시에 API를 호출해 볼 수 있는 도구이다.

**4) OpenAPI 문서 확인하기**

[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) 에 접속한다.
Swagger UI 화면의 원본인 **OpenAPI 문서**(JSON)가 출력된다. 이 문서를 다른 도구에 넣으면 클라이언트 코드를 자동으로 만들거나 API를 테스트하는 데 사용할 수 있다.

**5) 애노테이션으로 문서 보강하기**

자동으로 만든 문서에는 API 설명이 없고, `GET /boards/{id}`의 응답에도 `200`만 표시되어 `404`가 응답될 수 있다는 것을 알 수 없다.
`BoardController.java`에 문서용 애노테이션을 추가한다. 다음은 클래스와 `get()`, `create()` 메서드에 추가한 예이다.

```java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "게시판", description = "게시글 CRUD API")                        // 추가
@RestController
@RequestMapping("/boards")
public class BoardController {
  ...

  @Operation(summary = "게시글 조회", description = "게시글 번호로 게시글 한 개를 조회한다.")   // 추가
  @ApiResponse(responseCode = "200", description = "조회 성공")                      // 추가
  @ApiResponse(responseCode = "404", description = "게시글이 없음", content = @Content)  // 추가
  @GetMapping("/{id}")
  public ResponseEntity<BoardResponse> get(
      @Parameter(description = "게시글 번호", example = "1") @PathVariable Long id) {   // 변경
    ...
  }

  @Operation(summary = "게시글 등록", description = "새 게시글을 등록한다. 게시글 번호는 서버가 발급한다.")
  @ApiResponse(responseCode = "201", description = "등록 성공. Location 헤더에 새 게시글의 주소가 담긴다.")
  @ApiResponse(responseCode = "400", description = "제목이 없음", content = @Content)
  @PostMapping
  public ResponseEntity<BoardResponse> create(@RequestBody BoardCreateRequest request) {
    ...
  }
}
```

요청 DTO에는 `@Schema`로 항목 설명과 예시 값을 추가한다. `BoardCreateRequest.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 등록 요청")
public record BoardCreateRequest(
    @Schema(description = "제목", example = "스프링 공부") String title,
    @Schema(description = "내용", example = "오늘은 REST API를 배웠다.") String content,
    @Schema(description = "작성자", example = "홍길동") String writer) {
}
```

애플리케이션을 다시 실행하고 Swagger UI를 새로 고침한다. 다음이 바뀐 것을 확인한다.

- API 묶음의 이름이 `board-controller`에서 **게시판**으로 바뀌고 설명이 표시된다.
- `GET /boards/{id}`에 요약·설명이 표시되고, **Responses**에 `200`과 `404`가 모두 표시된다.
- `POST /boards`의 `Try it out`을 누르면, **Request body**에 `@Schema`의 예시 값이 미리 채워진다.

> 나머지 메서드(`list()`, `count()`, `update()`, `delete()`)와 DTO(`BoardUpdateRequest`, `BoardResponse`, `BoardSummary`)에도 같은 방식으로 애노테이션을 추가해 보자.

**6) 운영 환경에서 끄는 설정 확인하기**

`application.properties`에 다음 설정을 잠시 추가하고 애플리케이션을 다시 실행한다.

```properties
springdoc.api-docs.enabled=false
springdoc.swagger-ui.enabled=false
```

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 과 [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) 에 접속하면 `404`가 응답된다.
운영 서버에서는 이 설정으로 API 문서를 공개하지 않는다. 확인한 후에는 두 줄을 삭제한다.

**7) 정리**

| 실습 | 확인한 내용 |
| --- | --- |
| 실습-1 | 엔티티를 그대로 응답하면 순환 참조 오류, 예상하지 못한 SQL 실행, 내부 구조 노출 문제가 생긴다. |
| 실습-2, 3 | 메모리 저장소를 JPA 리포지토리로 바꾸고, 서비스 계층에서 트랜잭션과 엔티티 ↔ DTO 변환을 처리한다. |
| 실습-4 | 컨트롤러는 서비스와 DTO만 사용한다. 목록 조회는 프로젝션(조회 모델)과 페이징으로 필요한 열과 행만 조회한다. API는 5장과 똑같이 동작하고, 데이터는 데이터베이스에 유지된다. |
| 실습-5 | springdoc-openapi로 API 문서를 자동 생성하고, Swagger UI에서 API를 확인하고 호출한다. 애노테이션으로 설명과 상태 코드를 보강한다. |

이것으로 1일차 과정의 목표인 **데이터베이스와 연동한 CRUD REST API**가 완성되었다.

