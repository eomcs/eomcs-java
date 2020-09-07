// inner class : 바깥 클래스의 인스턴스를 보관할 this 변수와 생성자
package com.eomcs.oop.ex11.c;

class E {
  class X {
    // 인스턴스 메서드는 this 라는 내장 변수에 인스턴스 주소를 보관한다.
    // 그렇다면 inner class는 어디에 보관할까?
    // - 자바 컴파일러는 바깥 클래스의 인스턴스 주소를 저장하기 위해 
    //   다음과 같은 필드를 추가한다.
    // - 컴파일 완료된 inner class의 .class 파일을 열어 보면,
    //   바깥 클래스의 객체 주소를 저장할 인스턴스 필드가 추가된 것을 확인할 수 있다.  
    //    
    //    final synthetic com.eomcs.oop.ex11.c.E this$0;
    //

    // 또한 inner class의 인스턴스를 생성할 때 
    // 바깥 클래스의 인스턴스 주소를 받기 위해 
    // 생성자에 파라미터가 추가된다.
    // 
    //    E$X(com.eomcs.oop.ex11.c.E arg0);
    //    0  aload_0 [this]
    //    1  aload_1 [arg0]
    //    ...
    // - 생성자의 파라미터로 받은 바깥 클래스의 객체 주소는 
    //   컴파일 할 때 자동으로 추가된 인스턴스 필드에 보관된다.
    //
  }
}

public class Exam0510 {

  public static void main(String[] args) {
    E outer = new E();

    E.X obj = outer.new X();
    // inner class 의 인스턴스를 생성할 때 바깥 클래스의 객체 주소를 어떻게 전달할까?
    // - 컴파일러는 위의 기본 생성자 호출 코드를 
    //   바깥 클래스의 객체 주소를 받는 생성자 호출 코드로 변경한다.
    // 
    //    E.x obj = new X(outer);
    // 
  }

}
