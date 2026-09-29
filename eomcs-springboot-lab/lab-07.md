# 7장. JPA 활용

6장에서는 엔티티와 리포지토리를 만들고, `save()`와 `findAll()`로 게시글을 저장하고 조회했다.
이번 장에서는 JPA로 데이터를 **등록·조회·변경·삭제**(CRUD)하는 방법을 익히고, 메서드 이름만으로 조회 조건을 만드는 **Query Method**와 객체를 대상으로 쿼리를 작성하는 **JPQL**을 배운다.
그리고 게시글과 댓글처럼 서로 관계가 있는 엔티티를 `@ManyToOne`, `@OneToMany`로 연결하고, 연관된 엔티티를 필요할 때 조회하는 **지연 로딩**의 개념을 이해한다.

## JPA로 CRUD 하기

### 트랜잭션

JPA로 데이터를 다루려면 먼저 **트랜잭션**(Transaction)을 이해해야 한다.
트랜잭션은 **여러 데이터베이스 작업을 하나로 묶어, 모두 성공하거나 모두 취소되게 하는 작업 단위**이다.

계좌 이체를 예로 들면, "A 계좌에서 출금"과 "B 계좌에 입금"은 반드시 함께 성공해야 한다. 출금만 되고 입금이 실패하면 안 된다.

| 용어 | 의미 |
| --- | --- |
| 커밋(Commit) | 트랜잭션 안의 작업을 모두 **확정**하여 데이터베이스에 반영한다. |
| 롤백(Rollback) | 트랜잭션 안의 작업을 모두 **취소**하고 이전 상태로 되돌린다. |

스프링에서는 메서드에 `@Transactional`을 붙이면, 메서드가 시작될 때 트랜잭션이 시작되고 메서드가 끝날 때 커밋된다. 메서드에서 예외가 발생하면 롤백된다.

```java
@Transactional
public void transfer(...) {
  // 이 메서드 안의 데이터베이스 작업은 하나의 트랜잭션으로 묶인다.
}
```

JPA는 **트랜잭션 안에서** 엔티티를 관리한다. 이번 장에서 배우는 **변경 감지**와 **지연 로딩**도 트랜잭션 안에서만 동작한다.

> `JpaRepository`의 `save()`, `findById()` 같은 메서드는 각자 트랜잭션을 갖고 있으므로 따로 `@Transactional`을 붙이지 않아도 동작한다.
> 여러 작업을 하나의 트랜잭션으로 묶거나 변경 감지를 사용하려면, 그 작업들을 호출하는 메서드에 `@Transactional`을 붙인다.

> `@Transactional`은 `org.springframework.transaction.annotation` 패키지의 것을 사용한다.
> 트랜잭션을 어느 계층에 적용하는지는 8장의 Controller-Service-Repository 계층 구성에서 다룬다.

### 등록과 조회

등록과 조회는 6장에서 사용한 방법과 같다.

```java
// 등록: id가 없는 새 엔티티 → INSERT
BoardEntity saved = boardJpaRepository.save(new BoardEntity("제목", "내용", "홍길동"));

// 한 개 조회 → SELECT ... WHERE id = ?
Optional<BoardEntity> board = boardJpaRepository.findById(saved.getId());

// 전체 조회 → SELECT ...
List<BoardEntity> boards = boardJpaRepository.findAll();
```

### 변경: 변경 감지

JPA에는 `update()` 같은 변경 메서드가 없다. 대신 **트랜잭션 안에서 조회한 엔티티의 값을 바꾸기만 하면**, 트랜잭션이 커밋될 때 JPA가 바뀐 부분을 찾아 **UPDATE SQL을 자동으로 실행**한다.
이 기능을 **변경 감지**(Dirty Checking)라고 한다.

```java
@Transactional
public void update(Long id) {
  BoardEntity board = boardJpaRepository.findById(id).orElseThrow();   // ① 조회 (SELECT)
  board.update("수정한 제목", "수정한 내용");                              // ② 엔티티의 값만 바꾼다.
  // save()를 호출하지 않는다.
}                                                                       // ③ 커밋 → UPDATE가 자동으로 실행된다.
```

```
① findById()    ──▶  SELECT (조회한 순간의 상태를 기억해 둔다)
② update()      ──▶  엔티티의 필드 값만 바뀐다. (SQL 실행 없음)
③ 메서드 종료(커밋) ──▶ 기억해 둔 상태와 비교 → 바뀐 것이 있으면 UPDATE 실행
```

엔티티의 값을 바꾸는 메서드는 `setTitle()`처럼 필드마다 만들기보다, **의미가 드러나는 메서드**로 만든다.

```java
@Entity
@Table(name = "board")
public class BoardEntity {
  ...
  // 게시글의 제목과 내용을 수정한다. (작성자는 바꿀 수 없다.)
  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
```

> 트랜잭션이 없는 곳에서 엔티티의 값을 바꾸면 변경 감지가 동작하지 않아 데이터베이스에 반영되지 않는다.
> 이때는 `save()`를 호출해야 변경 내용이 저장된다. (`save()`는 이미 있는 엔티티를 받으면 UPDATE를 실행한다.)

### 삭제

```java
boardJpaRepository.deleteById(id);        // 번호로 삭제
boardJpaRepository.delete(board);         // 엔티티로 삭제
```

> Spring Data JPA의 `deleteById()`는 먼저 엔티티를 조회(SELECT)한 다음 삭제(DELETE)한다. 그래서 로그에 SQL이 두 번 출력된다.

### CRUD 메서드 정리

| 작업 | 방법 | 실행되는 SQL |
| --- | --- | --- |
| 등록 (Create) | `save(새 엔티티)` | `INSERT` |
| 조회 (Read) | `findById(id)`, `findAll()`, `count()` | `SELECT` |
| 변경 (Update) | 트랜잭션 안에서 조회한 엔티티의 값 변경 (변경 감지) | 커밋할 때 `UPDATE` |
| 삭제 (Delete) | `deleteById(id)`, `delete(엔티티)` | `SELECT` 후 `DELETE` |

## Query Method

### 메서드 이름으로 조회 조건 만들기

`findById()`, `findAll()`만으로는 "작성자가 홍길동인 게시글"이나 "제목에 JPA가 들어간 게시글"을 조회할 수 없다.
Spring Data JPA는 리포지토리 인터페이스에 **정해진 규칙에 따라 메서드 이름만 선언하면**, 이름을 분석하여 조회 쿼리를 자동으로 만든다. 이를 **쿼리 메서드**(Query Method)라고 한다.

```java
public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {

  List<BoardEntity> findByWriter(String writer);
}
```

```
find  By  Writer
 │    │    └─ 조건: writer 필드가 파라미터 값과 같은
 │    └─ 여기부터 조건
 └─ 조회한다
```

`findByWriter("홍길동")`을 호출하면 다음과 같은 SQL이 실행된다.

```sql
SELECT ... FROM board WHERE writer = '홍길동'
```

메서드 이름의 **필드 이름**은 테이블의 열 이름이 아니라 **엔티티의 필드 이름**이다. 필드 이름이 틀리면 애플리케이션이 시작될 때 오류가 발생한다.

### 쿼리 메서드 이름 규칙

**메서드 이름의 시작**

| 시작 | 의미 | 리턴 타입 예 |
| --- | --- | --- |
| `findBy...` | 조회 | `List<BoardEntity>`, `Optional<BoardEntity>` |
| `countBy...` | 개수 | `long` |
| `existsBy...` | 있는지 확인 | `boolean` |
| `deleteBy...` | 삭제 | `void`, `long` |
| `findTop3By...`, `findFirstBy...` | 앞에서부터 N개(1개)만 조회 | `List<BoardEntity>`, `Optional<BoardEntity>` |

