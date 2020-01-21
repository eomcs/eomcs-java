// 메서드 레퍼런스(method reference) - 스태틱 메서드 레퍼런스
package com.eomcs.oop.ex12;

public class Exam0510 {

  static interface Calculator {
    int compute(int a, int b);
  }

  static class MyCalculator {
    public static int plus(int a, int b) {
      return a + b;
    }

    public static int minus(int a, int b) {
      return a - b;
    }

    public static int multiple(int a, int b) {
      return a * b;
    }

    public static int divide(int a, int b) {
      return a / b;
    }
  }

  public static void main(String[] args) {
    // '람다'는 새로 메서드를 구현해야 하지만,
    // '메서드 레퍼런스'는 기존 클래스의 메서드를 재활용 할 수 있다.
    //

    // 스태틱 메서드 레퍼런스
    // 문법:
    // => 클래스명::메서드명
    //
    Calculator c1 = MyCalculator::plus;
    System.out.println(c1.compute(200, 100));

    Calculator c2 = MyCalculator::minus;
    System.out.println(c2.compute(200, 100));
  }

}


