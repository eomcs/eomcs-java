# 2장. Spring Boot 개요

스프링 부트(Spring Boot)는 스프링 프레임워크(Spring Framework)를 기반으로 자바 애플리케이션을 쉽고 빠르게 만들고 실행하도록 돕는 도구이다. Spring Boot를 사용하면 복잡한 설정 없이 빠르게 애플리케이션을 시작할 수 있으며, 내장 웹 서버와 다양한 스타터를 통해 개발 생산성을 높일 수 있다.

## 스프링 부트란 무엇인가?

### 웹 애플리케이션을 만들 때 필요한 것들

게시판처럼 간단한 웹 애플리케이션이라도 실제로 동작하려면 생각보다 많은 것이 필요하다.

| 필요한 일 | 예 |
| --- | --- |
| 웹 요청 받기 | 웹 서버를 실행하고 `http://localhost:8080/hello` 같은 요청을 기다린다. |
| 요청 분석과 응답 | 요청 주소와 파라미터를 분석하여 알맞은 코드를 실행하고, 결과를 HTML이나 JSON으로 만들어 보낸다. |
| 데이터 저장 | 데이터베이스에 연결하여 게시글을 저장하고 조회한다. |
| 보안 | 로그인한 사용자만 글을 쓸 수 있도록 막는다. |
| 설정 관리 | 포트 번호, 데이터베이스 주소, 비밀번호 같은 값을 코드와 분리하여 관리한다. |
| 실행과 배포 | 완성한 애플리케이션을 서버 컴퓨터에 옮겨 실행한다. |

이 모든 것을 개발자가 처음부터 직접 만든다면, 정작 만들고 싶은 기능(게시글 등록, 목록 조회 등)은 시작도 하기 전에 지쳐 버릴 것이다.
그래서 자바 개발자들은 이런 공통 기능을 미리 만들어 둔 **프레임워크**(Framework)를 사용한다.
자바 진영에서 가장 널리 사용되는 프레임워크가 바로 **스프링 프레임워크**(Spring Framework)이다.

그런데 스프링 프레임워크는 기능이 많은 만큼, 사용하기 전에 해야 할 준비 작업(라이브러리 구성, 설정 파일 작성, 웹 서버 설치 등)도 많았다.
이 준비 작업을 대신 처리해 주는 도구가 **스프링 부트**(Spring Boot)이다.

### 스프링 부트의 정의

스프링 공식 사이트([https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot))는 스프링 부트를 다음과 같이 소개한다.

> Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
>
> (스프링 부트를 사용하면 **단독으로 실행**되고 **실제 서비스에 바로 쓸 수 있는** 스프링 기반 애플리케이션을 쉽게 만들 수 있으며, 만든 애플리케이션을 **그냥 실행**하기만 하면 된다.)

이 문장의 핵심 표현을 풀어 보면 다음과 같다.

| 표현 | 의미 |
| --- | --- |
| stand-alone (단독 실행) | 웹 서버를 따로 설치하지 않아도 애플리케이션 혼자서 실행된다. |
| production-grade (실무 수준) | 개발용 장난감이 아니라, 실제 서비스를 운영하는 데 필요한 기능과 설정을 갖추고 있다. |
| Spring based (스프링 기반) | 스프링 프레임워크를 기반으로 동작한다. 스프링을 대체하는 것이 아니다. |
| just run (그냥 실행) | 복잡한 준비 과정 없이 `java -jar` 명령 하나로 실행된다. |

### 1장에서 경험한 스프링 부트

1장에서 만든 `hello` 프로젝트를 떠올려 보자. 개발자가 직접 한 일과 스프링 부트가 대신 해 준 일을 나눠 보면 다음과 같다.

| 개발자가 한 일 | 스프링 부트가 대신 해 준 일 |
| --- | --- |
| Spring Initializr에서 `Spring Web` 선택 | 웹 개발에 필요한 라이브러리를 서로 호환되는 버전으로 모두 추가 |
| `HelloController` 클래스 작성 (10여 줄) | 웹 요청을 받아 `HelloController`로 전달하는 데 필요한 설정 등록 |
| `./gradlew bootRun` 명령 실행 | 웹 서버(Tomcat)를 내장하여 8080번 포트로 실행 |

개발자는 **"`/hello` 요청이 오면 문자열을 응답한다"는 기능 코드만 작성**했을 뿐, 설정 파일을 작성하거나 웹 서버를 설치하지 않았다.
나머지는 스프링 부트가 모두 처리했다.

### 스프링 부트를 사용하는 이유

**1) 빠르게 시작할 수 있다**

Spring Initializr에서 몇 가지 항목을 선택하면 곧바로 실행할 수 있는 프로젝트가 만들어진다.
1장에서 프로젝트를 만들고 첫 응답을 확인하기까지 걸린 시간을 생각해 보자. 개발 환경 준비에 드는 시간이 크게 줄어든다.

**2) 기능 개발에 집중할 수 있다**

반복되는 설정 작업을 스프링 부트가 대신 처리하므로, 개발자는 애플리케이션의 핵심 기능을 만드는 데 시간을 쓸 수 있다.
앞으로 이 과정에서 작성하는 코드도 대부분 게시글, 사용자 관리 처럼 **애플리케이션의 기능과 관련된 코드**이다.

**3) 검증된 기본값을 제공한다**

스프링 부트는 많은 프로젝트에서 공통으로 사용하는 설정을 **기본값**으로 정해 두었다.
예를 들어 웹 서버 포트는 8080, 문자 인코딩은 UTF-8, JSON 변환 라이브러리는 Jackson을 기본으로 사용한다.
기본값이 마음에 들지 않으면 `application.properties` 파일에서 바꾸고 싶은 값만 설정하면 된다.

**4) 실행과 배포가 간단하다**

애플리케이션과 웹 서버를 `.jar` 파일 하나로 묶어 `java -jar` 명령으로 실행한다.
JDK만 설치되어 있으면 어떤 컴퓨터에서든 같은 방법으로 실행할 수 있어서, 서버 컴퓨터나 클라우드, 도커(Docker) 컨테이너에 배포하기 쉽다.

**5) 사용자가 많고 자료가 풍부하다**

스프링 부트는 자바 백엔드 개발에서 가장 널리 사용되는 도구이다.
국내외 많은 기업이 사용하고 있으며, 공식 문서와 예제, 커뮤니티 자료가 풍부하여 문제가 생겼을 때 해결 방법을 찾기 쉽다.

### 스프링 부트에 대한 오해

처음 스프링 부트를 접할 때 흔히 하는 오해를 정리해 두자.

| 오해 | 사실 |
| --- | --- |
| 스프링 부트는 스프링을 대체하는 새로운 프레임워크이다. | 스프링 부트는 스프링 프레임워크 **위에서** 동작한다. 실제 기능은 스프링 프레임워크가 제공한다. |
| 스프링 부트를 쓰면 스프링 프레임워크를 몰라도 된다. | 설정이 자동으로 될 뿐, 코드는 스프링 프레임워크의 방식(IoC/DI, Spring MVC 등)으로 작성한다. 스프링 프레임워크를 알아야 스프링 부트를 제대로 쓸 수 있다. |
| 자동으로 설정되므로 설정을 바꿀 수 없다. | 기본값일 뿐이다. `application.properties`나 직접 작성한 설정으로 언제든 바꿀 수 있다. |
| 스프링 부트는 코드를 자동으로 만들어 주는 도구이다. | 코드를 생성하는 것이 아니라, 실행할 때 필요한 설정을 자동으로 구성한다. Spring Initializr가 만들어 주는 것도 프로젝트의 기본 뼈대뿐이다. |

첫 번째와 두 번째 오해에서 보듯이, 스프링 부트를 제대로 이해하려면 스프링 프레임워크와의 관계를 알아야 한다.
다음 절에서 둘의 관계를 자세히 살펴본다.

## 스프링 부트와 스프링 프레임워크의 관계

### 스프링 프레임워크

스프링 프레임워크는 자바 애플리케이션 개발을 돕는 오픈 소스 프레임워크이다.
로드 존슨(Rod Johnson)이 2002년 출간한 책의 예제 코드에서 출발하여 2004년에 1.0 버전이 발표되었다.
당시 기업용 자바 개발의 표준이던 EJB(Enterprise JavaBeans)가 너무 복잡하고 무거웠기 때문에, 평범한 자바 객체(POJO, Plain Old Java Object)만으로 기업용 애플리케이션을 만들 수 있도록 하는 것이 목표였다.

스프링 프레임워크가 제공하는 **핵심 기능**은 다음과 같다.

| 기능 | 설명 |
| --- | --- |
| IoC 컨테이너와 DI | 객체를 대신 생성하고, 객체 사이의 의존 관계를 연결해 준다. 스프링의 가장 핵심이 되는 기능이다. |
| AOP | 로깅, 트랜잭션처럼 여러 곳에 반복되는 공통 기능을 핵심 코드와 분리한다. |
| Spring MVC | 웹 요청을 받아 처리하고 응답하는 웹 애플리케이션을 만든다. |
| 트랜잭션 관리 | 여러 데이터베이스 작업을 하나의 단위로 묶어 처리한다. |
| 데이터 접근 | JDBC, JPA 같은 데이터베이스 기술을 일관된 방법으로 사용하게 한다. |

스프링 프레임워크는 기능이 풍부하고 유연하지만, 그만큼 **사용하기 전에 해야 할 준비 작업이 많았다.**

### 스프링 부트 이전의 개발 방식

스프링 부트가 없던 시절에는 스프링 MVC로 간단한 웹 애플리케이션 하나를 만들려고 해도 다음과 같은 작업을 개발자가 직접 해야 했다.

1. **라이브러리와 버전 결정**: `spring-core`, `spring-context`, `spring-webmvc`, JSON 변환 라이브러리, 로깅 라이브러리 등을 하나하나 추가하고, 서로 호환되는 버전을 직접 찾아 맞춰야 했다.
2. **설정 파일 작성**: 스프링 MVC의 핵심인 `DispatcherServlet`, 화면을 찾아 주는 `ViewResolver`, 데이터베이스 연결 정보(`DataSource`) 등을 XML 파일이나 Java 설정 클래스로 직접 등록해야 했다.
3. **웹 서버 설치와 배포**: Tomcat 같은 웹 서버를 따로 설치하고, 애플리케이션을 `.war` 파일로 패키징하여 웹 서버에 배포해야 실행할 수 있었다.

