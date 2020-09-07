// anonymous class - 익명 클래스가 놓이는 장소: 파라미터
package com.eomcs.oop.ex11.x;

public class Exam0540 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  interface A {
    void print();
  }

  void m1(A obj) {
    obj.print();
  }
  
  public static void main(String[] args) {
    Exam0540 r = new Exam0540();
    r.m1(new A() {
      @Override
      public void print() {
        System.out.println("안녕!");
      }
    });
  }
}
