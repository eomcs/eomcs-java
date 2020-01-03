// 다형성 - 다형적 변수(polymorphic variables)
package com.eomcs.oop.ex06.a;

public class Exam0111 {

  public static void main(String[] args) {
    Vehicle v = new Vehicle();
    Bike b = new Bike();
    Car c = new Car();
    Sedan s = new Sedan();
    Truck t = new Truck();

    // 레퍼런스는 같은 타입의 객체를 가리킬 수 있을 뿐만아니라 
    // 그 클래스의 서브클래스 객체까지 가리킬 수 있다.
    // 왜? 서브 클래스는 항상 상위 클래스의 모든 것을 사용할 수 있기 때문이다.
    //
    // 이런 규칙에 따라, 다음 v2 변수는 Vehicle 객체 뿐만아니라 
    // Bike, Car, Sedan, Truck 객체까지 다양한 서브클래스의 객체를 가리킬 수 있다.
    // 그래서 v2를 "다형적 변수"의 기능을 갖고 있다 말한다. 
    Vehicle v2 = null;
    
    v2 = b; // OK
    v2 = c; // OK
    v2 = s; // OK
    v2 = t; // OK
    
    // 이렇게 상위 클래스의 레퍼런스로 하위 클래스의 인스턴스를 가리킬 수 있는 이유?
    // => 하위 클래스의 인스턴스는 상위 클래스의 인스턴스 멤버를 갖고 있기 때문이다.
    // => 그래서 상위 클래스의 레퍼런스로 
    //    상위 클래스의 인스턴스 멤버 사용을 보장한다.  
    //    
    
    // 예를 들어, Vehicle 변수 v2가 Bike 객체를 가리킨다면,
    v2 = b;
    
    // v2를 통해 Vehicle의 인스턴스 변수에 접근할 수 있다.
    v2.model = "티코";
    v2.capacity = 5;
    
    // 왜?
    // => Bike 인스턴스에는 수퍼 클래스인 Vehicle의 인스턴스 변수가 있기 때문.
  }

}