예를 들어 `DispatcherServlet`을 등록하려면 `web.xml`에 다음과 같은 설정을 작성해야 했다.

```xml
<!-- web.xml : 웹 요청을 DispatcherServlet으로 전달하도록 등록 -->
<servlet>
  <servlet-name>dispatcher</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/spring/dispatcher-servlet.xml</param-value>
  </init-param>
  <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
  <servlet-name>dispatcher</servlet-name>
  <url-pattern>/</url-pattern>
</servlet-mapping>
```

그리고 이 설정이 가리키는 `dispatcher-servlet.xml` 파일에 다시 스프링 MVC 설정을 작성해야 했다.
이런 설정은 프로젝트마다 거의 같았지만 매번 반복해서 작성해야 했고, 한 줄만 틀려도 애플리케이션이 실행되지 않았다.

### 스프링 부트의 등장

스프링 부트는 이런 반복 작업을 없애기 위해 2014년에 1.0 버전이 발표되었다.
스프링 부트는 "대부분의 프로젝트가 사용하는 설정은 미리 정해 두고, 바꾸고 싶은 부분만 개발자가 설정한다"는 방식을 따른다.

앞의 세 가지 준비 작업을 스프링 부트는 다음과 같이 해결한다.

| 스프링 부트 이전 | 스프링 부트 |
| --- | --- |
| 라이브러리를 하나하나 추가하고 버전을 직접 맞춘다. | **스타터**(Starter) 하나만 추가하면 관련 라이브러리가 검증된 버전으로 함께 추가된다. |
| `DispatcherServlet`, `ViewResolver` 등을 직접 설정한다. | **자동 구성**(Auto Configuration)이 추가된 라이브러리를 보고 필요한 설정을 자동으로 등록한다. |
| 웹 서버를 따로 설치하고 `.war` 파일을 배포한다. | **내장 웹 서버**가 포함된 `.jar` 파일을 `java -jar` 명령으로 바로 실행한다. |

1장에서 `hello` 프로젝트를 만들 때 `web.xml`이나 XML 설정 파일을 하나도 작성하지 않고, Tomcat도 설치하지 않았는데 웹 애플리케이션이 실행된 것은 이 때문이다.

> 스타터, 자동 구성, 내장 웹 서버는 다음 절 "Spring Boot의 특징"에서 자세히 살펴본다.

### 계층 구조로 보는 관계

스프링 부트로 만든 애플리케이션은 다음과 같은 계층 구조로 이루어진다.

```text
[ 내 애플리케이션 ]
    HelloApplication, HelloController, ...
            │
            ▼ 사용
[ Spring Boot ]
    스타터 · 자동 구성 · 내장 웹 서버 · 외부 설정(application.properties)
            │
            ▼ 기반
[ Spring Framework ]                   [ 기타 Spring 프로젝트 ]
    IoC/DI · AOP · Spring MVC              Spring Data JPA
    트랜잭션 · 데이터 접근                 Spring Security
            │
            ▼ 기반
[ Java (JDK) ]
```

- **Spring Framework**: 애플리케이션이 실제로 사용하는 기능을 제공한다. 자동차로 비유하면 엔진, 변속기, 브레이크 같은 **부품**에 해당한다.
- **Spring Boot**: 부품을 골라 조립하고 기본 설정을 마쳐서 바로 탈 수 있게 만든다. 자동차로 비유하면 **완성차 조립 라인**에 해당한다.
- **기타 Spring 프로젝트**: 스프링 프레임워크를 기반으로 만든 별도의 프로젝트이다. 이 과정에서는 데이터베이스 연동에 **Spring Data JPA**(6~8장), 사용자 인증에 **Spring Security**(12~14장)를 사용한다. 스프링 부트는 이 프로젝트들도 스타터와 자동 구성으로 쉽게 사용할 수 있게 해 준다.

### 버전 관계

스프링 부트의 각 버전은 특정 버전의 스프링 프레임워크를 기반으로 만들어진다.
스프링 부트 버전을 정하면 함께 사용할 스프링 프레임워크 버전은 자동으로 정해지므로, 스프링 프레임워크 버전을 따로 지정할 필요가 없다.

| Spring Boot | Spring Framework | 최소 Java 버전 | 비고 |
| --- | --- | --- | --- |
| 1.x | 4.x | Java 6~7 | |
| 2.x | 5.x | Java 8 | |
| 3.x | 6.x | Java 17 | `javax.*` 패키지가 `jakarta.*` 패키지로 변경됨 |
| **4.x** | **7.x** | **Java 17** | **이 과정에서 사용하는 버전** |

> 인터넷에서 예제 코드를 찾을 때는 어느 버전을 기준으로 작성된 코드인지 확인해야 한다.
> 예를 들어 Spring Boot 2.x 이전의 예제는 `javax.persistence.Entity`처럼 `javax`로 시작하는 패키지를 사용하지만, 3.x 이후에는 `jakarta.persistence.Entity`를 사용해야 한다.

## Spring Boot의 특징

앞에서 스프링 부트를 **사용하는 이유**(빠른 시작, 기능 개발에 집중, 간단한 배포 등)를 살펴보았다.
이번 절에서는 그런 장점을 만들어 내는 스프링 부트의 **기능**을 살펴본다.

- 단독으로 실행되는 스프링 애플리케이션을 만든다.
- 웹 서버를 애플리케이션에 내장한다. WAR 파일을 배포할 필요가 없다.
- 검증된 조합의 스타터 의존성으로 빌드 설정을 단순하게 만든다.
- 스프링과 외부 라이브러리를 가능한 한 자동으로 설정한다.
- 운영에 필요한 기능(지표 수집, 상태 확인, 외부 설정)을 제공한다.
- 코드를 생성하지 않으며, XML 설정도 필요 없다.

이 중 **스타터, 내장 웹 서버, 자동 구성**은 스프링 부트의 3대 핵심 기능으로, 이어지는 절에서 하나씩 자세히 다룬다. 각 특징을 간단히 살펴보자.

### 1) 스타터: 라이브러리를 묶음으로 추가한다

웹 애플리케이션을 만들려면 Spring MVC, 웹 서버(Tomcat), JSON 변환 라이브러리(Jackson), 로깅 라이브러리 등 여러 라이브러리가 필요하다.
스프링 부트는 목적별로 필요한 라이브러리를 묶은 **스타터**(Starter)를 제공한다.

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-webmvc'   // 웹 개발에 필요한 라이브러리 묶음
}
```

스타터 하나만 추가하면 관련 라이브러리가 **서로 호환되는 버전으로** 함께 추가된다.
개발자는 라이브러리 목록이나 버전 충돌을 신경 쓰지 않아도 된다.

### 2) 내장 웹 서버: 애플리케이션이 웹 서버를 품고 실행된다

예전에는 Tomcat 같은 웹 서버를 먼저 설치하고, 애플리케이션을 `.war` 파일로 만들어 웹 서버에 배포해야 했다.
스프링 부트는 반대로 **애플리케이션 안에 웹 서버를 내장**한다.
애플리케이션과 웹 서버가 `.jar` 파일 하나에 함께 들어 있으므로, 다음 명령 하나로 실행된다.

```bash
java -jar hello-0.0.1-SNAPSHOT.jar
```

즉 JDK만 있으면 어디서든 같은 방법으로 실행할 수 있다.
기본 웹 서버는 Tomcat이며, 필요하면 Jetty로 바꿀 수 있다.

### 3) 자동 구성: 필요한 설정을 알아서 등록한다

스프링 부트는 애플리케이션이 시작될 때 **어떤 라이브러리가 추가되어 있는지 살펴보고**, 그에 맞는 설정을 자동으로 등록한다.

- Spring MVC 라이브러리가 있다 → 웹 요청을 처리하는 `DispatcherServlet`을 설정한다.
- Tomcat 라이브러리가 있다 → 내장 Tomcat을 8080번 포트로 실행한다.
- Jackson 라이브러리가 있다 → 자바 객체를 JSON으로 변환하는 기능을 설정한다.

1장에서 설정 파일을 하나도 작성하지 않았는데도 `/hello` 요청이 처리된 것은 자동 구성 덕분이다.

### 4) 외부 설정: 코드를 고치지 않고 동작을 바꾼다

자동 구성은 **기본값**을 사용한다. 기본값을 바꾸고 싶으면 코드를 고치지 않고 코드 **바깥**에서 설정한다. 이를 **외부 설정**(Externalized Configuration)이라고 한다.

가장 많이 사용하는 방법은 `src/main/resources/application.properties` 파일에 설정하는 것이다.

```properties
server.port=8081
```

실행할 때 명령행 인자로 설정 값을 넘길 수도 있다. 명령행 인자는 `application.properties`보다 우선 적용된다.

```bash
java -jar hello-0.0.1-SNAPSHOT.jar --server.port=9090
```

같은 `.jar` 파일을 개발 컴퓨터에서는 8080번 포트로, 서버 컴퓨터에서는 9090번 포트로 실행하는 것처럼 **한 번 빌드한 결과물을 환경에 따라 다르게 실행**할 수 있다.
데이터베이스 주소나 비밀번호처럼 환경마다 다른 값도 같은 방법으로 관리한다.

### 5) 운영 기능: 실행 중인 애플리케이션의 상태를 확인한다

애플리케이션은 만든 후에도 서버에서 계속 운영해야 한다.
운영 중에는 "애플리케이션이 정상적으로 동작하고 있는가?", "메모리는 얼마나 쓰고 있는가?", "요청이 몇 번 들어왔는가?" 같은 정보가 필요하다.

스프링 부트는 **Actuator** 스타터(`spring-boot-starter-actuator`)를 추가하면 이런 정보를 웹 주소로 제공한다.
기본으로는 `/actuator/health`만 공개되며, 나머지 주소는 설정으로 공개할 수 있다.

| 주소 | 제공하는 정보 |
| --- | --- |
| `/actuator/health` | 애플리케이션의 상태 (정상이면 `{"status":"UP"}`) |
| `/actuator/metrics` | 메모리 사용량, 요청 수, 응답 시간 등의 지표 |
| `/actuator/info` | 애플리케이션 버전 등의 정보 |

### 6) 코드 생성 없음, XML 설정 불필요

스프링 부트는 개발자 대신 **코드를 생성해 주는 도구가 아니다.**
Spring Initializr가 만들어 주는 것도 프로젝트의 기본 뼈대(빌드 설정 파일, 실행 클래스)뿐이며, 자동 구성은 소스 코드를 만드는 것이 아니라 **애플리케이션이 실행될 때** 필요한 설정을 등록한다.
그래서 생성된 코드를 이해하거나 관리해야 하는 부담이 없다.

또한 예전 스프링 프로젝트에서 사용하던 `web.xml`, `applicationContext.xml` 같은 **XML 설정 파일이 필요 없다.**
설정이 필요하면 `application.properties`에 값을 적거나, Java 클래스에 애노테이션을 붙여 작성한다.

### 스프링 부트 특징과 실행 과정

스프링 부트의 특징은 따로 떨어져 있는 것이 아니라 서로 이어져 있다.
1장에서 `hello` 애플리케이션을 실행했을 때 일어난 일을 순서대로 정리하면 다음과 같다.

```text
① 스타터 추가
   spring-boot-starter-webmvc
        │  Spring MVC, Tomcat, Jackson 등의 라이브러리가 함께 추가된다.
        ▼
