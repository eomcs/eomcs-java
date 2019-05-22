//# 소스 파일의 인코딩 지정하기
//
//1) 소스 파일을 문자집합 'UTF-8'로 인코딩하여 저장하라.
//2) 다음과 같이 컴파일하라.
//     eomcs-java-basic$ javac src/main/java/com/eomcs/basic/ch01/test/Exam02.java
//   
//   => Windows 운영체제의 경우 컴파일 오류가 발생한다. 그 이유는 무엇인가?
//   => macOS, Linux, Unix에서는 소스 파일 문자집합 인코딩 오류가 발생하지 않는다. 그 이유는 무엇인가?
//
//3) 문자집합 인코딩 문제가 발생하지 않도록 컴파일하라.
//       
package com.eomcs.basic.ch01.test;

public class Exam2 {
  public static void main(String[] args) {
    System.out.println("안녕하세요!");
  }
}
