# 10장. Thymeleaf 기초

9장에서는 Thymeleaf 템플릿으로 서버에서 HTML 화면을 만드는 방법을 배웠다. 이때 사용한 문법은 값을 출력하는 `th:text`와 목록을 반복하는 `th:each`뿐이었다.
이번 장에서는 Thymeleaf 템플릿의 구조와 주요 문법인 **변수 표현식**, **조건문**, **반복문**, **링크 URL 표현식**, **프래그먼트**(Fragment)를 배운다.
그리고 템플릿을 **HTML5 표준에 맞게** 작성하는 방법을 익히고, 이번 장부터는 모든 템플릿을 이 방법으로 작성한다.

## Thymeleaf 템플릿의 구조

### 내추럴 템플릿

Thymeleaf 템플릿은 평범한 HTML 파일에 Thymeleaf가 처리할 **속성**을 추가한 것이다.

```html
<h1 th:text="${message}">여기에 메시지가 출력된다</h1>
```

| 상황 | 결과 |
| --- | --- |
| 서버를 거치지 않고 웹 브라우저로 파일을 직접 열 때 | 웹 브라우저는 모르는 속성(`th:text`)을 무시하고, 태그 안의 내용(`여기에 메시지가 출력된다`)을 그대로 보여 준다. |
| 서버(Thymeleaf)가 처리할 때 | `th:text`의 값으로 태그 안의 내용을 **바꾸고**, `th:text` 속성은 **지운다.** → `<h1>Hello!</h1>` |

이처럼 서버 없이 열어도 화면의 모양을 확인할 수 있는 템플릿을 **내추럴 템플릿**(Natural Template)이라고 한다.
템플릿 안의 원래 내용은 화면을 설계할 때 보여 주는 **임시 내용**(프로토타입)이고, 실제 서비스 화면에서는 모델의 데이터로 바뀐다.

### 템플릿이 처리되는 과정

```
templates/demo/basic.html (템플릿)
        │  ① Thymeleaf가 템플릿을 읽는다.
        │  ② th:* 속성을 찾아 표현식을 계산한다. (모델의 데이터 사용)
        │  ③ 계산 결과로 태그의 내용이나 속성을 바꾸고, th:* 속성은 지운다.
        ▼
완성된 HTML → 웹 브라우저
```

## HTML5 표준에 맞게 템플릿 작성하기

### th: 속성의 문제

9장에서는 템플릿을 다음과 같이 작성했다.

```html
<html xmlns:th="http://www.thymeleaf.org">
...
<h1 th:text="${message}">메시지</h1>
```

`th:text` 같은 속성과 `xmlns:th` 선언은 **HTML5 표준에 없는 속성**이다.
웹 브라우저는 모르는 속성을 무시하므로 화면은 문제없이 보이지만, HTML 표준 검사기로 검사하면 오류가 발생한다.

```
Error: Attribute “xmlns:th” not allowed here.
Error: Attribute “th:text” not allowed on element “h1” at this point.
```

### data-th- 형식

HTML5는 개발자가 자유롭게 사용할 수 있도록 **`data-`로 시작하는 속성**(사용자 정의 데이터 속성)을 허용한다.
Thymeleaf는 모든 `th:` 속성을 **`data-th-` 형식**으로도 쓸 수 있게 지원한다. 이 형식을 사용하면 템플릿이 **HTML5 표준에 맞는 문서**가 된다.

```html
<!DOCTYPE html>
<html lang="ko">                                     <!-- xmlns:th 선언이 필요 없다. -->
<head>
  <meta charset="UTF-8">
  <title>Hello</title>
</head>
<body>
  <h1 data-th-text="${message}">메시지</h1>          <!-- th:text 대신 data-th-text -->
</body>
</html>
```

규칙은 간단하다. **`th:` 뒤의 이름 앞에 `data-th-`를 붙이면 된다.**

| `th:` 형식 | `data-th-` 형식 (HTML5 표준) |
| --- | --- |
| `<html xmlns:th="http://www.thymeleaf.org">` | `<html>` (선언 필요 없음) |
| `th:text="${...}"` | `data-th-text="${...}"` |
| `th:each="item : ${...}"` | `data-th-each="item : ${...}"` |
| `th:if="${...}"` | `data-th-if="${...}"` |
| `th:href="@{...}"` | `data-th-href="@{...}"` |
| `th:replace="~{...}"` | `data-th-replace="~{...}"` |
| `<th:block>` | `<th-block>` |

두 형식은 **기능이 완전히 같으며**, 한 템플릿 안에서 섞어 쓸 수도 있다.

| 비교 | `th:` 형식 | `data-th-` 형식 |
| --- | --- | --- |
| HTML5 표준 검사 | 오류가 발생한다. | 통과한다. |
| 코드 길이 | 짧다. | 조금 길다. |
| 자료 | Thymeleaf 공식 문서와 인터넷 예제 대부분이 이 형식을 사용한다. | 상대적으로 적다. |

> **이 교재에서는 10장부터 모든 템플릿을 `data-th-` 형식으로 작성한다.**
> 인터넷의 예제는 대부분 `th:` 형식이므로, `th:` 뒤의 이름 앞에 `data-th-`를 붙여 읽으면 된다.
> `${...}`, `@{...}` 같은 **표현식**은 두 형식에서 똑같다.

> 어느 형식을 쓰든 서버가 응답하는 최종 HTML에는 `th:`나 `data-th-` 속성이 **남지 않는다.** Thymeleaf가 처리하면서 모두 지운다.

## 표현식

Thymeleaf의 속성 값에는 **표현식**(Expression)을 쓴다. 표현식의 종류는 다음과 같다.

| 표현식 | 이름 | 용도 | 다루는 곳 |
| --- | --- | --- | --- |
| `${...}` | **변수 표현식** | 모델의 데이터를 꺼낸다. | 이번 장 |
| `*{...}` | 선택 변수 표현식 | `data-th-object`로 선택한 객체의 값을 꺼낸다. | 다음 장 (폼) |
| `@{...}` | **링크 URL 표현식** | 링크 주소를 만든다. | 이번 장 |
| `~{...}` | **프래그먼트 표현식** | 다른 템플릿의 조각을 가져온다. | 이번 장 |
| `#{...}` | 메시지 표현식 | 메시지 파일(다국어)의 값을 꺼낸다. | - |

## 변수 표현식

### 값 출력하기

`${...}`는 모델에 담긴 데이터를 꺼내는 **변수 표현식**이다.

