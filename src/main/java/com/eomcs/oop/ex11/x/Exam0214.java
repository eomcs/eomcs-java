// non-static nested class : inner class에 바깥 클래스의 인스턴스를 전달하는 원리
package com.eomcs.oop.ex11.x;

public class Exam0214 {
  public static void main(String[] args) {
    // 중첩 클래스에 바깥 클래스의 인스턴스 주소를 전달 하는 방법

    //1) 바깥 클래스의 인스턴스를 준비한다.
    Exam0213 outer = new Exam0213();

    //2) 중첩 클래스의 레퍼런스 준비
    Exam0213.A obj;

    //3) 중첩 클래스의 인스턴스 생성
    obj = outer.new A();

    // 내부 구동원리
    // - 실제 중첩 클래스 A에 대해 호출되는 생성자는 기본 생성자가 아니다.
    // - 바깥 클래스의 인스턴스 주소를 받는 생성자가 호출된다.
    // - 즉 실제 컴파일러는 위의 코드를 다음과 같이 변경한다.
    //    obj = new A(outer);
  }
}








