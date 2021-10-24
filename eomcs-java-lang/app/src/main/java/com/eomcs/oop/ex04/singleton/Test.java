// Singleton 패턴
// => 객체를 한 개만 생성할 수 있게 만드는 설계 기법
package com.eomcs.oop.ex04.singleton;

public class Test {

  public static void main(String[] args) {
    // Singleton을 적용하지 않은 클래스의 인스턴스 만들기
    Car c1 = new Car();
    Car c2 = new Car();
    Car c3 = new Car();
    // 이렇게 인스턴스 생성에 제한이 없다.
    System.out.println(c1 == c2);
    System.out.println(c1 == c3);
    System.out.println(c2 == c3);

    System.out.println("----------------");

    // Car2 클래스의 생성자를 접근할 수 없기 때문에 호출할 수 없다.
    //Car2 x = new Car2(); // 컴파일 오류!

    Car2 x1 = Car2.getInstance();
    Car2 x2 = Car2.getInstance();
    Car2 x3 = Car2.getInstance();
    
    System.out.println(x1 == x2);
    System.out.println(x1 == x3);
    System.out.println(x2 == x3);
  }

}





