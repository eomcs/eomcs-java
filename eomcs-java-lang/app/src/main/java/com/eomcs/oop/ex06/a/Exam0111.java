// 다형성 - 다형적 변수(polymorphic variables)
package com.eomcs.oop.ex06.a;

public class Exam0111 {

  public static void main(String[] args) {

    Bike bike = new Bike();

    Vehicle vehicle = bike; // OK

    // 이렇게 상위 클래스의 레퍼런스로 하위 클래스의 인스턴스를 가리킬 수 있는 이유?
    // => 하위 클래스의 인스턴스는 상위 클래스의 인스턴스 멤버를 갖고 있기 때문이다.
    // => 그래서 상위 클래스의 레퍼런스로
    //    상위 클래스의 인스턴스 멤버 사용을 보장한다.
    //

    // vehicle을 통해 Vehicle의 인스턴스 변수에 접근할 수 있다.
    vehicle.model = "티코";
    vehicle.capacity = 5;

    // 왜?
    // => Bike 인스턴스에는 수퍼 클래스인 Vehicle의 인스턴스 변수가 있기 때문.
  }

}









