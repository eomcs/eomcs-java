// 인스턴스 메서드에 정의된 local class : 변수를 찾는 순서
package com.eomcs.oop.ex11.d;

class D2 {
  int v1 = 1;

  void m1() {
    //int v1 = 10;

    class X {
      //int v1 = 100;

      void f() {
        //int v1 = 1000;
        System.out.printf("v1 = %d\n", v1);
        // this 를 생략하면 다음 순서로 변수를 찾는다.
        // 1) 로컬 변수
        // 2) 인스턴스 변수
        // 3) 메서드의 선언된 로컬 변수
        // 4) 바깥 클래스의 인스턴스 변수 또는 스태틱 변수
      }
    }

    X obj = new X();
    obj.f();
  }
}

public class Exam0320 {

  public static void main(String[] args) {
    D2 obj = new D2();
    obj.m1();
  }

}
