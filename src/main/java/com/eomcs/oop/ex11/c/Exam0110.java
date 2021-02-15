// inner class : 클래스 정의와 인스턴스 생성
package com.eomcs.oop.ex11.c;

class A {
  class X {

  }
}

public class Exam0110 {

  public static void main(String[] args) {
    // 레퍼런스 선언
    A.X obj;

    // 인스턴스 생성
    //    obj = new A.X(); // 컴파일 오류!

    //1) 바깥 클래스의 인스턴스 준비
    A  outer = null;//new A();

    //2) 바깥 클래스의 인스턴스 주소를 사용하여 inner class의 인스턴스 생성
    obj = outer.new X();

  }

}
