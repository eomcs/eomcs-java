package com.eomcs.basic.ex04;

//# 변수에 값 저장 - 큰 메모리의 값을 작은 메모리에 저장
//
public class Exam911 {
  public static void main(String[] args) {
    byte b = 100;
    short s = 100;
    int i = 100;
    long l = 100;
    char c = 100;

    byte b2;
    short s2;
    int i2;
    long l2;
    char c2;

    // 큰 변수(메모리)에 들어 있는 값이 작은 값이라 하더라도 작은 메모리에는 넣을 수 없다.
    // => 컴파일 오류!

    b2 = s; // 컴파일 오류!
    s2 = i; // 컴파일 오류!
    i2 = l; // 컴파일 오류!
    c2 = s; // 컴파일 오류! 
    // char(0 ~ 65535), short(-32768 ~ 32767)
  }
}
