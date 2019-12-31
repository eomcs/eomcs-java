// Wrapper 클래스 - auto-boxing/auto-unboxing 
package com.eomcs.corelib.ex01;

public class Exam0220 {
  public static void main(String[] args) {
    int i1 = 100;

    // int ==> Integer
    Integer obj1 = new Integer(i1);

    // Integer ==> int
    Integer obj2 = new Integer(200);
    int i2 = obj2.intValue();

    // JDK1.5부터 auto-boxing, auto-unboxing 기능을 제공한다.
    // => auto-boxing 
    //    int 값을 가지고 자동으로 Integer 랩퍼 객체를 만든다. 
    //    이렇게 객체 안에 값을 넣어 포장한다고 해서 "오토박싱"이라 부른다.
    Integer obj3 = i1; // 내부적으로 new Integer(i1)으로 바뀐다.
    // 즉 int 값이 obj3에 저장되는 것이 아니라,
    // 내부적으로 Integer 객체가 생성되어 그 주소가 저장된다.

    // => auto-unboxing
    //    Integer 객체에서 값을 꺼내 리턴한다.
    //    이렇게 객체 안에 들어 있는 값을 자동으로 꺼낸다고 해서 "오토언박싱"이라 부른다.
    Integer obj4 = new Integer(300);
    int i3 = obj4;  // 내부적으로 obj4.intValue()로 바뀐다.
    // 즉 obj4에 들어있는 인스턴스 주소가 i3에 저장되는 것이 아니라,
    // obj4 인스턴스에 들어 있는 값을 꺼내 i3에 저장하는 것이다.


  }
}






