// 제네릭(Generic) 문법 정리 - 제네릭 파라미터 wildcard(?)
package com.eomcs.generic.ex02;

import java.util.ArrayList;

public class Exam0222 {

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
    // m1(ArrayList<?>)
    // => 모든 타입에 대해 ArrayList 객체를 파라미터로 넘길 수 있다.
    // => 다만 메서드 내부에서는 타입 검사를 할 수 없기 때문에 
    //    add() 메서드 호출 같은 타입 검사가 필요한 코드를 사용한 경우에는 컴파일 오류가 발생한다.
    //
    m1(new ArrayList()); // OK
    m1(new ArrayList<Object>()); // OK
    m1(new ArrayList<A>()); // OK
    m1(new ArrayList<B1>()); // OK
    m1(new ArrayList<B2>()); // OK
    m1(new ArrayList<C>()); // OK

    ArrayList<B1> my1 = new ArrayList<>();
    my1.add(new B1());
    my1.add(new B1());
    my1.add(new B1());
    my1.add(new C());

    m1(my1); // OK
  }

  static void m1(ArrayList<?> list) {
    // 컴파일러는 파라미터로 받은 ArrayList가 어떤 타입의 값을 다루는 지 알 수 없기 때문에 
    // 메서드를 사용할 때, 옳은 타입의 값을 전달하는지 컴파일는 알 수 없다.
    // 따라서 컴파일 오류를 발생시킨다.
    // 즉 다음 add()처럼 제네릭에 대한 타입 검사가 필요한 메서드를 호출할 때는 
    // 타입 파라미터의 값이 지정되어 있지 않기 때문에 
    // add() 메서드의 파라미터 타입을 알 수 없다.
    // 그래서 add()를 호출할 때 어떤 타입이 유효한 문법인지 알 수 없어서 컴파일 할 수 없다.
    //
    //    list.add(new Object());
    //    list.add(new A());
    //    list.add(new B1());
    //    list.add(new B2());
    //    list.add(new C());

    Object obj1 = list.get(0);

    // 다음의 경우도 마찬가지로 get()의 리턴 타입이 무엇인지 결정할 수 없기 때문에 
    // 컴파일 해 줄수 없다. 그래서 컴파일 오류가 발생한다. 
    //A obj2 = list.get(1); // 컴파일 오류!


    // println()의 파라미터 타입이 Object 이기 때문에 다음 코드는 오류가 아니다.
    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    System.out.println(list.get(3));
  }
}








