package com.eomcs.oop.ex06.c;

public class C4 extends C {

  // 오버라이딩 할 때(메서드를 재정의 할 때)
  // => 원본 보다 접근 범위를 좁힐 수는 없다.
  @Override private void m2() {} // (default) ==> private
  @Override void m3() {} // protected ==> (default) 
  @Override protected void m4() {} // public ==> protected

}
// 멤버의 접근 범위
// private      : 같은 클래스
// (default)    : 같은 클래스 + 같은 패키지
// protected    : 같은 클래스 + 같은 패키지 + 서브 클래스
// public       : 모두
//
// 결론!
// 수퍼 클래스의 메서드를 자식 클래스가 재정의할 때 
// 접근 범위를 확대할 순 있지만, 좁힐 수는 없다!