```html
<p data-th-text="${name}">이름</p>                   <!-- 모델의 name -->
<p data-th-text="${board.title}">제목</p>            <!-- board 객체의 title 값 -->
<p data-th-text="${name.length()}">0</p>             <!-- 메서드 호출 -->
```

- `${board.title}`처럼 점(`.`)으로 객체의 값을 꺼낸다. 일반 클래스는 `getTitle()`, record는 `title()` 메서드가 호출된다.
- `${name.length()}`처럼 메서드를 직접 호출할 수도 있다.

### 연산하기

| 연산 | 예 | 결과 (`name`이 `"홍길동"`, `price`가 `1234567`일 때) |
| --- | --- | --- |
| 문자열 연결 | `'안녕하세요, ' + ${name} + '님'` | `안녕하세요, 홍길동님` |
| 리터럴 대체 | `\|안녕하세요, ${name}님\|` | `안녕하세요, 홍길동님` |
| 산술 | `${price * 2}` | `2469134` |
| 비교 | `${price >= 1000000}` | `true` |
| 조건 연산 | `${price >= 1000000} ? '백만 이상' : '백만 미만'` | `백만 이상` |
| 기본값 (엘비스 연산) | `${nickname} ?: '닉네임 없음'` | `nickname`이 `null`이면 `닉네임 없음` |

- 문자열은 **작은따옴표**(`'`)로 감싼다. 속성 값 전체를 큰따옴표로 감싸기 때문이다.
- `|...|`(**리터럴 대체**)를 사용하면 `+` 없이 문자열 안에 `${...}`를 섞어 쓸 수 있어서 읽기 쉽다.

| 비교 연산자 | 의미 | 논리 연산자 | 의미 |
| --- | --- | --- | --- |
| `==`, `!=` | 같다, 다르다 | `and` | 그리고 |
| `>`, `<`, `>=`, `<=` | 크다, 작다, 크거나 같다, 작거나 같다 | `or` | 또는 |
| | | `not` 또는 `!` | 부정 |

### data-th-text와 data-th-utext

`data-th-text`는 값에 들어 있는 HTML 태그를 **글자 그대로** 출력한다. 태그로 해석하지 않는다.

| 속성 | 값이 `"<b>굵은 글씨</b>"`일 때 화면 | 실제 HTML |
| --- | --- | --- |
| `data-th-text` | `<b>굵은 글씨</b>` (글자 그대로 보인다) | `&lt;b&gt;굵은 글씨&lt;/b&gt;` |
| `data-th-utext` | **굵은 글씨** (태그로 해석된다) | `<b>굵은 글씨</b>` |

`data-th-text`처럼 `<`, `>` 같은 문자를 `&lt;`, `&gt;`로 바꿔 글자 그대로 보이게 하는 것을 **이스케이프**(Escape)라고 한다.

> **보안 주의**: 사용자가 입력한 값(게시글 내용 등)은 반드시 `data-th-text`로 출력한다.
> `data-th-utext`로 출력하면, 사용자가 `<script>` 태그를 입력했을 때 다른 사용자의 웹 브라우저에서 그 스크립트가 실행된다. 이런 공격을 **XSS**(Cross-Site Scripting)라고 한다.
> `data-th-utext`는 개발자가 직접 만든 안전한 HTML을 출력할 때만 사용한다.

### 유틸리티 객체

Thymeleaf는 자주 쓰는 기능을 **유틸리티 객체**로 제공한다. 이름이 `#`으로 시작한다.

| 유틸리티 객체 | 예 | 결과 |
| --- | --- | --- |
| `#temporals` (날짜·시간) | `${#temporals.format(now, 'yyyy-MM-dd HH:mm')}` | `2026-09-28 13:45` |
| `#numbers` (숫자) | `${#numbers.formatInteger(price, 3, 'COMMA')}` | `1,234,567` |
| `#strings` (문자열) | `${#strings.abbreviate(content, 20)}` | 20자까지 자르고 `...`를 붙인다. |
| `#strings` | `${#strings.isEmpty(keyword)}` | 비어 있으면 `true` |
| `#lists` (목록) | `${#lists.size(boards)}`, `${#lists.isEmpty(boards)}` | 목록의 크기, 비어 있는지 |

> `#temporals`는 `LocalDateTime`, `LocalDate` 같은 Java 날짜·시간 객체를 다룬다.

### 속성 값 바꾸기

`data-th-text`는 태그의 **내용**을 바꾼다. 태그의 **속성**을 바꾸려면 `data-th-속성이름`을 사용한다.

| 속성 | 예 | 결과 |
| --- | --- | --- |
| `data-th-value` | `<input data-th-value="${keyword}">` | `<input value="스프링">` |
| `data-th-href` | `<a data-th-href="@{/web/boards}">` | `<a href="/web/boards">` (링크 URL 표현식과 함께) |
| `data-th-src` | `<img data-th-src="@{/images/logo.png}">` | `<img src="/images/logo.png">` |
| `data-th-action` | `<form data-th-action="@{/web/boards}">` | `<form action="/web/boards">` |
| `data-th-classappend` | `<tr class="row" data-th-classappend="${stat.even} ? 'even'">` | 조건이 참이면 `class="row even"` |

## 조건문

### data-th-if와 data-th-unless

`data-th-if`는 조건이 **참일 때만** 태그를 출력한다. 거짓이면 태그 전체(안의 내용 포함)가 출력되지 않는다.
`data-th-unless`는 반대로 조건이 **거짓일 때만** 출력한다.

```html
<p data-th-if="${score >= 60}">합격입니다.</p>
<p data-th-unless="${score >= 60}">불합격입니다.</p>
```

조건에는 `true`/`false`가 아닌 값도 쓸 수 있다. 다음 값은 **거짓**으로, 나머지는 참으로 판단한다.

| 거짓으로 판단하는 값 |
| --- |
| `null` |
| `false` |
| 숫자 `0` |
| 문자열 `"false"`, `"off"`, `"no"` |

> 빈 문자열(`""`)은 참으로 판단한다. 문자열이 비어 있는지 확인하려면 `${#strings.isEmpty(keyword)}`를 사용한다.

### data-th-switch와 data-th-case

값에 따라 여러 경우 중 하나를 출력할 때는 `data-th-switch`와 `data-th-case`를 사용한다.

```html
<div data-th-switch="${role}">
  <p data-th-case="'ADMIN'">관리자 메뉴를 볼 수 있습니다.</p>
  <p data-th-case="'USER'">일반 사용자 메뉴를 볼 수 있습니다.</p>
  <p data-th-case="*">로그인이 필요합니다.</p>       <!-- 나머지 모든 경우 -->
</div>
```

