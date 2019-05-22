package com.eomcs.basic.ch03.solution;

//# 부동소수점의 리터럴 - 2진수로 변경된 것을 확인하기
//
//- 자바가 부동소수점을 다룰 때 IEEE 754 규칙에 따라 2진수로 변환하는 것을 확인해 보자.

public class Exam34 {
  public static void main(String[] args) {
    System.out.println(12.375f);
  }
}

//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam34.java
//
//2) 클래스 파일을 확인하기
//- Exam34.class 파일을 hexdump 플러그인으로 출력한다.
//- 12.375의 2진수 값인 0x41460000을 찾아본다.