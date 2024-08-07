package com.eomcs.oop.ex07.step.s4;

// 정렬을 수행하는 클래스를 한 타입으로 묶기 위해
// 다음 클래스를 정의하였다.
// => 추상 클래스로 선언하면 인스턴스 생성을 막을 수 있다.
// => 즉 서브 클래스를 정의할 때 상속 받아 쓰는 용도로 한정할 수 있다.
public abstract class Sorter {

  // 메서드를 추상 메서드로 선언하는 순간
  // => 모든 서브 클래스는 반드시 이 메서드를 구현해야 한다.
  // => 구현하지 않으면 추상 클래스가 될 수 밖에 없다.
  // => 서브 클래스에게 구현을 강제하는 효과가 있다.
  public abstract void sort(int[] values);
}
