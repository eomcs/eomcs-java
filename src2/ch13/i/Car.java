// 상속 : generalization(일반화) 수행 후 
package ch13.i;

// Car 클래스는 Convertible과 DumpTruck에 공통으로 일반 기능을 뽑아서 만든 클래스이다.
// => 이렇게 서브 클래스의 공통 기능을 추출하여 수퍼 클래스를 정의하는 것을 "generalization"이라 한다.
// => 수퍼 클래스를 정의한 후 기존 클래스와 부모-자식 관계를 맺는다.
public class Car {
  String model;
  int cc;
  
  public void run() {
    System.out.println("달린다");
  }
  
  public void stop() {
    System.out.println("멈춘다");
  }
}
