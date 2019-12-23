package com.eomcs.basic.ex07;

//# 메서드 : 스택 오버플로우 오류!
//
public class Exam0462 {

  static int sum(int value) {
    if (value == 1)
        return 1;
    
    long a1 = 100, a2 = 100, a3 = 100, a4 = 100, a5 = 100, a6 = 100, a7 = 100, a8 = 100, a9 = 100;
    long a11 = 100, a12 = 100, a13 = 100, a14 = 100, a15 = 100, a16 = 100, a17 = 100, a18 = 100, a19 = 100;
    long a111 = 100, a112 = 100, a113 = 100, a114 = 100, a115 = 100, a116 = 100, a117 = 100, a118 = 100, a119 = 100;
    return value + sum(value - 1);
  }

  public static void main(String[] args) {
    // 호출하는 메서드의 로컬 변수가 클 때는 스택 메모리가 빨리 찬다.
    // => 즉 스택 오버플로우는 메서드 호출 회수에 영향을 받는 것이 아니라,
    //    메서드에서 생성하는 로컬 변수의 크기에 영향을 받는다.
    System.out.println(sum(10000));
  }
}





