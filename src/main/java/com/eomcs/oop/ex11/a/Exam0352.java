// local class - 로컬 클래스와 로컬 변수
package com.eomcs.oop.ex11.a;

public class Exam0352 {

  public static void main(final String[] args) {
    final Exam0352 obj = new Exam0352();
    obj.m1();
  }

  void m1() {
    int a; // 로컬 변수
    a = 100;
    a = 200;
    class A {
      void m1() {
        // 로컬 변수의 값이 두 번 이상 바뀌는 경우,
        // 상수 값으로 취급할 수 없기 때문에
        // 로컬 클래스에서 메서드의 로컬 변수를 사용할 수 없다.
        System.out.println(a); // 컴파일 오류!

        // 결론!
        // => 로컬 클래스는 메서드의 final 변수를 사용할 수 있다.
        // => 값이 변경될 수 있는 일반 변수는 사용할 수 없다.
      }
    }

    final A obj = new A();
    obj.m1();
  }
}
