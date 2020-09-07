// non-static nested class : inner class의 특별한 변수와 생성자
package com.eomcs.oop.ex11.a;

public class Exam0213 {
  class A {
    // 바깥 클래스의 인스턴스 주소를 저장하기 위해 
    // 컴파일러는 다음과 같이 인스턴스 필드를 컴파일 할 때 자동으로 삽입한다.
    // => 컴파일 완료된 .class 파일을 열어 확인해 보라!
    //    Exam0213 outer;

    // 또한 바깥 클래스의 인스턴스 주소를 설정하기 위해 
    // 다음과 같이 생성자에 파라미터를 자동으로 추가한다.
    // => 컴파일 완료된 .class 파일을 열어 확인해 보라!
    //    public A(Exam0213 outer) {
    //      this.outer = outer;
    //    }
  }
}
