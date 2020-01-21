package com.eomcs.basic.ex05;

//# 증감 연산자 : 전위(pre-fix) 증감 연산자 응용 I
//
public class Exam0672 {
  public static void main(String[] args) {
    int i = 2;
    i = ++i;
    // 1) i = i + 1  => i = 3 이다.
    // 2) i = 3 => i는 이미 3인데 또 3을 넣는다. 부질없는 짓!

    System.out.println(i); // 3
  }
}