**조건 키워드**

| 키워드 | 메서드 이름 예 | 만들어지는 조건 |
| --- | --- | --- |
| (없음) | `findByWriter(String writer)` | `writer = ?` |
| `And` | `findByWriterAndTitle(String writer, String title)` | `writer = ? AND title = ?` |
| `Or` | `findByTitleOrContent(String title, String content)` | `title = ? OR content = ?` |
| `Containing` | `findByTitleContaining(String keyword)` | `title LIKE '%키워드%'` |
| `StartingWith` | `findByTitleStartingWith(String prefix)` | `title LIKE '접두어%'` |
| `GreaterThan` | `findByIdGreaterThan(Long id)` | `id > ?` |
| `Between` | `findByIdBetween(Long start, Long end)` | `id BETWEEN ? AND ?` |
| `In` | `findByWriterIn(List<String> writers)` | `writer IN (?, ?, ...)` |
| `IsNull` | `findByContentIsNull()` | `content IS NULL` |
| `OrderBy...Asc/Desc` | `findByWriterOrderByIdDesc(String writer)` | `writer = ? ORDER BY id DESC` |

> 조건이 많아지면 메서드 이름이 너무 길어진다. 예를 들어 `findByWriterAndTitleContainingOrderByIdDesc`처럼 이름이 길어지면 읽기 어렵다.
> 이럴 때는 다음 절의 **JPQL**로 쿼리를 직접 작성하는 것이 좋다.

## JPQL

### JPQL이란?

**JPQL**(Java Persistence Query Language)은 JPA가 제공하는 **객체 지향 쿼리 언어**이다.
SQL과 모양은 비슷하지만, **테이블이 아니라 엔티티를 대상으로** 쿼리를 작성한다.

| 구분 | SQL | JPQL |
| --- | --- | --- |
| 대상 | 테이블과 열 | **엔티티와 필드** |
| 예 | `SELECT * FROM board WHERE writer = '홍길동'` | `select b from BoardEntity b where b.writer = '홍길동'` |
| 실행 | 데이터베이스가 직접 실행 | Hibernate가 SQL로 **변환**하여 실행 |

```sql
select b from BoardEntity b where b.writer = :writer order by b.id desc
```

| 부분 | 의미 |
| --- | --- |
| `BoardEntity` | 테이블 이름(`board`)이 아니라 **엔티티 이름**이다. 대소문자를 구별한다. |
| `b` | 엔티티의 **별칭**(alias). JPQL에서는 별칭이 필수이다. |
| `select b` | 엔티티 전체를 조회한다. (SQL의 `SELECT *`에 해당) |
| `b.writer` | 열 이름이 아니라 **엔티티의 필드 이름**이다. |
| `:writer` | **이름 있는 파라미터**. 메서드의 파라미터 값이 들어간다. |

JPQL은 엔티티를 대상으로 작성하므로, 데이터베이스를 MySQL에서 PostgreSQL로 바꾸더라도 쿼리를 고칠 필요가 없다.
Hibernate가 사용하는 데이터베이스에 맞는 SQL로 변환해 준다.

### @Query로 JPQL 작성하기

리포지토리 메서드에 `@Query`를 붙이고 JPQL을 작성한다. 메서드 이름은 자유롭게 지을 수 있다.

```java
public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {

  // 제목 또는 내용에 키워드가 포함된 게시글을 최신순으로 조회한다.
  @Query("""
      select b from BoardEntity b
      where b.title like concat('%', :keyword, '%')
         or b.content like concat('%', :keyword, '%')
      order by b.id desc
      """)
  List<BoardEntity> search(@Param("keyword") String keyword);
}
```

- `"""`로 감싼 문자열은 여러 줄 문자열(**텍스트 블록**, Java 15 이상)이다. 긴 쿼리를 읽기 쉽게 작성할 수 있다.
- `@Param("keyword")`는 메서드 파라미터를 JPQL의 `:keyword` 자리에 연결한다.
- JPQL에 문법 오류가 있거나 엔티티·필드 이름이 틀리면 애플리케이션이 시작될 때 오류가 발생하므로, 실수를 일찍 발견할 수 있다.

### Query Method와 JPQL 비교

| 구분 | Query Method | JPQL (`@Query`) |
| --- | --- | --- |
| 작성 방법 | 메서드 이름만 선언 | 메서드에 쿼리를 직접 작성 |
| 장점 | 간단한 조회를 빠르게 만들 수 있다. | 복잡한 조건, 조인, 집계 등을 자유롭게 표현할 수 있다. |
| 단점 | 조건이 많으면 이름이 길어진다. | 쿼리를 직접 작성해야 한다. |
| 사용 기준 | 조건이 1~2개인 단순 조회 | 조건이 복잡하거나 이름이 너무 길어질 때 |

> 데이터베이스 전용 기능이 꼭 필요하면 `@Query(value = "SELECT ...", nativeQuery = true)`로 **SQL을 직접** 작성할 수도 있다(네이티브 쿼리).
> 다만 특정 데이터베이스에 묶이게 되므로, 가능하면 JPQL을 사용한다.

## 엔티티 연관관계

### 테이블의 관계와 객체의 관계

게시글에는 여러 개의 댓글이 달린다. 이런 관계를 **일대다**(1:N) 관계라고 한다.

```
게시글(board) 1 ────< N 댓글(board_comment)
```

테이블과 객체는 이 관계를 **다른 방식**으로 표현한다.

**테이블: 외래 키로 연결한다**

댓글 테이블에 게시글의 번호를 저장하는 **외래 키**(Foreign Key) 열을 둔다.

`board` 테이블

| id | title | writer |
| --- | --- | --- |
| 1 | 스프링 부트 시작하기 | 홍길동 |

`board_comment` 테이블

| id | board_id (외래 키) | content | writer |
| --- | --- | --- | --- |
| 1 | **1** | 좋은 글이네요. | 임꺽정 |
| 2 | **1** | 잘 읽었습니다. | 유관순 |

```sql
CREATE TABLE IF NOT EXISTS board_comment (
  id       BIGINT       GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  board_id BIGINT       NOT NULL,
  content  VARCHAR(500) NOT NULL,
  writer   VARCHAR(50)  NOT NULL,
  CONSTRAINT fk_board_comment_board
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE
);
```

| 구문 | 의미 |
| --- | --- |
| `FOREIGN KEY (board_id) REFERENCES board (id)` | `board_id` 열의 값은 반드시 `board` 테이블에 있는 `id`여야 한다. 없는 게시글 번호로 댓글을 저장할 수 없다. |
| `ON DELETE CASCADE` | 게시글이 삭제되면 그 게시글의 댓글도 **함께 삭제**된다. |

**객체: 참조로 연결한다**

객체에서는 번호 대신 **객체 자체를 참조**한다.

```java
public class CommentEntity {
  private BoardEntity board;      // 게시글 번호(Long)가 아니라 게시글 객체를 참조한다.
  ...
}
```

JPA의 **연관관계 매핑**은 객체의 참조(`comment.getBoard()`)와 테이블의 외래 키(`board_id`)를 연결해 준다.

### @ManyToOne: 다대일

댓글 **여러 개**(Many)가 게시글 **하나**(One)에 속하므로, 댓글 쪽에서 게시글을 참조할 때 `@ManyToOne`을 사용한다.

