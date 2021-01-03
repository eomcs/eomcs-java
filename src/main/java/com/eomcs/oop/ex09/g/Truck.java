// 인터페이스 간접 구현 : 추상 클래스 상속 받기
package com.eomcs.oop.ex09.g;

//자동차의 기능을 갖추려면 CarSpec을 만족시켜야 하는데,
//직접 CarSpec 인터페이스를 구현하는 대신에
//CarSpec을 미리 구현한 AbstractCar를 상속 받는 것이
//클래스를 만들기가 편하다.
public class Truck extends AbstractCar {

  // CarSpec에 선언된 on(), off() 메서드는 수퍼 클래스에서 미리 구현했기 때문에
  // 서브 클래스에서 다시 구현할 필요가 없어 편하다!
  //
  // 서브 클래스는 수퍼 클래스가 구현하지 않은 나머지 메서드만 구현하면 된다.
  @Override
  public void run() {
    System.out.println("덜컹 덜컹 달린다!");
  }
}
