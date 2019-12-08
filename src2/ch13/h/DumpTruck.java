// 상속 : generalization(일반화) 수행 전
package ch13.h;

// 다음과 같이 필요한 클래스를 정의하여 사용한다.
//
public class DumpTruck {
  String model;
  int cc;
  int weight;
  
  public void run() {
    System.out.println("달린다");
  }
  
  public void stop() {
    System.out.println("멈춘다");
  }
  
  public void dump() {
    System.out.println("짐을 내린다.");
  }
}