```java
@Entity
@Table(name = "board_comment")
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)          // 댓글(N) → 게시글(1)
  @JoinColumn(name = "board_id")              // 외래 키 열 이름
  private BoardEntity board;

  private String content;
  private String writer;
  ...
}
```

| 애노테이션 | 의미 |
| --- | --- |
| `@ManyToOne` | 이 필드가 다대일 관계의 **"하나" 쪽 엔티티**를 참조한다. |
| `fetch = FetchType.LAZY` | 연관된 게시글을 **필요할 때 조회**한다. (지연 로딩, 뒤에서 설명) |
| `@JoinColumn(name = "board_id")` | 이 관계를 저장하는 **외래 키 열**을 지정한다. |

댓글을 저장할 때는 게시글 번호가 아니라 **게시글 엔티티**를 넘긴다. JPA가 게시글의 `id`를 꺼내 `board_id` 열에 저장한다.

```java
BoardEntity board = boardJpaRepository.findById(1L).orElseThrow();
commentJpaRepository.save(new CommentEntity(board, "좋은 글이네요.", "임꺽정"));
// → INSERT INTO board_comment (board_id, content, writer, id) VALUES (1, '좋은 글이네요.', '임꺽정', default)
```

### @OneToMany: 일대다

반대로 게시글 쪽에서 자신의 댓글 목록을 참조하려면 `@OneToMany`를 사용한다.

```java
@Entity
@Table(name = "board")
public class BoardEntity {
  ...
  @OneToMany(mappedBy = "board")                        // 게시글(1) → 댓글(N)
  private List<CommentEntity> comments = new ArrayList<>();

  public List<CommentEntity> getComments() {
    return comments;
  }
}
```

`mappedBy = "board"`는 "이 관계는 `CommentEntity`의 `board` 필드가 관리한다"는 뜻이다.

- 외래 키(`board_id`)는 `board_comment` 테이블에 있으므로, 관계의 **주인**(Owner)은 `CommentEntity.board`이다.
- `BoardEntity.comments`는 주인이 아니므로 **조회만** 할 수 있다. 이 목록에 댓글을 추가해도 데이터베이스에는 저장되지 않는다.
- 댓글을 저장하려면 `CommentEntity`의 `board`에 게시글을 지정하여 저장한다.

### 단방향과 양방향

| 구분 | 매핑 | 가능한 탐색 |
| --- | --- | --- |
| 단방향 | `CommentEntity.board` (`@ManyToOne`)만 | 댓글 → 게시글 |
| 양방향 | `CommentEntity.board` + `BoardEntity.comments` (`@OneToMany`) | 댓글 → 게시글, 게시글 → 댓글 |

테이블은 외래 키 하나로 양쪽 방향을 모두 조회할 수 있지만, 객체는 참조가 있는 방향으로만 탐색할 수 있다.
그래서 게시글에서 댓글 목록을 꺼내야 한다면 `@OneToMany`를 추가하여 **양방향**으로 매핑한다.

> 연관관계는 꼭 필요한 방향만 매핑하는 것이 좋다. 양방향 매핑은 편리하지만, 두 객체의 참조를 함께 관리해야 하는 부담이 생긴다.
> 실무에서는 `@ManyToOne` 단방향을 기본으로 하고, 반대 방향 탐색이 꼭 필요할 때만 `@OneToMany`를 추가한다.

## 지연 로딩

### 즉시 로딩과 지연 로딩

댓글을 조회할 때 그 댓글의 게시글도 함께 조회해야 할까?
JPA는 연관된 엔티티를 **언제 조회할지** 두 가지 방식 중에서 선택할 수 있다.

| 방식 | 설정 | 동작 |
| --- | --- | --- |
| **즉시 로딩**(Eager Loading) | `fetch = FetchType.EAGER` | 댓글을 조회할 때 게시글도 **함께 조회**한다. (조인 SQL 또는 SQL 추가 실행) |
| **지연 로딩**(Lazy Loading) | `fetch = FetchType.LAZY` | 댓글만 조회하고, 게시글은 **실제로 사용할 때** 조회한다. |

```java
@Transactional
public void showComment() {
  CommentEntity comment = commentJpaRepository.findById(1L).orElseThrow();
  // 지연 로딩: 여기까지는 board_comment 테이블만 조회한다.

  System.out.println(comment.getContent());         // 댓글 내용 → 추가 SQL 없음

  BoardEntity board = comment.getBoard();            // 게시글 객체를 꺼내도 아직 SQL 없음
  System.out.println(board.getTitle());              // 게시글의 값을 실제로 사용하는 순간 → board 테이블 SELECT
}
```

### 프록시

지연 로딩으로 설정하면 `comment.getBoard()`는 진짜 `BoardEntity` 대신 **프록시**(Proxy)라는 **대리 객체**를 리턴한다.
프록시는 `BoardEntity`를 상속하여 Hibernate가 만든 객체로, 처음에는 `id`만 가지고 있다가 다른 값을 요청받으면 그때 데이터베이스에서 게시글을 조회한다.

```
comment.getBoard()           → 프록시 (id = 1만 알고 있다)
comment.getBoard().getId()   → 프록시가 바로 대답 (SQL 없음)
comment.getBoard().getTitle()→ 프록시가 SELECT 실행 → 진짜 게시글 데이터로 채운 후 대답
```

> 6장에서 "엔티티 클래스를 `final`로 선언하지 않는다"고 한 이유가 이것이다. 프록시는 엔티티 클래스를 **상속**해서 만들기 때문이다.

### 지연 로딩을 사용하는 이유

댓글 목록 화면에는 댓글 내용만 필요하고 게시글 정보는 필요 없을 수 있다.
즉시 로딩을 사용하면 필요 없는 게시글까지 매번 조회하게 되고, 연관관계가 많아질수록 한 번의 조회에 여러 테이블이 줄줄이 조회된다.
지연 로딩을 사용하면 **필요한 데이터만, 필요한 순간에** 조회하므로 불필요한 조회를 줄일 수 있다.

| 애노테이션 | 기본값 | 권장 |
| --- | --- | --- |
| `@ManyToOne` | **`EAGER`** (즉시 로딩) | `fetch = FetchType.LAZY`로 **바꿔서** 사용한다. |
| `@OneToMany` | `LAZY` (지연 로딩) | 기본값 그대로 사용한다. |

> **모든 연관관계는 지연 로딩(`LAZY`)으로 설정하는 것이 원칙이다.** `@ManyToOne`은 기본값이 즉시 로딩이므로 반드시 `fetch = FetchType.LAZY`를 지정한다.

### 지연 로딩의 주의 사항

**1) 트랜잭션 밖에서는 지연 로딩을 할 수 없다**

프록시가 데이터베이스를 조회하려면 JPA가 엔티티를 관리하는 상태, 즉 **트랜잭션 안**이어야 한다.
트랜잭션이 끝난 후에 프록시의 값을 사용하면 `LazyInitializationException` 예외가 발생한다.

```java
// 트랜잭션이 없는 메서드
CommentEntity comment = commentJpaRepository.findById(1L).orElseThrow();   // 조회 트랜잭션은 여기서 끝난다.
comment.getBoard().getTitle();    // LazyInitializationException: could not initialize proxy - no Session
```

**2) N+1 문제**

