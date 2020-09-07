// non-static nested class : inner class에 바깥 클래스의 인스턴스를 전달하는 원리 II
package com.eomcs.oop.ex11.x;

public class Exam0216 {
  public static void main(String[] args) {
    Exam0215 outer = new Exam0215();

    Exam0215.A obj;

    // 개발자가 작성한 코드
    obj = outer.new A();
    // => 컴파일러가 컴파일하면서 바꾼 코드
    //    obj = new A(outer);

    // 개발자가 작성한 코드
    obj = outer.new A(100);
    // => 컴파일러가 컴파일하면서 바꾼 코드
    //    obj = new A(outer, 100);
  }
}








