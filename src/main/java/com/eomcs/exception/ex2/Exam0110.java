// 예외 처리 문법을 적용한 후 - 메서드가 던지는 예외 정보를 받는다.
package com.eomcs.exception.ex2;

public class Exam0110 {
  public static void main(String[] args) {

    try { 
      // 예외를 던질 수도 있는 메서드를 호출할 때는 try 블록 안에서 호출한다. 
      int result = Calculator3.compute("#", 100, 200);
      System.out.println(result);
    } catch (RuntimeException e) {
      // try 블록 안에서 메서드를 호출하다가 예외가 발생하면 
      // catch 블록에서 파라미터로 받는다.
      System.out.println(e.getMessage());
    }

    try {
      int result = Calculator3.compute("+", -2078654356, 866533144);
      System.out.println(result);
    } catch (RuntimeException e) {
      // try 블록을 실행하는 중에 예외가 발생하지 않으면 
      // catch 블록은 실행하지 않는다.
      System.out.println(e.getMessage());
    }

    // 리턴 값을 검사하는 방식보다 더 정확하게 예외를 처리할 수 있다.

  }
}







