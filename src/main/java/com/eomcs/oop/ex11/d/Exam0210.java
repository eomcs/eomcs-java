// local class : 변수 접근
package com.eomcs.oop.ex11.d;

class C {
  int v1 = 1;

  void m1() {
    int v2 = 2;

    class X {
      int v3 = 3;

      void f() {
        int v4 = 4;
        System.out.printf("v4 = %d\n", v4);
        System.out.printf("v3 = %d\n", this.v3);
        System.out.printf("v2 = %d\n", v2);
        System.out.printf("v1 = %d\n", C.this.v1);
      }
    }

    X obj = new X();
    obj.f();
  }
}

public class Exam0210 {

  public static void main(String[] args) {
    C c = new C();
    c.m1();
  }

}
