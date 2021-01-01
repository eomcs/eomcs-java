// 상속 - 추상클래스
package com.eomcs.oop.ex05.m;

// Car 클래스는 Sedan과 Truck의 공통 기능을 관리하기 위해 만든 클래스이다.
// 즉 Car 클래스는 직접 인스턴스를 만들어 사용하기 위해서 정의한 클래스가 아니라,
// Sedan과 Truck에서 겹치는 코드를 공통 관리하기 위해 만든 클래스이다.
// 가능한 직접 Car 클래스를 사용하는 것을 피해야 한다.
public class Exam01 {

  public static void main(String[] args) {
    // => 그럼에도 다음과 같이 Car 클래스를 사용하는 것을 막을 수 없다.
    // => 이런 경우를 방지하기 위해
    //    즉 Car 클래스의 경우처럼 서브클래스에 공통 기능을 상속해주는 용도로 만든 클래스를
    //    직접 사용하지 못하도록 막기위해 만든 문법이
    //    "추상클래스(abstract)"이다.

    // => 다음과 경우처럼 Car 클래스를 추상 클래스로 선언하는 순간 컴파일 오류가 발생한다.
    //    Car c = new Car(); // 컴파일 오류!

    // generalization 과정에서 정의한 수퍼 클래스에 대해
    // 직접 사용하지 못하도록 추상클래스로 선언한다.

    Sedan s = new Sedan();
    Truck t = new Truck();
  }

}





