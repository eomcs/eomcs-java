// lambda : 사용법 II
package com.eomcs.oop.ex11.f;

public class Exam0120 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  interface A {
    void print();
    void print2();
  }

  public static void main(final String[] args) {
    // 인터페이스에 추상 메서드가 한 개를 초과하는 경우
    // 람다 문법을 사용할 수 없다.
    final A obj = () -> System.out.println("Hello!");

    obj.print();
  }
}
