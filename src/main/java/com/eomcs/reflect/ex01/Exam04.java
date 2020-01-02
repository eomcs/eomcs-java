// Reflection API : 클래스 로딩과 "class"라는 스태틱 변수
package com.eomcs.reflect.ex01;

class X {
  static {
    System.out.println("X 클래스 로딩");
  }
}
class Y {
  static {
    System.out.println("Y 클래스 로딩");
  }
}
class Z {
  static {
    System.out.println("Z 클래스 로딩");
  }
}
public class Exam04 {

  public static void main(String[] args) throws Exception {
    // forName()은 
    // 이렇게 프로그램 아규먼트나 키보드 입력을 통해 
    // 클래스 이름을 입력 받아서 로딩할 수 있다.
    Class.forName(args[0]);

    // 그러나 다음과 같이 "class" 변수를 사용하게 되면 
    // 코드로 이름을 고정하기 때문에 임의의 클래스를 로딩할 수 없다.
    Class clazz = A.class;
  }

}