댓글 10개를 조회한 후 각 댓글의 게시글 제목을 출력하면, 댓글 목록 조회 SQL **1번**에 게시글 조회 SQL이 **최대 N번**(10번) 추가로 실행될 수 있다.
이를 **N+1 문제**라고 하며, JPA를 사용할 때 성능이 나빠지는 대표적인 원인이다.
연관된 엔티티를 한 번에 함께 조회하는 **페치 조인**(`join fetch`) 등으로 해결한다.

```java
// 댓글을 조회할 때 게시글도 조인하여 한 번에 가져온다.
@Query("select c from CommentEntity c join fetch c.board where c.writer = :writer")
List<CommentEntity> findByWriterWithBoard(@Param("writer") String writer);
```

> 페치 조인과 성능 최적화는 이 과정의 범위를 넘으므로, 이런 문제가 있다는 것만 기억해 두자.

## 정리

| 주제 | 핵심 내용 |
| --- | --- |
| 트랜잭션 | `@Transactional`로 여러 작업을 하나로 묶는다. 변경 감지와 지연 로딩은 트랜잭션 안에서 동작한다. |
| CRUD | 등록 `save()`, 조회 `findById()`/`findAll()`, 변경은 **변경 감지**, 삭제 `deleteById()` |
| Query Method | `findByWriter`, `findByTitleContaining`처럼 **메서드 이름**으로 조회 조건을 만든다. |
| JPQL | **엔티티와 필드**를 대상으로 작성하는 쿼리. `@Query`로 리포지토리 메서드에 작성한다. |
| `@ManyToOne` | 다대일 관계. 외래 키를 가진 쪽(댓글)에서 사용하며, `@JoinColumn`으로 외래 키 열을 지정한다. |
| `@OneToMany` | 일대다 관계. `mappedBy`로 관계의 주인을 지정하며, 조회만 할 수 있다. |
| 지연 로딩 | 연관된 엔티티를 실제로 사용할 때 조회한다. 모든 연관관계는 `LAZY`로 설정하는 것이 원칙이다. |

## 실습

이번 장의 실습은 6장에서 사용한 `hello` 프로젝트의 `board` 패키지에서 이어서 진행한다.
6장과 마찬가지로 **테이블은 DDL 스크립트(`db/schema.sql`)로 정의**하고, Hibernate는 `ddl-auto=validate`로 검사만 한다.

실습을 마치면 다음 파일이 추가되거나 바뀐다.

```
src/main
├── java/com/example/hello/board
│   ├── BoardEntity.java              ← update() 메서드, 댓글 목록 추가   (실습-1, 5)
│   ├── BoardJpaRepository.java       ← 쿼리 메서드, JPQL 추가           (실습-3, 4)
│   ├── CommentEntity.java            ← 댓글 엔티티                     (실습-5)
│   ├── CommentJpaRepository.java     ← 댓글 리포지토리                  (실습-5)
│   ├── BoardJpaPractice.java         ← JPA 실습 코드                   (실습-1 ~ 6)
│   └── BoardDataRunner.java          ← 실습 코드를 호출한다              (실습-1 ~ 6)
└── resources/db
    ├── schema.sql                    ← 댓글 테이블 추가                  (실습-5)
    └── sample-data.sql               ← 실습용 샘플 데이터                (실습-1)
```

### 실습-1: 실습 준비하기

**1) 엔티티에 변경 메서드 추가하기**

`BoardEntity.java`에 게시글의 제목과 내용을 수정하는 메서드를 추가한다.

```java
  // 게시글의 제목과 내용을 수정한다. (작성자는 바꿀 수 없다.)
  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
```

**2) 샘플 데이터 스크립트 만들기**

실습 결과를 예측할 수 있도록 게시글 데이터를 정해진 상태로 초기화한다.
`src/main/resources/db` 폴더에 `sample-data.sql` 파일을 만들고 다음과 같이 작성한다.

```sql
-- 실습용 샘플 데이터
-- H2 콘솔에서 직접 실행한다. (애플리케이션이 자동으로 실행하지 않는다.)

-- 기존 게시글을 모두 삭제하고, 게시글 번호를 1부터 다시 시작한다.
DELETE FROM board;
ALTER TABLE board ALTER COLUMN id RESTART WITH 1;

-- 샘플 게시글
INSERT INTO board (title, content, writer) VALUES
  ('스프링 부트 시작하기', '스프링 부트로 첫 프로젝트를 만들었다.', '홍길동'),
  ('JPA 엔티티 만들기', '엔티티와 테이블을 매핑했다.', '홍길동'),
  ('REST API 설계', 'URI와 HTTP 메서드로 API를 설계했다.', '임꺽정'),
  ('스프링 DI 정리', '생성자 주입을 사용하자.', '유관순'),
  ('JPA 쿼리 메서드', '메서드 이름으로 조회 조건을 만든다.', '임꺽정');

SELECT * FROM board;
```

> `schema.sql`은 `spring.sql.init.schema-locations`에 지정했으므로 애플리케이션이 자동으로 실행하지만, `sample-data.sql`은 지정하지 않았으므로 실행되지 않는다.
> 이 파일은 실습 데이터를 초기화하고 싶을 때 H2 콘솔에 붙여 넣어 실행한다.

**3) 샘플 데이터 넣기**

애플리케이션을 실행하고 [http://localhost:8080/h2-console](http://localhost:8080/h2-console) 에 접속한다. (JDBC URL: `jdbc:h2:file:./data/hellodb`)
`sample-data.sql`의 내용을 모두 복사하여 입력 창에 붙여 넣고 `Run` 버튼을 누른다.
마지막 `SELECT` 결과로 다음 게시글 다섯 개가 출력되는지 확인한다.

| ID | TITLE | WRITER |
| --- | --- | --- |
| 1 | 스프링 부트 시작하기 | 홍길동 |
| 2 | JPA 엔티티 만들기 | 홍길동 |
| 3 | REST API 설계 | 임꺽정 |
| 4 | 스프링 DI 정리 | 유관순 |
| 5 | JPA 쿼리 메서드 | 임꺽정 |

확인한 후에는 `Ctrl + C`를 눌러 애플리케이션을 종료한다.

**4) 실습 코드 클래스 만들기**

이번 장의 실습 코드는 `BoardJpaPractice` 클래스에 메서드로 작성하고, `BoardDataRunner`가 애플리케이션이 시작될 때 그 메서드를 호출하도록 한다.
`board` 폴더에 `BoardJpaPractice.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BoardJpaPractice {

  private final BoardJpaRepository boardJpaRepository;

  public BoardJpaPractice(BoardJpaRepository boardJpaRepository) {
    this.boardJpaRepository = boardJpaRepository;
  }

  // 전체 게시글 출력
  @Transactional(readOnly = true)
  public void printAll() {
    System.out.println("--- 전체 게시글 ---");
    for (BoardEntity board : boardJpaRepository.findAll()) {
      System.out.println(board);
    }
  }
}
```

> `@Transactional(readOnly = true)`는 **조회만 하는** 트랜잭션이라는 뜻이다. 변경 감지를 하지 않아서 조회 성능이 조금 좋아지고, 실수로 데이터를 바꾸는 것도 막을 수 있다.

**5) 실행 코드 바꾸기**

6장에서 만든 `BoardDataRunner.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BoardDataRunner implements CommandLineRunner {

  private final BoardJpaPractice practice;

  public BoardDataRunner(BoardJpaPractice practice) {
    this.practice = practice;
  }

  @Override
  public void run(String... args) {
    System.out.println("========== 실습 시작 ==========");

    practice.printAll();

    System.out.println("========== 실습 끝 ==========");
  }
}
```

