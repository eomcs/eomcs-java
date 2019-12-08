// 인터페이스의 다중 상속 - 구현된 메서드가 아니기 때문에 다중 상속이 가능하다.
package ch18.d;

// 구현된 메서드가 아니기 때문에 여러 인터페이스에서 중복되더라도 상관없다.
// 그 중 어떤 메서드를 상속 받더라도 똑 같기 때문이다.
// 주의!
// => 수퍼 인터페이스 중에서 리턴 타입만 다른 메서드가 있을 경우에는 다중 상속이 불가하다!
public interface ProtocolC extends ProtocolA2, ProtocolB {
  void x();
  void y();
}
