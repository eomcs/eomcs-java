// 인터페이스 - static 메서드
package com.eomcs.oop.ex09.b;

interface MyInterface5 {
  // 인터페이스도 클래스처럼 static 메서드를 정의할 수 있다.
  static void m1() {
    System.out.println("MyInterface5.m1()");
  }
}

class MyInterface5Impl implements MyInterface5 {

}

public class Exam05 {
  public static void main(String[] args) {
    // 인터페이스의 스태틱 메서드 호출하기
    MyInterface5.m1();

    // 인스턴스 레퍼런스를 통해서는
    // 인터페이스의 스태틱 메서드를 호출할 수 없다.
    //
    MyInterface5Impl obj = new MyInterface5Impl();
    //    obj.m1(); // 컴파일 오류!
  }

}