`data-th-case="*"`는 앞의 어느 경우에도 해당하지 않을 때 출력된다. (Java `switch` 문의 `default`)

## 반복문

### data-th-each

`data-th-each`는 목록의 요소 개수만큼 **태그를 반복**하여 출력한다.

```html
<tr data-th-each="member : ${members}">
  <td data-th-text="${member.name}">이름</td>
  <td data-th-text="${member.age}">0</td>
</tr>
```

`member : ${members}`는 "`members` 목록에서 요소를 하나씩 꺼내 `member`라는 이름으로 사용한다"는 뜻이다. Java의 `for (Member member : members)`와 같다.
`data-th-each`를 붙인 태그(`<tr>`)가 **통째로 반복**된다.

### 반복 상태 변수

반복 변수 이름 뒤에 쉼표(`,`)와 이름을 하나 더 쓰면, **반복 상태**를 알려 주는 변수를 사용할 수 있다.

```html
<tr data-th-each="member, stat : ${members}">
  <td data-th-text="${stat.count}">1</td>
  ...
</tr>
```

| 속성 | 의미 | 예 (3개 중 첫 번째) |
| --- | --- | --- |
| `index` | 0부터 시작하는 순번 | `0` |
| `count` | 1부터 시작하는 순번 | `1` |
| `size` | 전체 요소 개수 | `3` |
| `even` / `odd` | 짝수 번째 / 홀수 번째인지 (`count` 기준) | `false` / `true` |
| `first` / `last` | 첫 번째 / 마지막인지 | `true` / `false` |
| `current` | 현재 요소 | `member`와 같다. |

### 목록이 비어 있을 때

목록이 비어 있으면 `data-th-each`는 아무것도 출력하지 않는다. "데이터가 없다"는 안내를 보여 주려면 조건문과 함께 사용한다.

```html
<tr data-th-if="${#lists.isEmpty(members)}">
  <td colspan="3">회원이 없습니다.</td>
</tr>
```

### 여러 태그를 함께 반복하기: th-block

태그 여러 개를 묶어서 반복하고 싶지만, 묶기 위한 태그를 HTML에 남기고 싶지 않을 때는 `<th-block>`을 사용한다.
`<th-block>`은 Thymeleaf가 처리한 후 **태그 자체는 사라지고** 안의 내용만 남는다.

```html
<div>
  <th-block data-th-each="member : ${members}">
    <h3 data-th-text="${member.name}">이름</h3>
    <p data-th-text="${member.email}">이메일</p>
  </th-block>
</div>
```

> `th:` 형식에서는 `<th:block>`으로 쓴다. `<th-block>`은 이름에 하이픈(`-`)이 있는 태그여서 HTML5의 사용자 정의 태그(custom element) 규칙에 맞는다.

> 다만 `<table>`, `<ul>`, `<dl>`처럼 **안에 들어갈 수 있는 태그가 정해진** 태그 안에서는 `<th-block>`을 쓰면 HTML5 표준 검사에서 오류가 발생한다.
> 이런 곳에서는 `<tr>`, `<li>`처럼 원래 들어갈 수 있는 태그에 `data-th-each`를 붙인다. `<dl>` 안에서 `<dt>`와 `<dd>`를 함께 반복하려면 HTML5가 허용하는 `<div>`로 묶는다.

## 링크 URL 표현식

### @{...}로 링크 만들기

링크 주소는 **`@{...}`**(링크 URL 표현식)로 만들고, `data-th-href`, `data-th-src`, `data-th-action` 등의 속성에 사용한다.

```html
<a href="list.html" data-th-href="@{/web/boards}">게시글 목록</a>
```

- 서버를 거치지 않고 열면 `href="list.html"`이 사용되고, 서버에서는 `data-th-href`의 값으로 **바뀐다.**
- 내추럴 템플릿을 위해 `href`에는 임시 주소를, `data-th-href`에는 실제 주소를 적는다.

### 파라미터 붙이기

주소 뒤의 **괄호** 안에 `이름=값`을 쓰면 **쿼리 스트링**으로 붙는다.

| 표현식 | 결과 |
| --- | --- |
| `@{/web/boards(page=2)}` | `/web/boards?page=2` |
| `@{/web/boards(page=${page + 1}, size=${size})}` | `/web/boards?page=3&size=10` |
| `@{/web/boards(keyword=${keyword})}` | `/web/boards?keyword=%EC%8A%A4...` (한글은 자동으로 URL 인코딩) |

주소 안에 `{이름}`을 쓰고 괄호에 같은 이름의 값을 주면 **경로에 값이 들어간다.** (5장의 `@PathVariable` 형식의 주소를 만들 때 사용한다.)

| 표현식 | 결과 |
| --- | --- |
| `@{/boards/{id}(id=${board.id})}` | `/boards/3` |
| `@{/boards/{id}(id=${board.id}, preview=true)}` | `/boards/3?preview=true` (경로에 쓰이지 않은 값은 쿼리 스트링이 된다.) |

### @{...}를 사용하는 이유

링크 주소를 그냥 `href="/web/boards"`로 적어도 되지 않을까?
2장에서 배운 `server.servlet.context-path` 설정으로 애플리케이션의 모든 주소 앞에 `/app`을 붙이면, 직접 적은 `/web/boards`는 **없는 주소**가 된다.

| 작성 방법 | `context-path`가 없을 때 | `context-path=/app`일 때 |
| --- | --- | --- |
| `href="/web/boards"` | `/web/boards` | `/web/boards` (**404 오류**) |
| `data-th-href="@{/web/boards}"` | `/web/boards` | `/app/web/boards` (자동으로 앞에 붙는다) |

`@{...}`에서 `/`로 시작하는 주소는 **애플리케이션을 기준으로 한 주소**이다. Thymeleaf가 현재 애플리케이션의 context path를 자동으로 붙여 준다.
그래서 애플리케이션 안의 링크는 항상 `@{...}`로 만든다.

## 프래그먼트

### 화면의 공통 부분

웹 사이트의 화면들은 보통 머리글(헤더), 메뉴, 바닥글(푸터) 같은 **공통 부분**을 가지고 있다.
이 부분을 화면마다 복사해서 붙여 넣으면, 메뉴 하나를 바꿀 때 모든 템플릿을 고쳐야 한다.

**프래그먼트**(Fragment)는 템플릿의 **조각**이다. 공통 부분을 프래그먼트로 한 곳에 정의해 두고, 여러 템플릿에서 가져다 쓴다.

### 프래그먼트 정의하기: data-th-fragment

