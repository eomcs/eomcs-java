package com.eomcs.oop.ex08.c;

// 추상 클래스
// => 서브클래스에게 공통 기능을 상속해주는 목적으로 만든 클래스이다.
// => 직접 사용하지 않는 클래스이다.
// => 즉 개발자에게 이 클래스를 상속 받아 새 클래스를 만들어 쓰라는 의미다!
public abstract class Car {

  public Car() {
    super();
  }

  public void start() {
    System.out.println("시동 건다!");
  }

  public void shutdown() {
    System.out.println("시동 끈다!");
  }

  public void run() {
    System.out.println("달린다.");
  }

}