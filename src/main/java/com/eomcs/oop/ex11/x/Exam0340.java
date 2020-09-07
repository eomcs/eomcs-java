// local class - 인스턴스 멤버에 선언된 로컬 클래스
package com.eomcs.oop.ex11.x;

public class Exam0340 {

  public static void main(final String[] args) {
    // 인스턴스 멤버는 반드시 인스턴스를 생성한 후에 사용할 수 있다.
    final Exam0340 obj = new Exam0340();
    obj.im();
    // im()을 호출할 때 Exam0340 객체를 넘겨준다.
    // 그러면 im() 에서는 this라는 내장 변수에 보관할 것이다.
  }

  int iValue;

  // 인스턴스 메서드의 로컬 클래스 사용법
  void im() {
    // 이 인스턴스 메서드는 Exam0340 객체 주소를 받는 내장 변수 this가 있다.

    class A {

      // 인스턴스 메서드에서 로컬 클래스를 정의하는 경우,
      // 바깥 클래스의 인스턴스 주소를 알수 있기 때문에
      // 컴파일러는 바깥 클래스의 인스턴스 주소를 담을 필드를 추가하고,
      // 생성자도 추가한다.
      /*
       * Exam0340 outer;
       *
       * public A(Exam0340 obj) { outer = obj; }
       */

      void m1() {
        // 로컬 클래스가 인스턴스 멤버에 정의된 경우
        // 바깥 클래스의 인스턴스를 사용할 수 있다.
        // 다음과 같은 문법으로 바깥 클래스의 인스턴스 멤버를 사용한다.
        // => [바깥클래스명].this.[인스턴스멤버]
        //
        // Exam0340.this.iValue = 100; // OK!

        // 로컬 클래스에 같은 이름을 가진 필드가 없거나
        // 현재 메서드에 같은 이름을 가진 로컬 변수가 없을 경우
        // 바깥 클래스의 인스턴스를 가리키는 코드를 생략할 수 있다.
        iValue = 100; // OK!
      }
    }
    final A obj = new A();
    // 컴파일러는 위 코드를 바깥 클래스의 인스턴스 주소를 받는
    // 생성자 호출 코드로 바꾼다.
    // => final A obj = new A(this);
    //
    obj.m1();
  }
}
