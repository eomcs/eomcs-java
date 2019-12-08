// 상속 : generalization(일반화) 수행 전
package ch13.h;

// 프로그래밍을 하면서 필요한 클래스가 있으면 다음과 같이 만들어 사용한다.
public class Convertible {
  String model;
  int cc;
  boolean open;
  
  public void run() {
    System.out.println("달린다");
  }
  
  public void stop() {
    System.out.println("멈춘다");
  }
  
  public void openRoof() {
    open = true;
  }
  
  public void closeRoof() {
    open = false;
  }
}






