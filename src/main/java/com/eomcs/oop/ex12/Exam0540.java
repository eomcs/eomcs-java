// 메서드 레퍼런스 - 스태틱 메서드 레퍼런스
package com.eomcs.oop.ex12;


public class Exam0540 {

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

  static interface Calculator1 {
    int compute(byte a, byte b);
  }

  static interface Calculator2 {
    int compute(short a, short b);
  }

  static interface Calculator3 {
    int compute(long a, long b);
  }

  static interface Calculator4 {
    int compute(float a, float b);
  }

  static interface Calculator5 {
    int compute(Object a, Object b);
  }

  static interface Calculator6 {
    int compute(String a, String b);
  }

  static interface Calculator7 {
    int compute(Integer a, Integer b);
  }

  static interface Calculator8 {
    int compute(int a);
  }

  static interface Calculator9 {
    int compute(int a, int b, int c);
  }

  public static void main(String[] args) {

    // 파라미터 타입: byte ===> int
    Calculator1 c1 = MyCalculator::plus; // OK!

    // 파라미터 타입: short ===> int
    Calculator2 c2 = MyCalculator::plus; // OK!

    // 파라미터 타입: long ===> int
    // Calculator3 c3 = MyCalculator::plus; // 컴파일 오류!

    // 파라미터 타입: float ===> int
    // Calculator4 c4 = MyCalculator::plus; // 컴파일 오류!

    // 파라미터 타입: Object ===> int
    // Calculator5 c5 = MyCalculator::plus; // 컴파일 오류!

    // 파라미터 타입: String ===> int
    // Calculator6 c6 = MyCalculator::plus; // 컴파일 오류!

    // 파라미터 타입: Integer ===> int
    Calculator7 c7 = MyCalculator::plus; // OK

    // 파라미터 타입: int, int ===> int
    // Calculator8 c8 = MyCalculator::plus; // 컴파일 오류!

    // 파라미터 타입: int, int ===> int
    // Calculator9 c9 = MyCalculator::plus; // 컴파일 오류!

    // 메서드 레퍼런스를 지정할 때 파라미터 타입 규칙:
    // => 인터페이스 규칙에 따라 받은 값을
    // 실제 메서드에 그대로 전달할 수 있다면 가능하다.
  }
}


