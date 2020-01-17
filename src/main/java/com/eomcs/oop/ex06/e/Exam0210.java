// final 사용법: 상수 필드 만들기
package com.eomcs.oop.ex06.e;

public final class Exam0210 {
  // 필드에 final 을 붙이면 상수 필드가 된다.
  // 생성자에서 초기화시켜야 한다.
  //
  final int a;

  public Exam0210() {
    a = 100;
  }

  public void m1() {
    // 상수 필드는 값을 변경할 수 없다.
    // a = 200; // 컴파일 오류!
  }
}
