// lambda : 클래스와 람다
package com.eomcs.oop.ex11.f;

public class Exam0140 {
  static abstract class A {
    public abstract void print();
  }

  public static void main(final String[] args) {
    // 추상 메서드가 한 개만 있더라도
    // 인터페이스가 아닌 클래스라면
    // 람다를 사용할 수 없다.
    // 인터페이스만 가능하다.
    final A obj = () -> System.out.println("Hello2!"); // 컴파일 오류!
    obj.print();
  }
}
