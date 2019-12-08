// 인터페이스의 필드
package ch18.g1;

public interface A {
  // 인터페이스에 선언하는 모든 필드는 public static final 이다.
  // 즉 상수이다.
  public static final int V1 = 100;
  
  // public static final을 생략할 수 있다.
  // 즉 적지 않으면 무조건 public static final이다.
  int V2 = 200;
}
