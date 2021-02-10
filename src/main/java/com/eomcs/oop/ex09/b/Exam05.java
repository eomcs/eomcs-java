// 인터페이스 - static 메서드
package com.eomcs.oop.ex09.b;

interface MyInterface5 {
  // 인터페이스도 클래스처럼 static 메서드를 정의할 수 있다.
  static void m1() {
    System.out.println("MyInterface5.m1()");
  }
}

class Parent {
  static void m2() {
    System.out.println("Parent.m2()");
  }
}

class MyInterface5Impl extends Parent implements MyInterface5 {

}

public class Exam05 {
  public static void main(String[] args) {
    // 인터페이스의 스태틱 메서드 호출하기
    MyInterface5.m1();

    // 클래스의 스태틱 메서드 호출하기
    Parent.m2();

    MyInterface5Impl obj = new MyInterface5Impl();

    // 인스턴스 레퍼런스를 통해서는
    // 인터페이스의 스태틱 메서드를 호출할 수 없다.
    //
    //    obj.m1(); // 컴파일 오류!

    // 인스턴스 레퍼런스를 통해 클래스의 스태틱 메서드는 호출할 수 있다.
    // => 그러나 스태틱 메서드는 가능한 그 메서드가 선언된 클래스 이름으로 호출하라!
    obj.m2(); 
  }

}
