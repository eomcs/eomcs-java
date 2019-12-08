// 서브 인터페이스 구현 - 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
package ch18.d;

// 수퍼 인터페이스가 있는 있는 인터페이스를 구현할 때는 
// 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
public class Tool implements ProtocolA3 {
  // ProtocolA 인터페이스 구현
  @Override
  public void m1() {
    System.out.println("Tool.m1()");
  }
  
  @Override
  public void m2() {
    System.out.println("Tool.m2()");
  }
  
  // ProtocolA2 인터페이스 구현
  @Override
  public void m3() {
    System.out.println("Tool.m3()");
  }
  
  // ProtocolA3 인터페이스 구현
  @Override
  public void m4() {
    System.out.println("Tool.m4()");
  }
}











