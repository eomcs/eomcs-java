//# 클래스 이름과 소스 파일 이름
//
//- 클래스를 공개하는 경우 소스 파일 이름은 반드시 클래스 이름과 같아야 한다.
//
//## 클래스 접근 제어
//- 클래스를 선언할 때 사용 범위를 조정할 수 있다.
//  - public : 다른 패키지의 클래스가 사용할 수 있게 공개한다.
//  - (default) : 같은 패키지에 소속된 클래스만이 사용할 수 있다.
//- 문법
//  - 공개: public class 클래스명 {...}
//  - 비공개: class 클래스명 {...}
//- public 이 붙은 공개 클래스의 경우 소스 파일명이 클래스 이름과 같아야 한다.
//  다르면 컴파일 오류가 발생한다.
//- 비공개 클래스의 경우 클래스명과 소스 파일명이 일치하지 않아도 된다.
// 
package com.eomcs.basic.ch01.solution;

//## 비공개 클래스인 경우
//- 다음과 같이 클래스명과 소스 파일명이 다르더라도 괜찮다. 
class Exam04_Test1 {}

//## 공개 클래스인 경우
//- 다음과 같이 클래스명과 소스 파일명이 같아야 한다.
public class Exam04 {}

//- 클래스명과 소스 파일명이 다르면 컴파일 오류가 발생한다.
//public class Exam04_Test2 {} // 컴파일 오류!

//## 실습
//1) 컴파일하기
//     eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch01/solution/Exam04.java
//
//2) 생성된 클래스 파일 확인하기
//- bin/main/com/eomcs/basic/ch01/solution 디렉토리에 Exam04_Test1.class와 Exam04.class 파일이 생성된다
//
//3) Exam04_Test2 클래스 선언의 주석을 제거한 후 컴파일 한다.
//- 컴파일 오류 메시지를 확인한다.