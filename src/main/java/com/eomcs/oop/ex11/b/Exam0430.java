//static nested class : import static 사용 후
package com.eomcs.oop.ex11.b;

// 각각의 스태틱 멤버를 지정하는 대신 
// 다음과 같이 wildcard(*)를 사용하여 전체 스태틱 멤버를 한 번에 지정할 수 있다.
import static com.eomcs.oop.ex11.b.F.*;
import static com.eomcs.oop.ex11.b.sub.M.*;

class F {
  static int v1;

  static void m1() {}

  static class X {
    void test() {
      System.out.println("test()");
    }
  }
} 

public class Exam0430 {

  public static void main(String[] args) {
    // 같은 패키지 클래스의 스태틱 멤버
    v1 = 100;
    m1();
    X obj = new X();

    // 다른 패키지 클래스의 스태틱 멤버
    v2 = 200;
    m2();
    Y obj2 = new Y();
  }

}
