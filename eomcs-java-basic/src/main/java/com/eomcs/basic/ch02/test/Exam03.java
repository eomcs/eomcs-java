//# 주석 - 애노테이션(annotation)
//
package com.eomcs.basic.ch02.test;

//1) 현재 상태의 소스 파일을 그대로 컴파일하라.
//- 컴파일 오류가 발생하지 않는다.

public class Exam03 {
  public static void main(String[] args) {
    System.out.println("애노테이션");
  }

  //2) 다음 메서드가 수퍼 클래스(Object)로부터 상속 받은 메서드를 오버라이딩 한 것인지
  //   컴파일러가 검사하도록 애노테이션으로 지정한 후 컴파일 하라.
  //- 컴파일 오류가 발생했는가? 이유는 무엇인가?
  
  public String toString2() {
    return "Exam03";
  }
}
