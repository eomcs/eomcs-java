// non-static nested class = inner class
package com.eomcs.oop.ex11.a;

public class Exam0211 {

  class A {
    int iValue;

    /*
     * 컴파일러가 추가하는 필드 및 생성자;
     *
     * Exam0211 outer;
     *
     * public A(Exam0211 obj) { outer = obj; }
     */

    void m1() {
      // 바깥 클래스의 인스턴스 멤버 이름이 inner class의 인스턴스 멤버 이름과 같을 경우
      // inner class 안에서는 당연히 inner class 멤버를 가리킨다.
      //
      iValue = 100; // A 객체의 iValue를 가리킨다.
      iValue = 100;// A 객체의 iValue를 가리킨다.

      // 바깥 클래스의 인스턴스를 가리키고 싶다면,
      // 다음의 문법을 사용하여 바깥 클래스의 이름을 명시하라.
      //
      Exam0211.this.iValue = 100; // 바깥 클래스의 인스턴스 멤버이다.

      // inner class 에서 바깥 클래스의 인스턴스 멤버를 사용할 수 있는 이유는?
      // => inner class의 객체를 생성하려면, 반드시 바깥 클래스의 인스턴스가 있어야 한다.
      // => 따라서 inner class의 인스턴스 메서드를 호출하는 시점에는
      // 당연히 바깥 클래스의 인스턴스를 알고 있는 상태이다.
    }
  }

  public static void main(final String[] args) {}

  int iValue;

  void im() {}
}
