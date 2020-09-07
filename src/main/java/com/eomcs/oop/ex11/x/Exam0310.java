// local class
package com.eomcs.oop.ex11.x;

public class Exam0310 {

  void m1() {

    // 로컬 클래스
    class A {
      void m1() {}
    }

    // 로컬 클래스는 그 클래스가 정의되어 있는 메서드 안에서만 사용할 수 있다.
    final A obj = new A();
    obj.m1();
  }

  void m2() {
    // 다른 메서드에 있는 로컬 클래스를 사용할 수 없다.
    // => 이것은 다른 메서드에 있는 로컬 변수를 사용할 수 없는 것과 같다.
    //
    // final A obj = new A(); // 컴파일 오류!
  }
}
