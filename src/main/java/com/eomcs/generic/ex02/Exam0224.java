// 제네릭(Generic) 문법 정리 - 제네릭 파라미터 
package com.eomcs.generic.ex02;

import java.util.ArrayList;

public class Exam0224 {

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
    // m1(ArrayList<? extends B1>)
    // => A 타입 및 그 하위 타입에 대해서 ArrayList 객체를 파라미터로 넘길 수 있다.
    //
    //m1(new ArrayList<Object>()); // 컴파일 오류!
    //m1(new ArrayList<A>()); // 컴파일 오류!
    m1(new ArrayList<B1>()); 
    //m1(new ArrayList<B2>()); // 컴파일 오류!
    m1(new ArrayList<C>()); 
  }

  static void m1(ArrayList<? extends B1> list) {
    // 파라미터로 받은 ArrayList가 구체적으로 어떤 타입의 값을 다루는 것인지 
    // 결정되지 않았기 때문에 컴파일러는 다음 코드가 옳은지 검사할 수 없다.
    // 그래서 컴파일 오류가 발생한다.
    //list.add(new B1()); // 컴파일 오류!

    Object obj1 = list.get(0);
    B1 obj2 = list.get(0);
    //C obj3 = list.get(0); // 컴파일 오류!
  }
}








