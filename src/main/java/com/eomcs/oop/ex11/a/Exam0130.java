// static nested class
package com.eomcs.oop.ex11.a;

class Exam0130_X {
  static int sValue;
  static void m1() {};
  
  static class A {
    void m1() {
    }
  }  
}

public class Exam0130 {
  public static void main(String[] args) {
    // 다른 클래스의 스태틱 멤버는 클래스 이름을 이용하여 사용할 수 있듯이
    Exam0130_X.sValue = 100;
    Exam0130_X.m1();
    
    // static nested class 또한 같은 방식으로 사용할 수 있다.
    Exam0130_X.A obj;
    obj = new Exam0130_X.A();
    
  }
}
