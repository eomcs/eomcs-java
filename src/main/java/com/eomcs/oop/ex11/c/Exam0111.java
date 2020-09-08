// inner class : 선언할 수 있는 멤버
package com.eomcs.oop.ex11.c;

class A2 {
  class X {
    // inner class 는 스태틱 멤버를 가질 수 없다.
    // 스태틱 멤버는 오직 top level class 난 static nested class 만이 가질 수 있다.
    //static int v1; // 컴파일 오류!
    //static void m1() {} // 컴파일 오류!
    //static {} // 컴파일 오류!

    int v2;
    void m2() {}
    {}
  }
}

public class Exam0111 {
  public static void main(String[] args) {

    //    A2 outer = new A2();
    //    A2.X obj = outer.new X();

    // 위의 두 줄을 다음 한 줄로 표현할 수 있다.
    A2.X obj = new A2().new X();
  }
}