② 자동 구성
   추가된 라이브러리를 보고 DispatcherServlet, 내장 Tomcat 등을 자동으로 설정한다.
        │  기본값은 외부 설정(application.properties, 명령행 인자)으로 바꿀 수 있다.
        ▼
③ 내장 웹 서버 실행
   설정된 포트(기본 8080)로 Tomcat이 실행되어 요청을 기다린다.
        │
        ▼
④ 단독 실행
   애플리케이션과 웹 서버가 함께 든 .jar 파일 하나로 어디서든 실행된다.
```

이어지는 절에서는 이 흐름의 핵심인 **스타터**, **내장 웹 서버**, **자동 구성**을 차례로 자세히 살펴본다.

## Starter

### 스타터란?

**스타터**(Starter)는 특정 기능을 개발하는 데 필요한 라이브러리를 **한 묶음으로 정리해 둔 의존성**이다.
스타터를 하나 추가하면, 그 스타터에 정리된 라이브러리들이 함께 추가된다.

스타터를 음식점의 **세트 메뉴**에 비유할 수 있다.
햄버거, 감자튀김, 음료를 하나씩 고르는 대신 세트 메뉴 하나를 주문하면, 서로 잘 어울리는 구성으로 한 번에 나온다.
마찬가지로 웹 개발에 필요한 라이브러리를 하나씩 고르는 대신 `spring-boot-starter-webmvc` 하나를 추가하면, 서로 호환되는 라이브러리들이 한 번에 추가된다.

스타터 자체에는 실행되는 코드가 거의 없다. 스타터는 **"이 기능을 쓰려면 이 라이브러리들이 필요하다"는 목록**일 뿐이다.
실제 기능은 스타터가 가져온 라이브러리(스프링 프레임워크, Tomcat, Jackson 등)가 제공한다.

### 스타터가 없다면

스타터가 없다면 웹 애플리케이션을 만들기 위해 `build.gradle`에 다음과 같이 필요한 라이브러리를 하나하나 적어야 한다. (설명을 위한 예시이다.)

```groovy
dependencies {
  // 스프링 MVC
  implementation 'org.springframework:spring-webmvc:7.0.x'
  // 내장 웹 서버
  implementation 'org.apache.tomcat.embed:tomcat-embed-core:11.0.x'
  implementation 'org.apache.tomcat.embed:tomcat-embed-el:11.0.x'
  // JSON 변환
  implementation 'tools.jackson.core:jackson-databind:3.x.x'
  // 로깅
  implementation 'ch.qos.logback:logback-classic:1.5.x'
  // 스프링 부트 자동 구성
  implementation 'org.springframework.boot:spring-boot-autoconfigure:4.1.x'
  // ... 그 밖에 여러 라이브러리
}
```

이 방식에는 두 가지 어려움이 있다.

1. **무엇이 필요한지 알아야 한다.** 웹 애플리케이션 하나를 만드는 데 어떤 라이브러리가 필요한지 개발자가 모두 알고 있어야 한다.
2. **버전을 맞춰야 한다.** 라이브러리끼리 서로 호환되는 버전이 정해져 있다. 예를 들어 스프링 프레임워크 7.0과 함께 쓸 수 있는 Tomcat, Jackson 버전을 직접 찾아서 맞춰야 한다. 버전이 맞지 않으면 컴파일은 되더라도 실행 중에 오류가 발생할 수 있다.

스타터를 사용하면 다음 한 줄로 끝난다.

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-webmvc'
}
```

### 스타터와 버전 관리

스타터를 추가할 때 버전을 적지 않는다는 점에 주목하자.
버전은 `build.gradle`의 `plugins` 블록에 적은 **스프링 부트 버전 하나로 결정**된다.

```groovy
plugins {
  id 'org.springframework.boot' version '4.1.1'              // 스프링 부트 버전
  id 'io.spring.dependency-management' version '1.1.7'      // 버전 관리 플러그인
}
```

스프링 부트는 버전마다 **함께 사용해도 문제가 없는지 검증한 라이브러리 버전 목록**을 가지고 있다.
이런 버전 목록을 **BOM**(Bill of Materials, 부품 명세서)이라고 한다.
`io.spring.dependency-management` 플러그인은 이 목록을 읽어서, 버전을 적지 않은 라이브러리에 알맞은 버전을 채워 준다.

```text
스프링 부트 4.1.1 선택
   └─ BOM(검증된 버전 목록)
        ├─ spring-webmvc       → 7.0.x
        ├─ tomcat-embed-core   → 11.0.x
        ├─ jackson-databind    → 3.x.x
        └─ ...
```

