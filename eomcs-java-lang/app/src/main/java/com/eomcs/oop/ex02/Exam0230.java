package com.eomcs.oop.ex02;

// # 관련된 기능(메서드)을 묶어 분류하기
// 1) 분류 전
// 2) 메서드를 클래스로 묶어 분류하기
// 3) 클래스 변수 도입
//
public class Exam0230 {

  static class Calculator {
    // ## 클래스 변수 사용
    // - 메서드들의 작업 결과를 보관할 때 사용할 변수이다.
    // - 변수 선언에 static을 붙이다.
    // - "스태틱 변수"라고도 부른다.
    // - 클래스 변수는 new 명령으로 생성하지 않는다.
    // - 클래스가 메모리에 로딩될 때 자동으로 "Method Area" 영역에 생성된다.
    static int result = 0;

    static void plus(int value) {
      // 메서드 작업 결과는 클래스 변수에 보관한다.
      result += value; // result = result + value
    }

    static void minus(int value) {
      result -= value; // result = result - value
    }

    static void multiple(int value) {
      result *= value; // result = result * value
    }

    static void divide(int value) {
      result /= value; // result = result / value
    }

    // 인스턴스를 사용하지 않는 메서드라면 그냥 클래스 메서드로 두어라.
    static int abs(int a) {
      return a >= 0 ? a : a * -1;
    }
  }

  public static void main(String[] args) {

    // 다음 식을 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
    // 2 + 3 - 1 * 7 / 3 = ?

    // 계산 결과를 보관할 변수는 더이상 필요가 없다.
    // Calculator 내부에서 계산 결과를 관리한다.
    // int result = 0;

    Calculator.plus(2);
    Calculator.plus(3);
    Calculator.minus(1);
    Calculator.multiple(7);
    Calculator.divide(3);

    System.out.printf("result = %d\n", Calculator.result);
  }
}

