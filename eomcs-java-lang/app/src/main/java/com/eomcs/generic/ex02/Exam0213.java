// 제네릭(Generic) 문법 정리 - 레퍼런스와 인스턴스 생성 
package com.eomcs.generic.ex02;

import java.util.ArrayList;

public class Exam0213 {

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

    ArrayList<A> list1;

    //    list1 = new ArrayList(); // 이렇게 사용하지 말고, 명확히 제네릭의 타입을 지정하라.
    //    list1 = new ArrayList<Object>(); // 컴파일 오류!
    list1 = new ArrayList<A>();
    list1 = new ArrayList<>(); // 생략하면 레퍼런스와 같은 제네릭 타입이 된다.
    //    list1 = new ArrayList<B1>(); // 컴파일 오류!
    //    list1 = new ArrayList<B2>(); // 컴파일 오류!
    //    list1 = new ArrayList<C>(); // 컴파일 오류!

    list1.add(new B2()); // 컴파일러는 레퍼런스의 형식을 따져서 문법의 옳고 그름을 판단한다.

    ArrayList<B2> list2;

    //    list2 = new ArrayList(); // 이렇게 사용하지 말고, 명확히 제네릭의 타입을 지정하라.
    //    list2 = new ArrayList<Object>(); // 컴파일 오류!
    list2 = new ArrayList<B2>();
    list2 = new ArrayList<>(); // B2를 생략한 문법이다.
    //    list2 = new ArrayList<A>(); // 컴파일 오류!
    //    list2 = new ArrayList<B1>(); // 컴파일 오류!
    //    list2 = new ArrayList<C>(); // 컴파일 오류!

  }
}