덕분에 스프링 부트 버전을 올리면 관련 라이브러리의 버전도 함께 검증된 조합으로 올라간다.
각 스프링 부트 버전이 관리하는 라이브러리와 버전은 공식 문서의 [Dependency Versions](https://docs.spring.io/spring-boot/appendix/dependency-versions/coordinates.html)에서 확인할 수 있다.

> 스프링 부트가 관리하는 라이브러리에 버전을 직접 적으면, 검증된 조합이 깨져서 호환성 문제가 생길 수 있다.
> 특별한 이유가 없다면 버전을 적지 않고 스프링 부트에 맡긴다.
>
> 반대로 스프링 부트가 관리하지 **않는** 라이브러리는 버전을 직접 적어야 한다. 버전을 적지 않았는데 빌드 오류가 발생하면, 스프링 부트가 관리하지 않는 라이브러리인지 확인한다.

### 스타터 이름 규칙

스프링 부트가 공식으로 제공하는 스타터는 모두 `spring-boot-starter-*` 형식의 이름을 가진다. `*` 자리에는 기능이나 기술의 이름이 들어간다.

| 구분 | 이름 형식 | 예 |
| --- | --- | --- |
| 공식 스타터 | `spring-boot-starter-기술이름` | `spring-boot-starter-webmvc`, `spring-boot-starter-data-jpa` |
| 공식 테스트용 스타터 | `spring-boot-starter-기술이름-test` | `spring-boot-starter-webmvc-test`, `spring-boot-starter-data-jpa-test` |
| 외부(서드파티) 스타터 | `프로젝트이름-spring-boot-starter` | `mybatis-spring-boot-starter` |

- **테스트용 스타터**: Spring Boot 4부터는 기술마다 테스트용 스타터가 짝을 이루어 제공된다. 테스트용 스타터는 `testImplementation`으로 추가하며, 테스트 코드에서만 사용된다.
- **외부 스타터**: 스프링 팀이 아닌 다른 프로젝트에서 만든 스타터이다. `spring-boot`로 시작하는 이름은 공식 스타터용으로 예약되어 있으므로, 외부 스타터는 프로젝트 이름을 앞에 붙인다.

### 스타터는 다른 스타터를 포함한다

스타터는 다른 스타터를 포함할 수 있다. 예를 들어 `spring-boot-starter-webmvc`는 다음과 같은 스타터를 포함한다.

```text
spring-boot-starter-webmvc
 ├─ spring-boot-starter          ← 모든 스타터의 기반 (스프링 부트 핵심, 자동 구성, 로깅)
 │    └─ spring-boot-starter-logging
 ├─ spring-boot-starter-jackson  ← JSON 변환
 ├─ spring-boot-starter-tomcat   ← 내장 웹 서버
 └─ (Spring MVC 관련 라이브러리)
```

`spring-boot-starter`는 거의 모든 스타터가 포함하는 **기본 스타터**이다.
스프링 부트의 핵심 기능, 자동 구성, 로깅 라이브러리를 가져온다.
그래서 어떤 스타터를 추가하든 자동 구성과 로깅은 기본으로 사용할 수 있다.

> 스타터가 작은 스타터들을 조합하는 구조이므로, 필요하면 일부를 다른 것으로 바꿀 수 있다.
> 예를 들어 `spring-boot-starter-tomcat` 대신 `spring-boot-starter-jetty`를 사용하면 내장 웹 서버를 Jetty로 바꿀 수 있다. 이 방법은 다음 절 "내장 웹 서버"에서 다룬다.

### 이 과정에서 사용하는 스타터

이 과정에서 사용할 주요 스타터는 다음과 같다.

| 스타터 | 제공하는 기능 | Spring Initializr 이름
| --- | --- | --- |
| `spring-boot-starter-webmvc` | Spring MVC 웹 애플리케이션, REST API, 내장 Tomcat | Spring Web |
| `spring-boot-starter-data-jpa` | JPA를 이용한 데이터베이스 연동 | Spring Data JPA |
| `spring-boot-starter-thymeleaf` | Thymeleaf 템플릿으로 HTML 화면 생성 | Thymeleaf |
| `spring-boot-starter-security` | 인증(로그인)과 인가(권한 검사) | Spring Security |
| `spring-boot-starter-validation` | 입력 값 검증 | Validation |
| `spring-boot-starter-actuator` | 애플리케이션 상태 확인, 지표 수집 | Spring Boot Actuator |

Spring Initializr의 **Dependencies**에서 항목을 선택하는 것은, 대부분 해당 스타터를 `build.gradle`에 추가하는 것과 같다.

> 데이터베이스 드라이버(H2, MySQL 등)처럼 스타터가 아닌 일반 라이브러리도 있다. 이런 라이브러리도 스프링 부트가 버전을 관리하므로 버전을 적지 않아도 된다.

### 정리

- 스타터는 특정 기능에 필요한 라이브러리를 한 묶음으로 정리한 의존성이다.
- 스타터를 사용하면 필요한 라이브러리를 몰라도 되고, 버전을 맞출 필요도 없다.
- 라이브러리 버전은 스프링 부트 버전 하나로 결정되며, 검증된 조합(BOM)이 적용된다.
- 공식 스타터의 이름은 `spring-boot-starter-*`이고, 테스트용 스타터는 뒤에 `-test`가 붙는다.

## 내장 웹 서버

### 웹 서버와 서블릿 컨테이너

웹 브라우저가 보낸 요청을 받아 응답을 돌려주는 프로그램을 **웹 서버**(Web Server)라고 한다.
자바 웹 애플리케이션은 **서블릿 컨테이너**(Servlet Container)라는 특별한 웹 서버 위에서 실행된다.
서블릿 컨테이너는 요청을 받아 자바 코드(서블릿)를 실행하고, 그 결과를 응답으로 보내 준다.

대표적인 서블릿 컨테이너는 다음과 같다.

| 서블릿 컨테이너 | 설명 |
| --- | --- |
| **Tomcat** | 가장 널리 사용되는 서블릿 컨테이너. 스프링 부트의 기본 내장 웹 서버이다. |
| **Jetty** | 가볍고 다른 프로그램에 내장하기 쉬운 서블릿 컨테이너. 스프링 부트에서 Tomcat 대신 사용할 수 있다. |

스프링 MVC의 핵심인 `DispatcherServlet`도 서블릿이다. 그래서 스프링 MVC 애플리케이션을 실행하려면 반드시 서블릿 컨테이너가 필요하다.

> 이 교재에서는 "웹 서버"와 "서블릿 컨테이너"를 같은 뜻으로 사용한다.

### 전통적인 방식: 웹 서버에 애플리케이션을 배포한다

스프링 부트 이전에는 **웹 서버가 먼저 있고, 그 안에 애플리케이션을 넣는** 방식을 사용했다.

```
① Tomcat 설치  →  ② 애플리케이션을 .war 파일로 빌드  →  ③ .war 파일을 Tomcat의 webapps 폴더에 복사  →  ④ Tomcat 실행

[ Tomcat ]  ← 별도로 설치한 프로그램
   ├── 애플리케이션 A.war
   ├── 애플리케이션 B.war
   └── ...
```

**WAR**(Web Application Archive) 파일은 웹 애플리케이션을 웹 서버에 배포하기 위해 묶은 파일이다. WAR 파일은 혼자서는 실행되지 않고, 반드시 웹 서버 안에서 실행된다.

이 방식에는 다음과 같은 번거로움이 있었다.

- 개발자 컴퓨터, 테스트 서버, 운영 서버마다 **같은 버전의 Tomcat을 설치하고 설정**해야 한다.
- Tomcat 버전이나 설정이 서로 다르면 "내 컴퓨터에서는 되는데 서버에서는 안 되는" 문제가 생긴다.
- 개발 중에 코드를 확인하려면 빌드 → 배포 → Tomcat 재시작 과정을 반복해야 한다.

### 스프링 부트 방식: 애플리케이션이 웹 서버를 품는다

스프링 부트는 관계를 뒤집었다. **애플리케이션이 먼저 있고, 그 안에 웹 서버를 넣는다.**
Tomcat을 별도의 프로그램으로 설치하는 대신, Tomcat을 **라이브러리**(`tomcat-embed-core` 등)로 애플리케이션에 포함시킨다.
이렇게 애플리케이션 안에 포함된 웹 서버를 **내장 웹 서버**(Embedded Web Server)라고 한다.

```
java -jar hello-0.0.1-SNAPSHOT.jar

[ hello-0.0.1-SNAPSHOT.jar ]  ← 애플리케이션
   ├── HelloApplication, HelloController, ...
   ├── 스프링 프레임워크, 스프링 부트 라이브러리
   └── 내장 Tomcat  ← 라이브러리로 포함된 웹 서버
```

애플리케이션의 `main()` 메서드에서 `SpringApplication.run()`이 호출되면, 스프링 부트가 **내장 Tomcat을 직접 생성하고 시작**한다.
1장의 실행 로그에서 본 `Tomcat started on port 8080`은 스프링 부트가 내장 Tomcat을 시작했다는 뜻이다.

내장 Tomcat은 `spring-boot-starter-webmvc`에 포함된 `spring-boot-starter-tomcat` 스타터를 통해 추가된 것이다. (앞 절 "스타터는 다른 스타터를 포함한다" 참고)

### 두 방식 비교

| 항목 | 전통적인 방식 | 스프링 부트 방식 |
| --- | --- | --- |
| 웹 서버 | 별도로 설치한다. | 라이브러리로 애플리케이션에 포함된다. |
| 빌드 결과 | `.war` 파일 | 실행 가능한 `.jar` 파일 |
| 실행 방법 | Tomcat에 배포한 후 Tomcat을 실행한다. | `java -jar 파일명.jar` |
| 웹 서버 설정 | Tomcat의 설정 파일(`server.xml` 등)을 수정한다. | `application.properties`에 설정한다. |
| 웹 서버 버전 | 서버마다 설치된 버전이 다를 수 있다. | 스프링 부트가 정한 버전으로 항상 같다. |
| 실행에 필요한 것 | JDK + Tomcat | JDK |

> 스프링 부트도 필요하면 `.war` 파일로 빌드하여 외부 Tomcat에 배포할 수 있다. Spring Initializr의 **Packaging**에서 `War`를 선택하면 된다.
> 하지만 새로 만드는 프로젝트는 대부분 내장 웹 서버를 사용하는 `.jar` 방식을 사용한다.

### 실행 가능한 jar 파일의 구조

스프링 부트가 만드는 `.jar` 파일은 일반 `.jar` 파일과 구조가 다르다.
애플리케이션의 클래스뿐만 아니라 **실행에 필요한 모든 라이브러리**(내장 Tomcat 포함)를 함께 담고 있어서, 다른 파일 없이 혼자서 실행된다.
이런 jar 파일을 **실행 가능한 jar**(Executable Jar) 또는 **Fat Jar**라고 한다.

```
hello-0.0.1-SNAPSHOT.jar
├── META-INF
│   └── MANIFEST.MF                 ← 실행 정보 (어떤 클래스부터 실행할지)
├── org/springframework/boot/loader ← jar 안의 라이브러리를 읽어 들이는 스프링 부트 로더
└── BOOT-INF
    ├── classes                     ← 개발자가 작성한 클래스와 application.properties
    │   ├── com/example/hello/HelloApplication.class
    │   ├── com/example/hello/HelloController.class
    │   └── application.properties
    └── lib                         ← 스타터가 가져온 모든 라이브러리
        ├── spring-webmvc-7.0.x.jar
        ├── tomcat-embed-core-11.0.x.jar
        └── ...
```

`META-INF/MANIFEST.MF` 파일에는 다음과 같은 실행 정보가 들어 있다.

```
Main-Class: org.springframework.boot.loader.launch.JarLauncher
Start-Class: com.example.hello.HelloApplication
Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
```

`java -jar` 명령으로 실행하면 다음 순서로 진행된다.

1. `Main-Class`에 지정된 스프링 부트 로더(`JarLauncher`)가 먼저 실행된다.
2. 로더가 `BOOT-INF/lib`의 라이브러리와 `BOOT-INF/classes`의 클래스를 읽어 들인다.
3. 로더가 `Start-Class`에 지정된 `HelloApplication`의 `main()` 메서드를 호출한다.
4. `SpringApplication.run()`이 스프링 컨테이너를 만들고 내장 Tomcat을 시작한다.

> 1장에서 `build/libs` 폴더에 함께 생긴 `hello-0.0.1-SNAPSHOT-plain.jar`는 `BOOT-INF/lib`에 해당하는 라이브러리가 없는 일반 jar 파일이다. 그래서 혼자서는 실행할 수 없다.

### 내장 웹 서버 설정

내장 웹 서버의 설정은 `application.properties`에 `server.`으로 시작하는 이름으로 지정한다. 자주 사용하는 설정은 다음과 같다.

| 설정 | 기본값 | 의미 |
| --- | --- | --- |
| `server.port` | `8080` | 요청을 받을 포트 번호 |
| `server.servlet.context-path` | (없음) | 모든 요청 주소 앞에 붙는 경로. 예를 들어 `/app`으로 지정하면 `/hello` 대신 `/app/hello`로 요청해야 한다. |
| `server.servlet.session.timeout` | `30m` | 세션 유지 시간 |
| `server.servlet.encoding.charset` | `UTF-8` | 요청과 응답의 문자 인코딩 |

```properties
server.port=8081
server.servlet.context-path=/app
```

> 설정할 수 있는 전체 목록은 [Common Application Properties](https://docs.spring.io/spring-boot/appendix/application-properties/index.html) 문서의 **Server Properties**에서 확인할 수 있다.

### 다른 웹 서버로 바꾸기

스프링 부트의 기본 내장 웹 서버는 Tomcat이지만, 스타터를 바꾸면 Jetty를 사용할 수 있다.
`spring-boot-starter-webmvc`가 포함하는 `spring-boot-starter-tomcat`을 **제외**하고, 대신 `spring-boot-starter-jetty`를 **추가**한다.

```groovy
dependencies {
  implementation('org.springframework.boot:spring-boot-starter-webmvc') {
    // Tomcat 스타터 제외
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
  }
  // Jetty 스타터 추가
  implementation 'org.springframework.boot:spring-boot-starter-jetty'
}
```

애플리케이션 코드는 한 줄도 바꿀 필요가 없다.
스프링 부트가 시작할 때 Tomcat 대신 Jetty 라이브러리가 있는 것을 보고, **자동으로 Jetty를 내장 웹 서버로 구성**하기 때문이다.
이것이 다음 절에서 다룰 **자동 구성**(Auto Configuration)이다.

> 이 과정에서는 기본값인 Tomcat을 사용한다.

### 정리

- 스프링 부트는 웹 서버(Tomcat)를 라이브러리로 애플리케이션에 **내장**한다.
- 애플리케이션과 모든 라이브러리가 **실행 가능한 jar** 파일 하나에 담기므로, JDK만 있으면 `java -jar` 명령으로 어디서든 실행된다.
- 웹 서버 설정은 `application.properties`의 `server.*` 설정으로 바꾼다.
- 스타터를 바꾸면 코드 수정 없이 웹 서버를 Jetty로 바꿀 수 있다.

## 자동 구성(Auto Configuration)

### 자동 구성이란?

**자동 구성**(Auto Configuration)은 스프링 부트가 애플리케이션을 시작할 때 **어떤 라이브러리가 추가되어 있는지 살펴보고, 그에 맞는 설정을 자동으로 등록하는 기능**이다.

스마트폰에 이어폰을 연결하면 소리가 자동으로 이어폰으로 나오고, 이어폰을 빼면 다시 스피커로 나오는 것을 떠올려 보자.
사용자가 설정 화면에서 출력 장치를 바꾸지 않아도, 스마트폰이 **무엇이 연결되어 있는지 보고** 알맞게 동작한다.
스프링 부트의 자동 구성도 마찬가지이다. 개발자가 설정을 작성하지 않아도, 스프링 부트가 **어떤 라이브러리가 있는지 보고** 알맞게 설정한다.

앞 절에서 Tomcat 스타터를 빼고 Jetty 스타터를 넣었을 때 코드를 바꾸지 않았는데도 Jetty가 실행된 것은, 스프링 부트가 "Tomcat은 없고 Jetty가 있다"는 것을 보고 Jetty를 설정했기 때문이다.

### 1장에서 일어난 자동 구성

1장의 `hello` 프로젝트에는 설정 파일이 없었지만, 애플리케이션이 시작될 때 스프링 부트는 다음과 같은 설정을 자동으로 등록했다.

| 이런 라이브러리가 있으면 | 이런 설정을 자동으로 등록한다 | 확인할 수 있었던 곳 |
| --- | --- | --- |
| Spring MVC (`spring-webmvc`) | 웹 요청을 받아 컨트롤러로 전달하는 `DispatcherServlet` | `/hello` 요청이 `HelloController`로 전달됨 |
| 내장 Tomcat (`tomcat-embed-core`) | 8080번 포트로 내장 Tomcat 실행 | 로그의 `Tomcat started on port 8080` |
| Jackson (`jackson-databind`) | 자바 객체를 JSON으로 변환하는 기능 | Actuator의 JSON 응답 |
| Spring MVC | `static` 폴더의 파일을 제공하는 기능, `index.html`을 첫 화면으로 사용하는 기능 | 1장의 `static/index.html` |
| Spring MVC | 오류가 발생했을 때 보여 주는 기본 오류 페이지 | 없는 주소로 요청했을 때의 `Whitelabel Error Page` |
| Logback (`logback-classic`) | 콘솔에 로그를 출력하는 설정 | 실행할 때 출력된 로그 |

개발자가 이 설정을 모두 직접 작성해야 했다면, `HelloController` 하나를 실행하는 데도 많은 설정 코드가 필요했을 것이다.

### 자동 구성은 어떻게 동작하는가

자동 구성은 다음 네 단계로 동작한다. 여기서는 전체 흐름만 이해하면 된다.

**1단계: `@SpringBootApplication`이 자동 구성을 켠다**

1장에서 본 것처럼 `@SpringBootApplication`에는 `@EnableAutoConfiguration`이 포함되어 있다.
이 애노테이션이 "자동 구성을 사용하라"는 스위치 역할을 한다.

```java
@SpringBootApplication   // = @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
public class HelloApplication { ... }
```

**2단계: 자동 구성 후보 목록을 읽는다**

스프링 부트의 라이브러리(jar 파일) 안에는 **자동 구성 클래스의 목록**이 들어 있다.
목록 파일의 경로는 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`이다.
스프링 부트는 애플리케이션이 시작될 때 이 목록을 읽어 자동 구성 **후보**를 모은다.

목록에는 웹 서버, Spring MVC, JSON, 데이터베이스 등 기술별 자동 구성 클래스가 들어 있다.
스타터를 추가하면 그 기술의 자동 구성 목록도 함께 추가된다.

**3단계: 후보마다 조건을 검사한다**

각 자동 구성 클래스에는 **조건**이 붙어 있다. 조건을 만족하는 후보만 적용되고, 만족하지 못하는 후보는 건너뛴다.
조건은 `@ConditionalOn...` 형태의 애노테이션으로 표시한다. 자주 사용되는 조건은 다음과 같다.

| 조건 애노테이션 | 의미 | 예 |
| --- | --- | --- |
| `@ConditionalOnClass` | 특정 클래스(라이브러리)가 **있으면** 적용한다. | Tomcat 클래스가 있으면 내장 Tomcat을 설정한다. |
| `@ConditionalOnMissingClass` | 특정 클래스가 **없으면** 적용한다. | |
| `@ConditionalOnMissingBean` | 개발자가 같은 종류의 객체를 직접 등록하지 **않았으면** 적용한다. | 개발자가 JSON 변환 객체를 직접 만들었으면 자동 구성은 물러난다. |
| `@ConditionalOnProperty` | 특정 설정 값이 지정되어 **있으면** 적용한다. | `server.error.whitelabel.enabled`가 `false`가 아니면 기본 오류 페이지를 설정한다. |
| `@ConditionalOnWebApplication` | 웹 애플리케이션이면 적용한다. | |

**4단계: 조건을 만족한 설정을 등록한다**

조건을 모두 만족한 자동 구성만 실제로 적용되어, 필요한 객체를 만들고 설정한다.
스프링이 만들어 관리하는 이런 객체를 **빈**(Bean)이라고 한다. (빈은 3장에서 자세히 다룬다.)

전체 흐름을 그림으로 정리하면 다음과 같다.

```
@SpringBootApplication
   └─ @EnableAutoConfiguration  ← 자동 구성 켜기
         │
         ▼
자동 구성 후보 목록 읽기 (AutoConfiguration.imports)
   ├─ 내장 Tomcat 자동 구성   ─ 조건: Tomcat 클래스가 있는가?        → 예  → 적용 ✔
   ├─ 내장 Jetty 자동 구성    ─ 조건: Jetty 클래스가 있는가?         → 아니오 → 건너뜀 ✘
   ├─ Spring MVC 자동 구성   ─ 조건: DispatcherServlet 클래스가 있는가? → 예  → 적용 ✔
   ├─ Jackson 자동 구성      ─ 조건: Jackson 클래스가 있는가?        → 예  → 적용 ✔
   └─ ...
         │
         ▼
조건을 만족한 설정만 등록 → 애플리케이션 실행
```

자동 구성 클래스의 모습을 간단히 보면 다음과 같다. (이해를 돕기 위해 단순화한 코드이며, 실제 코드와는 다르다.)

```java
@AutoConfiguration
@ConditionalOnClass(Tomcat.class)              // ① Tomcat 라이브러리가 있을 때만
public class TomcatAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean                    // ② 개발자가 직접 만든 것이 없을 때만
  public TomcatWebServerFactory tomcatWebServerFactory() {
    return new TomcatWebServerFactory();       // ③ 내장 Tomcat을 만드는 객체를 등록한다
  }
}
```

자동 구성도 결국 **조건이 붙은 평범한 스프링 설정 클래스**이다. 스프링 부트는 이런 설정 클래스를 수백 개 미리 만들어 두고, 조건에 맞는 것만 골라 적용한다.

### 자동 구성을 바꾸는 방법

자동 구성은 **기본값**일 뿐이다. 개발자는 필요에 따라 자동 구성을 바꿀 수 있다.

**방법 1: 설정 값 바꾸기 (가장 많이 사용)**

대부분의 자동 구성은 `application.properties`의 설정 값을 읽어서 동작한다.
설정 값을 지정하면 자동 구성이 그 값을 사용한다.

```properties
# 내장 Tomcat 포트 변경
server.port=8081

# JSON을 보기 좋게 들여쓰기
spring.jackson.serialization.indent_output=true

# 기본 오류 페이지 끄기
server.error.whitelabel.enabled=false
```

**방법 2: 직접 설정하기**

개발자가 같은 역할의 객체를 직접 등록하면, `@ConditionalOnMissingBean` 조건 때문에 자동 구성이 **물러나고** 개발자의 설정이 사용된다.
즉, **개발자가 직접 한 설정이 자동 구성보다 우선**한다.

**방법 3: 특정 자동 구성 끄기**

특정 자동 구성을 아예 사용하지 않으려면 `@SpringBootApplication`의 `exclude` 속성에 지정한다.

```java
@SpringBootApplication(exclude = 끌_자동_구성_클래스.class)
```

> 방법 3은 자동 구성이 원하지 않는 방식으로 동작할 때 사용한다. 이 과정에서는 사용하지 않는다.

이처럼 스프링 부트는 "**많이 쓰는 방식은 미리 정해 두고, 다르게 하고 싶은 부분만 개발자가 설정한다**"는 원칙을 따른다.
이런 원칙을 **설정보다 관례**(Convention over Configuration)라고 한다.

### 자동 구성 결과 확인하기

자동 구성은 눈에 보이지 않게 동작하므로, 어떤 설정이 적용되었는지 궁금할 때가 있다.
`application.properties`에 다음 설정을 추가하고 애플리케이션을 실행하면, 자동 구성의 조건 검사 결과가 로그에 출력된다.

```properties
debug=true
```

이 보고서를 **조건 평가 보고서**(Conditions Evaluation Report)라고 하며, 다음과 같이 구성된다.

| 항목 | 의미 |
| --- | --- |
| `Positive matches` | 조건을 만족하여 **적용된** 자동 구성 |
| `Negative matches` | 조건을 만족하지 못하여 **적용되지 않은** 자동 구성과 그 이유 |
| `Exclusions` | `exclude`로 제외한 자동 구성 |
| `Unconditional classes` | 조건 없이 항상 적용되는 자동 구성 |

> 애플리케이션이 예상대로 동작하지 않을 때 이 보고서를 보면, 어떤 자동 구성이 왜 적용되었는지(또는 적용되지 않았는지) 확인할 수 있다.

### 정리

- 자동 구성은 추가된 라이브러리를 보고 필요한 설정을 자동으로 등록하는 기능이다.
- `@EnableAutoConfiguration`이 자동 구성을 켜고, 스프링 부트는 자동 구성 후보 목록에서 **조건을 만족하는 것만** 적용한다.
- 자동 구성은 기본값일 뿐이며, 설정 값을 바꾸거나 직접 설정하면 개발자의 설정이 우선한다.
- `debug=true`로 조건 평가 보고서를 출력하면 자동 구성 결과를 확인할 수 있다.

스타터가 라이브러리를 가져오고, 자동 구성이 그 라이브러리를 보고 설정하며, 그 결과로 내장 웹 서버가 실행된다.
이것이 개발자가 `HelloController`만 작성하고도 웹 애플리케이션을 실행할 수 있었던 이유이다.

## 실습

### 실습-1: hello 프로젝트에서 스프링 부트와 스프링 프레임워크의 관계 확인하기

1장에서 만든 `hello` 프로젝트에서 스프링 부트와 스프링 프레임워크가 각각 어떤 역할을 하는지 확인해 보자.

**1) 소스 코드에서 확인하기**

`HelloApplication.java`와 `HelloController.java`에서 사용한 클래스와 애노테이션의 패키지를 살펴보면 다음과 같다.

| 사용한 클래스/애노테이션 | 패키지 | 제공 |
| --- | --- | --- |
| `SpringApplication` | `org.springframework.boot` | Spring Boot |
| `@SpringBootApplication` | `org.springframework.boot.autoconfigure` | Spring Boot |
| `@RestController` | `org.springframework.web.bind.annotation` | Spring Framework (Spring MVC) |
| `@GetMapping` | `org.springframework.web.bind.annotation` | Spring Framework (Spring MVC) |

애플리케이션을 **시작하고 구성하는 부분**은 스프링 부트가, 웹 요청을 **처리하는 부분**은 스프링 프레임워크가 담당한다는 것을 알 수 있다.
패키지 이름에 `boot`가 들어 있으면 스프링 부트, 그렇지 않으면 스프링 프레임워크(또는 다른 스프링 프로젝트)가 제공하는 것이다.

**2) 의존 라이브러리에서 확인하기**

`build.gradle`에는 `spring-boot-starter-webmvc` 하나만 추가했다.
하지만 이 스타터를 통해 스프링 프레임워크의 라이브러리가 함께 추가된다.
`hello` 프로젝트 폴더에서 다음 명령을 실행하여 추가된 라이브러리 중 스프링 프레임워크의 라이브러리만 골라 출력한다.

```bash
# macOS
./gradlew dependencies --configuration runtimeClasspath | grep "org.springframework:"
```

```powershell
# Windows (PowerShell)
.\gradlew dependencies --configuration runtimeClasspath | Select-String "org.springframework:"
```

다음과 같이 `spring-core`, `spring-context`, `spring-webmvc` 등 스프링 프레임워크의 라이브러리가 **7.0.x** 버전으로 추가된 것을 확인할 수 있다. (출력 형식은 버전에 따라 조금 다를 수 있다.)

```text
|    |    +--- org.springframework:spring-core:7.0.x
|    |    |    \--- org.springframework:spring-jcl:7.0.x
|    |    +--- org.springframework:spring-context:7.0.x
|    |    |    +--- org.springframework:spring-aop:7.0.x
|    |    |    +--- org.springframework:spring-beans:7.0.x
|    |    |    +--- org.springframework:spring-expression:7.0.x
|    +--- org.springframework:spring-web:7.0.x
|    \--- org.springframework:spring-webmvc:7.0.x
...
```

VS Code에서는 탐색기 아래의 **JAVA PROJECTS** 영역에서 `hello > Referenced Libraries 또는 Project and External Dependencies`를 펼쳐 추가된 라이브러리 목록을 확인할 수도 있다.

**3) 정리**

- 개발자는 스프링 부트의 스타터(`spring-boot-starter-webmvc`) **하나만** 추가했다.
- 스프링 부트가 이 스타터를 통해 스프링 프레임워크의 라이브러리(`spring-core`, `spring-context`, `spring-webmvc` 등)를 **호환되는 버전으로** 추가했다.
- 스프링 부트가 이 라이브러리들을 **자동으로 설정**했기 때문에 개발자는 `HelloController`만 작성하면 되었다.
- 실제로 `/hello` 요청을 받아 `HelloController`의 메서드를 호출한 것은 스프링 프레임워크의 **Spring MVC**이다.

### 실습-2: 명령행 인자로 포트 바꾸기

1장에서 만든 `hello` 프로젝트 폴더에서 다음을 실행한다.

```bash
# macOS
./gradlew build
java -jar build/libs/hello-0.0.1-SNAPSHOT.jar --server.port=9090
```

```powershell
# Windows (PowerShell)
.\gradlew build
java -jar build\libs\hello-0.0.1-SNAPSHOT.jar --server.port=9090
```

실행 로그에 `Tomcat started on port 9090`이 출력되는지 확인하고, 웹 브라우저에서 [http://localhost:9090/hello](http://localhost:9090/hello) 에 접속한다.
확인한 후에는 `Ctrl + C`를 눌러 종료한다.

### 실습-3: Actuator 확인하기

1장에서 만든 `hello` 프로젝트에 Actuator를 추가하고, 실행 중인 애플리케이션의 상태 정보를 확인해 보자.

**1) Actuator 스타터 추가**

`build.gradle` 파일의 `dependencies` 블록에 다음 한 줄을 추가한다.

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-webmvc'
  implementation 'org.springframework.boot:spring-boot-starter-actuator'   // 추가
  testImplementation 'org.springframework.boot:spring-boot-starter-webmvc-test'
  testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

> VS Code에서 `build.gradle`을 저장하면 오른쪽 아래에 빌드 설정을 동기화할지 묻는 알림이 뜬다. `Yes`(또는 `Always`)를 누른다.

**2) 애플리케이션 실행**

`hello` 프로젝트 폴더에서 애플리케이션을 실행한다.

```bash
# macOS
./gradlew bootRun
```

```powershell
# Windows (PowerShell)
.\gradlew bootRun
```

**3) 공개된 주소 목록 확인**

웹 브라우저에서 [http://localhost:8080/actuator](http://localhost:8080/actuator) 에 접속한다.
Actuator가 제공하는 주소의 목록이 JSON 형식으로 출력된다.

```json
{
  "_links": {
    "self": { "href": "http://localhost:8080/actuator", "templated": false },
    "health": { "href": "http://localhost:8080/actuator/health", "templated": false },
    "health-path": { "href": "http://localhost:8080/actuator/health/{*path}", "templated": true }
  }
}
```

> Chrome 브라우저에서는 화면 위쪽의 `예쁘게 인쇄(Pretty-print)`를 선택하면 JSON을 보기 좋게 정리하여 보여 준다.

목록에 `health`만 있는 것에 주목하자.
Actuator가 제공하는 정보에는 애플리케이션의 내부 설정처럼 외부에 노출되면 위험한 정보도 있기 때문에, **기본으로는 `health`만 웹으로 공개**한다.

**4) 애플리케이션 상태 확인**

[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) 에 접속한다.

```json
{"status":"UP"}
```

`UP`은 애플리케이션이 정상적으로 동작하고 있다는 뜻이다. 문제가 있으면 `DOWN`이 출력된다.
운영 환경에서는 모니터링 도구나 로드 밸런서가 이 주소를 주기적으로 호출하여 애플리케이션이 살아 있는지 확인한다.

**5) 더 많은 정보 공개하기**

`Ctrl + C`를 눌러 애플리케이션을 종료하고, `src/main/resources/application.properties` 파일에 다음 설정을 추가한다.

```properties
# 웹으로 공개할 Actuator 주소 지정
management.endpoints.web.exposure.include=health,info,metrics

# health 주소에서 세부 항목까지 보여 주기
management.endpoint.health.show-details=always

# info 주소에 출력할 정보
management.info.env.enabled=true
management.info.java.enabled=true
info.app.name=hello
info.app.description=Hello Spring Boot
```

| 설정 | 의미 |
| --- | --- |
| `management.endpoints.web.exposure.include` | 웹으로 공개할 주소를 쉼표로 구분하여 지정한다. |
| `management.endpoint.health.show-details` | `always`로 지정하면 상태를 판단한 세부 항목까지 보여 준다. |
| `management.info.env.enabled` | `info.`으로 시작하는 설정 값을 `/actuator/info`에 출력한다. |
| `management.info.java.enabled` | 실행 중인 Java의 버전 정보를 `/actuator/info`에 출력한다. |

애플리케이션을 다시 실행하고 [http://localhost:8080/actuator](http://localhost:8080/actuator) 에 접속하면, 목록에 `info`와 `metrics`가 추가된 것을 확인할 수 있다.

**6) 세부 상태 확인**

[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) 에 다시 접속한다.
이번에는 상태를 판단한 세부 항목(`components`)이 함께 출력된다.

```json
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP",
      "details": { "total": 494384795648, "free": 123456789012, "threshold": 10485760, ... }
    },
    "ping": { "status": "UP" },
    ...
  }
}
```

- `diskSpace`: 디스크 여유 공간이 기준값(`threshold`, 기본 10MB) 이상이면 `UP`이다.
- `ping`: 애플리케이션이 요청에 응답할 수 있으면 `UP`이다.

> 6장에서 데이터베이스를 연결하면 `db` 항목이 자동으로 추가되어 데이터베이스 연결 상태도 함께 확인할 수 있다. 추가된 스타터에 맞춰 확인 항목이 자동으로 늘어나는 것도 자동 구성 덕분이다.

**7) 애플리케이션 정보 확인**

[http://localhost:8080/actuator/info](http://localhost:8080/actuator/info) 에 접속한다.
`application.properties`에 적은 `info.` 설정 값과 Java 버전 정보가 출력된다.

```json
{
  "app": {
    "name": "hello",
    "description": "Hello Spring Boot"
  },
  "java": {
    "version": "25.0.x",
    "vendor": { "name": "Eclipse Adoptium", ... },
    ...
  }
}
```

**8) 지표 확인**

[http://localhost:8080/actuator/metrics](http://localhost:8080/actuator/metrics) 에 접속하면 확인할 수 있는 지표의 이름 목록이 출력된다.

```json
{
  "names": [
    "application.ready.time",
    "application.started.time",
    "jvm.memory.used",
    "process.uptime",
    ...
  ]
}
```

지표 이름을 주소 뒤에 붙이면 해당 지표의 값을 확인할 수 있다.

- JVM 메모리 사용량: [http://localhost:8080/actuator/metrics/jvm.memory.used](http://localhost:8080/actuator/metrics/jvm.memory.used)
- 실행 시간(초): [http://localhost:8080/actuator/metrics/process.uptime](http://localhost:8080/actuator/metrics/process.uptime)

```json
{
  "name": "jvm.memory.used",
  "description": "The amount of used memory",
  "baseUnit": "bytes",
  "measurements": [
    { "statistic": "VALUE", "value": 45678912 }
  ],
  ...
}
```

**9) 요청 횟수 확인**

[http://localhost:8080/hello](http://localhost:8080/hello) 에 여러 번(예: 5번) 접속한 후, 다음 주소에 접속한다.

- [http://localhost:8080/actuator/metrics/http.server.requests](http://localhost:8080/actuator/metrics/http.server.requests)

```json
{
  "name": "http.server.requests",
  "baseUnit": "seconds",
  "measurements": [
    { "statistic": "COUNT", "value": 5 },
    { "statistic": "TOTAL_TIME", "value": 0.0123 },
    { "statistic": "MAX", "value": 0.0045 }
  ],
  "availableTags": [
    { "tag": "uri", "values": ["/hello", ...] },
    ...
  ]
}
```

`COUNT`는 처리한 요청 수, `TOTAL_TIME`은 요청 처리에 걸린 전체 시간, `MAX`는 가장 오래 걸린 요청의 처리 시간(초)이다.
주소 뒤에 `?tag=uri:/hello`를 붙이면 `/hello` 요청만 골라서 확인할 수 있다.

- [http://localhost:8080/actuator/metrics/http.server.requests?tag=uri:/hello](http://localhost:8080/actuator/metrics/http.server.requests?tag=uri:/hello)

> `http.server.requests` 지표는 요청이 한 번 이상 처리된 후에 목록에 나타난다.
> Actuator 주소를 호출한 요청도 함께 집계되므로, 전체 `COUNT`는 `/hello`를 호출한 횟수보다 클 수 있다.

**10) 정리**

- 스타터 한 줄(`spring-boot-starter-actuator`)만 추가했는데, 상태 확인과 지표 수집 기능이 자동으로 구성되었다.
- 공개할 주소와 출력할 정보는 코드를 고치지 않고 `application.properties`로 조정했다.

이처럼 Actuator 실습에도 **스타터**, **자동 구성**, **외부 설정**이라는 스프링 부트의 특징이 모두 담겨 있다.

> **주의**: `management.endpoints.web.exposure.include=*`로 설정하면 모든 Actuator 주소가 공개된다.
> 여기에는 애플리케이션의 설정 값이나 환경 변수처럼 민감한 정보를 보여 주는 주소도 포함되므로, 실제 서비스에서는 꼭 필요한 주소만 공개하고 Spring Security로 접근을 제한해야 한다.

### 실습-4: 스타터가 가져온 라이브러리 살펴보기

`hello` 프로젝트에서 스타터가 어떤 라이브러리를 가져왔는지 확인하고, Spring Initializr에서 선택한 항목이 어떤 스타터로 바뀌는지 확인해 보자.

**1) 의존성 트리 출력하기**

`hello` 프로젝트 폴더에서 다음 명령을 실행한다.
`runtimeClasspath`는 애플리케이션을 실행할 때 사용하는 라이브러리 목록이라는 뜻이다.

```bash
# macOS
./gradlew dependencies --configuration runtimeClasspath
```

```powershell
# Windows (PowerShell)
.\gradlew dependencies --configuration runtimeClasspath
```

다음과 같이 라이브러리가 트리 형태로 출력된다. (길어서 일부를 생략했다. 출력 내용은 버전에 따라 조금 다를 수 있다.)

```text
runtimeClasspath - Runtime classpath of source set 'main'.
+--- org.springframework.boot:spring-boot-starter-webmvc -> 4.1.1
|    +--- org.springframework.boot:spring-boot-starter:4.1.1
|    |    +--- org.springframework.boot:spring-boot:4.1.1
|    |    +--- org.springframework.boot:spring-boot-autoconfigure:4.1.1
|    |    +--- org.springframework.boot:spring-boot-starter-logging:4.1.1
|    |    |    +--- ch.qos.logback:logback-classic:1.5.x
|    |    |    ...
|    |    ...
|    +--- org.springframework.boot:spring-boot-starter-jackson:4.1.1
|    |    ...
|    +--- org.springframework.boot:spring-boot-starter-tomcat:4.1.1
|    |    +--- org.apache.tomcat.embed:tomcat-embed-core:11.0.x
|    |    ...
|    ...
\--- org.springframework.boot:spring-boot-starter-actuator -> 4.1.1
     ...
