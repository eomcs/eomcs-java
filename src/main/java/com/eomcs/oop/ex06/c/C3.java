package com.eomcs.oop.ex06.c;

public class C3 extends C {

  // 오버라이딩 메서드의 접근 범위를 확대하는 것은 괜찮다.
  @Override protected void m2() {} // default 보다 접근 범위를 확대했기 때문에 OK!
  @Override public void m3() {} // protected 보다 접근 범위를 확대!

}
// 멤버의 접근 범위
// private      : 같은 클래스
// (default)    : 같은 클래스 + 같은 패키지
// protected    : 같은 클래스 + 같은 패키지 + 서브 클래스
// public       : 모두
//







