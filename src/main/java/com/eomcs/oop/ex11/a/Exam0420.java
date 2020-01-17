// anonymous class - 인터페이스 구현
package com.eomcs.oop.ex11.a;

public class Exam0420 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  interface A {
    void print();
  }

  public static void main(final String[] args) {
    // 익명 클래스로 인터페이스 구현하기
    // 문법:
    // => 인터페이스명 레퍼런스 = new 인터페이스명() {}
    //
    A obj = new A() {
      @Override
      public void print() {
        System.out.println("Hello!");
      }
    };

    obj.print();
  }
}
