// local class - 로컬 클래스와 로컬 변수
package com.eomcs.oop.ex11.a;

public class Exam0350 {

  public static void main(final String[] args) {
    final Exam0350 obj = new Exam0350();
    obj.m1();
  }

  void m1() {
    final int a = 100; // 로컬 변수

    class A {
      void m1() {
        // 로컬 클래스는 자신이 선언된 그 메서드의 로컬 변수를 사용할 수 있다.
        // => 단 값을 변경하지 못하는 final 변수를 사용할 수 있다.
        System.out.println(a);
      }
    }

    final A obj = new A();
    obj.m1();
  }
}
