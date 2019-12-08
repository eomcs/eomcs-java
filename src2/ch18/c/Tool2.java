// 다중 인터페이스를 구현하지 못하는 경우 - 메서드명과 파라미터는 같은데 리턴 타입이 다른 경우
package ch18.c;

// 한 클래스가 여러 규칙에 따라 동작하도록 정의할 수 있는데
// 불가능한 경우가 있다.
// 규칙에 중복되는 메서드 중에 리턴값은 다르고 기타 시그너처는 같은 메서드가 있을 때 불가능하다.
public class Tool2 implements ProtocolA, ProtocolC {
  
  // ProtocolA 규칙에 따라 동작할 수 있도록 메서드를 구현
  @Override
  public void m1() {
    System.out.println("Tool.m1()");
  }
  
  // 다음 메서드는 ProtocolA 규칙은 만족시키지만, ProtocolC 규칙은 만족시키지 못한다.
  @Override
  public void m2() {
    System.out.println("Tool.m2()");
  }
  
  // 그럼 리턴 값이 다른 메서드를 다음과 같이 또 만들면 되지 않나요?
  // => 오버로딩 규칙에 따라 파라미터와 메서드명은 같고 리턴 값만 다른 메서드를 여러 개 만들 수 없다.
  @Override
  public int m2() {
    System.out.println("Tool.m2()");
    return 0;
  }
  
  // ProtocolC 규칙에 따라 메서드를 작성한다.
  @Override
  public void m3() {
    System.out.println("Tool.m3()");
  }
  
  
}









