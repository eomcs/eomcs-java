// lambda : 사용법
package com.eomcs.oop.ex11.f;

public class Exam0110 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  interface A {
    void print();
  }

  public static void main(final String[] args) {
    // 람다 문법으로 인터페이스 구현하기
    // 문법:
    // => 인터페이스명 레퍼런스 = (파라미터, ...) -> {...}
    //
    final A obj = () -> System.out.println("Hello!");

    obj.print();
  }
}