공통 부분을 모아 둘 템플릿(예: `templates/fragments/layout.html`)을 만들고, 조각으로 쓸 태그에 `data-th-fragment="이름"`을 붙인다.

```html
<!-- templates/fragments/layout.html -->
<header data-th-fragment="header">
  <h1>스프링 게시판</h1>
  <nav>...</nav>
</header>

<footer data-th-fragment="footer">
  <p>&copy; 2026 Spring Boot 실습</p>
</footer>
```

### 프래그먼트 사용하기: data-th-replace와 data-th-insert

다른 템플릿에서 **프래그먼트 표현식** `~{템플릿 이름 :: 프래그먼트 이름}`으로 프래그먼트를 가져온다.

```html
<!-- templates/boards/list.html -->
<header data-th-replace="~{fragments/layout :: header}">헤더가 들어갈 자리</header>
...
<footer data-th-replace="~{fragments/layout :: footer}">푸터가 들어갈 자리</footer>
```

- `fragments/layout`은 템플릿 이름이다. 뷰 이름과 같은 규칙으로 `templates/fragments/layout.html`을 찾는다.
- `header`, `footer`는 `data-th-fragment`로 붙인 프래그먼트 이름이다.

| 속성 | 동작 | 결과 |
| --- | --- | --- |
| `data-th-replace` | 이 태그를 프래그먼트로 **바꾼다.** | `<header>...프래그먼트 내용...</header>` |
| `data-th-insert` | 이 태그는 두고, 태그 **안에** 프래그먼트를 넣는다. | `<div><header>...프래그먼트 내용...</header></div>` |

> 대부분의 경우 `data-th-replace`를 사용한다.

### 파라미터가 있는 프래그먼트

프래그먼트에 **파라미터**를 선언하면, 가져다 쓰는 쪽에서 값을 넘길 수 있다. 화면마다 제목이 다른 `<head>`를 만들 때 유용하다.

```html
<!-- 정의: fragments/layout.html -->
<head data-th-fragment="head(title)">
  <meta charset="UTF-8">
  <title data-th-text="${title}">제목</title>
  <link rel="stylesheet" data-th-href="@{/css/app.css}">
</head>
```

```html
<!-- 사용: boards/list.html -->
<head data-th-replace="~{fragments/layout :: head('게시글 목록')}">
  <meta charset="UTF-8">
  <title>게시글 목록</title>
</head>
```

## 정리

| 문법 | 형식 | 용도 |
| --- | --- | --- |
| HTML5 표준 형식 | `data-th-속성` | `th:속성`과 같은 기능. HTML5 표준 검사를 통과한다. |
| 변수 표현식 | `${...}` | 모델의 데이터 출력, 연산, 유틸리티 객체(`#temporals`, `#numbers`, `#strings`, `#lists`) |
| 값 출력 | `data-th-text` / `data-th-utext` | 내용 출력 (이스케이프 함 / 안 함). 사용자 입력은 반드시 `data-th-text` |
| 조건문 | `data-th-if`, `data-th-unless`, `data-th-switch` + `data-th-case` | 조건에 따라 태그 출력 |
| 반복문 | `data-th-each="item, stat : ${list}"` | 목록의 요소마다 태그 반복. `stat`으로 순번 등 확인 |
| 링크 URL 표현식 | `@{/경로(이름=값)}` | 링크 주소 생성. context path 자동 처리, 파라미터와 경로 변수 |
| 프래그먼트 | `data-th-fragment`, `data-th-replace="~{템플릿 :: 이름}"` | 공통 화면 조각을 정의하고 가져다 쓴다. |

## 실습

이번 장의 실습은 9장까지 사용한 `hello` 프로젝트에서 이어서 진행한다.
실습-1~4에서는 문법을 연습하는 화면(`/web/demo/...`)을 만들고, 실습-5~6에서는 9장에서 만든 게시글 목록 화면에 링크와 공통 레이아웃을 적용한다.
**모든 템플릿은 `data-th-` 형식으로 작성한다.**

> 9장 실습-1에서 설정한 `spring.thymeleaf.cache=false`와 `build.gradle`의 `sourceResources` 설정 덕분에, 템플릿을 고친 후에는 새로 고침만 하면 된다.
> Java 코드(컨트롤러 등)를 고쳤을 때는 `bootRun`을 다시 실행한다.

실습을 마치면 다음 파일이 추가되거나 바뀐다.

```
src/main
├── java/com/example/hello
│   ├── DemoMember.java                    ← 연습용 회원 데이터        (실습-4)
│   ├── ThymeleafDemoController.java       ← 연습용 화면 컨트롤러      (실습-1 ~ 4)
│   └── board
│       └── BoardWebController.java        ← 목록 화면 컨트롤러 (수정) (실습-5)
└── resources
    ├── static/css
    │   └── app.css                        ← 공통 스타일             (실습-6)
    └── templates
        ├── demo
        │   ├── basic.html                 ←                       (실습-1)
        │   ├── expressions.html           ←                       (실습-2)
        │   ├── conditions.html            ←                       (실습-3)
        │   └── members.html               ←                       (실습-4)
        ├── fragments
        │   └── layout.html                ← 공통 레이아웃 프래그먼트   (실습-6)
        └── boards
            └── list.html                  ← 게시글 목록 (수정)       (실습-5, 6)
```

### 실습-1: HTML5 표준에 맞는 템플릿 만들기

**1) 연습용 컨트롤러 만들기**

`src/main/java/com/example/hello` 폴더에 `ThymeleafDemoController.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/demo")
public class ThymeleafDemoController {

  @GetMapping("/basic")
  public String basic(Model model) {
    model.addAttribute("message", "Thymeleaf 템플릿 기초");
    return "demo/basic";
  }
}
```

**2) data-th- 형식으로 템플릿 만들기**

`src/main/resources/templates` 폴더 아래에 `demo` 폴더를 만들고, 그 안에 `basic.html` 파일을 만든다. 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Thymeleaf 기초</title>
</head>
<body>
  <h1 data-th-text="${message}">여기에 메시지가 출력된다</h1>
  <p>이 템플릿은 HTML5 표준에 맞게 작성했다.</p>
