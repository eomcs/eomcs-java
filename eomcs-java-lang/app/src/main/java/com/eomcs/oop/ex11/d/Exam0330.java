// 로컬 클래스에서 메서드에 선언된 로컬 변수 접근하기
package com.eomcs.oop.ex11.d;

class D3 {

  void m1() {
    final int v1 = 1;
    int v2 = 2;
    int v3 = 3;
    v3 = 30;

    class X {
      void f() {
        // 로컬 클래스에서는 바깥 메서드의 로컬 변수를 사용할 수 있다.
        // 1) final 로 선언된 경우
        System.out.printf("v1 = %d\n", v1);

        // 2) final 로 선언된 것은 아니지만 값을 한 번만 할당한 경우.
        System.out.printf("v2 = %d\n", v2);

        // => 값을 여러 번 할당한 경우에는 접근할 수 없다.
        //        System.out.printf("v3 = %d\n", v3); // 컴파일 오류!

        // 결론!
        // - 상수 값이거나 상수에 준하는 경우(값을 한 번만 할당한 경우)
        //   로컬 클래스에서 메서드의 로컬 변수를 사용할 수 있다.
        // - 즉 로컬 클래스에서 바깥 메서드의 로컬 변수를 사용할 경우에는
        //   값을 조회하는 용도로 사용하는 것이다.
        //- 왜?
        //   로컬 클래스의 객체가 사용하는 로컬 변수는
        //   메서드 호출이 끝났을 때 제거되기 때문이다.
      }
    }

    X obj = new X();
    obj.f();
  }
}

public class Exam0330 {

  public static void main(String[] args) {
    D3 obj = new D3();
    obj.m1();
  }

}
