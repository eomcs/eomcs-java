// 다형적 변수와 오버라이딩
package com.eomcs.oop.ex06.d;

public class Exam02 {
  public static void main(String[] args) {
    A a = new A3();
    a.m(); // A2의 m() 호출 
    // a가 실제 가리키는 A3 클래스부터 상위 클래스로 따라 올라가면서 
    // 첫 번째로 만난 m()을 호출한다.
    System.out.println("--------------------");
    
  }
}






