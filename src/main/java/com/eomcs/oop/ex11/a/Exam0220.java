// non-static nested class = inner class
package com.eomcs.oop.ex11.a;

public class Exam0220 {

  // 스태틱 멤버
  static int sValue;
  static void sm() {};
  
  // 인스턴스 멤버
  int iValue;
  void im() {}
  
  // 인스턴스 멤버
  class A {
    void m1() {
    }
  }
  
  
  static void m1() {
    // 스태틱 멤버는 다른 스태틱 멤버를 사용할 수 있다.
    sValue = 100; 
    sm();
    
    // 스태틱 멤버는 this 라는 내장변수가 없기 때문에 인스턴스 멤버를 사용할 수 없다.
    //this.iValue = 100; // 컴파일 오류!
    //this.im(); // 컴파일 오류!
    A obj; // 단, inner class의 레퍼런스를 만드는 것은 허용한다.
    //obj = new A(); // 컴파일 오류! 인스턴스 없이 inner class의 생성자를 호출할 수 없다.
  }
  
  void m2() {
    // 인스턴스 멤버는 스태틱 멤버를 사용할 수 있다.
    sValue = 100; 
    sm();
    
    // 인스턴스 멤버는 this 라는 내장변수가 있기 때문에 다른 인스턴스 멤버를 사용할 수 있다.
    this.iValue = 100; // OK!
    this.im(); // OK!
    iValue = 100; // OK! this 생략 가능
    im(); // OK! this 생략 가능
    //this.A obj; // 컴파일 오류! inner class의 변수를 선언할 때는 this를 붙이지 않는다.
    
    A obj; // OK!
    obj = this.new A(); // OK! inner class의 객체를 생성할 때 바깥 클래스의 인스턴스가 있어야 한다.
    obj = new A(); // 다른 인스턴스 멤버를 사용하는 것처럼 this를 생략할 수 있다.
  }

  public static void main(String[] args) {
  }
}