```

> 3) Actuator 실습을 진행했다면 `spring-boot-starter-actuator`도 함께 출력된다.

출력 결과를 보며 다음을 확인한다.

- 맨 왼쪽(첫 번째 단계)에는 `build.gradle`에 직접 적은 스타터만 나온다.
- 스타터 아래에 `spring-boot-starter`, `spring-boot-starter-tomcat` 같은 **다른 스타터**가 포함되어 있다.
- `spring-boot-starter-webmvc -> 4.1.1`처럼 화살표(`->`) 뒤에 버전이 표시된다. `build.gradle`에는 버전을 적지 않았지만, 스프링 부트 버전에 맞는 버전이 **자동으로 채워졌다**는 뜻이다.

**2) 스프링 부트가 정한 버전 확인하기**

특정 라이브러리의 버전이 어떻게 결정되었는지 확인하려면 `dependencyInsight` 명령을 사용한다.
다음은 내장 Tomcat 라이브러리(`tomcat-embed-core`)의 버전을 확인하는 명령이다.

```bash
# macOS
./gradlew dependencyInsight --dependency tomcat-embed-core --configuration runtimeClasspath
```

```powershell
# Windows (PowerShell)
.\gradlew dependencyInsight --dependency tomcat-embed-core --configuration runtimeClasspath
```

출력의 첫 부분에서 선택된 버전과 그 이유를 확인할 수 있다.

```text
org.apache.tomcat.embed:tomcat-embed-core:11.0.x (selected by rule)
...
```

`selected by rule`은 스프링 부트의 버전 관리 규칙(BOM)에 따라 버전이 선택되었다는 뜻이다.

**3) Spring Initializr에서 스타터 확인하기**

1. 웹 브라우저에서 [https://start.spring.io](https://start.spring.io) 에 접속한다.
2. **Project**는 `Gradle - Groovy`, **Java**는 `25`를 선택한다.
3. `ADD DEPENDENCIES...` 버튼을 눌러 다음 항목을 추가한다.
   - `Spring Web`
   - `Spring Data JPA`
   - `Thymeleaf`
   - `Spring Security`
   - `H2 Database`
4. 화면 아래의 `EXPLORE` 버튼을 누르고, 왼쪽 파일 목록에서 `build.gradle`을 선택한다.

`dependencies` 블록이 다음과 비슷하게 구성된 것을 확인할 수 있다. (순서는 다를 수 있다.)

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
  implementation 'org.springframework.boot:spring-boot-starter-security'
  implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
  implementation 'org.springframework.boot:spring-boot-starter-webmvc'
  implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
  runtimeOnly 'com.h2database:h2'
  testImplementation 'org.springframework.boot:spring-boot-starter-data-jpa-test'
  testImplementation 'org.springframework.boot:spring-boot-starter-security-test'
  testImplementation 'org.springframework.boot:spring-boot-starter-webmvc-test'
  testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

다음 사항을 확인한다.

- 선택한 항목이 각각 `spring-boot-starter-*` 스타터로 추가되었다.
- 테스트용 스타터가 있는 기술은 짝이 되는 **테스트용 스타터**(`-test`)가 `testImplementation`으로 함께 추가되었다.
- `H2 Database`는 스타터가 아닌 일반 라이브러리(`com.h2database:h2`)로 추가되었다. `runtimeOnly`는 컴파일할 때는 필요 없고 실행할 때만 필요하다는 뜻이다.
- Thymeleaf와 Spring Security를 함께 선택하면, 둘을 연동하는 라이브러리(`thymeleaf-extras-springsecurity6`)도 자동으로 추가된다.
- 모든 라이브러리에 버전이 적혀 있지 않다.

> 이 실습에서는 `build.gradle`의 내용만 확인한다. 프로젝트를 내려받을 필요는 없다.

### 실습-5: 내장 웹 서버 살펴보기

실행 가능한 jar 파일의 내부를 들여다보고, 내장 웹 서버의 설정을 바꾸고, 웹 서버를 Jetty로 교체해 보자.

**1) jar 파일 만들기**

`hello` 프로젝트 폴더에서 jar 파일을 빌드한다.

```bash
# macOS
./gradlew build
```

```powershell
# Windows (PowerShell)
.\gradlew build
```

**2) jar 파일의 내용 확인하기**

JDK에 포함된 `jar` 명령으로 jar 파일에 들어 있는 파일 목록을 출력한다.

```bash
# macOS
jar tf build/libs/hello-0.0.1-SNAPSHOT.jar
```

```powershell
# Windows (PowerShell)
jar tf build\libs\hello-0.0.1-SNAPSHOT.jar
```

출력된 목록에서 다음 파일이 있는지 확인한다.

- `BOOT-INF/classes/com/example/hello/HelloController.class` : 직접 작성한 클래스
- `BOOT-INF/classes/application.properties` : 설정 파일
- `BOOT-INF/lib/tomcat-embed-core-11.0.x.jar` : 내장 Tomcat 라이브러리
- `BOOT-INF/lib/spring-webmvc-7.0.x.jar` : 스프링 MVC 라이브러리

`BOOT-INF/lib` 폴더에 들어 있는 라이브러리의 개수도 세어 보자.

```bash
# macOS
jar tf build/libs/hello-0.0.1-SNAPSHOT.jar | grep -c "BOOT-INF/lib/.*\.jar$"
```

```powershell
# Windows (PowerShell)
(jar tf build\libs\hello-0.0.1-SNAPSHOT.jar | Select-String "BOOT-INF/lib/.+\.jar$").Count
```

스타터 한 줄로 추가된 라이브러리가 모두 jar 파일 안에 들어 있다.

**3) 실행 정보 확인하기**

jar 파일에서 `META-INF/MANIFEST.MF` 파일만 꺼내어 내용을 확인한다.
`jar xf` 명령은 jar 파일에서 지정한 파일을 현재 폴더로 꺼낸다.

```bash
# macOS
jar xf build/libs/hello-0.0.1-SNAPSHOT.jar META-INF/MANIFEST.MF
cat META-INF/MANIFEST.MF
```

```powershell
# Windows (PowerShell)
jar xf build\libs\hello-0.0.1-SNAPSHOT.jar META-INF/MANIFEST.MF
Get-Content META-INF\MANIFEST.MF
```

`Main-Class`와 `Start-Class`의 값을 확인한다.

```
Manifest-Version: 1.0
Main-Class: org.springframework.boot.loader.launch.JarLauncher
Start-Class: com.example.hello.HelloApplication
Spring-Boot-Version: 4.1.x
Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
...
```

확인한 후에는 꺼낸 `META-INF` 폴더를 삭제한다.

```bash
# macOS
rm -rf META-INF
```

```powershell
# Windows (PowerShell)
Remove-Item -Recurse META-INF
```

**4) 다른 폴더에서 실행하기**

실행 가능한 jar 파일은 혼자서 실행된다는 것을 확인해 보자.
jar 파일을 프로젝트와 관계없는 폴더(예: 홈 폴더)로 복사한 후, 그 폴더에서 실행한다.

```bash
# macOS
cp build/libs/hello-0.0.1-SNAPSHOT.jar ~/
cd ~
java -jar hello-0.0.1-SNAPSHOT.jar
```

```powershell
# Windows (PowerShell)
Copy-Item build\libs\hello-0.0.1-SNAPSHOT.jar $HOME
cd $HOME
java -jar hello-0.0.1-SNAPSHOT.jar
```

[http://localhost:8080/hello](http://localhost:8080/hello) 에 접속하여 정상적으로 응답하는지 확인한다.
프로젝트 폴더도, Gradle도, 별도로 설치한 Tomcat도 없이 **JDK와 jar 파일 하나만으로** 웹 애플리케이션이 실행되었다.

확인한 후에는 `Ctrl + C`를 눌러 종료하고, 복사한 jar 파일을 삭제한 후 `hello` 프로젝트 폴더로 돌아간다.

**5) 내장 웹 서버 설정 바꾸기**

`src/main/resources/application.properties` 파일에 다음 설정을 추가한다.

```properties
server.port=8081
server.servlet.context-path=/app
```

애플리케이션을 실행한다.

```bash
# macOS
./gradlew bootRun
```

```powershell
# Windows (PowerShell)
.\gradlew bootRun
```

실행 로그에서 포트 번호와 context path가 바뀐 것을 확인한다.

```
... Tomcat started on port 8081 (http) with context path '/app'
```

웹 브라우저에서 다음 두 주소에 각각 접속해 본다.

- [http://localhost:8081/hello](http://localhost:8081/hello) → 404 오류가 발생한다.
- [http://localhost:8081/app/hello](http://localhost:8081/app/hello) → `Hello, Spring Boot!`가 출력된다.

모든 요청 주소 앞에 `/app`이 붙었다는 것을 알 수 있다.
확인한 후에는 `Ctrl + C`를 눌러 종료하고, 추가한 두 줄을 삭제하여 원래대로 되돌린다.

**6) 웹 서버를 Jetty로 바꾸기**

`build.gradle`의 `dependencies` 블록에서 `spring-boot-starter-webmvc` 부분을 다음과 같이 바꾼다.

```groovy
dependencies {
  implementation('org.springframework.boot:spring-boot-starter-webmvc') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
  }
  implementation 'org.springframework.boot:spring-boot-starter-jetty'
  // ... 나머지는 그대로 둔다.
}
```

> VS Code에서 `build.gradle`을 저장한 후 빌드 설정을 동기화할지 묻는 알림이 뜨면 `Yes`를 누른다.

애플리케이션을 다시 실행하고, 실행 로그를 확인한다.

```
... Jetty started on port 8080 (http/1.1) with context path '/'
```

`Tomcat started` 대신 `Jetty started`가 출력된다. (로그 형식은 버전에 따라 조금 다를 수 있다.)
[http://localhost:8080/hello](http://localhost:8080/hello) 에 접속하면 이전과 똑같이 응답한다.

**Java 코드는 한 줄도 바꾸지 않고** 스타터만 바꿨는데 웹 서버가 교체되었다.

확인한 후에는 `Ctrl + C`를 눌러 종료하고, `build.gradle`을 원래대로 되돌린다.

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-webmvc'
  // ... 나머지는 그대로 둔다.
}
```

