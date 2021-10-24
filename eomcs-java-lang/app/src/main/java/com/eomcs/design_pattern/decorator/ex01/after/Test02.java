package com.eomcs.design_pattern.decorator.ex01.after;

public class Test02 {

  public static void main(String[] args) {
    A a = new A();

    // A 객체의 기능에 머리말을 덧붙이기
    B b = new B(a);

    b.f1("홍길동");

  }

}
