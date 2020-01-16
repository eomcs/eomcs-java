// static nested class
package com.eomcs.oop.ex11.a;

public class Exam0120 {

  static class A {
    void m1() {
    }
  }
  
  static void m1() {
    // static nested class는 스태틱 멤버이기 때문에 
    // 다른 스태틱 멤버가 사용할 수 있다.
    A obj;
    obj = new A(); // OK!
  }
  
  void m2() {
    // 인스턴스 멤버는 스태틱 멤버를 사용할 수 있기 때문에 
    // 당연히 static nested class를 사용할 수 있다.
    A obj;
    obj = new A();
  }

  public static void main(String[] args) {
  }
}
