// non-static nested class : inner class의 특별한 변수와 생성자 II
package com.eomcs.oop.ex11.x;

public class Exam0215 {
  class A {
    // 바깥 클래스의 인스턴스 주소를 저장하기 위해 
    // 컴파일러는 다음과 같이 인스턴스 필드를 컴파일 할 때 자동으로 삽입한다.
    // => 컴파일 완료된 .class 파일을 열어 확인해 보라!
    //    Exam0215 outer;

    int value;

    // 기존에 생성자가 있다면 
    // 바깥 클래스의 인스턴스 주소를 받는 파라미터를 추가한다.

    public A() {}
    // => 위 생성자는 다음의 생성자로 변경된다.
    //    public A(Exam0215 outer) {
    //      this.outer = outer;
    //    }

    public A(int value) {
      this.value = value;
    }
    // => 위 생성자는 다음의 생성자로 변경된다.
    //    public A(Exam0215 outer, int value) {
    //      this.outer = outer;
    //      this.value = value;
    //    }
  }
}
