// 예외 처리 - 개념
// => 메서드를 실행하는 중에 예외가 발생했을 때 호출자에게 알려주는 문법
// => 메서드를 호출하는 중에 예외를 받았을 때 처리하는 문법
// => 예외처리 문법의 의미
// 1) 메서드 실행 중에 예외 상황을 만났을 때 리턴 값으로 알려주는 방식의 한계를 극복하기 위해
// 2) 예외가 발생하더라도 시스템을 멈추지 않고 적절한 조치를 취한 후 계속 실행하기 위해
package com.eomcs.exception.ex3;

public class Exam0111 {

  static void m() {
    // 예외를 호출자에게 알려주는 문법
    // => throw [Throwable 객체];
    // throw new String("예외가 발생했습니다!"); // 컴파일 오류!
    throw new RuntimeException("예외가 발생했습니다!");
  }

  public static void main(String[] args) {

    // 예외를 받지 않으면?
    m();
    // 즉시 현재 메서드의 실행을 멈추고 호출자에게 예외 처리를 위임한다.
    // 만약에 그 호출자가 JVM이라면 프로그램 실행을 종료한다.

    System.out.println("시스템을 종료합니다.");

  }

}
