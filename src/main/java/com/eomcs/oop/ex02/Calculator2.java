package com.eomcs.oop.ex02;

//# 메서드 분류 - 클래스 변수 사용
//
public class Calculator2 {
  // 이 클래스에 들어있는 메서드들의 작업 결과를 보관할 변수를 준비한다.
  // => 클래스 변수는 new 명령으로 생성하지 않는다.
  // => 클래스가 메모리에 로딩되면 자동으로 "Method Area" 영역에 생성된다. 
  static int result = 0;

  // 다음 메서드는 작업 결과를 클래스 변수에 보관한다.
  public static void plus(int value) {
    result += value; // result = result + value
  }
  public static void minus(int value) {
    result -= value; // result = result - value
  }
  public static void multiple(int value) {
    result *= value; // result = result * value
  }
  public static void divide(int value) {
    result /= value; // result = result / value
  }
}




