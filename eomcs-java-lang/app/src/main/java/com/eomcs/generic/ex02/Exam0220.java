// 제네릭(Generic) 문법 정리 - 제네릭 파라미터 
package com.eomcs.generic.ex02;

import java.util.ArrayList;

public class Exam0220 {

  static class A {}
  static class B1 extends A {}
  static class B2 extends A {}
  static class C extends B1 {}
  /*
   *   Object
   *     |
   *     A
   *    / \
   *   B1 B2
   *   |
   *   C
   */

  public static void main(String[] args) {

    // m1(ArrayList)
    // => 제네릭의 타입을 지정하지 않으면, 
    //    다음과 같이 다양한 종류의 ArrayList를 파라미터로 넘길 수 있다.

    m1(new ArrayList()); // OK
    m1(new ArrayList<A>()); // OK
    m1(new ArrayList<B1>()); // OK
    m1(new ArrayList<B2>()); // OK
    m1(new ArrayList<C>()); // OK
    System.out.println("실행 완료!");
  }

  // 제네릭의 타입을 지정하지 않으면 
  // 위와 같이 특정 타입으로 제한하는 문법이 무용지물이 된다.
  // 따라서 제네릭으로 선언된 클래스를 사용할 때는 반드시 타입 파라미터 값을 지정하라!
  // => 제네릭 문법의 목적은 코드 안정성을 추구하는 것이다.
  // => 원하는 타입이 아닌 다른 타입의 값을 지정하는 오류(타입 오류)를 줄이기 위해 만든 문법이다.
  // => 제네릭 문법의 대상은 컴파일러다.
  // => 즉 컴파일 단계에서 최대한으로 타입 오류를 잡아 내는 것이 목적이다.
  static void m1(ArrayList list) {
    list.add(new Object());
    list.add(new A());
    list.add(new B1());
    list.add(new B2());
    list.add(new C());

    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    System.out.println(list.get(3));
    System.out.println(list.get(4));
  }
}








