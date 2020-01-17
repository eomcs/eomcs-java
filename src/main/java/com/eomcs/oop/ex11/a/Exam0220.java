// non-static nested class = inner class
package com.eomcs.oop.ex11.a;

public class Exam0220 {

  // 인스턴스 멤버
  class A {
    /*
     * 컴파일러가 추가하는 필드 및 생성자;
     *
     * Exam0220 outer;
     *
     * public A(Exam0220 obj) { outer = obj; }
     */

    void m1() {}
  }

  // 스태틱 멤버
  static int sValue;

  static void m1() {
    // 스태틱 멤버는 다른 스태틱 멤버를 사용할 수 있다.
    sValue = 100;
    sm();
  }

  public static void main(final String[] args) {}

  static void sm() {}


  // 인스턴스 멤버
  int iValue;

  void im() {}

  void m2() {
    // 인스턴스 멤버는 스태틱 멤버를 사용할 수 있다.
    sValue = 100;
    sm();

    // 인스턴스 멤버는 this 라는 내장변수가 있기 때문에 다른 인스턴스 멤버를 사용할 수 있다.
    iValue = 100; // OK!
    im(); // OK!
    iValue = 100; // OK! this 생략 가능
    im(); // OK! this 생략 가능
    // this.A obj; // 컴파일 오류! inner class의 변수를 선언할 때는 this를 붙이지 않는다.

    this.new A();
    // 컴파일러는 inner class 를 생성하는 위 코드를 다음과 같이 바꾼다.
    // => A 클래스의 기본 생성자가 호출되는 것이 아니다.
    // => 컴파일러가 바깥 클래스의 객체 주소를 받으려고 추가한
    // 생성자를 호출하는 코드로 바뀐다.
    //
    /*
     * new A(this);
     */

    new A(); // 이렇게 this를 생략할 수 있다.
    // 컴파일러는 위 코드를 다음과 같이 바꾼다.
    /*
     * new A(this);
     */
  }
}