</body>
</html>
```

9장의 템플릿과 비교하면 `<html>`에 `xmlns:th` 선언이 없고, `th:text` 대신 `data-th-text`를 사용했다.

**3) 실행하고 확인하기**

애플리케이션을 다시 실행하고 [http://localhost:8080/web/demo/basic](http://localhost:8080/web/demo/basic) 에 접속한다.
`th:text`를 사용했을 때와 똑같이 모델의 메시지가 출력된다.
`페이지 소스 보기`로 응답된 HTML을 확인하면 `data-th-text` 속성이 사라진 것을 볼 수 있다.

**4) HTML5 표준 검사하기**

W3C가 제공하는 HTML 검사기로 두 형식의 템플릿을 검사해 본다.

1. 웹 브라우저에서 [https://validator.w3.org/nu/#textarea](https://validator.w3.org/nu/#textarea) 에 접속한다.
2. 9장에서 만든 `templates/hello.html`의 내용을 모두 복사하여 입력 창에 붙여 넣고 `Check` 버튼을 누른다.

다음과 비슷한 오류가 표시된다. (문구는 검사기 버전에 따라 다를 수 있다.)

```
Error: Attribute “xmlns:th” not allowed here.
Error: Attribute “th:text” not allowed on element “h1” at this point.
Error: Attribute “th:text” not allowed on element “span” at this point.
```

3. 이번에는 `templates/demo/basic.html`의 내용을 붙여 넣고 `Check` 버튼을 누른다.

오류 없이 통과한다. (`Document checking completed. No errors or warnings to show.`)

> 9장에서 만든 `hello.html`, `greet.html`, `boards/list.html`은 `th:` 형식이지만 그대로 동작한다. `boards/list.html`은 실습-5에서 `data-th-` 형식으로 바꾼다.

### 실습-2: 변수 표현식 사용하기

**1) 컨트롤러에 메서드 추가하기**

`ThymeleafDemoController.java`에 다음 메서드를 추가한다.

```java
import java.time.LocalDateTime;

  @GetMapping("/expressions")
  public String expressions(Model model) {
    model.addAttribute("name", "홍길동");
    model.addAttribute("nickname", null);                               // 값이 없는 경우
    model.addAttribute("price", 1234567);
    model.addAttribute("now", LocalDateTime.now());
    model.addAttribute("html", "<b>굵은 글씨</b>");
    model.addAttribute("content", "Thymeleaf는 서버에서 HTML 화면을 만드는 자바 템플릿 엔진이다.");
    return "demo/expressions";
  }
```

**2) 템플릿 만들기**

`templates/demo` 폴더에 `expressions.html` 파일을 만들고 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>변수 표현식</title>
</head>
<body>
  <h1>변수 표현식</h1>

  <h2>값 출력과 연산</h2>
  <ul>
    <li>이름: <span data-th-text="${name}">이름</span></li>
    <li>문자열 연결: <span data-th-text="'안녕하세요, ' + ${name} + '님'">인사</span></li>
    <li>리터럴 대체: <span data-th-text="|안녕하세요, ${name}님|">인사</span></li>
    <li>메서드 호출: <span data-th-text="${name.length()}">0</span>글자</li>
    <li>산술 연산: <span data-th-text="${price * 2}">0</span></li>
    <li>조건 연산: <span data-th-text="${price >= 1000000} ? '백만 이상' : '백만 미만'">비교</span></li>
    <li>기본값: <span data-th-text="${nickname} ?: '닉네임 없음'">닉네임</span></li>
  </ul>

  <h2>data-th-text와 data-th-utext</h2>
  <ul>
    <li>data-th-text: <span data-th-text="${html}">text</span></li>
    <li>data-th-utext: <span data-th-utext="${html}">utext</span></li>
  </ul>

  <h2>유틸리티 객체</h2>
  <ul>
    <li>숫자: <span data-th-text="${#numbers.formatInteger(price, 3, 'COMMA')}">1,234,567</span>원</li>
    <li>날짜: <span data-th-text="${#temporals.format(now, 'yyyy-MM-dd HH:mm:ss')}">2026-01-01 00:00:00</span></li>
    <li>요약: <span data-th-text="${#strings.abbreviate(content, 20)}">요약</span></li>
    <li>대문자: <span data-th-text="${#strings.toUpperCase('thymeleaf')}">THYMELEAF</span></li>
  </ul>
</body>
</html>
```

**3) 실행하고 확인하기**

애플리케이션을 다시 실행하고 [http://localhost:8080/web/demo/expressions](http://localhost:8080/web/demo/expressions) 에 접속한다. 다음과 같이 출력되는지 확인한다.

| 항목 | 출력 |
| --- | --- |
| 이름 | 홍길동 |
| 문자열 연결, 리터럴 대체 | 안녕하세요, 홍길동님 |
| 메서드 호출 | 3글자 |
| 산술 연산 | 2469134 |
| 조건 연산 | 백만 이상 |
| 기본값 | 닉네임 없음 (`nickname`이 `null`이므로) |
| data-th-text | `<b>굵은 글씨</b>` (태그가 글자 그대로 보인다) |
| data-th-utext | **굵은 글씨** (태그가 적용된다) |
| 숫자 | 1,234,567원 |
| 날짜 | 현재 날짜와 시각 (예: 2026-09-28 13:45:10) |
| 요약 | Thymeleaf는 서버에서 HTML... (20자까지) |
| 대문자 | THYMELEAF |

`페이지 소스 보기`로 `data-th-text`와 `data-th-utext`의 출력 결과를 비교한다. `data-th-text`는 `&lt;b&gt;굵은 글씨&lt;/b&gt;`처럼 **이스케이프**되어 있다.

**4) (확인) XSS 공격을 막는 이스케이프**

컨트롤러의 `html` 값을 다음과 같이 잠시 바꾸고 애플리케이션을 다시 실행한다.

```java
    model.addAttribute("html", "<img src=x onerror=\"alert('XSS 공격!')\">");
```

같은 주소에 접속하면, `data-th-utext` 부분에서 **경고 창**이 뜬다. 값에 들어 있는 스크립트가 실행된 것이다.
`data-th-text` 부분은 글자 그대로 출력되어 스크립트가 실행되지 않는다.
사용자가 입력한 값을 `data-th-utext`로 출력하면 이런 공격에 노출된다. 확인한 후에는 원래 값(`"<b>굵은 글씨</b>"`)으로 되돌린다.

### 실습-3: 조건문 사용하기

**1) 컨트롤러에 메서드 추가하기**

`ThymeleafDemoController.java`에 다음 메서드를 추가한다.

```java
import org.springframework.web.bind.annotation.RequestParam;

  @GetMapping("/conditions")
  public String conditions(@RequestParam(defaultValue = "75") int score,
                           @RequestParam(defaultValue = "GUEST") String role,
                           Model model) {
    model.addAttribute("score", score);
    model.addAttribute("role", role);
    return "demo/conditions";
  }
```

