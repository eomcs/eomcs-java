// Wrapper 클래스 - wrapper 객체에 들어 있는 primitive type의 값 꺼내기
package com.eomcs.basic.ex02;

public class Exam0212 {
  public static void main(String[] args) {

    Long obj1 = Long.valueOf(100L);
    Double obj2 = Double.valueOf(3.14);
    Boolean obj3 = Boolean.valueOf(true);

    int i = obj1.intValue();
    long l = obj2.longValue();
    boolean bool = obj3.booleanValue();

    // 문자열로 꺼내기
    String s1 = obj1.toString(); // Object의 toString()이 아니다. Long에서 오버라이딩한 toString()이다.
    String s2 = obj2.toString(); // Object의 toString()이 아니다. Double에서 오버라이딩한 toString()이다.
    String s3 = obj3.toString(); // Object의 toString()이 아니다. Boolean에서 오버라이딩한 toString()이다.

    // 다른 진수로 변환하여 문자열로 꺼내기
    System.out.println(Integer.toHexString(i));
    System.out.println(Integer.toOctalString(i));
    System.out.println(Integer.toBinaryString(i));
  }
}






