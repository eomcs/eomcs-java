package com.eomcs.oop.ex11.x;

public class Exam0210Test {

  public static void main(String[] args) {
    Exam0210 obj = new Exam0210();

    // 스태틱 멤버 접근
    Exam0210.sValue = 100;
    Exam0210.sm();

    // 인스턴스 멤버 접근
    //Exam0210.iValue = 100; // 컴파일 오류!
    //Exam0210.im(); // 컴파일 오류!

    obj.iValue = 100; // OK
    obj.im(); // OK

    // non-static nested class 사용
    // => 레퍼런스를 선언할 때는 static nested class 처럼 
    //    클래스 이름으로 중첩 클래스를 사용한다.
    Exam0210.A obj2;  

    // => non-static nested class, 즉 inner 클래스의 인스턴스를 생성할 때는
    //    인스턴스 메서드를 호출하듯이 
    //    반드시 바깥 클래스의 인스턴스 주소가 있어야 한다.
    //obj2 = new Exam0210.A(); // 컴파일 오류!

    obj2 = obj.new A(); // OK!
    //obj2 = new obj.A(); // 문법이 맞지 않다. 컴파일 오류!
    obj2.m1();
  }

}
