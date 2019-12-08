// 서브 인터페이스 구현 II - 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
package ch18.d;

// 수퍼 인터페이스가 있는 있는 인터페이스를 구현할 때는 
// 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
public class Tool2 implements ProtocolC {

  @Override public void m3() {} // ProtocolA2

  @Override public void m1() {} // ProtocolA, ProtocolB

  @Override public void m2() {} // ProtocolA

  @Override public void k1() {} // ProtocolB

  @Override public void k2() {} // ProtocolB

  @Override public void x() {} // ProtocolC

  @Override public void y() {} // ProtocolC
  
}











