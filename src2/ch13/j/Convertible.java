// 상속 : generalization(일반화) 수행 후
package ch13.j;

// 여러 클래스에 공통으로 들어 가는 기능이나 필드가 있다면 
// 유지보수가 쉽도록 별도의 클래스로 추출한다.
// 그리고 상속 관계를 맺는다.
// => Convertible과 DumpTruck 사이에 공통 필드와 메서드가 있다.
//    공통 기능을 추출하여 별도의 클래스를 정의하는 것을 "일반화(generalization)"라 한다.
// => 수퍼 클래스를 추출한 후 수퍼 클래스를 상속 받으면 된다.
//
public class Convertible extends Car {

  boolean open;
  
  public void openRoof() {
    open = true;
  }
  
  public void closeRoof() {
    open = false;
  }
}






