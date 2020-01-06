// 캡슐화(encapsulation) - 접근 범위 테스트
package com.eomcs.oop.ex07.b;

import com.eomcs.oop.ex07.b.sub.B;
import com.eomcs.oop.ex07.b.sub.C;

public class Exam0210 extends C {

  public static void main(String[] args) {
    A obj1 = new A();

    //obj1.privateVar = 100; // 접근 불가! 오직 그 클래스 안에서만 사용가능.
    obj1.defaultVar = 100; // OK! 이 클래스는 A 클래스와 같은 패키지에 소속되어 있다.
    obj1.protectedVar = 100; // OK! 비록 이 클래스가 자식클래스는 아니지만
    // 같은 패키지에 소속되어 있다.
    obj1.publicVar = 100; // OK! 모두 다 접근 가능!

    B obj2 = new B();

    //obj2.privateVar = 100; // 접근 불가! 오직 그 클래스 안에서만 사용 가능.
    //obj2.defaultVar = 100; // 접근 불가! 같은 패키지까지만 접근 가능.
    //obj2.protectedVar = 100; // 접근 불가! 같은 패키지 또는 자식 클래스 접근 가능
    obj2.publicVar = 100; // OK! 모두 다 접근 가능.


    C obj3 = new C();

    //obj3.privateVar = 100; // 접근 불가! 오직 그 클래스 안에서만 사용 가능.
    //obj3.defaultVar = 100; // 접근 불가! 같은 패키지까지만 접근 가능.
    //obj3.protectedVar = 100; // 접근 불가! 같은 패키지 또는 자식 클래스 접근 가능
    // 자식 클래스인데 접근 불가?
    // 이유 => 자기의 인스턴스 변수가 아니다.
    obj2.publicVar = 100; // OK! 모두 다 접근 가능.

    Exam0210 obj4 = new Exam0210();
    //obj4.privateVar = 100; // 접근 불가! C 클래스에서만 접근 가능
    //obj4.defaultVar = 100; // 접근 불가! C 클래스와 같은 패키지가 아니다.
    obj4.protectedVar = 100; // OK! Exam02_1는 C의 자식 클래스이며,
    // 또한 C로부터 상속 받아서 만든 자기 변수이다.
    obj4.publicVar = 100;
  }
  
  void m1(C obj) {
    //obj.privateVar = 100;
    //obj.defaultVar = 100;
    //obj.protectedVar = 100;
    obj.publicVar = 100;
  }
  
  void m2(Exam0210 obj) {
    //obj.privateVar = 100;
    //obj.defaultVar = 100;
    obj.protectedVar = 100;
    obj.publicVar = 100;
  }
 
  void m3() {
    //this.privateVar = 100;
    //this.defaultVar = 100;
    this.protectedVar = 100;
    this.publicVar = 100;
  }
  
  
}

// private      : 클래스 안에서만 접근 가능
// (default)    : private + 같은 패키지 소속
// protected    : (default) + 자식 클래스에서 자신이 만든 변수일 경우
// public       : 모두 접근 가능
//
// 실무
// => 인스턴스 변수는 보통 private으로 접근을 제한한다.
// => 겟터,셋터는 public으로 접근을 모두에게 공개한다.
// => 일반 메서드도 public으로 접근을 모두에게 공개한다.
// => 그 클래스 내부에서만 사용되는 메서드는 private으로 접근을 제한한다.
// => 자식 클래스의 접근을 허용할 필요가 있을 경우에만 protected로 만든다. 
// => 다른 개발자가 사용할 클래스 모음을 만들 때 
//    그 모음집 내에서만 사용될 변수나 메서드인 경우 (default)로 접근을 제한한다.
//    즉 라이브러리를 만드는 개발자인 경우 (default)를 사용하는 경우가 있다.






