package com.eomcs.design_pattern.decorator.ex01.before;

public class C extends B {
  @Override
  public void f1(String name) {
    super.f1(name); // 기존 기능은 그대로 실행

    // 새 기능을 덧붙인다.
    // => 꼬리말을 출력한다.
    System.out.println("********************[끝]");
  }
}
