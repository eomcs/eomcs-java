// anonymous class : 로컬 클래스 vs 익명 클래스
package com.eomcs.oop.ex11.e;

public class Exam0120 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  interface A {
    void print();
  }

  public static void main(final String[] args) {
    // 익명 클래스:
    // => 인스턴스를 한 번 만 생성할 것이라면, 
    //    로컬 클래스로 정의하는 것 보다 익명 클래스로 정의하는 것이 더 낫다.
    // => 특히 객체를 사용하려는 곳에 바로 익명 클래스를 정의하면 
    //    읽기 쉽기 때문에 소스 코드를 유지보수에 더 좋다.
    //
    // 익명 클래스로 인터페이스 구현하기
    // 문법:
    // => 인터페이스명 레퍼런스 = new 인터페이스명() {};
    //
    A obj = new A() {
      @Override
      public void print() {
        System.out.println("Hello!");
      }
    };
    obj.print();
  }
}
