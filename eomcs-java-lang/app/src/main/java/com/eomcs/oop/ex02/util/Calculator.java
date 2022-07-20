package com.eomcs.oop.ex02.util;

// 다른 패키지에서 이 클래스와 멤버(필드 및 메서드)를 접근할 수 있도록 public 으로 공개한다.
//
public class Calculator {

  public int result = 0;

  public void plus(int value) {
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

  // 인스턴스를 사용하지 않는 메서드라면 그냥 클래스 메서드로 두어라.
  public static int abs(int a) {
    return a >= 0 ? a : a * -1;
  }

}
