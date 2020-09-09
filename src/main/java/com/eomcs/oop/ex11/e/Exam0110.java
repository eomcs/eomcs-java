// anonymous class : 로컬 클래스 vs 익명 클래스
package com.eomcs.oop.ex11.e;

public class Exam0110 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  interface A {
    void print();
  }

  public static void main(final String[] args) {
    // 로컬 클래스로 인터페이스 구현하기
    class My implements A {
      @Override
      public void print() {
        System.out.println("Hello!");
      }
    }

    final A obj = new My();
    obj.print();
  }
}