**7) 정리**

- 실행 가능한 jar 파일 안에는 개발자가 작성한 클래스와 내장 Tomcat을 포함한 모든 라이브러리가 들어 있다.
- `MANIFEST.MF`의 `Main-Class`(스프링 부트 로더)가 먼저 실행되고, 로더가 `Start-Class`(`HelloApplication`)를 실행한다.
- 내장 웹 서버의 포트와 context path는 `application.properties`로 바꿀 수 있다.
- 스타터만 바꾸면 코드 수정 없이 웹 서버가 교체된다.

### 실습-6: 자동 구성 확인하기

`hello` 프로젝트에서 자동 구성의 결과를 확인하고, 설정 값으로 자동 구성을 바꿔 보자.

**1) 조건 평가 보고서 출력하기**

`src/main/resources/application.properties` 파일에 다음 설정을 추가한다.

```properties
debug=true
```

애플리케이션을 실행한다.

```bash
# macOS
./gradlew bootRun
```

```powershell
# Windows (PowerShell)
.\gradlew bootRun
```

실행 로그 중간에 다음과 같은 **조건 평가 보고서**가 출력된다. 내용이 길기 때문에 터미널을 위로 스크롤하여 찾는다.

```
============================
CONDITIONS EVALUATION REPORT
============================


Positive matches:
-----------------
   ...

Negative matches:
-----------------
   ...
```

