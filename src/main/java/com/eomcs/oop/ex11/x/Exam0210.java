// non-static nested class = inner class
package com.eomcs.oop.ex11.x;

public class Exam0210 {

  // 스태틱 멤버
  static int sValue;
  static void sm() {
    // 스태틱 메서드는 인스턴스를 주소를 담는 this 라는 내장 변수가 없다.
    // 왜? 클래스 이름을 사용하기 때문이다.
    // 설사 인스턴스를 가지고 이 메서드를 호출한다고 하더라도 
    // 그 인스턴스의 주소를 저장할 this 라는 변수가 없기 때문에 그 인스턴스 주소를 저장할 수 없다.
  }

  // 인스턴스 멤버
  int iValue;
  void im() {
    // non-static 메서드는 인스턴스 주소가 있어야만 호출할 수 있다.
    // 인스턴스 주소를 담은 this 라는 변수가 내장되어 있다.
    // 
    // 만약 인스턴스 주소 없이 이 메서드를 호출하면 NullPointerException 오류가 발생한다.
    // 예)
    //    Exam0210 obj = null;
    //    obj.im(); // NullPointerException 오류!
    //
    // 클래스 이름으로 바로 호출하면 컴파일 오류가 발생한다.
    // 예)
    //    Exam0210.im(); // 컴파일 오류!
  }

  // non-static nested class 는 인스턴스 멤버이다.
  // 따라서 인스턴스가 있어야만 사용할 수 있다.
  class A {

    // 자바 컴파일러는 non-static nested class를 컴파일할 때 
    // 바깥 클래스의 인스턴스 주소를 담을 필드를 자동으로 생성한다.
    // 예)
    // Exam0210 outer;

    // 또한 이 inner class의 객체를 생성할 때 호출될 생성자를 자동으로 만든다.
    // => 생성자는 바깥 클래스의 인스턴스를 받는 파라미터를 갖고 있다.
    // 예)
    //
    //    public A(Exam0210 arg0) {
    //      outer = arg0;
    //    }


    void m1() {
      // 인스턴스 멤버(인스턴스 블록, 생성자, 인스턴스 메서드, inner class)는
      // 바깥 클래스의 스태틱 멤버를 자유롭게 사용할 수 있다.
      sValue = 100; // OK <== Exam0210.sValue = 100;
      sm(); // OK <== Exam0210.sm();

      // 인스턴스 멤버는 같은 인스턴스 멤버를 사용할 수 있다.
      // 왜?
      // => inner class의 객체를 생성할 때 
      //    컴파일러가 자동으로 추가한 변수(예: outer)에 
      //    바깥 클래스의 객체 주소가 저장된다.
      // => 이렇게 inner class 객체에 보관된 바깥 클래스의 객체를 사용하려면
      //    다음과 같은 이름으로 변수를 사용해야 한다.
      //      "바깥클래스명.this"
      // => 다음 코드를 보자!
      //
      Exam0210.this.iValue = 100; // OK!
      Exam0210.this.im(); // OK!

      // inner 클래스에 같은 이름의 멤버가 없다면,
      // "바깥 클래스명.this"를 생략해도 된다.
      // 구체적으로 어떤 클래스의 인스턴스 멤버를 찾을 지 지정하지 않으면 
      // 다음 순서로 찾는다.
      // 1) 로컬 변수를 찾는다.
      // 2) 인스턴스 변수를 찾는다.
      // 3) 스태틱 변수를 찾는다.
      // 4) 바깥 클래스의 인스턴스 변수를 찾는다.
      // 5) 바깥 클래스의 스태틱 변수를 찾는다.
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
