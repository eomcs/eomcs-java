# 스프링 프레임워크 & 스프링 부트

Spring Framework의 핵심 개념을 익히고 Spring Boot 기반 웹 애플리케이션을 구현하는 2일 과정이다.

1일차에는 개발 환경을 구성하고 IoC·DI, REST API, JPA를 학습하여 데이터베이스와 연동한 CRUD API를 구현한다. 2일차에는 Spring MVC와 Thymeleaf로 웹 화면을 만들고, Spring Security로 사용자 인증과 권한별 접근 제어를 적용한다. 마지막으로 각 기능을 통합한 미니 프로젝트를 구현하며 전체 요청 처리 흐름과 계층 구조를 정리한다.

## 학습 목표

- Spring Boot의 특징을 이해하고 프로젝트를 구성할 수 있다.
- IoC와 DI를 이해하고 객체 간 의존관계를 구성할 수 있다.
- HTTP와 REST를 이해하고 CRUD REST API를 구현할 수 있다.
- JPA로 데이터를 저장·조회·수정·삭제할 수 있다.
- Spring MVC와 Thymeleaf로 웹 화면을 구현할 수 있다.
- Spring Security로 사용자 인증과 권한별 접근 제어를 구현할 수 있다.
- 계층 구조를 적용하고 웹 애플리케이션의 전체 요청 흐름을 이해한다.

---

## 세부 강의 일정

|  학습 단원     | 학습 주제              | 세부 학습 내용 내용                                                                                               |
| -------- | ------------------ | ------------------------------------------------------------------------------------------------------ |
| **1장**  | 실습 준비           | JDK 및 IDE 설치·설정, Gradle/Maven 개요, Spring Initializr를 이용한 프로젝트 생성 및 실행, Spring Boot 프로젝트 구조 이해               |
| **2장**  | Spring Boot 개요     | Spring Framework와 Spring Boot의 관계, Spring Boot 특징, Starter, 내장 웹 서버, Auto Configuration 이해             |
| **3장**  | IoC와 DI            | IoC/DI 개념, Spring Bean, ApplicationContext, `@Component`, `@Service`, `@Repository`, 생성자 기반 의존성 주입     |
| **4장**  | REST API 기초        | REST 개념, HTTP Method와 Status Code, `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping` |
| **5장**  | REST API 구현        | `@PathVariable`, `@RequestParam`, `@RequestBody`, DTO, JSON 요청·응답, CRUD REST API 구현                    |
| **6장**  | JPA 기초             | ORM과 JPA 개념, Spring Data JPA, Entity와 Repository, `@Entity`, `@Id`, `@GeneratedValue`                  |
| **7장**  | JPA 활용             | CRUD, Query Method, JPQL 개요, Entity 연관관계, `@ManyToOne`, `@OneToMany`, 지연 로딩 개념                         |
| **8장**  | REST API + JPA     | Controller-Service-Repository 계층 구성, DTO와 Entity 분리, JPA 기반 CRUD REST API 구현, API 문서 자동 생성(springdoc-openapi, Swagger UI)                           |
| **9장**  | Spring WebMVC      | MVC 패턴, Spring MVC 요청 처리 구조, `@Controller`, Model, View, View Resolver, `@RestController`와 비교          |
| **10장** | Thymeleaf 기초       | Thymeleaf 템플릿 구조, 변수 표현식, 조건문, 반복문, 링크 및 URL 표현식, Fragment                                             |
| **11장** | Thymeleaf + WebMVC | 목록·상세 화면 구현, 등록·수정 Form 처리, Controller와 View 데이터 전달, JPA 연동 CRUD 화면 구현                                 |
| **12장** | Spring Security 개요 | 인증(Authentication)과 인가(Authorization), Spring Security 처리 구조, Security Filter Chain, 기본 보안 설정          |
| **13장** | 사용자 인증 구현          | 사용자 Entity 및 Repository 구성, `UserDetailsService`, `PasswordEncoder`, 회원가입, 로그인·로그아웃 구현                 |
| **14장** | 권한 기반 인가           | Role과 Authority, URL별 접근 제어, 사용자/관리자 권한 분리, Method Security, Thymeleaf와 Security 연동                    |
| **15장** | 종합 실습 평가         | Spring Boot + REST API + JPA + WebMVC + Thymeleaf + Security 통합한 미니 프로젝트 구현               |
