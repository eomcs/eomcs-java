// 오버라이딩(overriding) - 상위 클래스의 레퍼런스로 하위 클래스의 오버라이딩 메서드 호출
package com.eomcs.oop.ex06.c;

public class Exam0430 {
  public static void main(String[] args) {
    X4 x4 = new X4();
    x4.m1();

    // 인스턴스 필드와 달리 메서드의 경우는
    // 상위 레퍼런스가 하위 인스턴스를 가리킬 경우
    // 호출하는 메서드는 그 레퍼런스가 가리키는 인스턴스의 클래스에서 찾는다.
    // 없으면 상위 클래스로 따라 올라 가면서 찾는다.
    //
    ((X3)x4).m1();
    ((X2)x4).m1();
    ((X)x4).m1();

    X3 x3 = x4;
    X2 x2 = x4;
    X x = x4;

    x3.m1();
    x2.m1();
    x.m1();

  }
}








