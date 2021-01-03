// 인터페이스 상속과 구현
package com.eomcs.oop.ex09.c;


public class Exam0110 {

  interface ProtocolA {
    void rule1();
  }

  // 인터페이스도 다른 인터페이스를 상속 받을 수 있다.
  // - ProtocolB 는 ProtocolA의 입장에서 서브 인터페이스이다.
  // - ProtocolA 는 ProtocolB의 입장에서 수퍼 인터페이스이다.
  interface ProtocolB extends ProtocolA {
    void rule2();
  }

  // 인터페이스를 구현할 때는
  // 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
  class ProtocolImpl implements ProtocolB {
    // ProtocolA 규칙 준수!
    @Override
    public void rule1() {System.out.println("rule1()");}

    // ProtocolB 규칙 준수!
    @Override
    public void rule2() {System.out.println("rule2()");}
  }

  void test() {

    ProtocolImpl obj = new ProtocolImpl();

    // 1) 인터페이스 레퍼런스로 구현체의 주소 받기
    ProtocolB b = obj;

    // 2) 메서드 호출
    // - 해당 인터페이스의 규칙에 따라서만 호출할 수 있다.
    b.rule1(); // OK
    b.rule2(); // OK
    System.out.println("-------------------------------");

    // 3) 수퍼 인터페이스 레퍼런스
    // - 해당 클래스가 ProtocolB 의 규칙에 따라 제작되었다면
    //   결국 그 수퍼 인터페이스의 규칙도 준수하는 것이 된다.
    // - 따라서 수퍼 인터페이스 레퍼런스에 객체를 담을 수 있다.
    //   즉 수퍼 인터페이스 레퍼런스로 객체를 가리킬 수 있다.
    //
    ProtocolA a = obj;

    // 4) 메서드 호출
    // - 해당 인터페이스의 규칙에 따라서만 호출할 수 있다.
    a.rule1(); // OK!
    //    a.rule2(); // 컴파일 오류!
  }

  public static void main(String[] args) {
    new Exam0110().test();
  }
}







