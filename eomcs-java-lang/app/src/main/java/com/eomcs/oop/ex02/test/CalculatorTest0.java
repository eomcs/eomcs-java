package com.eomcs.oop.ex02.test;

// # 관련된 기능(메서드)을 묶어 분류하기
//1) 분류 전
//2) 메서드를 클래스로 묶어 분류하기
//3) 클래스 변수 도입
//4) 인스턴스 변수 도입
//5) 인스턴스 메서드 활용
//6) 패키지 멤버 클래스로 분리
//7) 클래스를 역할에 따라 패키지로 분류하기
//
public class CalculatorTest0 {

  public static void main(String[] args) {

    int result = 0;

    result = plus(2, 3);
    result = minus(result, 1);
    result = multiple(result, 7);
    result = divide(result, 3);

    System.out.printf("result = %d\n", result);
  }

  static int plus(int a, int b) {
    return a + b;
  }

  static int minus(int a, int b) {
    return a - b;
  }

  static int multiple(int a, int b) {
    return a * b;
  }

  static int divide(int a, int b) {
    return a / b;
  }
}