**2) 템플릿 만들기**

`templates/demo` 폴더에 `conditions.html` 파일을 만들고 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>조건문</title>
  <style>
    .pass { color: blue; }
    .fail { color: red; }
  </style>
</head>
<body>
  <h1>조건문</h1>

  <h2>data-th-if / data-th-unless</h2>
  <p>점수: <span data-th-text="${score}">0</span>점</p>
  <p data-th-if="${score >= 60}" class="pass">합격입니다.</p>
  <p data-th-unless="${score >= 60}" class="fail">불합격입니다.</p>
  <p data-th-if="${score == 100}">만점입니다! 축하합니다.</p>

  <h2>조건에 따라 속성 바꾸기</h2>
  <p data-th-text="${score >= 60} ? '합격' : '불합격'"
     data-th-classappend="${score >= 60} ? 'pass' : 'fail'">결과</p>

  <h2>data-th-switch / data-th-case</h2>
  <p>역할: <span data-th-text="${role}">역할</span></p>
  <div data-th-switch="${role}">
    <p data-th-case="'ADMIN'">관리자 메뉴를 볼 수 있습니다.</p>
    <p data-th-case="'USER'">일반 사용자 메뉴를 볼 수 있습니다.</p>
    <p data-th-case="*">로그인이 필요합니다.</p>
  </div>
</body>
</html>
```

**3) 실행하고 확인하기**

애플리케이션을 다시 실행하고, 주소의 쿼리 스트링을 바꿔 가며 접속한다.

| 주소 | 출력 |
| --- | --- |
| [/web/demo/conditions](http://localhost:8080/web/demo/conditions) | 합격입니다. (파란색), 결과: 합격, 로그인이 필요합니다. |
| [/web/demo/conditions?score=40](http://localhost:8080/web/demo/conditions?score=40) | 불합격입니다. (빨간색), 결과: 불합격 |
| [/web/demo/conditions?score=100](http://localhost:8080/web/demo/conditions?score=100) | 합격입니다., 만점입니다! 축하합니다. |
| [/web/demo/conditions?role=ADMIN](http://localhost:8080/web/demo/conditions?role=ADMIN) | 관리자 메뉴를 볼 수 있습니다. |
| [/web/demo/conditions?role=USER](http://localhost:8080/web/demo/conditions?role=USER) | 일반 사용자 메뉴를 볼 수 있습니다. |

`페이지 소스 보기`로 확인하면, 조건이 거짓인 태그는 HTML에 **아예 없다.** 화면에서 숨긴 것이 아니라 출력하지 않은 것이다.

> 12~14장에서 로그인과 권한을 배우면, 이 조건문으로 "로그인한 사용자에게만 보이는 메뉴", "관리자에게만 보이는 버튼" 등을 만든다.

### 실습-4: 반복문 사용하기

**1) 연습용 데이터 클래스 만들기**

`src/main/java/com/example/hello` 폴더에 `DemoMember.java` 파일을 만들고 다음과 같이 작성한다.

```java
package com.example.hello;

public record DemoMember(String name, int age, String email) {
}
```

**2) 컨트롤러에 메서드 추가하기**

`ThymeleafDemoController.java`에 다음 메서드를 추가한다.

```java
import java.util.List;

  @GetMapping("/members")
  public String members(@RequestParam(defaultValue = "false") boolean empty, Model model) {
    List<DemoMember> members = empty
        ? List.of()                                                  // ?empty=true이면 빈 목록
        : List.of(
            new DemoMember("홍길동", 30, "hong@example.com"),
            new DemoMember("임꺽정", 35, "lim@example.com"),
            new DemoMember("유관순", 20, "yu@example.com"),
            new DemoMember("이순신", 45, "lee@example.com"));
    model.addAttribute("members", members);
    return "demo/members";
  }
```

**3) 템플릿 만들기**

`templates/demo` 폴더에 `members.html` 파일을 만들고 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>반복문</title>
  <style>
    table { border-collapse: collapse; }
    th, td { border: 1px solid #999; padding: 4px 8px; }
    .even { background-color: #eef; }
  </style>
</head>
<body>
  <h1>반복문</h1>

  <h2>data-th-each와 반복 상태</h2>
  <table>
    <thead>
      <tr>
        <th>순번</th>
        <th>이름</th>
        <th>나이</th>
        <th>이메일</th>
        <th>반복 상태</th>
      </tr>
    </thead>
    <tbody>
      <tr data-th-each="member, stat : ${members}"
          data-th-classappend="${stat.even} ? 'even'">
        <td data-th-text="${stat.count}">1</td>
        <td data-th-text="${member.name}">이름</td>
        <td data-th-text="${member.age}">0</td>
        <td data-th-text="${member.email}">이메일</td>
        <td data-th-text="|index=${stat.index}, size=${stat.size}, first=${stat.first}, last=${stat.last}|">상태</td>
      </tr>
      <tr data-th-if="${#lists.isEmpty(members)}">
        <td colspan="5">회원이 없습니다.</td>
      </tr>
    </tbody>
  </table>
  <p>전체 회원 수: <span data-th-text="${#lists.size(members)}">0</span>명</p>

  <h2>th-block으로 여러 태그 반복하기</h2>
  <div>
    <th-block data-th-each="member : ${members}">
      <h3 data-th-text="${member.name}">이름</h3>
      <p data-th-text="${member.email}">이메일</p>
    </th-block>
  </div>
</body>
</html>
```

- `data-th-classappend="${stat.even} ? 'even'"`: 짝수 번째 행에만 `even` 클래스를 붙여 배경색을 바꾼다. 조건 연산에서 `: 거짓일 때 값`을 생략하면, 조건이 거짓일 때 아무것도 붙이지 않는다.

**4) 실행하고 확인하기**

