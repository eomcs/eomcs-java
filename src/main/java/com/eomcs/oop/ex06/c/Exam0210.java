// 오버라이딩(overriding) - 오버라이딩 실수의 예
package com.eomcs.oop.ex06.c;

public class Exam0210 {
  public static void main(String[] args) {
    B2 obj = new B2();

    // B2에서 B의 m() 메서드를 오버라이딩(재정의) 했다고 착각하고
    // 메서드를 사용할 수 있다.
    // => 그런데 B2 클래스를 가보면 m()의 파라미터가 float이다.
    //    즉 오버라이딩을 한 게 아니라 오버로딩을 한 것이 된다.
    obj.m(100); // B의 m(int) 메서드를 호출 

    // 해결책?
    // => 개발자들의 이런 실수가 많이 발생해서 자바에서는 
    //    오버라이딩 여부를 검사하는 특별한 문법을 추가하였다.
    // Exam0220.java에서 계속...
  }
}








