package com.eomcs.design_pattern.observer.after.h;

// 인터페이스 구현체가 메서드를 정의하기 쉽도록
// 이 클래스에서 미리 모든(또는 일부) 메서드를 구현하였다.
// 이 클래스의 존재 이유는 인터페이스 구현체가 메서드를 정의하기 쉽도록
// 미리 구현된 메서드를 상속해주는 일을 한다.
// 즉 이 클래스 자체를 사용하려는 것이 아니다.
// 이런 경우 추상 클래스로 정의하면 좋다.
// - 추상 메서드가 없지만, 추상 클래스로 선언함으로써
// 개발자에게 이 클래스의 역할을 알리는 효과가 있다.
//
public abstract class AbstractCarObserver implements CarObserver {

  @Override
  public void carStarted() {}

  @Override
  public void carStopped() {}
}