애플리케이션을 다시 실행하고 [http://localhost:8080/web/demo/members](http://localhost:8080/web/demo/members) 에 접속한다.

- 회원 네 명이 표로 출력되고, 2번째와 4번째 행의 배경색이 다르다.
- **반복 상태** 열에서 `index`는 0부터, 순번(`count`)은 1부터 시작하는 것을 확인한다. 첫 행은 `first=true`, 마지막 행은 `last=true`이다.
- 아래쪽에서 이름(`<h3>`)과 이메일(`<p>`)이 번갈아 반복된다. `페이지 소스 보기`로 확인하면 `<th-block>` 태그는 **남아 있지 않다.**

[http://localhost:8080/web/demo/members?empty=true](http://localhost:8080/web/demo/members?empty=true) 에 접속하면, 표에 "회원이 없습니다."가 출력되고 전체 회원 수는 0명이다.

### 실습-5: 링크 URL 표현식으로 게시글 목록 화면 개선하기

9장에서 만든 게시글 목록 화면(`/web/boards`)을 `data-th-` 형식으로 바꾸고, 검색 입력 창과 **페이지 이동 링크**를 추가한다.

**1) 컨트롤러가 페이지 정보를 모델에 담도록 바꾸기**

템플릿에서 페이지 이동 링크를 만들려면 현재 페이지 번호, 페이지 크기, 검색어가 필요하다.
`board/BoardWebController.java`의 `list()` 메서드를 다음과 같이 바꾼다.

```java
  @GetMapping
  public String list(
      @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      Model model) {
    model.addAttribute("boards", boardService.list(keyword, page, size));
    model.addAttribute("count", boardService.count());
    model.addAttribute("keyword", keyword);          // 추가: 검색어
    model.addAttribute("page", page);                // 추가: 현재 페이지 번호
    model.addAttribute("size", size);                // 추가: 페이지 크기
    return "boards/list";
  }
```

**2) 목록 템플릿 다시 작성하기**

`templates/boards/list.html`을 다음과 같이 바꾼다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>게시글 목록</title>
</head>
<body>
  <h1>게시글 목록</h1>

  <!-- 검색 -->
  <form action="list.html" method="get" data-th-action="@{/web/boards}">
    <input type="text" name="keyword" placeholder="제목 검색" data-th-value="${keyword}">
    <button type="submit">검색</button>
  </form>

  <p>전체 게시글 수: <span data-th-text="${count}">0</span></p>

  <table border="1">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
      </tr>
    </thead>
    <tbody>
      <tr data-th-each="board : ${boards}">
        <td data-th-text="${board.id}">1</td>
        <td>
          <!-- 다음 장에서 상세 화면을 만들기 전까지는 게시글 조회 API(JSON)로 연결한다. -->
          <a href="#" data-th-href="@{/boards/{id}(id=${board.id})}"
             data-th-text="${board.title}">제목</a>
        </td>
        <td data-th-text="${board.writer}">작성자</td>
      </tr>
      <tr data-th-if="${#lists.isEmpty(boards)}">
        <td colspan="3">게시글이 없습니다.</td>
      </tr>
    </tbody>
  </table>

  <!-- 페이지 이동 -->
  <p>
    <a href="#" data-th-if="${page > 1}"
       data-th-href="@{/web/boards(page=${page - 1}, size=${size}, keyword=${keyword ?: ''})}">◀ 이전</a>
    <span data-th-text="|${page} 페이지|">1 페이지</span>
    <a href="#" data-th-if="${#lists.size(boards) == size}"
       data-th-href="@{/web/boards(page=${page + 1}, size=${size}, keyword=${keyword ?: ''})}">다음 ▶</a>
  </p>
