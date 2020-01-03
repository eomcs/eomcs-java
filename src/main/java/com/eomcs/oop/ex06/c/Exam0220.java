// 오버라이딩(overriding) - @Override
package com.eomcs.oop.ex06.c;

public class Exam0220 {
  public static void main(String[] args) {
    B3 obj = new B3();
    obj.m(100); // B3의 m(int) 호출

    // B3 클래스에서는 m() 메서드를 제대로 오버라이딩 하였다.
    // @Override ?
    // => 애노테이션 문법이다.
    // => 컴파일러나 JVM에게 전달하는 특별한 주석이다.
    // => 개발자도 자신의 애노테이션을 정의하고 사용할 수 있다.
    //    나중에 따로 배운다!

  }
}








