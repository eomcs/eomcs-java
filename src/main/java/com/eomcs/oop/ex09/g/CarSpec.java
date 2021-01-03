// 인터페이스는 사용규칙을 정의한 것이다.
package com.eomcs.oop.ex09.g;

public interface CarSpec {
  // 그래서 인터페이스에 선언하는 모든 메서드는 추상 메서드이어야 한다.
  void run(); // 자동차를 몰 때 호출할 메서드
  void on(); // 자동차 시동을 켤 때 호출할 메서드
  void off(); // 자동차 시동을 끌 때 호출할 메서드
}