> VS Code 터미널에서는 `Cmd + F`(macOS) 또는 `Ctrl + F`(Windows)를 눌러 로그에서 글자를 검색할 수 있다.

**2) 적용된 자동 구성 찾기**

`Positive matches` 아래에서 `DispatcherServlet`을 검색한다.
다음과 비슷한 내용을 찾을 수 있다. (클래스 이름과 형식은 버전에 따라 조금 다를 수 있다.)

```
   DispatcherServletAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.web.servlet.DispatcherServlet' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)
```

`DispatcherServlet` 클래스를 **찾았기 때문에**(`found required class`) Spring MVC 자동 구성이 적용되었다는 뜻이다.
같은 방법으로 `Tomcat`, `Jackson`을 검색하여 내장 Tomcat과 JSON 변환 자동 구성이 적용된 것을 확인해 보자.

**3) 적용되지 않은 자동 구성 찾기**

`Negative matches` 아래의 항목을 몇 개 살펴본다.
다음과 같이 필요한 클래스를 **찾지 못했거나**(`did not find required class`), 조건에 맞는 설정 값이 없어서 적용되지 않은 자동 구성이 나열되어 있다.

```
   XxxAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'xxx.Xxx' (OnClassCondition)
```

해당 기능의 라이브러리를 추가하지 않았기 때문에 적용되지 않은 것이다.
나중에 그 기능의 스타터를 추가하면 이 자동 구성이 `Positive matches`로 옮겨 가게 된다.

