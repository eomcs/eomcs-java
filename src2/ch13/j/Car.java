// 추상 클래스 
package ch13.j;

// 이렇게 Convertible과 DumpTruck의 경우처럼
// 여러 클래스의 공통점을 추출하여 수퍼 클래스를 정의하는 경우,
// 그 수퍼 클래스의 목표는 서브 클래스의 공통 기능을 물려주는 것이다.
// 처음부터 Car를 먼저 만들어 쓰다가 더 특별한 클래스가 필요해서 Convertible이나 
// DumpTruck을 만든 것이 아니라,
// 여러 클래스를 사용하다 보니 공통 분모가 보여서, 
// 소스 코드의 유지보수가 쉽도록 한 클래스로 모아두기 위해 만든 경우는
// 해당 클래스를 직접 사용할 이유가 없다.
// 특히 generalization을 수행하여 만든 수퍼 클래스의 경우
// 직접 사용할 목적으로 만든 클래스가 아니다.
// 단지 서브 클래스에 공통 기능을 물려주기 위해 존재하는 클래스이다.
// 이런 클래스들은 직접 사용하지 못하게 막는 것이 좋다.
//
// 클래스를 직접 사용하지 못하게 막고 단지 서브 클래스를 만들어 사용하도록 제한하는 문법이
// "추상 클래스" 이다.
// => 문법:    abstract class 클래스명 {...}
//
public abstract class Car {
  String model;
  int cc;
  
  public void run() {
    System.out.println("달린다");
  }
  
  public void stop() {
    System.out.println("멈춘다");
  }
}
