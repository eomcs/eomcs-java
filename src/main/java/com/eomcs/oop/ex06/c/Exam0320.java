// 오버라이딩(overriding) - 오버라이딩할 때 접근 범위 조정하기
package com.eomcs.oop.ex06.c;

public class Exam0320 {

  static class C {
    private void m1() {}
    void m2() {}
    protected void m3() {}
    public void m4() {}
  }

  static class C3 extends C {

    // 오버라이딩 메서드의 접근 범위를 확대하는 것은 괜찮다.
    // => private 은 오버라이딩 자체가 불가능하기 때문에 접근 범위를 확대할 수 없다.
    //    @Override public m() {}

    @Override protected void m2() {} // default 보다 접근 범위를 확대했기 때문에 OK!
    @Override public void m3() {} // protected 보다 접근 범위를 확대!

  }

  public static void main(String[] args) {
    // C, C2, C3, C4 클래스의 주석을 확인하라!
  }
}








