// non-static nested class = inner class의 생성자
package com.eomcs.oop.ex11.x;

public class Exam0231 {
  public static void main(final String[] args) {
    final Exam0231_X obj = new Exam0231_X();

    obj.new A("홍길동", 20);
    // 위 코드는 컴파일러 다음과 같이 바꾼다.
    // => 바깥 클래스의 객체 주소를 받기 위해 컴파일러가 변경한 생성자를 호출한다.
    /*
     * new A(obj, "홍길동", 20);
     */
  }
}


class Exam0231_X {
  class A {
    String name;
    int age;

    public A(final String name, final int age) {
      this.name = name;
      this.age = age;
    }

    /*
     * 컴파일러가 추가하는 필드 및 생성자;
     *
     * Exam0231_X outer;
     *
     * 우리가 추가한 생성자는 다음과 같이 바깥 클래스의 객체 주소를 받을 수 있도록 파라미터가 추가된다.
     *
     * public A(Exam0231_X outer, final String name, final int age) { this.outer = outer; this.name
     * = name; this.age = age; }
     */
    void m1() {}
  }
}
