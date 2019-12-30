// 변수 초기화 - 자동 초기화
package com.eomcs.oop.ex03;

import java.sql.Date;

public class Exam0510 {
  static class A {
    static byte b;
    static short s;
    static int i;
    static long l;
    static char c;
    static float f;
    static double d;
    static boolean bool;
    static String str;
    static Date date;
    
    byte b2;
    short s2;
    int i2;
    long l2;
    char c2;
    float f2;
    double d2;
    boolean bool2;
    String str2;
    Date date2;
  }
  public static void main(String[] args) {

    // 클래스 변수는 생성되는 순간 0으로 자동 초기화 된다.
    // => 정수(byte, short, int, long, char) : 0
    // => 부동소수점(float, double) : 0.0
    // => 논리(boolean) : false
    //
    System.out.printf(
        "%d, %d, %d, %d, %c, %.1f, %.1f, %b, %s, %s\n",
        A.b, A.s, A.i, A.l, A.c, A.f, A.d, A.bool, A.str, A.date);

    // 인스턴스 변수도 클래스 변수와 마찬가지이다. 
    A obj = new A();
    System.out.printf(
        "%d, %d, %d, %d, %c, %.1f, %.1f, %b, %s, %s\n",
        obj.b2, obj.s2, obj.i2, obj.l2, obj.c2, obj.f2, obj.d2, 
        obj.bool2, obj.str2, obj.date2);

    // 로컬 변수는 자동 초기화되지 않는다.
    // 반드시 사용전에 값을 저장해야 한다.
    int i;
    String str;
    Date date;
    //System.out.println(i); // 컴파일 오류!
    //System.out.println(str); // 컴파일 오류!
    //System.out.println(date); // 컴파일 오류!
  } 
}