> 이후 실습에서는 `run()` 메서드의 `===` 줄 사이에 있는 코드만 바꾼다.

**6) 실행하기**

애플리케이션을 실행하고, 로그에 샘플 게시글 다섯 개가 출력되는지 확인한다.

```
========== 실습 시작 ==========
--- 전체 게시글 ---
Hibernate: 
    select
        be1_0.id,
        ...
    from
        board be1_0
BoardEntity[id=1, title=스프링 부트 시작하기, writer=홍길동]
BoardEntity[id=2, title=JPA 엔티티 만들기, writer=홍길동]
BoardEntity[id=3, title=REST API 설계, writer=임꺽정]
BoardEntity[id=4, title=스프링 DI 정리, writer=유관순]
BoardEntity[id=5, title=JPA 쿼리 메서드, writer=임꺽정]
========== 실습 끝 ==========
```

### 실습-2: JPA로 CRUD 하기

게시글을 등록하고, 조회하고, 변경하고, 삭제한다. 각 작업을 **별도의 트랜잭션**으로 실행하여 어떤 SQL이 언제 실행되는지 확인한다.

**1) CRUD 메서드 추가하기**

`BoardJpaPractice.java`에 다음 메서드를 추가한다.

```java
  // 등록
  @Transactional
  public Long create() {
    System.out.println("--- 등록 ---");
    BoardEntity saved = boardJpaRepository.save(new BoardEntity("JPA 실습", "CRUD를 연습한다.", "테스터"));
    System.out.println("등록한 게시글: " + saved);
    return saved.getId();
  }

  // 조회
  @Transactional(readOnly = true)
  public void read(Long id) {
    System.out.println("--- 조회 ---");
    boardJpaRepository.findById(id).ifPresentOrElse(
        board -> System.out.println("조회한 게시글: " + board + ", 내용: " + board.getContent()),
        () -> System.out.println(id + "번 게시글이 없다."));
  }

  // 변경: 변경 감지
  @Transactional
  public void update(Long id) {
    System.out.println("--- 변경 ---");
    BoardEntity board = boardJpaRepository.findById(id).orElseThrow();
    board.update("JPA 실습 (수정)", "변경 감지로 수정했다.");
    System.out.println("엔티티의 값을 바꿨다. save()는 호출하지 않는다.");
  }                                   // 메서드가 끝나면서 커밋된다. 이때 UPDATE가 실행된다.

  // 삭제
  @Transactional
  public void delete(Long id) {
    System.out.println("--- 삭제 ---");
    boardJpaRepository.deleteById(id);
  }
```

> `ifPresentOrElse(값이 있을 때, 값이 없을 때)`는 `Optional`에 값이 있으면 첫 번째 코드를, 없으면 두 번째 코드를 실행한다.
> `orElseThrow()`는 값이 있으면 그 값을 리턴하고, 없으면 예외를 발생시킨다.

**2) 실행 코드 바꾸기**

`BoardDataRunner.java`의 `run()` 메서드에서 `===` 줄 사이를 다음과 같이 바꾼다.

```java
    Long id = practice.create();   // 등록
    practice.read(id);             // 조회
    practice.update(id);           // 변경
    practice.read(id);             // 변경 확인
    practice.delete(id);           // 삭제
    practice.read(id);             // 삭제 확인
```

**3) 실행하고 확인하기**

애플리케이션을 실행하고 로그를 확인한다. (SQL 형식은 버전에 따라 조금 다를 수 있다.)

```
--- 등록 ---
Hibernate: insert into board (content, title, writer, id) values (?, ?, ?, default)
등록한 게시글: BoardEntity[id=6, title=JPA 실습, writer=테스터]
--- 조회 ---
Hibernate: select ... from board be1_0 where be1_0.id=?
조회한 게시글: BoardEntity[id=6, title=JPA 실습, writer=테스터], 내용: CRUD를 연습한다.
--- 변경 ---
Hibernate: select ... from board be1_0 where be1_0.id=?
엔티티의 값을 바꿨다. save()는 호출하지 않는다.
Hibernate: update board set content=?, title=?, writer=? where id=?
--- 조회 ---
Hibernate: select ... from board be1_0 where be1_0.id=?
조회한 게시글: BoardEntity[id=6, title=JPA 실습 (수정), writer=테스터], 내용: 변경 감지로 수정했다.
--- 삭제 ---
Hibernate: select ... from board be1_0 where be1_0.id=?
Hibernate: delete from board where id=?
--- 조회 ---
Hibernate: select ... from board be1_0 where be1_0.id=?
6번 게시글이 없다.
```

> 실제 로그에서는 `format_sql=true` 설정 때문에 SQL이 여러 줄로 나뉘어 출력된다. 위에서는 보기 쉽게 한 줄로 줄였다.

다음을 확인한다.

- **변경**: `save()`를 호출하지 않았는데도 `UPDATE` SQL이 실행되었다. 그리고 `UPDATE`는 "엔티티의 값을 바꿨다" 메시지 **다음에**, 즉 메서드가 끝나고 트랜잭션이 커밋될 때 실행되었다. 이것이 **변경 감지**이다.
- **삭제**: `deleteById()`는 먼저 `SELECT`로 게시글을 조회한 다음 `DELETE`를 실행했다.
- 삭제한 후에는 조회 결과가 없다.

**4) (확인) 트랜잭션이 없으면 변경 감지가 동작하지 않는다**

`BoardJpaPractice.java`의 `update()` 메서드에서 `@Transactional`을 잠시 주석 처리한다.

```java
  // @Transactional
  public void update(Long id) {
```

애플리케이션을 다시 실행하면, "엔티티의 값을 바꿨다" 메시지 다음에 `UPDATE`가 실행되지 않고, 변경 후 조회한 게시글의 제목도 바뀌지 않은 것을 확인할 수 있다.
트랜잭션이 없으면 `findById()`의 트랜잭션이 조회 직후 끝나 버려서, JPA가 엔티티의 변경을 감지하지 못하기 때문이다.

확인한 후에는 `@Transactional`의 주석을 해제하여 원래대로 되돌린다.

> 이 실습에서 등록한 게시글은 마지막에 삭제되므로, 실습을 여러 번 실행해도 샘플 데이터는 그대로 유지된다. (게시글 번호는 실행할 때마다 7, 8, ...로 늘어난다.)

### 실습-3: 쿼리 메서드로 조회하기

**1) 쿼리 메서드 선언하기**

`BoardJpaRepository.java`를 다음과 같이 바꾼다.

```java
package com.example.hello.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {

  // 작성자가 일치하는 게시글
  List<BoardEntity> findByWriter(String writer);

  // 제목에 키워드가 포함된 게시글
  List<BoardEntity> findByTitleContaining(String keyword);

  // 작성자가 일치하는 게시글을 번호의 역순으로
  List<BoardEntity> findByWriterOrderByIdDesc(String writer);

  // 작성자가 일치하는 게시글의 개수
  long countByWriter(String writer);

  // 제목이 일치하는 게시글이 있는지
  boolean existsByTitle(String title);

  // 최근 게시글 3개 (번호의 역순으로 3개)
  List<BoardEntity> findTop3ByOrderByIdDesc();
}
```

메서드를 **선언만** 하고 구현하지 않는다.

**2) 실습 메서드 추가하기**

`BoardJpaPractice.java`에 다음 메서드를 추가한다.

