package com.eomcs.design_pattern.observer.after.c;

public interface CarObserver {
  // 자동차 시동을 켤 때 호출될 메서드
  void carStarted();

  // 자동차 시동을 끌 때 호출될 메서드
  void carStopped();
}
