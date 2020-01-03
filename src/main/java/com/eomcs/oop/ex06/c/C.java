package com.eomcs.oop.ex06.c;

public class C {
  //private 접근 범위: 
  //=> 현재 클래스
  private void m1() {}

  //(default) 접근 범위:
  //=> 현재 클래스 + 같은 패키지 소속 클래스
  void m2() {}
  
  //protected 접근 범위:
  //=> 현재 클래스 + 같은 패키지 소속 클래스 + 서브 클래스
  protected void m3() {}
  
  //public 접근 범위:
  //=> 모두
  public void m4() {}
}
