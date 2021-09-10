package com.eomcs.design_pattern.decorator.ex01.after;

public class Test03 {

  public static void main(String[] args) {
    // 1) 실제 작업을 할 객체를 준비한다.
    A a = new A();

    // 2) A 객체에 기능을 덧붙인다.
    B b = new B(a);

    // 3) B 객체에 기능을 덧붙인다.
    C c = new C(b);

    c.f1("홍길동");

  }

}
