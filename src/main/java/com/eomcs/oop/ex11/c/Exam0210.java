// inner class : 다른 멤버에 접근하기
package com.eomcs.oop.ex11.c;

class B {
  // 클래스 멤버
  static int v1;
  static void m1() {}

  // 인스턴스 멤버
  int v2;
  void m2() {}

  class X {
    void test() {
      // 같은 클래스 멤버 접근 가능
      B.v1 = 100;
      B.m1();

      // 바깥 클래스 이름 생략 가능
      v1 = 200; 
      m2(); 

      // 인스턴스 멤버에 접근 가능
      // => inner class 의 가장 큰 특징은 바깥 클래스의 다른 멤버에 손쉽게 접근할 수 있다는 것이다.
      // => 이것이 가능한 이유는 바깥 클래스의 인스턴스 멤버이기 때문이다.
      v2 = 100; 
      m2();
    }
  }
}

public class Exam0210 {

  public static void main(String[] args) {
    B outer = new B();

    B.X obj = outer.new X();
    obj.test();

    System.out.println(B.v1);
    System.out.println(outer.v2);
  }

}