```java
  // 쿼리 메서드
  @Transactional(readOnly = true)
  public void queryMethods() {
    System.out.println("--- findByWriter(\"홍길동\") ---");
    boardJpaRepository.findByWriter("홍길동").forEach(System.out::println);

    System.out.println("--- findByTitleContaining(\"JPA\") ---");
    boardJpaRepository.findByTitleContaining("JPA").forEach(System.out::println);

    System.out.println("--- findByWriterOrderByIdDesc(\"임꺽정\") ---");
    boardJpaRepository.findByWriterOrderByIdDesc("임꺽정").forEach(System.out::println);

    System.out.println("--- countByWriter(\"임꺽정\") ---");
    System.out.println(boardJpaRepository.countByWriter("임꺽정"));

    System.out.println("--- existsByTitle(\"REST API 설계\") ---");
    System.out.println(boardJpaRepository.existsByTitle("REST API 설계"));

    System.out.println("--- findTop3ByOrderByIdDesc() ---");
    boardJpaRepository.findTop3ByOrderByIdDesc().forEach(System.out::println);
  }
```

> `System.out::println`은 `board -> System.out.println(board)`를 줄여 쓴 메서드 참조이다.

**3) 실행 코드 바꾸기**

`BoardDataRunner.java`의 `run()` 메서드에서 `===` 줄 사이를 다음과 같이 바꾼다.

```java
    practice.queryMethods();
```

**4) 실행하고 확인하기**

애플리케이션을 실행하고, 각 쿼리 메서드의 결과와 실행된 SQL의 `where`, `order by` 부분을 비교한다.

| 쿼리 메서드 | 결과 (게시글 번호) | 실행된 SQL의 조건 |
| --- | --- | --- |
| `findByWriter("홍길동")` | 1, 2 | `where be1_0.writer=?` |
| `findByTitleContaining("JPA")` | 2, 5 | `where be1_0.title like ? escape '\'` |
| `findByWriterOrderByIdDesc("임꺽정")` | 5, 3 | `where be1_0.writer=? order by be1_0.id desc` |
| `countByWriter("임꺽정")` | 2 | `select count(be1_0.id) ... where be1_0.writer=?` |
| `existsByTitle("REST API 설계")` | true | `... where be1_0.title=? fetch first ? rows only` |
| `findTop3ByOrderByIdDesc()` | 5, 4, 3 | `order by be1_0.id desc fetch first ? rows only` |

메서드 이름만 선언했는데, Spring Data JPA가 이름을 분석하여 알맞은 조건의 SQL을 만들었다. (SQL의 세부 형식은 버전과 데이터베이스에 따라 다를 수 있다.)

**5) (확인) 필드 이름이 틀리면**

`BoardJpaRepository.java`에 엔티티에 없는 필드 이름으로 메서드를 잠시 추가해 본다.

```java
  List<BoardEntity> findByAuthor(String author);     // BoardEntity에는 author 필드가 없다.
```

애플리케이션을 실행하면 다음과 비슷한 오류가 발생하며 실행에 실패한다.

```
... No property 'author' found for type 'BoardEntity' ...
```

쿼리 메서드의 이름은 애플리케이션이 시작될 때 검사되므로, 오타를 일찍 발견할 수 있다. 확인한 후에는 추가한 메서드를 삭제한다.

### 실습-4: JPQL로 조회하기

제목 또는 내용에 키워드가 포함된 게시글을 조회하는 쿼리를 JPQL로 작성한다.
쿼리 메서드로 만들면 `findByTitleContainingOrContentContainingOrderByIdDesc(String title, String content)`처럼 이름이 너무 길어지는 경우이다.

**1) JPQL 메서드 추가하기**

`BoardJpaRepository.java`에 다음 메서드를 추가한다.

```java
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

  // 제목 또는 내용에 키워드가 포함된 게시글을 최신순으로 (JPQL)
  @Query("""
      select b from BoardEntity b
      where b.title like concat('%', :keyword, '%')
         or b.content like concat('%', :keyword, '%')
      order by b.id desc
      """)
  List<BoardEntity> search(@Param("keyword") String keyword);
```

**2) 실습 메서드 추가하기**

`BoardJpaPractice.java`에 다음 메서드를 추가한다.

```java
  // JPQL
  @Transactional(readOnly = true)
  public void jpql() {
    System.out.println("--- search(\"스프링\") ---");
    boardJpaRepository.search("스프링").forEach(System.out::println);

    System.out.println("--- search(\"설계\") ---");
    boardJpaRepository.search("설계").forEach(System.out::println);
  }
```

**3) 실행 코드 바꾸기**

`BoardDataRunner.java`의 `run()` 메서드에서 `===` 줄 사이를 다음과 같이 바꾼다.

```java
    practice.jpql();
```

**4) 실행하고 확인하기**

애플리케이션을 실행하고 결과를 확인한다.

```
--- search("스프링") ---
Hibernate: 
    select
        be1_0.id,
        be1_0.content,
        be1_0.title,
        be1_0.writer 
    from
        board be1_0 
    where
        be1_0.title like ('%'||?||'%') escape '' 
        or be1_0.content like ('%'||?||'%') escape '' 
    order by
        be1_0.id desc
BoardEntity[id=4, title=스프링 DI 정리, writer=유관순]
BoardEntity[id=1, title=스프링 부트 시작하기, writer=홍길동]
--- search("설계") ---
...
BoardEntity[id=3, title=REST API 설계, writer=임꺽정]
```

- `search("스프링")`: 제목에 "스프링"이 있는 4번과, 제목과 내용 모두에 "스프링"이 있는 1번이 번호의 역순으로 조회되었다.
- `search("설계")`: 제목(`REST API 설계`)과 내용(`...API를 설계했다.`)에 "설계"가 있는 3번이 조회되었다.
- JPQL의 `BoardEntity`, `b.title`이 SQL에서는 테이블 `board`, 열 `title`로 **변환**되었다. (SQL의 세부 형식은 버전에 따라 다를 수 있다.)

**5) (확인) JPQL은 엔티티를 대상으로 한다**

`@Query`의 JPQL에서 `BoardEntity`를 테이블 이름인 `board`로 잠시 바꿔 본다.

```java
      select b from board b
```

애플리케이션을 실행하면 `board`라는 **엔티티**를 찾을 수 없다는 오류가 발생하며 실행에 실패한다.
JPQL에는 테이블 이름이 아니라 **엔티티 이름**을 써야 한다. 확인한 후에는 `BoardEntity`로 되돌린다.

### 실습-5: 게시글과 댓글 연관관계 매핑하기

댓글 테이블을 DDL로 정의하고, 댓글 엔티티와 게시글 엔티티를 `@ManyToOne`, `@OneToMany`로 연결한다.

**1) 댓글 테이블 정의하기**

`src/main/resources/db/schema.sql` 파일 아래에 다음 DDL을 추가한다.

```sql
-- 댓글 테이블
-- 게시글(board) 하나에 댓글 여러 개가 달린다. (1:N)
CREATE TABLE IF NOT EXISTS board_comment (
  id       BIGINT       GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,  -- 댓글 번호 (자동 증가)
  board_id BIGINT       NOT NULL,                                      -- 게시글 번호 (외래 키)
  content  VARCHAR(500) NOT NULL,                                      -- 댓글 내용
  writer   VARCHAR(50)  NOT NULL,                                      -- 작성자
  CONSTRAINT fk_board_comment_board
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE     -- 게시글이 삭제되면 댓글도 삭제
);
```

> `schema.sql`은 애플리케이션이 시작될 때마다 실행되므로, 다음에 애플리케이션을 실행하면 `board_comment` 테이블이 만들어진다. `board` 테이블은 이미 있으므로 그대로 유지된다.

