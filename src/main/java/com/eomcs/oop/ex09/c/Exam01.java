// 서브 인터페이스 구현 - 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
package com.eomcs.oop.ex09.c;

public class Exam01 implements B {

  // 인터페이스에 선언된 메서드 구현
  @Override
  public void m2() {} // B 인터페이스 뿐만아니라,
  @Override
  public void m1() {} // B의 수퍼인터페이스의 메서드까지 구현해야 한다.

  // 클래스에서 추가한 메서드
  public void x1() {} 
  public void x2() {}

  public static void main(String[] args) {

    Exam01 obj = new Exam01();

    // 클래스 타입 레퍼런스는 해당 클래스에 정의된 메서드를 호출할 수 있다.
    obj.m1(); // A.m1()
    obj.m2(); // B.m2()
    obj.x1(); // Exam01.x1() -> Exam01 클래스에 정의된 x1() 메서드를 의미한다.
    obj.x2(); // Exam01.x2()

    // 인터페이스 레퍼런스는 그 인터페이스를 구현한 클래스의 인스턴스를 가리킬 수 있다.
    // 그리고 해당 인터페이스에 선언된 메서드만 호출할 수 있다.
    // 물론 수퍼 인터페이스의 메서드도 포함한다.
    B obj2 = obj;
    obj2.m2(); // B.m2()
    obj2.m1(); // A.m1() <== B가 상속 받은 인터페이스의 메서드
    //obj2.x1(); // 컴파일 오류!
    //obj2.x2(); // 컴파일 오류!

    A obj3 = obj;
    obj3.m1(); // A.m1()
    //obj3.m2(); // 컴파일 오류!
    //obj3.x1(); // 컴파일 오류!
    //obj3.x2(); // 컴파일 오류!

  }
}







