package com.eomcs.oop.ex01;

//# 클래스 사용 - public 클래스와 기본 클래스
//
public class Exam0430 {
  public static void main(String[] args) {

    // 다른 패키지의 클래스 사용

    // => A 클래스는 public 이 아니다.
    // => 따라서 다른 패키지의 클래스는 A를 사용할 수 없다.
    //com.eomcs.oop.ex01.test.A v1; // 컴파일 오류!

    // => B 클래스는 public 이다.
    // => 그래서 다른 패키지에 소속된 클래스도 B를 사용할 수 있다.
    com.eomcs.oop.ex01.test.B v2;
  }
}
