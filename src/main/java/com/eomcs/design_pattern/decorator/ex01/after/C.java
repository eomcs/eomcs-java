package com.eomcs.design_pattern.decorator.ex01.after;

public class C extends PlugIn {

  // C 클래스가 기능을 덧붙여야 하는 대상이 되는 객체를 생성자로 받는다.
  public C(Printer origin) {
    super(origin);
  }

  @Override
  public void f1(String name) {

    // 생성자에서 받은 객체에 대해 먼저 기능을 실행한다.
    origin.f1(name);

    // 새 기능을 덧붙인다.
    // => 꼬리말을 출력한다.
    System.out.println("********************[끝]");
  }
}
