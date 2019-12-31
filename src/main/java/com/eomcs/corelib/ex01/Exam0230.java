// Wrapper 클래스 - wrapper 객체의 값 비교 
package com.eomcs.corelib.ex01;

public class Exam0230 {
  public static void main(String[] args) {
    Integer obj1 = new Integer(100); // Heap에 인스턴스 생성 
    Integer obj2 = new Integer(100); // Heap에 인스턴스 생성 
    if (obj1 == obj2) // 인스턴스의 주소 비교 
      System.out.println("obj1 == obj2");
    else 
      System.out.println("obj1 != obj2");

    Integer obj3 = 100; // constant pool에 생성
    Integer obj4 = 100; // constant pool에 생성된 기존 주소 리턴
    if (obj3 == obj4) // 인스턴스의 주소가 같다!
      System.out.println("obj3 == obj4");
    else 
      System.out.println("obj3 != obj4");

    int i = 100;
    Integer obj5 = new Integer(100);
    if (i == obj5) // obj5가 auto-unboxing을 수행하여 int와 int를 비교한다.
      System.out.println("i == obj5");
    else 
      System.out.println("i != obj5");

  }
}