**2) 댓글 엔티티 만들기**

`board` 폴더에 `CommentEntity.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "board_comment")
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)        // 댓글(N) → 게시글(1), 지연 로딩
  @JoinColumn(name = "board_id")            // 외래 키 열
  private BoardEntity board;

  private String content;

  private String writer;

  protected CommentEntity() {
  }

  public CommentEntity(BoardEntity board, String content, String writer) {
    this.board = board;
    this.content = content;
    this.writer = writer;
  }

  public Long getId() {
    return id;
  }

  public BoardEntity getBoard() {
    return board;
  }

  public String getContent() {
    return content;
  }

  public String getWriter() {
    return writer;
  }

  @Override
  public String toString() {
    // board는 지연 로딩되므로 게시글 번호만 출력한다.
    return "CommentEntity[id=" + id + ", boardId=" + board.getId() + ", content=" + content + ", writer=" + writer + "]";
  }
}
```

**3) 게시글 엔티티에 댓글 목록 추가하기**

`BoardEntity.java`에 다음 필드와 메서드를 추가한다.

```java
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;

  @OneToMany(mappedBy = "board")                          // 게시글(1) → 댓글(N), 관계의 주인은 CommentEntity.board
  private List<CommentEntity> comments = new ArrayList<>();

  public List<CommentEntity> getComments() {
    return comments;
  }
```

> `BoardEntity`의 `toString()`에는 `comments`를 넣지 않는다. `toString()`을 호출할 때마다 댓글 목록을 조회하게 되기 때문이다.

**4) 댓글 리포지토리 만들기**

`board` 폴더에 `CommentJpaRepository.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

  // 게시글 번호로 댓글 조회 (board 필드의 id)
  List<CommentEntity> findByBoardId(Long boardId);
}
```

> `findByBoardId`는 `CommentEntity`의 `board` 필드 안에 있는 `id`를 조건으로 사용한다. 쿼리 메서드는 이처럼 연관된 엔티티의 필드도 조건으로 사용할 수 있다.

**5) 실습 메서드 추가하기**

`BoardJpaPractice.java`에서 `CommentJpaRepository`도 주입받도록 필드와 생성자를 바꾸고, 다음 메서드를 추가한다.

```java
  private final BoardJpaRepository boardJpaRepository;
  private final CommentJpaRepository commentJpaRepository;          // 추가

  public BoardJpaPractice(BoardJpaRepository boardJpaRepository,
                          CommentJpaRepository commentJpaRepository) {   // 변경
    this.boardJpaRepository = boardJpaRepository;
    this.commentJpaRepository = commentJpaRepository;
  }
```

```java
  // 댓글 등록
  @Transactional
  public void addComments() {
    System.out.println("--- 댓글 등록 ---");
    if (commentJpaRepository.count() > 0) {
      System.out.println("댓글이 이미 있으므로 등록하지 않는다.");
      return;
    }
    BoardEntity board1 = boardJpaRepository.findById(1L).orElseThrow();
    BoardEntity board3 = boardJpaRepository.findById(3L).orElseThrow();

    // 게시글 번호가 아니라 게시글 엔티티를 넘긴다.
    commentJpaRepository.save(new CommentEntity(board1, "좋은 글이네요.", "임꺽정"));
    commentJpaRepository.save(new CommentEntity(board1, "잘 읽었습니다.", "유관순"));
    commentJpaRepository.save(new CommentEntity(board3, "REST 설계 규칙이 유용해요.", "홍길동"));
  }

  // 연관관계 탐색
  @Transactional(readOnly = true)
  public void printComments() {
    System.out.println("--- 댓글 → 게시글 (@ManyToOne) ---");
    for (CommentEntity comment : commentJpaRepository.findAll()) {
      System.out.println(comment.getContent() + " → 게시글: " + comment.getBoard().getTitle());
    }

    System.out.println("--- 게시글 → 댓글 (@OneToMany) ---");
    BoardEntity board = boardJpaRepository.findById(1L).orElseThrow();
    System.out.println(board.getTitle() + "의 댓글 수: " + board.getComments().size());
    for (CommentEntity comment : board.getComments()) {
      System.out.println(" - " + comment.getContent() + " (" + comment.getWriter() + ")");
    }

    System.out.println("--- findByBoardId(3L) ---");
    commentJpaRepository.findByBoardId(3L).forEach(System.out::println);
  }
```

**6) 실행 코드 바꾸기**

`BoardDataRunner.java`의 `run()` 메서드에서 `===` 줄 사이를 다음과 같이 바꾼다.

```java
    practice.addComments();
    practice.printComments();
```

**7) 실행하고 확인하기**

애플리케이션을 실행하고 로그를 확인한다. (`ddl-auto=validate`이므로 오류 없이 실행되면 `CommentEntity`가 `board_comment` 테이블과 맞게 작성된 것이다.)

```
--- 댓글 등록 ---
Hibernate: select count(*) from board_comment ce1_0
Hibernate: select ... from board be1_0 where be1_0.id=?
Hibernate: select ... from board be1_0 where be1_0.id=?
Hibernate: insert into board_comment (board_id, content, writer, id) values (?, ?, ?, default)
Hibernate: insert into board_comment (board_id, content, writer, id) values (?, ?, ?, default)
Hibernate: insert into board_comment (board_id, content, writer, id) values (?, ?, ?, default)
--- 댓글 → 게시글 (@ManyToOne) ---
Hibernate: select ... from board_comment ce1_0
Hibernate: select ... from board be1_0 where be1_0.id=?
좋은 글이네요. → 게시글: 스프링 부트 시작하기
잘 읽었습니다. → 게시글: 스프링 부트 시작하기
Hibernate: select ... from board be1_0 where be1_0.id=?
REST 설계 규칙이 유용해요. → 게시글: REST API 설계
--- 게시글 → 댓글 (@OneToMany) ---
Hibernate: select ... from board_comment c1_0 where c1_0.board_id=?
스프링 부트 시작하기의 댓글 수: 2
 - 좋은 글이네요. (임꺽정)
 - 잘 읽었습니다. (유관순)
--- findByBoardId(3L) ---
Hibernate: select ... from board_comment ce1_0 where ce1_0.board_id=?
CommentEntity[id=3, boardId=3, content=REST 설계 규칙이 유용해요., writer=홍길동]
```

다음을 확인한다.

- **저장**: `new CommentEntity(board1, ...)`처럼 게시글 **엔티티**를 넘겼는데, `INSERT` SQL에는 `board_id` 값으로 저장되었다.
- **댓글 → 게시글**: 댓글 목록을 조회한 후, 게시글 제목을 사용할 때 `board` 테이블을 조회했다. 1번 게시글은 한 번 조회한 후 다시 조회하지 않았다. 같은 트랜잭션 안에서는 이미 조회한 엔티티를 재사용하기 때문이다.
- **게시글 → 댓글**: `board.getComments()`를 사용할 때 `board_comment` 테이블을 `board_id` 조건으로 조회했다.

> 애플리케이션을 다시 실행하면 "댓글이 이미 있으므로 등록하지 않는다."가 출력되고, 댓글 탐색 결과는 똑같이 출력된다.

**8) H2 콘솔에서 확인하기**

