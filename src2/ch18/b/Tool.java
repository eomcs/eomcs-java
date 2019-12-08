// 인터페이스 구현 - 접근 범위를 좁힐 수는 없다.
package ch18.b;

public abstract class Tool implements SpecA {
  
  // public 접근 범위보다 좁은 메서드를 구현할 수 없다.
  @Override
  //private void m1() {} // 컴파일 오류!
  //protected void m1() {} // 컴파일 오류!
  //void m1() {} // 컴파일 오류! (default) 접근 제한자이다.
  public void m1() {} // OK!
  
  // 모든 추상 메서드를 구현해야 한다. 한 개라도 빠뜨리면 concrete 클래스로 만들 수 없다.
  // => 추상 클래스로 만들어야 한다. 왜? 추상 메서드가 있으니까!
  //public void m2() {} // OK!
}
