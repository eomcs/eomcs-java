// 인터페이스를 간접 구현한 클래스
package com.eomcs.oop.ex09.f;

// Exam01을 상속 받는 순간 
// Exam02도 A 규칙을 따르게 된다.
// => Exam01의 모든 메서드를 상속 받았으니까
//    당연히 A 규칙을 모두 구현한 게 되는 것이다.
public class Exam02 extends Exam01 {
  
  public static void main(String[] args) {
    
    Exam02 obj = new Exam02();
    
    // Exam02는 Exam01이 구현한 A의 모든 메서드를 상속 받는다.
    // 따라서 Exam02는 A 인터페이스를 구현한 것이 된다.
    A obj2 = obj; // OK!
    obj2.m1(); 
  }
}