[http://localhost:8080/h2-console](http://localhost:8080/h2-console) 에 접속하여 다음 SQL을 실행한다.

```sql
SELECT * FROM board_comment;

SELECT b.title, c.content, c.writer
FROM board b JOIN board_comment c ON b.id = c.board_id;
```

`board_comment` 테이블의 `BOARD_ID` 열에 게시글 번호가 저장된 것을 확인한다.

**9) (확인) 외래 키 제약 조건**

없는 게시글 번호(`999`)로 댓글을 추가해 본다.

```sql
INSERT INTO board_comment (board_id, content, writer) VALUES (999, '없는 게시글의 댓글', '유관순');
```

`Referential integrity constraint violation`과 비슷한 오류가 발생하며 저장되지 않는다.
DDL에 정의한 외래 키 제약 조건 때문에, 없는 게시글에는 댓글을 달 수 없다.

### 실습-6: 지연 로딩 확인하기

**1) 실습 메서드 추가하기**

`BoardJpaPractice.java`에 다음 메서드를 추가한다.

```java
  // 지연 로딩
  @Transactional(readOnly = true)
  public void lazyLoading() {
    System.out.println("--- ① 댓글 조회 ---");
    CommentEntity comment = commentJpaRepository.findById(1L).orElseThrow();
    System.out.println("댓글 내용: " + comment.getContent());

    System.out.println("--- ② 게시글 꺼내기 ---");
    BoardEntity board = comment.getBoard();
    System.out.println("게시글 객체의 클래스: " + board.getClass().getName());
    System.out.println("게시글 번호: " + board.getId());

    System.out.println("--- ③ 게시글 제목 사용 ---");
    System.out.println("게시글 제목: " + board.getTitle());
  }

  // 트랜잭션 없이 댓글 조회
  public CommentEntity findCommentWithoutTransaction(Long id) {
    return commentJpaRepository.findById(id).orElseThrow();
  }
```

**2) 실행 코드 바꾸기**

`BoardDataRunner.java`의 `run()` 메서드에서 `===` 줄 사이를 다음과 같이 바꾼다.

```java
    practice.lazyLoading();
```

**3) 실행하고 확인하기**

애플리케이션을 실행하고 로그를 확인한다.

```
--- ① 댓글 조회 ---
Hibernate: select ce1_0.id, ce1_0.board_id, ce1_0.content, ce1_0.writer from board_comment ce1_0 where ce1_0.id=?
댓글 내용: 좋은 글이네요.
--- ② 게시글 꺼내기 ---
게시글 객체의 클래스: com.example.hello.board.BoardEntity$HibernateProxy...
게시글 번호: 1
--- ③ 게시글 제목 사용 ---
Hibernate: select ... from board be1_0 where be1_0.id=?
게시글 제목: 스프링 부트 시작하기
```

| 단계 | 실행된 SQL | 설명 |
| --- | --- | --- |
| ① 댓글 조회 | `board_comment`만 조회 | 게시글은 조회하지 않았다. |
| ② 게시글 꺼내기 | 없음 | `getBoard()`가 리턴한 객체는 `BoardEntity`가 아니라 **프록시**(`BoardEntity$HibernateProxy...`)이다. 프록시는 게시글 번호를 이미 알고 있으므로 `getId()`는 SQL 없이 대답한다. |
| ③ 게시글 제목 사용 | `board` 조회 | 프록시가 제목을 요청받자 그때 게시글을 조회했다. |

> 프록시 클래스의 이름은 Hibernate 버전에 따라 조금 다를 수 있다.

**4) (비교) 즉시 로딩으로 바꿔 보기**

`CommentEntity.java`의 `@ManyToOne`을 잠시 즉시 로딩으로 바꾼다.

```java
  @ManyToOne(fetch = FetchType.EAGER)        // LAZY → EAGER
```

애플리케이션을 다시 실행하고 로그를 비교한다.

```
--- ① 댓글 조회 ---
Hibernate: select ce1_0.id, b1_0.id, b1_0.content, b1_0.title, b1_0.writer, ce1_0.content, ce1_0.writer
           from board_comment ce1_0 left join board b1_0 on b1_0.id=ce1_0.board_id where ce1_0.id=?
댓글 내용: 좋은 글이네요.
--- ② 게시글 꺼내기 ---
게시글 객체의 클래스: com.example.hello.board.BoardEntity
게시글 번호: 1
--- ③ 게시글 제목 사용 ---
게시글 제목: 스프링 부트 시작하기
```

- 댓글을 조회할 때 게시글까지 **조인하여 함께** 조회했다.
- `getBoard()`가 프록시가 아니라 **진짜 `BoardEntity`**를 리턴했다.
- 게시글 제목을 사용할 때 추가 SQL이 없다.

게시글 정보가 필요 없는 경우에도 매번 함께 조회하게 되므로, 확인한 후에는 **`FetchType.LAZY`로 되돌린다.**

**5) (확인) 트랜잭션 밖에서 지연 로딩하기**

`BoardDataRunner.java`의 `run()` 메서드에서 `===` 줄 사이를 다음과 같이 바꾼다.

```java
    // 트랜잭션이 없는 메서드로 댓글을 조회한다.
    CommentEntity comment = practice.findCommentWithoutTransaction(1L);
    System.out.println("댓글 내용: " + comment.getContent());
    try {
      System.out.println("게시글 제목: " + comment.getBoard().getTitle());
    } catch (Exception e) {
      System.out.println("예외 발생: " + e.getClass().getSimpleName());
      System.out.println("메시지: " + e.getMessage());
    }
```

애플리케이션을 실행하면 다음과 비슷한 내용이 출력된다.

```
댓글 내용: 좋은 글이네요.
예외 발생: LazyInitializationException
메시지: Could not initialize proxy [com.example.hello.board.BoardEntity#1] - no session
```

`findById()`가 끝나면서 트랜잭션도 끝났기 때문에, 그 후에 프록시가 게시글을 조회하려고 하자 예외가 발생했다.
**지연 로딩은 트랜잭션 안에서만 동작한다.** 연관된 엔티티를 사용하는 코드는 `@Transactional` 메서드 안에 두어야 한다.

**6) 실행 코드 정리하기**

`BoardDataRunner.java`의 `run()` 메서드에서 `===` 줄 사이를 다음과 같이 바꿔 둔다.

```java
    practice.printAll();
```

> `BoardDataRunner`와 `BoardJpaPractice`는 JPA의 동작을 확인하기 위한 실습용 코드이다. 8장에서 게시판 API가 JPA를 사용하도록 바꾼 후에는 삭제한다.

**7) 정리**

| 실습 | 확인한 내용 |
| --- | --- |
| 실습-2 CRUD | 변경은 `save()` 없이 **변경 감지**로 처리되며, 커밋할 때 `UPDATE`가 실행된다. 트랜잭션이 없으면 변경 감지가 동작하지 않는다. |
| 실습-3 쿼리 메서드 | 메서드 이름만 선언하면 조건에 맞는 SQL이 만들어진다. 필드 이름이 틀리면 시작할 때 오류가 발생한다. |
| 실습-4 JPQL | 엔티티와 필드를 대상으로 쿼리를 작성하면 Hibernate가 SQL로 변환한다. |
| 실습-5 연관관계 | `@ManyToOne`으로 댓글에서 게시글을, `@OneToMany(mappedBy)`로 게시글에서 댓글 목록을 탐색한다. 외래 키는 DDL로 정의한다. |
| 실습-6 지연 로딩 | 지연 로딩은 프록시를 사용하여 연관된 엔티티를 실제로 사용할 때 조회한다. 트랜잭션 밖에서는 `LazyInitializationException`이 발생한다. |

