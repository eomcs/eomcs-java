// 예외 처리 문법을 적용한 후 - 메서드가 던지는 예외 정보를 받는다.
package com.eomcs.exception.ex2;

public class Exam0110 {
  public static void main(String[] args) {
    String op = "#";
    int a = 100;
    int b = 200;

    try {
      // 예외를 던질 수도 있는 메서드를 호출할 때는 try 블록 안에서 호출한다.
      int result = Calculator3.compute(op, a, b);
      System.out.println(result);

    } catch (RuntimeException e) {
      // try 블록 안에서 메서드를 호출하다가 예외가 발생하면
      // catch 블록에서 파라미터로 받는다.
      System.out.println(e.getMessage());
    }
  }
}


