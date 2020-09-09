// lambda : 사용법 III
package com.eomcs.oop.ex11.f;

public class Exam0130 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  interface A {
    void print();
    default void print2() {} // 이것은 추상 메서드가 아니다.
  }

  public static void main(final String[] args) {
    // 인터페이스에 메서드가 여러 개 있더라도
    // 추상 메서드가 한 개만 있으면 된다.
    final A obj = () -> System.out.println("Hello!");

    obj.print();
  }
}