확인한 후에는 `Ctrl + C`를 눌러 종료하고, `debug=true`를 삭제한다.

**4) JSON 자동 변환 확인하기**

Jackson 자동 구성 덕분에 컨트롤러가 자바 객체를 리턴하면 자동으로 JSON으로 변환된다.
`HelloController.java`에 다음 메서드를 추가한다.

```java
package com.example.hello;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello, Spring Boot!";
  }

  // 추가
  @GetMapping("/hello/json")
  public Map<String, Object> helloJson() {
    return Map.of(
        "message", "Hello, Spring Boot!",
        "time", LocalDateTime.now().toString());
  }
}
```

애플리케이션을 실행하고 [http://localhost:8080/hello/json](http://localhost:8080/hello/json) 에 접속한다.

```json
{"message":"Hello, Spring Boot!","time":"2026-xx-xxTxx:xx:xx.xxxxxx"}
```

JSON으로 변환하는 코드를 작성하지 않았는데도, 리턴한 `Map` 객체가 JSON으로 변환되어 응답되었다. (항목의 순서는 다를 수 있다.)

**5) 설정 값으로 자동 구성 바꾸기 ①: JSON 들여쓰기**

애플리케이션을 종료하고, `application.properties`에 다음 설정을 추가한다.

```properties
spring.jackson.serialization.indent_output=true
```

애플리케이션을 다시 실행하고 [http://localhost:8080/hello/json](http://localhost:8080/hello/json) 에 접속한다.
이번에는 JSON이 보기 좋게 들여쓰기되어 출력된다.

```json
{
  "message" : "Hello, Spring Boot!",
  "time" : "2026-xx-xxTxx:xx:xx.xxxxxx"
}
```

자동 구성된 JSON 변환 기능이 **설정 값을 읽어서** 동작 방식을 바꾼 것이다.

**6) 설정 값으로 자동 구성 바꾸기 ②: 기본 오류 페이지 끄기**

웹 브라우저에서 없는 주소인 [http://localhost:8080/nothing](http://localhost:8080/nothing) 에 접속한다.
스프링 부트가 자동으로 구성한 기본 오류 페이지인 **Whitelabel Error Page**가 출력된다.

```
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

... There was an unexpected error (type=Not Found, status=404).
```

애플리케이션을 종료하고, `application.properties`에 다음 설정을 추가한다.

```properties
spring.web.error.whitelabel.enabled=false
```

애플리케이션을 다시 실행하고 같은 주소에 접속하면, Whitelabel Error Page 대신 내장 웹 서버(Tomcat)의 기본 오류 페이지가 출력된다.
설정 값 하나로 기본 오류 페이지 자동 구성이 **꺼진** 것이다.

확인한 후에는 `Ctrl + C`를 눌러 종료하고, 이 실습에서 `application.properties`에 추가한 설정을 삭제한다.
`HelloController`에 추가한 `helloJson()` 메서드는 그대로 두어도 된다.

**7) 정리**

- `debug=true`로 조건 평가 보고서를 출력하면, 어떤 자동 구성이 적용되었고 어떤 것이 왜 적용되지 않았는지 확인할 수 있다.
- JSON 변환처럼 자동 구성이 등록한 기능은 코드 없이 바로 사용할 수 있다.
- 자동 구성의 동작은 `application.properties`의 설정 값으로 바꾸거나 끌 수 있다.

