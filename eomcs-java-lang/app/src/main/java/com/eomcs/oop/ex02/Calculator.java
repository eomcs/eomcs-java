package com.eomcs.oop.ex02;

// 여러 곳에서 사용할 클래스라면 패키지 멤버로 만드는 것이 유지보수에 좋다.
// => 패키지 멤버 클래스
class Calculator {

  int result = 0;

  void plus(int value) {
    this.result += value;
  }

  void minus(int value) {
    this.result -= value;
  }

  void multiple(int value) {
    this.result *= value;
  }

  void divide(int value) {
    this.result /= value;
  }

  // 인스턴스를 사용하지 않는 메서드라면 그냥 클래스 메서드로 두어라.
  static int abs(int value) {
    return value >= 0 ? value : value * -1;
  }
}
