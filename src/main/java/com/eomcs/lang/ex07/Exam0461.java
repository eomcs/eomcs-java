package com.eomcs.lang.ex07;

//# 메서드 : 스택 오버플로우 오류!
//
public class Exam0461 {

  static long sum(long value) {
    if (value == 1)
      return 1;

    long v1 = value + 10L;
    long v2 = value + 10L;
    long v3 = value + 10L;
    long v4 = value + 10L;

    System.out.println(v1);


    return value + sum(value - 1);
  }

  public static void main(String[] args) {
    // 호출하는 메서드의 로컬 변수가 클 때는 스택 메모리가 빨리 찬다.
    // => 즉 스택 오버플로우는 메서드 호출 회수에 영향을 받는 것이 아니라,
    //    메서드에서 생성하는 로컬 변수의 크기에 영향을 받는다.
    System.out.println(sum(5000));
  }
}





