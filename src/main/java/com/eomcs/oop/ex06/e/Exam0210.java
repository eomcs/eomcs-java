// final 사용법: 상수 필드 만들기
package com.eomcs.oop.ex06.e;

public final class Exam0210 {
  // 필드에 final 을 붙이면 상수 필드가 된다.
  // 필드를 선언할 때 즉시 값을 저장해야 한다.
  //
  final int a = 100;

  public void m1() {
    // 상수 필드는 값을 변경할 수 없다.
    a = 200; // 컴파일 오류!
  }
}
