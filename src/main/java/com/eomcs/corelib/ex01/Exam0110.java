// String - "Hello" vs new String("Hello")
package com.eomcs.corelib.ex01;

public class Exam0110 {
  public static void main(String[] args) {
    // Heap 메모리 영역에 String 인스턴스를 생성하는 방법
    String s1 = new String("Hello");
    String s2 = new String("Hello");
    // => 내용물의 동일 여부를 검사하지 않고 무조건 인스턴스를 생성한다.
    // => 가비지가 되면 가비지 컬렉터에 의해 제거된다.
    if (s1 == s2) 
      System.out.println("s1 == s2");
    else 
      System.out.println("s1 != s2");


    // string constant pool 메모리 영역에 String 인스턴스를 생성하는 방법
    String x1 = "Hello";
    String x2 = "Hello";
    // => 내용물이 같으면 기존 인스턴스의 주소를 리턴한다.
    //    즉 메모리 절약을 위해 중복 데이터를 갖는 인스턴스를 생성하지 않는다.
    // => JVM이 끝날 때까지 메모리에 유지된다.
    if (x1 == x2) 
      System.out.println("x1 == x2");
    else 
      System.out.println("x1 != x2");
  }
}