</body>
</html>
```

| 코드 | 의미 |
| --- | --- |
| `data-th-action="@{/web/boards}"` | 검색 폼을 `/web/boards`로 보낸다. 입력 창의 `name="keyword"`가 쿼리 스트링(`?keyword=...`)이 된다. |
| `data-th-value="${keyword}"` | 검색한 후에도 입력 창에 검색어가 남아 있도록 한다. |
| `@{/boards/{id}(id=${board.id})}` | 경로에 게시글 번호가 들어간 주소를 만든다. → `/boards/5` |
| `data-th-if="${page > 1}"` | 첫 페이지에서는 이전 링크를 출력하지 않는다. |
| `data-th-if="${#lists.size(boards) == size}"` | 이번 페이지가 가득 찼을 때만 다음 링크를 출력한다. (다음 페이지가 있을 수 있다.) |
| `keyword=${keyword ?: ''}` | 검색어가 없으면(`null`) 빈 문자열로 넘긴다. 서비스는 빈 검색어를 "검색 안 함"으로 처리한다. |

> 이 검색 폼은 입력 값을 쿼리 스트링으로 보내는 단순한 GET 폼이다. 입력 값을 객체로 받아 등록·수정하는 폼은 11장에서 다룬다.

**3) 실행하고 확인하기**

애플리케이션을 다시 실행하고, 페이지 이동을 확인하기 쉽도록 페이지 크기를 2로 하여 접속한다.

- [http://localhost:8080/web/boards?size=2](http://localhost:8080/web/boards?size=2)

다음을 확인한다.

1. 게시글 두 개와 `1 페이지`, `다음 ▶` 링크가 출력된다. 첫 페이지이므로 `◀ 이전` 링크는 없다.
2. `다음 ▶`을 누르면 주소가 `/web/boards?page=2&size=2&keyword=`로 바뀌고 다음 게시글이 출력된다. `◀ 이전` 링크도 나타난다.
3. 검색 창에 `스프링`을 입력하고 검색하면, 제목에 "스프링"이 들어간 게시글만 출력되고 검색 창에 검색어가 남아 있다.
4. 게시글 제목을 누르면 `/boards/{번호}`로 이동하여 게시글 조회 API의 JSON이 출력된다.

`페이지 소스 보기`로 `href` 속성에 만들어진 주소를 확인한다. 검색어가 한글이면 URL 인코딩되어 있다.

**4) (확인) context path와 @{...}**

`application.properties`에 다음 설정을 잠시 추가하고 애플리케이션을 다시 실행한다.

```properties
server.servlet.context-path=/app
```

[http://localhost:8080/app/web/boards?size=2](http://localhost:8080/app/web/boards?size=2) 에 접속한다.
`다음 ▶` 링크와 게시글 제목 링크의 주소를 확인하면, 모두 앞에 **`/app`이 자동으로 붙어** 있다. 템플릿을 고치지 않았는데도 링크가 올바르게 동작한다.
`@{...}` 대신 `href="/web/boards"`처럼 주소를 직접 적었다면 `/app`이 빠져서 404 오류가 발생했을 것이다.

확인한 후에는 추가한 설정을 삭제하고 애플리케이션을 다시 실행한다.

### 실습-6: 프래그먼트로 공통 레이아웃 만들기

모든 화면에 공통으로 들어갈 `<head>`, 머리글(헤더), 바닥글(푸터)을 프래그먼트로 만들고, 게시글 목록 화면에 적용한다.

**1) 공통 스타일 파일 만들기**

`src/main/resources/static` 폴더 아래에 `css` 폴더를 만들고, 그 안에 `app.css` 파일을 만든다. 다음과 같이 작성한다.

```css
body { font-family: sans-serif; margin: 0 24px; }
header nav a { margin-right: 12px; }
table { border-collapse: collapse; }
th, td { border: 1px solid #999; padding: 4px 8px; }
th { background-color: #eee; }
footer { color: #777; font-size: 0.9em; }
```

> `static` 폴더의 파일은 주소로 바로 접근할 수 있다. (2장) `static/css/app.css` → `http://localhost:8080/css/app.css`

**2) 공통 레이아웃 프래그먼트 만들기**

`templates` 폴더 아래에 `fragments` 폴더를 만들고, 그 안에 `layout.html` 파일을 만든다. 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html lang="ko">

<!-- head 프래그먼트: 화면마다 제목(title)을 파라미터로 받는다. -->
<head data-th-fragment="head(title)">
  <meta charset="UTF-8">
  <title data-th-text="${title}">제목</title>
  <link rel="stylesheet" href="../../static/css/app.css" data-th-href="@{/css/app.css}">
</head>

<body>

  <!-- header 프래그먼트 -->
  <header data-th-fragment="header">
    <h1><a href="#" data-th-href="@{/web/boards}">스프링 게시판</a></h1>
    <nav>
      <a href="#" data-th-href="@{/web/boards}">게시글 목록</a>
      <a href="#" data-th-href="@{/web/demo/basic}">Thymeleaf 연습</a>
      <a href="#" data-th-href="@{/swagger-ui.html}">API 문서</a>
    </nav>
    <hr>
  </header>

  <!-- footer 프래그먼트 -->
  <footer data-th-fragment="footer">
    <hr>
    <p>&copy; 2026 스프링 프레임워크 &amp; 스프링 부트 실습</p>
  </footer>

</body>
</html>
```

> `href="../../static/css/app.css"`는 서버 없이 이 파일을 직접 열었을 때 사용하는 **임시 경로**이다. 서버에서는 `data-th-href`의 `@{/css/app.css}`로 바뀐다.

**3) 목록 화면에 프래그먼트 적용하기**

`templates/boards/list.html`의 `<head>` 부분과 `<body>`의 시작·끝 부분을 다음과 같이 바꾼다. (가운데의 검색 폼, 표, 페이지 이동 부분은 그대로 둔다. 헤더 프래그먼트에 사이트 제목이 `<h1>`으로 들어가므로, 기존의 `<h1>게시글 목록</h1>`은 `<h2>`로 바꾼다.)

```html
<!DOCTYPE html>
<html lang="ko">
<head data-th-replace="~{fragments/layout :: head('게시글 목록')}">
  <meta charset="UTF-8">
  <title>게시글 목록</title>
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>

  <h2>게시글 목록</h2>

  <!-- ... 검색 폼, 표, 페이지 이동 부분은 그대로 둔다. ... -->

  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
</html>
```

| 코드 | 의미 |
| --- | --- |
| `data-th-replace="~{fragments/layout :: head('게시글 목록')}"` | 이 `<head>`를 `layout.html`의 `head` 프래그먼트로 바꾸고, 제목으로 `'게시글 목록'`을 넘긴다. |
| `data-th-replace="~{fragments/layout :: header}"` | 이 `<header>`를 `header` 프래그먼트로 바꾼다. |

> `<head>` 안의 `<meta>`, `<title>`은 서버 없이 파일을 열었을 때와 HTML5 표준 검사를 위한 내용이다. 서버에서는 프래그먼트로 **통째로 바뀐다.**
> 표의 `border="1"` 속성은 이제 `app.css`가 테두리를 그리므로 지워도 된다.

**4) 실행하고 확인하기**

애플리케이션을 다시 실행하고 [http://localhost:8080/web/boards](http://localhost:8080/web/boards) 에 접속한다.

- 위쪽에 **스프링 게시판** 제목과 메뉴(게시글 목록, Thymeleaf 연습, API 문서)가, 아래쪽에 바닥글이 출력된다.
- `app.css`가 적용되어 글꼴과 표 모양이 바뀐다.
- 웹 브라우저 탭의 제목이 `게시글 목록`이다. 파라미터로 넘긴 제목이 `<title>`에 들어갔다.

`페이지 소스 보기`로 확인하면 `<head>`, `<header>`, `<footer>`가 `layout.html`의 내용으로 바뀌어 있다.

**5) 다른 화면에 적용하기**

`templates/demo/members.html`에도 같은 방법으로 프래그먼트를 적용해 보자.

```html
<head data-th-replace="~{fragments/layout :: head('반복문')}">
  ...
</head>
<body>
  <header data-th-replace="~{fragments/layout :: header}">헤더</header>
  ...
  <footer data-th-replace="~{fragments/layout :: footer}">푸터</footer>
</body>
```

> `members.html`의 `<head>` 안에 있던 `<style>`은 프래그먼트로 바뀌면서 사라지므로, 짝수 행의 배경색이 적용되지 않는다. `.even { background-color: #eef; }`를 `app.css`로 옮기면 다시 적용된다.

**6) 공통 부분 한 곳에서 바꾸기**

`fragments/layout.html`의 메뉴(`<nav>`)에 다음 링크를 추가하고 저장한다.

```html
      <a href="#" data-th-href="@{/web/hello}">Hello</a>
```

`/web/boards`와 `/web/demo/members`를 새로 고침하면, 두 화면 모두 메뉴에 `Hello` 링크가 추가되어 있다.
공통 부분을 **한 곳에서만** 고쳤는데 프래그먼트를 사용하는 모든 화면에 반영되었다.

**7) 정리**

| 실습 | 확인한 내용 |
| --- | --- |
| 실습-1 | `data-th-` 형식은 `th:` 형식과 똑같이 동작하고, HTML5 표준 검사를 통과한다. |
| 실습-2 | `${...}`로 값 출력, 문자열 연결, 연산, 기본값, 유틸리티 객체를 사용한다. 사용자 입력은 `data-th-text`로 이스케이프하여 출력한다. |
| 실습-3 | `data-th-if`, `data-th-unless`, `data-th-switch`로 조건에 따라 태그를 출력한다. 거짓인 태그는 HTML에 남지 않는다. |
| 실습-4 | `data-th-each`로 목록을 반복하고, 반복 상태 변수로 순번과 짝수·홀수를 확인한다. |
| 실습-5 | `@{...}`로 파라미터와 경로 변수가 들어간 링크를 만든다. context path가 바뀌어도 링크가 올바르게 만들어진다. |
| 실습-6 | `data-th-fragment`로 공통 레이아웃을 정의하고, `data-th-replace`로 여러 화면에서 가져다 쓴다. |

