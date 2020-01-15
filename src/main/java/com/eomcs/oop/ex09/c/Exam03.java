// 다중 인터페이스 구현 II
package com.eomcs.oop.ex09.c;

// 클래스는 여러 개의 규칙을 이행할 수 있다.
// 규칙들 중에서 메서드가 겹칠 수 있다. 상관 없다. 
// A{m1()}, B{m2()}, C{m3()}, D{m2(),m4()}
// B 인터페이스 입장에서도 Exam03은 m2() 메서드를 만든 것이고,
// D 인터페이스 입장에서도 Exam03은 m2() 메서드를 마든 것이기 때문에 상관없다.
// 왜? 어차피 구현된 메서드가 아니기 때문이다.
public class Exam03 implements B, C, D {
  public void m1() {} // B의 수퍼인터페이스인 A 인터페이스 구현
  public void m2() {} // B와 D 인터페이스 구현
  public void m3() {} // C의 인터페이스 구현 
  public void m4() {} // D의 인터페이스 구현
  
  public static void main(String[] args) {
    Exam03 obj = new Exam03();
    
    B obj2 = obj;
    obj2.m2(); // 여기에서는 B.m2() 이다.
    obj2.m1();
    
    C obj3 = obj;
    obj3.m3();
    
    D obj4 = obj;
    obj4.m2(); // 여기에서는 D.m2() 이다. 같은 메서드를 여러 인터페이스에서 공유할 수 있다.
    obj4.m4();
    
  }
}







