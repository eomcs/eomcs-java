// non-static nested class : inner class 정의하기
package com.eomcs.oop.ex11.a;

public class Exam0211 {

  // 스태틱 멤버
  static int sValue;
  static void sm() {};
  static class A {}; // static nested class

  // 인스턴스 멤버
  int iValue;
  void im() {}
  class B {} // non-static nested class => inner class
}
