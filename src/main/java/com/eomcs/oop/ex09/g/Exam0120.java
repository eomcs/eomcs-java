// 인터페이스 구현 - default 메서드 구현
package com.eomcs.oop.ex09.g;

public class Exam0120 implements A {
  @Override
  public void m2() {
    // default 메서드는 구현해도 되고 안해도 된다.
  }

  @Override
  public void m3() {
    // 추상 메서드는 반드시 구현해야 한다.
  }
}
