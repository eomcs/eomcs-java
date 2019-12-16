package com.eomcs.oop.ex02;

//# 메서드 분류 - 인스턴스 메서드 사용
//
public class Calculator4 {
  int result = 0;

  // 인스턴스 변수를 다룰 때는 인스턴스 메서드를 사용하는 것이 편하다!
  // 왜?
  // => 파라미터로 따로 인스턴스의 주소를 받을 필요가 없기 때문이다.
  public void plus(int value) {
    // 인스턴스 메서드는 인스턴스의 주소를 this라는 내장 변수에 자동으로 받는다.
    this.result += value;
  }
  public void minus(int value) {
    this.result -= value;
  }
  public void multiple(int value) {
    this.result *= value;
  }
  public void divide(int value) {
    this.result /= value;
  }
}




