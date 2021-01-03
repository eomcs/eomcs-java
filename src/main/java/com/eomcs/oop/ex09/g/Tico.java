// 인터페이스 직접 구현
package com.eomcs.oop.ex09.g;

// 사용하는 쪽에서 일관된 방식으로 메서드를 호출할 수 있도록
// 규칙에 따라 클래스를 작성할 때 인터페이스를 구현한다.
public class Tico implements CarSpec {

  // 인터페이스를 구현한다는 것은 인터페이스에 선언된 모든 메서드를 정의함을 의미한다.
  @Override
  public void run() {
    System.out.println("달린다!");
  }

  @Override
  public void on() {
    System.out.println("시동 켠다!");
  }

  @Override
  public void off() {
    System.out.println("시동 끈다.");
  }
}
