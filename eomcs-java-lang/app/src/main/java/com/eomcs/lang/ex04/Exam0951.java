package com.eomcs.lang.ex04;

//# 형변환 - 명시적 형변환이 불가능한 경우, 특별한 메서드를 사용하면 가능하다. 
// 
public class Exam0951 {
  public static void main(String[] args) {

    boolean v1 = Boolean.valueOf("true");   // 문자열 ==> boolean

    char v2 = "가".charAt(0); // 문자열 ==> char

    int v3 = Integer.valueOf("123"); // 문자열 ==> int
    int v33 = Integer.parseInt("123"); // 문자열 ==> int

    float v4 = Float.valueOf("3.14f");
    float v44 = Float.parseFloat("3.14f");

    System.out.println(v1);
    System.out.println(v2);
    System.out.println(v3);
    System.out.println(v33);
    System.out.println(v4);
    System.out.println(v44);
  }
}
