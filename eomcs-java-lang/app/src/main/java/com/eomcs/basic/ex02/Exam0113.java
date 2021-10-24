// String - String 인스턴스를 상수풀에 생성하기: intern()
package com.eomcs.basic.ex02;

public class Exam0113 {
  public static void main(String[] args) {

    String s1 = new String("Hello");

    // intern()
    // - 지정된 String 객체를 상수풀에서 찾는다.
    // - 있으면 그 String 객체의 주소를 리턴한다.
    // - 없으면 상수풀에 String 객체를 생성한 후 그 주소를 리턴한다.
    String s2 = s1.intern();
    String s3 = "Hello";

    System.out.println(s1 == s2);
    System.out.println(s2 == s3);
  }
}


