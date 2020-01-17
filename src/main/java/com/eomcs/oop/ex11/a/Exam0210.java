// non-static nested class = inner class
package com.eomcs.oop.ex11.a;

public class Exam0210 {

  // 스태틱 멤버
  static int sValue;
  static void sm() {}

  // 인스턴스 멤버
  int iValue;
  void im() {}
  
  // non-static nested class 는 인스턴스 멤버이다.
  // 따라서 인스턴스가 있어야만 사용할 수 있다.
  class A {
    void m1() {
      // 인스턴스 멤버(인스턴스 블록, 생성자, 인스턴스 메서드, inner class)는
      // 스태틱 멤버를 자유롭게 사용할 수 있다.
      sValue = 100; // OK
      sm(); // OK

      // 인스턴스 멤버는 같은 인스턴스 멤버를 사용할 수 있다.
      // 왜?
      // => 인스턴스 멤버가 실행되는 시점에 this 라는 빌트인 변수에 인스턴스 주소가 들어있기 때문이다.
      // => 단 inner class 에서는 바깥 클래스의 인스턴스 주소가 
      //       "바깥클래스명.this" 변수에 들어 있다.
      // 
      Exam0210.this.iValue = 100; // OK!
      Exam0210.this.im(); // OK!
      
      // inner 클래스에 같은 이름의 멤버가 없다면,
      // "바깥 클래스명.this"를 생략해도 된다.
      //
      iValue = 100; // OK!
      im(); // OK!
      
      // 주의!
      // => "바깥 클래스명"을 빼고 그냥 this 변수를 사용하면 
      //    이때의 this는 inner class의 인스턴스를 가리킨다.
      // => 바깥 클래스의 인스턴스를 가리키고 싶으면 반드시 바깥 클래스명을 지정해야 한다.
      //
      //this.iValue = 100; // 컴파일 오류!
      //this.im(); // 컴파일 오류!
    }
  }
  
  // 결론:
  // nested class가 바깥 클래스의 인스턴스 멤버를 사용한다면 
  // non-static nested class 로 정의하라!

  public static void main(String[] args) {
  }
}
