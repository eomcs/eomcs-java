// 인스턴스 메서드에 정의된 local class : 변수 접근
package com.eomcs.oop.ex11.d;

class D {
  int v1 = 1;

  void m1() {
    // 인스턴스 메서드는 this에 C 인스턴스 주소를 저장하고 있다.
    int v2 = 2;

    class X {
      int v3 = 3;

      // 로컬 클래스는 바깥 클래스의 인스턴스 주소를 저장할 필드가 있을 뿐만 아니라
      // 인스턴스 메서드 안에 선언된 로컬 변수의 값을 저장할 필드도 있다.
      // .class 파일의 코드:
      //      class com.eomcs.oop.ex11.d.D$1X {
      //
      //        int v3; <== 로컬 클래스의 인스턴스 필드
      //        final synthetic com.eomcs.oop.ex11.d.D this$0;  <== 바깥 클래스의 인스턴스 주소 저장
      //        private final synthetic int val$v2; <== 바깥 메서드의 로컬 변수 저장
      //

      void f() {
        int v4 = 4;
        System.out.printf("v4 = %d\n", v4);
        System.out.printf("v3 = %d\n", this.v3);
        System.out.printf("v2 = %d\n", v2);
        System.out.printf("v1 = %d\n", D.this.v1);
      }
    }

    X obj = new X();
    obj.f();
  }
}

public class Exam0310 {

  public static void main(String[] args) {
    D obj = new D();
    obj.m1();
  }

}
