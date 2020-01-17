// final 사용법: 상수 필드 만들기
package com.eomcs.oop.ex06.e;

public final class Exam0212 {
  // 인스턴스 초기화 블록에서 값을 초기화시켜도 된다.
  final int a;

  // 초기화 블록의 코드 또한 생성자에 복사되기 때문이다.
  {
    a = 200; // OK!
    // a = 300; // 컴파일 오류!
  }
}
