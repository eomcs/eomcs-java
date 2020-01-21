package com.eomcs.basic.ex05;

//# 증감 연산자 : 전위(pre-fix) 증감 연산자 응용 II
//
public class Exam0680 {
  public static void main(String[] args) {
    int i = 2;
    int result = ++i + ++i * ++i;
    // 1) int result = 3 + 4 * 5;
    // 2) int result = 3 + 20;
    // 3) int result = 23;

    System.out.println(result); // 23
  }
}






