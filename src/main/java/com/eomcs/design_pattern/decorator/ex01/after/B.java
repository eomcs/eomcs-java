package com.eomcs.design_pattern.decorator.ex01.after;

public class B extends PlugIn {

  // 기존 기능을 수행하는 객체를 보관해야 한다.
  public B(Printer origin) {
    super(origin);
  }

  // 기존 기능에 새 기능을 덧붙이기 위하여 오버라이딩을 이용한다.
  @Override
  public void f1(String name) {
    // 새 기능 추가
    // => 머리말을 출력하는 기능을 덧붙인다.
    System.out.println("[머리말]---------------");

    // 기존 기능을 수행하기 위해서는 상속 받은 메서드를 호출해서는 안된다.
    // 생성자에서 파라미터로 받은 객체를 실행해야 한다.
    // 왜? 바로 그 객체에 기능을 덧붙이는 것이기 때문이다.
    origin.f1(name);
  }
}
